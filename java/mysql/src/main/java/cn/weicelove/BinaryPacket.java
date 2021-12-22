package cn.weicelove;

public class BinaryPacket extends Packet {

    public BinaryPacket(int packetBodyLength, byte packetId, byte[] data) {
        super(packetBodyLength, packetId, data);
    }

}
