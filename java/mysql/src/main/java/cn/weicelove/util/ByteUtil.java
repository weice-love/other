/*
 * Copyright (C) 2016 alchemystar, Inc. All Rights Reserved.
 */
package cn.weicelove.util;

import io.netty.buffer.ByteBuf;

/**
 * @author lizhuyang
 */
public class ByteUtil {

    public static byte readByte(ByteBuf data) {
        return data.readByte();
    }

    public static int readUB2(ByteBuf data) {
        int i = data.readByte() & 0xff;
        i |= (data.readByte() & 0xff) << 8;
        return i;
    }

    public static int readUB3(ByteBuf data) {
        int i = data.readByte() & 0xff;
        i |= (data.readByte() & 0xff) << 8;
        i |= (data.readByte() & 0xff) << 16;
        return i;
    }

    public static long readUB4(ByteBuf data) {
        long l = data.readByte() & 0xff;
        l |= (data.readByte() & 0xff) << 8;
        l |= (data.readByte() & 0xff) << 16;
        l |= (data.readByte() & 0xff) << 24;
        return l;
    }

    public static long readLong(ByteBuf data) {
        long l = (long) (data.readByte() & 0xff);
        l |= (long) (data.readByte() & 0xff) << 8;
        l |= (long) (data.readByte() & 0xff) << 16;
        l |= (long) (data.readByte() & 0xff) << 24;
        l |= (long) (data.readByte() & 0xff) << 32;
        l |= (long) (data.readByte() & 0xff) << 40;
        l |= (long) (data.readByte() & 0xff) << 48;
        l |= (long) (data.readByte() & 0xff) << 56;
        return l;
    }

//    public static long readLength(ByteBuf data) {
//        int length = data.readByte() & 0xff;
//        switch (length) {
//            case 251:
//                return MySQLMessage.NULL_LENGTH;
//            case 252:
//                return readUB2(data);
//            case 253:
//                return readUB3(data);
//            case 254:
//                return readLong(data);
//            default:
//                return length;
//        }
//    }


    public static int decodeLength(byte[] src) {
        int length = src.length;
        if (length < 251) {
            return 1 + length;
        } else if (length < 0x10000L) {
            return 3 + length;
        } else if (length < 0x1000000L) {
            return 4 + length;
        } else {
            return 9 + length;
        }
    }

    public static int decodeLength(long length) {
        if (length < 251) {
            return 1;
        } else if (length < 0x10000L) {
            return 3;
        } else if (length < 0x1000000L) {
            return 4;
        } else {
            return 9;
        }
    }

    public static byte[] readStringUtilNull(ByteBuf in) {
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
}
