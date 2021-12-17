package cn.weicelove;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 继承LengthFieldBasedFrameDecoder， 支持自动的TCP粘包和半包处理，只需要给出标识消息的长度的字段偏移量和消息长度自身所占的
 * 字节数，Netty就能自动实现对半包的处理。
 * @author DIDIBABA_CAR_QPW Create in 2020/6/11 11:26
 */
public final class NettyMessageDecoder extends ByteToMessageDecoder {


    public NettyMessageDecoder() {
//        marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readableBytes = in.readableBytes();
        byte contentLength = in.readByte();

        System.out.println(contentLength);
    }

//    @Override
//    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
//        // 为空说明是半包消息
//        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
//        if (frame == null) {
//            System.out.println("NettyMessageDecoder NULL");
//            return null;
//        }
//        System.out.println("frame: " + frame.toString());
//
//        // todo ？？？ 按理应该用frame 的
//        in.readerIndex(0);
//        return null;
//    }
}