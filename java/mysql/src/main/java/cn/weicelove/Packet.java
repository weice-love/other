package cn.weicelove;

public abstract class Packet {

    private int packetBodyLength;
    private byte packetId;
    private byte[] data;

    public Packet(int packetBodyLength, byte packetId, byte[] data) {
        this.packetBodyLength = packetBodyLength;
        this.packetId = packetId;
        this.data = data;
    }

    public int getPacketBodyLength() {
        return packetBodyLength;
    }

    public byte getPacketId() {
        return packetId;
    }

    public byte[] getData() {
        return data;
    }
}
