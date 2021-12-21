package cn.weicelove;

public class BinaryPacket extends Packet implements Parse {

    private Parse parse;

    public BinaryPacket(int packetBodyLength, byte packetId, byte[] data) {
        super(packetBodyLength, packetId, data);
    }

    public void parse() {
        parse.parse();
    }
}
