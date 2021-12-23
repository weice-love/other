package cn.weicelove;

import cn.weicelove.util.MockUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            byte b = messageReader.readByte();
            log.info("Packet msg: {}", b & 0xff);
            int errorCode = messageReader.readUB2();
//            messageReader.skip(6);
            byte[] sqlStateMarker = messageReader.readStringFixLength(1);
            byte[] sqlState = messageReader.readStringFixLength(5);
            byte[] msg = messageReader.readBytes();
            log.info("errorCode: {}, sqlStateMarker: {}, sqlState: {}, msg: {}",
                    errorCode,
                    new String(sqlStateMarker, StandardCharsets.UTF_8),
                    new String(sqlState, StandardCharsets.UTF_8),
                    new String(msg, StandardCharsets.UTF_8));
            log.info("Packet data: {}", binaryPacket.getData());
            processor.setState(AUTHED);
        }
    },
    AUTHED {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {

        }
    };

    private static final Logger log = LoggerFactory.getLogger(State.class);

     void auth(HandPacket handPacket, ChannelHandlerContext ctx) {
         try {
             AuthPacket authPacket = AuthPacket.writePacket(handPacket);
             ByteBuf buffer = authPacket.transferByteBuf(ctx);
             log.info("auth packet size: {}", buffer.readableBytes());
             ctx.writeAndFlush(buffer);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    abstract void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket);
}
