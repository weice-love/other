//package cn.weicelove;
//
//import cn.weicelove.util.ByteTransferUtil;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
///**
// * 继承LengthFieldBasedFrameDecoder， 支持自动的TCP粘包和半包处理，只需要给出标识消息的长度的字段偏移量和消息长度自身所占的
// * 字节数，Netty就能自动实现对半包的处理。
// * @author DIDIBABA_CAR_QPW Create in 2020/6/11 11:26
// */
//public final class NettyMessageDecoderV1 extends ByteToMessageDecoder {
//
//
//    public NettyMessageDecoderV1() {
////        marshallingDecoder = new MarshallingDecoder();
//    }
//
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        // 4 bytes:3 length + 1 packetId
//        if (in.readableBytes() < packetHeaderSize) {
//            return;
//        }
//        in.markReaderIndex();
//        int packetLength = ByteUtil.readUB3(in);
//        // 过载保护
//        if (packetLength > maxPacketSize) {
//            throw new IllegalArgumentException("Packet size over the limit " + maxPacketSize);
//        }
//        byte packetId = in.readByte();
//        if (in.readableBytes() < packetLength) {
//            // 半包回溯
//            in.resetReaderIndex();
//            return;
//        }
//        BinaryPacket packet = new BinaryPacket();
//        packet.packetLength = packetLength;
//        packet.packetId = packetId;
//        // data will not be accessed any more,so we can use this array safely
//        packet.data = in.readBytes(packetLength).array();
//        if (packet.data == null || packet.data.length == 0) {
////            logger.error("get data errorMessage,packetLength=" + packet.packetLength);
//        }
//        out.add(packet);
//    }
//
//    private void printByte(byte[] operationFlag, String title) {
//        System.out.println(title);
//        for (int i = 0; i < operationFlag.length; i++) {
//            System.out.print(operationFlag[i]);
//        }
//        System.out.println("");
//    }
//
////    @Override
////    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
////        // 为空说明是半包消息
////        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
////        if (frame == null) {
////            System.out.println("NettyMessageDecoder NULL");
////            return null;
////        }
////        System.out.println("frame: " + frame.toString());
////
////        // todo ？？？ 按理应该用frame 的
////        in.readerIndex(0);
////        return null;
////    }
//}