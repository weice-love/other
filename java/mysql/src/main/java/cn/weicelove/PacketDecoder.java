package cn.weicelove;

import cn.weicelove.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    private static final Logger log = LoggerFactory.getLogger(PacketDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in instanceof EmptyByteBuf) {
            return;
        }
        int packetBodyLength = ByteUtil.readUB3(in);
        byte packetId = in.readByte();
        log.debug("bodyLength: {}, id: {}", packetBodyLength, packetId);
        // 做标记，读取到半包,可以回滚
        in.markReaderIndex();
        if (in.readableBytes() < packetBodyLength) {
            // 半包回溯
            System.out.println("================半包回溯========================");
            in.resetReaderIndex();
            return;
        }
        byte[] data = in.readBytes(packetBodyLength).array();
        BinaryPacket binaryPacket = new BinaryPacket(packetBodyLength, packetId, data);
        out.add(binaryPacket);
    }
}
