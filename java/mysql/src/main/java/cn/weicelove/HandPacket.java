package cn.weicelove;

import cn.weicelove.util.ByteUtil;

public class HandPacket extends BinaryPacket {

    private BinaryPacket binaryPacket;
    public byte protocolVersion;
    public byte[] serverVersion;
    public long threadId;
    public byte[] seed;
    public int serverCapabilities;
    public byte serverCharsetIndex;
    public int serverStatus;
    public byte[] restOfScrambleBuff;

    public HandPacket(BinaryPacket binaryPacket) {
        super(binaryPacket.getPacketBodyLength(), binaryPacket.getPacketId(), binaryPacket.getData());
        this.binaryPacket = binaryPacket;
    }

    public void parse() {
        System.out.println("handshake");

//        byte[] data = getData();
//        protocolVersion = ByteUtil.readByte(in);
//        serverVersion = ByteUtil.readStringUtilNull(in);
//        threadId = ByteUtil.readUB4(in);
//        seed = ByteUtil.readStringUtilNull(in);
//        serverCapabilities = ByteUtil.readUB2(in);
//        serverCharsetIndex = ByteUtil.readByte(in);
//        serverStatus = ByteUtil.readUB2(in);
    }
}
