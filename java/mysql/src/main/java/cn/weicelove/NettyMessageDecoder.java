package cn.weicelove;

import cn.weicelove.util.ByteTransferUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
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
        byte[] messageHead = ByteTransferUtil.readFixLength(in, 3);
        int readableBytes;
        readableBytes = (messageHead[2]&0xFF)
                | ((messageHead[1]<<8) & 0xFF00)
                | ((messageHead[0]<<16)& 0xFF0000);
//        int readableBytes = in.readableBytes();


        byte[] id = ByteTransferUtil.readFixLength(in, 1);

        byte[] protocol = ByteTransferUtil.readFixLength(in, 1);
        readableBytes -= 1;
        byte[] version = ByteTransferUtil.readUtilNull(in);
        readableBytes -= version.length;
        String versionInfo = new String(version, StandardCharsets.UTF_8);
        System.out.println(versionInfo);
        byte[] threadId = ByteTransferUtil.readFixLength(in, 4);
        readableBytes -= 4;
        byte[] randomNum = ByteTransferUtil.readFixLength(in, 8);
        readableBytes -= 8;
        byte[] fillByte = ByteTransferUtil.readFixLength(in, 1);
        printByte(fillByte, "填充值");
        readableBytes -= 1;
        byte[] operationFlag = ByteTransferUtil.readFixLength(in, 2);
        printByte(operationFlag, "服务器权能标志");
        readableBytes -= 2;
        byte[] charSet = ByteTransferUtil.readFixLength(in, 1);
        printByte(charSet, "字符编码");
        readableBytes -= 1;
        byte[] status = ByteTransferUtil.readFixLength(in, 2);
        printByte(status, "服务器状态");
        readableBytes -= 2;
        byte[] operationFlagV2 = ByteTransferUtil.readFixLength(in, 2);
        printByte(operationFlagV2, "服务器权能标志（高16位）");
        readableBytes -= 2;
        byte[] fixLength = ByteTransferUtil.readFixLength(in, 1);
        printByte(fixLength, "挑战长度（未使用）");
        readableBytes -= 1;
        byte[] fillLength = ByteTransferUtil.readFixLength(in, 10);
        readableBytes -= 10;
        readableBytes -= 2;
        byte[] randomNumV2 = ByteTransferUtil.readFixLength(in, readableBytes);
        byte[] end = ByteTransferUtil.readFixLength(in, 1);



//        System.out.println(contentLength);
    }

    private void printByte(byte[] operationFlag, String title) {
        System.out.println(title);
        for (int i = 0; i < operationFlag.length; i++) {
            System.out.print(operationFlag[i]);
        }
        System.out.println("");
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