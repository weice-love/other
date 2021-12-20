package cn.weicelove;

import cn.weicelove.util.ByteUtil;
import io.netty.buffer.ByteBuf;

public class HandPacket extends Packet{

    public byte protocolVersion;
    public byte[] serverVersion;
    public long threadId;
    public byte[] seed;
    public int serverCapabilities;
    public byte serverCharsetIndex;
    public int serverStatus;
    public byte[] restOfScrambleBuff;

    public void parse(ByteBuf in) {
        super.parse(in);
        protocolVersion = ByteUtil.readByte(in);
        serverVersion = ByteUtil.readStringUtilNull(in);
        threadId = ByteUtil.readUB4(in);
        seed = ByteUtil.readStringUtilNull(in);
        serverCapabilities = ByteUtil.readUB2(in);
        serverCharsetIndex = ByteUtil.readByte(in);
        serverStatus = ByteUtil.readUB2(in);






    }
}
