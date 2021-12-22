package cn.weicelove;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageReader {

    private static final Logger log = LoggerFactory.getLogger(MessageReader.class);

    private int position;
    private int length;
    private byte[] data;

    private static final byte[] EMPTY_BYTES = new byte[0];

    public MessageReader(byte[] data) {
        this(data.length, data);
    }

    public MessageReader(int packetBodyLength, byte[] data) {
        this.length = packetBodyLength;
        this.data = data;
        this.position = 0;
    }


    public byte readByte() {
        return data[position++];
    }

    public byte[] readStringUtilNull() {
        int endIndex = -1;
        for (int i = position; i <= length; i++) {
            if (i == length || data[i] == 0) {
                endIndex = i;
                break;
            }
        }
        if (endIndex > position) {
            byte[] content = new byte[endIndex - position];
            System.arraycopy(data, position, content, 0, content.length);
            position = (endIndex == length ? endIndex : endIndex + 1);
            return content;
        }
        return EMPTY_BYTES;
    }

    public static void main(String[] args) {
        byte[] bytes = {0};
        byte[] bytes1 = {1, 0, 2};
        byte[] bytes2 = {3, 4, 5};
        MessageReader messageReader = new MessageReader(bytes);
        byte[] bytes3 = messageReader.readStringUtilNull();
        log.info("src :{}, dest: {}", bytes, bytes3);
        MessageReader messageReader1 = new MessageReader(bytes1);
        byte[] bytes4 = messageReader1.readStringUtilNull();
        log.info("src :{}, dest: {}", bytes1, bytes4);
        MessageReader messageReader2 = new MessageReader(bytes2);
        byte[] bytes5 = messageReader2.readStringUtilNull();
        log.info("src :{}, dest: {}", bytes2, bytes5);
    }

    public long readUB4() {
        long l = data[position++] & 0xff;
        l |= (long) (data[position++] & 0xff) << 8;
        l |= (long) (data[position++] & 0xff) << 16;
        l |= (long) (data[position++] & 0xff) << 24;
        return l;
    }

    public int readUB2() {
        int i = data[position++] & 0xff;
        i |= (data[position++] & 0xff) << 8;
        return i;
    }
}
