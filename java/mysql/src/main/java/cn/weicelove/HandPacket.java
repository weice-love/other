package cn.weicelove;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HandPacket {

//    private BinaryPacket binaryPacket;
    public byte protocolVersion;
    public byte[] serverVersion;
    public long connectionId;
    public byte[] seed;
    public int serverLowCapabilities;
    public byte serverCharsetIndex;
    public int serverStatus;
    public int serverUpCapabilities;
    public byte[] restOfScrambleBuff;

    public void parse(BinaryPacket binaryPacket) {
        System.out.println("handshake");

        MessageReader messageReader = new MessageReader(binaryPacket.getPacketBodyLength(), binaryPacket.getData());

        protocolVersion = messageReader.readByte();
        serverVersion = messageReader.readStringUtilNull();
        connectionId = messageReader.readUB4();
        // 带0标志符，一起处理（原： 字符 + 填充）
        seed = messageReader.readStringUtilNull();
        serverLowCapabilities = messageReader.readUB2();
        serverCharsetIndex = messageReader.readByte();
        serverStatus = messageReader.readUB2();
        serverUpCapabilities = messageReader.readUB2();
        System.out.println(this.toString());
//        byte[] data = getData();
//        protocolVersion = ByteUtil.readByte(in);
//        serverVersion = ByteUtil.readStringUtilNull(in);
//        threadId = ByteUtil.readUB4(in);
//        seed = ByteUtil.readStringUtilNull(in);
//        serverCapabilities = ByteUtil.readUB2(in);
//        serverCharsetIndex = ByteUtil.readByte(in);
//        serverStatus = ByteUtil.readUB2(in);
    }

    @Override
    public String toString() {
        return "HandPacket{" +
                "protocolVersion=" + protocolVersion +
                ", serverVersion=" + new String(serverVersion, StandardCharsets.UTF_8) +
                ", threadId=" + connectionId +
                ", seed=" + Arrays.toString(seed) +
                ", serverCapabilities=" + serverLowCapabilities +
                ", serverCharsetIndex=" + serverCharsetIndex +
                ", serverStatus=" + serverStatus +
                ", restOfScrambleBuff=" + Arrays.toString(restOfScrambleBuff) +
                '}';
    }
}
