package cn.weicelove;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageWriter {

    private static final Logger log = LoggerFactory.getLogger(MessageWriter.class);

    private ByteBuf byteBuf;

    public MessageWriter(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    public void writeUB3(int data) {
        byteBuf.writeByte((byte) (data & 0xff));
        byteBuf.writeByte((byte) (data >>> 8));
        byteBuf.writeByte((byte) (data >>> 16));
    }

    public void writeUB4(long data) {
        byteBuf.writeByte((byte) (data & 0xff));
        byteBuf.writeByte((byte) (data >>> 8));
        byteBuf.writeByte((byte) (data >>> 16));
        byteBuf.writeByte((byte) (data >>> 24));
    }

    public void writeWithNull(byte[] data) {
        byteBuf.writeBytes(data);
        byteBuf.writeByte((byte)0);
    }

    public void writeWithLength(byte[] data) {
        int length = data.length;
        if (length < 251) {
            byteBuf.writeByte((byte) length);
        } else if (length < 0x10000L) {
            byteBuf.writeByte((byte) 252);
            writeUB2(length);
        } else if (length < 0x1000000L) {
            byteBuf.writeByte((byte) 253);
            writeUB3(length);
        } else {
            byteBuf.writeByte((byte) 254);
            writeLong(length);
        }
        byteBuf.writeBytes(data);
    }

    private void writeLong(long data) {
        byteBuf.writeByte((byte) (data & 0xff));
        byteBuf.writeByte((byte) (data >>> 8));
        byteBuf.writeByte((byte) (data >>> 16));
        byteBuf.writeByte((byte) (data >>> 24));
        byteBuf.writeByte((byte) (data >>> 32));
        byteBuf.writeByte((byte) (data >>> 40));
        byteBuf.writeByte((byte) (data >>> 48));
        byteBuf.writeByte((byte) (data >>> 56));
    }

    private void writeUB2(int data) {
        byteBuf.writeByte((byte) (data & 0xff));
        byteBuf.writeByte((byte) (data >>> 8));
    }

    public void writeByte(byte data) {
        byteBuf.writeByte(data);
    }

    public void writeBytes(byte[] data) {
        byteBuf.writeBytes(data);
    }
}
