package cn.weicelove;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/12/22 16:24
 */
public enum State {

    UN_AUTH {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {
            //有可能是error包，也有可能是初始化包
            // 解析握手包
            HandPacket handPacket = new HandPacket();
            handPacket.parse(binaryPacket);
            // 生成认证包
            auth(handPacket, ctx);
            processor.setState(AUTHING);
        }
    },
    AUTHING {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {
            // 有可能是ok包，也有可能是error包
            MessageReader messageReader = new MessageReader(binaryPacket.getPacketBodyLength(), binaryPacket.getData());
            int header = messageReader.readByte() & 0xff;
            log.info("Packet header: {}, length: {}", header , binaryPacket.getPacketBodyLength() + 4);
            if (header == 255) {
                log.debug("ERROR Packet");
                int errorCode = messageReader.readUB2();
                byte[] sqlStateMarker = messageReader.readStringFixLength(1);
                byte[] sqlState = messageReader.readStringFixLength(5);
                byte[] msg = messageReader.readBytes();
                log.error("errorCode: {}, sqlStateMarker: {}, sqlState: {}, msg: {}",
                        errorCode,
                        new String(sqlStateMarker, StandardCharsets.UTF_8),
                        new String(sqlState, StandardCharsets.UTF_8),
                        new String(msg, StandardCharsets.UTF_8));
            } else if (header == 0 && binaryPacket.getPacketBodyLength() + 4 > 7){
                log.debug("认证成功!!! OK Packet");
                byte affectedRow = messageReader.readByte();
                byte lastInsertId = messageReader.readByte();
                log.debug("affectedRow: {}, lastInsertId: {}", affectedRow, lastInsertId);
                if (messageReader.hasNext()) {
                    int statusFlag = messageReader.readUB2();
                    int numWarns = messageReader.readUB2();
                    byte[] statusInfo = messageReader.readBytes();
                    log.debug("statusFlag: {}, numWarns: {}, statusInfo: {}", statusFlag, numWarns, new String(statusInfo, StandardCharsets.UTF_8));
                }
                // todo 暂时请求一下
                try {
                    CmdPacket cmdPacket = CmdPacket.writePacket((byte) 3, "SHOW MASTER STATUS;");
                    ByteBuf buffer = cmdPacket.transferByteBuf(ctx);
                    log.info("cmd packet size: {}", buffer.readableBytes());
                    ctx.writeAndFlush(buffer);
                } catch (Exception e) {
                    log.error("生成CmdPacket失败", e);
                }

            } else if (header == 254 && binaryPacket.getPacketBodyLength() + 4 < 9) {
                log.debug("EOF Packet");
                int warnings = messageReader.readUB2();
                int statusFlag = messageReader.readUB2();
                log.debug("warnings: {}, statusFlag: {}", warnings, statusFlag);
            }

            processor.setState(AUTHED);
        }
    },
    AUTHED {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {
            // ERROR,CMD,EOF
            // 有可能是ok包，也有可能是error包
            MessageReader messageReader = new MessageReader(binaryPacket.getPacketBodyLength(), binaryPacket.getData());
            int header = messageReader.readByte() & 0xff;
            log.info("Packet header: {}, length: {}", header , binaryPacket.getPacketBodyLength() + 4);
            log.info("Packet data: {}", binaryPacket.getData());
            if (header == 0) {

            } else if(header == 0xff) {

            } else if(header == 0xfb) {
                log.debug("command packet response 返回0xfb");
            } else if(header == 0xfe) {
                log.debug("EOF Packet");
                int warnings = messageReader.readUB2();
                int statusFlag = messageReader.readUB2();
                log.debug("warnings: {}, statusFlag: {}", warnings, statusFlag);
            } else {
                log.debug("解析command packet response...");
                int column = header;
                log.info("column: {}", column);
                processor.setState(RECEIVE_COLUMN);
            }


        }
    },
    RECEIVE_COLUMN {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {

            MessageReader messageReader = new MessageReader(binaryPacket.getPacketBodyLength(), binaryPacket.getData());
            int header = messageReader.readByte() & 0xff;
            log.info("Packet header: {}, length: {}", header , binaryPacket.getPacketBodyLength() + 4);
            log.info("Packet data: {}", binaryPacket.getData());
             if(header == 0xfe) {
                log.debug("EOF Packet");
                int warnings = messageReader.readUB2();
                int statusFlag = messageReader.readUB2();
                log.debug("warnings: {}, statusFlag: {}", warnings, statusFlag);
                processor.setState(RECEIVE_ROW);
                return;
            }
            log.debug("解析 column definition....");
            ColumnDefinitionPacket columnDefinitionPacket = new ColumnDefinitionPacket();
            columnDefinitionPacket.parse(binaryPacket);

        }
    },
    RECEIVE_ROW {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {
            MessageReader messageReader = new MessageReader(binaryPacket.getPacketBodyLength(), binaryPacket.getData());
            int header = messageReader.readByte() & 0xff;
            log.info("Row Packet header: {}, length: {}", header , binaryPacket.getPacketBodyLength() + 4);
            log.info("Row Packet data: {}", binaryPacket.getData());
            if(header == 0xfe) {
                log.debug("EOF Packet");
                int warnings = messageReader.readUB2();
                int statusFlag = messageReader.readUB2();
                log.debug("warnings: {}, statusFlag: {}", warnings, statusFlag);
                processor.setState(AUTHED);
                return;
            }
            log.debug("解析 row....");
            RowPacket rowPacket = new RowPacket();
            rowPacket.parse(binaryPacket);
        }
    },
    ;

    private static final Logger log = LoggerFactory.getLogger(State.class);

     void auth(HandPacket handPacket, ChannelHandlerContext ctx) {
         try {
             AuthPacket authPacket = AuthPacket.writePacket(handPacket);
             ByteBuf buffer = authPacket.transferByteBuf(ctx);
             log.info("auth packet size: {}", buffer.readableBytes());
//             byte[] bytes = buffer.readBytes(buffer.readableBytes()).array();
//             ctx.writeAndFlush(bytes);
             ctx.writeAndFlush(buffer);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    abstract void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket);
}
