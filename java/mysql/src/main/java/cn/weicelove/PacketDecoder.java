package cn.weicelove;

import cn.weicelove.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    private static final Logger log = LoggerFactory.getLogger(PacketDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int bodyLength = ByteUtil.readUB3(in);
        byte id = in.readByte();
        log.debug("bodyLength: {}, id: {}", bodyLength, id);

//        ByteBuffer buffer = ByteBuffer.allocate(bodyLength).order(ByteOrder.BIG_ENDIAN);
//        in.readBytes(buffer);
        new HandPacket().parse(in);

    }
}
