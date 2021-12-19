package cn.weicelove.util;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/12/17 10:58
 */
public class ByteTransferUtil {

    public static byte[] big2little(byte[] bytes) {
        return new byte[1];
    }

    public static byte[] little2big(byte[] bytes) {
        return new byte[1];
    }

    public static  byte[] readFixLength(ByteBuf in, int length) {
        byte[] content = new byte[length];
        for (int i = 0; i < length; i++) {
            content[length - i - 1] = in.readByte();
        }
        return content;
    }
    public static  byte[] readUtilNull(ByteBuf in) {

        byte data;
        byte[] array = new byte[1024];
        int pos = 0;
        while ((data = in.readByte()) != 0) {
            array[pos++] = data;
        }

        byte[] content = new byte[pos];
        for (int i = 0; i < pos; i++) {
//            content[pos - i - 1] = array[i];
            content[i] = array[i];
        }
        return content;
    }

    public static byte[] readFixLength(ByteBuf in,int index, int length) {
        byte[] bytes = new byte[length];
        in.readBytes(bytes, index, length);
        return bytes;
    }

    public static String readStringUtilNull(ByteBuf in, int index) {
        byte[] bytes = new byte[10000];
        byte b;
        int i = 0;
        while ((b = in.readByte()) != 0) {
            bytes[i++] = b;
        }
        byte [] result = new byte[i];
        for (int j = 0; j< i; j++) {
            result[j] = bytes[i- j - 1];
        }
        return new String(result, StandardCharsets.UTF_8);
    }
}
