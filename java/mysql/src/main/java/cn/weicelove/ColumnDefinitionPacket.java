package cn.weicelove;

import cn.weicelove.constants.CapabilityConstants;
import cn.weicelove.util.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ColumnDefinitionPacket {

    private static final Logger log = LoggerFactory.getLogger(ColumnDefinitionPacket.class);

    public byte[] catalog;
    public byte[] schema;
    public byte[] table;
    public byte[] orgTable;
    public byte[] name;
    public byte[] orgName;
    public int fieldsCount;
    public int charset;
    public long columnLength;
    public byte type;
    public int flags;
    public byte decimals;
    public byte filler_1;
    public byte filler_2;

    public void  parse(BinaryPacket binaryPacket) {
        log.info("start parse column definition packet!!!");
        MessageReader messageReader = new MessageReader(binaryPacket.getPacketBodyLength(), binaryPacket.getData());
        catalog = readLenStr(messageReader);
        schema = readLenStr(messageReader);
        table = readLenStr(messageReader);
        orgTable = readLenStr(messageReader);
        name = readLenStr(messageReader);
        orgName = readLenStr(messageReader);
        fieldsCount = readLenInt(messageReader);
        charset = messageReader.readUB2();
        columnLength = messageReader.readUB4();
        type = messageReader.readByte();
        flags = messageReader.readUB2();
        decimals = messageReader.readByte();
        filler_1 = messageReader.readByte();
        filler_2 = messageReader.readByte();

        log.info("parse column definition success!!! data: {}", this.toString());
    }

    private int readLenInt(MessageReader messageReader) {
        int value = messageReader.readByte() & 0xff;
        if (value < 0xfc) {
            return value;
        } else if (value == 0xfc) {
            return messageReader.readUB2();
        } else if (value == 0xfd) {
            return messageReader.readUB3();
        } else {
            return (int)messageReader.readUB8();
        }
    }

    private byte[] readLenStr(MessageReader messageReader) {
        int length = messageReader.readByte() & 0xff;
        if (length < 0xfc) {
            return messageReader.readStringFixLength(length);
        } else if (length == 0xfc) {
            return messageReader.readStringFixLength(messageReader.readUB2());
        } else if (length == 0xfd) {
            return messageReader.readStringFixLength(messageReader.readUB3());
        } else {
            return messageReader.readStringFixLength((int)messageReader.readUB8());
        }
    }

    @Override
    public String toString() {
        return "ColumnDefinitionPacket{" +
                "catalog=" + ByteUtil.byte2StringWithUTF8(catalog) +
                ", schema=" + ByteUtil.byte2StringWithUTF8(schema) +
                ", table=" + ByteUtil.byte2StringWithUTF8(table) +
                ", orgTable=" + ByteUtil.byte2StringWithUTF8(orgTable) +
                ", name=" + ByteUtil.byte2StringWithUTF8(name) +
                ", orgName=" + ByteUtil.byte2StringWithUTF8(orgName) +
                ", fieldsCount=" + fieldsCount +
                ", charset=" + charset +
                ", columnLength=" + columnLength +
                ", type=" + type +
                ", flags=" + flags +
                ", decimals=" + decimals +
                ", filler_1=" + filler_1 +
                ", filler_2=" + filler_2 +
                '}';
    }
}
