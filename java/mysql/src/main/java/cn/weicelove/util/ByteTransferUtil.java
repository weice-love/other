package cn.weicelove.util;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/12/17 10:58
 */
public class ByteTransferUtil {

//    public static final String scramble323(String pass, String seed) {
//        if ((pass == null) || (pass.length() == 0)) {
//            return pass;
//        }
//        byte b;
//        double d;
//        long[] pw = hash(seed);
//        long[] msg = hash(pass);
//        long max = 0x3fffffffL;
//        long seed1 = (pw[0] ^ msg[0]) % max;
//        long seed2 = (pw[1] ^ msg[1]) % max;
//        char[] chars = new char[seed.length()];
//        for (int i = 0; i < seed.length(); i++) {
//            seed1 = ((seed1 * 3) + seed2) % max;
//            seed2 = (seed1 + seed2 + 33) % max;
//            d = (double) seed1 / (double) max;
//            b = (byte) java.lang.Math.floor((d * 31) + 64);
//            chars[i] = (char) b;
//        }
//        seed1 = ((seed1 * 3) + seed2) % max;
//        seed2 = (seed1 + seed2 + 33) % max;
//        d = (double) seed1 / (double) max;
//        b = (byte) java.lang.Math.floor(d * 31);
//        for (int i = 0; i < seed.length(); i++) {
//            chars[i] ^= (char) b;
//        }
//        return new String(chars);
//    }

    public static Byte hex2Byte(String hex) {
        int i = Integer.parseInt(hex, 16);
        return (byte) i;
    }

    public static final byte[] scramble411(byte[] pass, byte[] seed) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] pass1 = md.digest(pass);
        md.reset();
        byte[] pass2 = md.digest(pass1);
        md.reset();
        md.update(seed);
        byte[] pass3 = md.digest(pass2);
        for (int i = 0; i < pass3.length; i++) {
            pass3[i] = (byte) (pass3[i] ^ pass1[i]);
        }
        return pass3;
    }

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
