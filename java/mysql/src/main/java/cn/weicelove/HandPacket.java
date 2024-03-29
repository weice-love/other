package cn.weicelove;

import cn.weicelove.constants.CapabilityConstants;
import cn.weicelove.util.ByteUtil;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class HandPacket {

    private static final Logger log = LoggerFactory.getLogger(HandPacket.class);

//    private BinaryPacket binaryPacket;
    public byte packetId;
    public byte protocolVersion;
    public byte[] serverVersion;
    public long connectionId;
    public byte[] authPluginDataPart1;
    public int serverLowCapabilities;
    public byte serverCharsetIndex;
    public int serverStatus;
    public int serverUpCapabilities;
    public byte authDataLength;
    public byte[] authPluginDataPart2;
    public byte[] authPluginName;

    public void  parse(BinaryPacket binaryPacket) {
        log.info("start parse handShake!!!");
        MessageReader messageReader = new MessageReader(binaryPacket.getPacketBodyLength(), binaryPacket.getData());
        packetId = binaryPacket.getPacketId();
        protocolVersion = messageReader.readByte();
        serverVersion = messageReader.readStringUtilNull();
        connectionId = messageReader.readUB4();
        // 带0标志符，一起处理（原： 字符 + 填充）
        authPluginDataPart1 = messageReader.readStringUtilNull();
        serverLowCapabilities = messageReader.readUB2();
        serverCharsetIndex = messageReader.readByte();
        serverStatus = messageReader.readUB2();
        serverUpCapabilities = messageReader.readUB2();
        authDataLength = messageReader.readByte();
        // 10 reserved (all [00])
        messageReader.skip(10);
        if ((serverLowCapabilities & CapabilityConstants.CLIENT_SECURE_CONNECTION) > 0) {
            // todo 待选择
//            authPluginDataPart2 = messageReader.readStringFixLength(Math.max(13, authDataLength - 8));
            authPluginDataPart2 = messageReader.readStringUtilNull();
        }
        if ((serverLowCapabilities & CapabilityConstants.CLIENT_PLUGIN_AUTH) > 0) {
            authPluginName = messageReader.readStringUtilNull();
        }
        log.info("parse handShake success!!! data: {}", this.toString());
    }

    @Override
    public String toString() {
        return "HandPacket{" +
                "protocolVersion=" + protocolVersion +
                ", serverVersion=" + ByteUtil.byte2StringWithUTF8(serverVersion) +
                ", connectionId=" + connectionId +
                ", authPluginDataPart1=" + ByteUtil.byte2StringWithUTF8(authPluginDataPart1) +
                ", serverLowCapabilities=" + serverLowCapabilities +
                ", serverCharsetIndex=" + serverCharsetIndex +
                ", serverStatus=" + serverStatus +
                ", serverUpCapabilities=" + serverUpCapabilities +
                ", authDataLength=" + authDataLength +
                ", authPluginDataPart2=" + ByteUtil.byte2StringWithUTF8(authPluginDataPart2) +
                ", authPluginName=" + ByteUtil.byte2StringWithUTF8(authPluginName) +
                '}';
    }
}
