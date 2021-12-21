package cn.weicelove;

public class AuthPacket extends BinaryPacket {
    private static final byte[] FILLER = new byte[23];

    public long clientFlags;
    public long maxPacketSize;
    public int charsetIndex;
    public byte[] extra;// from FILLER(23)
    public String user;
    public byte[] password;
    public String database;

    public AuthPacket(int packetBodyLength, byte packetId, byte[] data) {
        super(packetBodyLength, packetId, data);
    }
}