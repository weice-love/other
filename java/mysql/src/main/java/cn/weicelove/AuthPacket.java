package cn.weicelove;

import cn.weicelove.constants.CapabilityConstants;
import cn.weicelove.constants.CharSetEnum;
import cn.weicelove.util.SecurityUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AuthPacket {

    private static final Logger log = LoggerFactory.getLogger(AuthPacket.class);

    private static final byte[] FILLER = new byte[23];
    private static final long MAX_PACKET_SIZE = 1024 * 1024 * 16;

    public byte packetId;
    public long clientFlags;
    public long maxPacketSize;
    public int charsetIndex;
    public byte[] extra;// from FILLER(23)
    public String user;
    public byte passwordLength;
    public byte[] password;
    public String database;
    public byte[] authPluginName;

    private AuthPacket() {}

    public static AuthPacket writePacket(HandPacket handPacket) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("start write auth packet...");
        AuthPacket authPacket = new AuthPacket();
        authPacket.packetId = 1;
        authPacket.clientFlags = getBasicCapability();
        authPacket.maxPacketSize = MAX_PACKET_SIZE;
        authPacket.charsetIndex = handPacket.serverCharsetIndex;
        authPacket.extra = FILLER;
        authPacket.user = "slave";
        if ((authPacket.clientFlags & CapabilityConstants.CLIENT_PLUGIN_AUTH_LENENC_CLIENT_DATA) > 0) {

        } else if((authPacket.clientFlags & CapabilityConstants.CLIENT_SECURE_CONNECTION) > 0) {
            authPacket.password = "123456".getBytes(CharSetEnum.valueOf(((int) handPacket.serverCharsetIndex)).getCharacterSetName());
            byte[] seed = new byte[handPacket.authPluginDataPart1.length + handPacket.authPluginDataPart2.length];
            System.arraycopy(handPacket.authPluginDataPart1, 0, seed, 0, handPacket.authPluginDataPart1.length);
            System.arraycopy(handPacket.authPluginDataPart2, 0, seed, handPacket.authPluginDataPart1.length, handPacket.authPluginDataPart2.length);
            log.debug("seed: {}", seed);
            authPacket.password = SecurityUtil.scramble411(authPacket.password, seed);
            log.debug("password: {}", authPacket.password);
            authPacket.passwordLength = ((byte) authPacket.password.length);
        } else {


        }

        if ((authPacket.clientFlags & CapabilityConstants.CLIENT_CONNECT_WITH_DB) > 0) {
            authPacket.database = "work";
        }
        if ((authPacket.clientFlags & CapabilityConstants.CLIENT_PLUGIN_AUTH) > 0) {
            authPacket.authPluginName = handPacket.authPluginName;
        }
        if ((authPacket.clientFlags & CapabilityConstants.CLIENT_CONNECT_ATTRS ) > 0) {

        }
        log.info("write auth packet success !!! data: {}", authPacket.toString());
        return authPacket;
    }

    private static long getBasicCapability() {
        int flag = 0;
        flag |= CapabilityConstants.CLIENT_LONG_PASSWORD;
        flag |= CapabilityConstants.CLIENT_FOUND_ROWS;
        flag |= CapabilityConstants.CLIENT_LONG_FLAG;
        // 需要带auth_plugin_name
        flag |= CapabilityConstants.CLIENT_PLUGIN_AUTH;
        flag |= CapabilityConstants.CLIENT_CONNECT_WITH_DB;
        flag |= CapabilityConstants.CLIENT_ODBC;
        flag |= CapabilityConstants.CLIENT_IGNORE_SPACE;
        flag |= CapabilityConstants.CLIENT_PROTOCOL_41;
        flag |= CapabilityConstants.CLIENT_INTERACTIVE;
        flag |= CapabilityConstants.CLIENT_IGNORE_SIGPIPE;
        flag |= CapabilityConstants.CLIENT_SECURE_CONNECTION;

//        flag |= CapabilityConstants.CLIENT_LONG_PASSWORD;
//        flag |= CapabilityConstants.CLIENT_FOUND_ROWS;
//        flag |= CapabilityConstants.CLIENT_LONG_FLAG;
//        flag |= CapabilityConstants.CLIENT_ODBC;
//        flag |= CapabilityConstants.CLIENT_IGNORE_SPACE;
//        flag |= CapabilityConstants.CLIENT_PROTOCOL_41;
//        flag |= CapabilityConstants.CLIENT_INTERACTIVE;
//        flag |= CapabilityConstants.CLIENT_IGNORE_SIGPIPE;
//        flag |= CapabilityConstants.CLIENT_SECURE_CONNECTION;

        return flag;
    }

    public ByteBuf transferByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        MessageWriter messageWriter = new MessageWriter(buffer);
        messageWriter.writeUB3(calcPacketSize());
        messageWriter.writeByte(packetId);
        messageWriter.writeUB4(clientFlags);
        messageWriter.writeUB4(maxPacketSize);
        messageWriter.writeByte((byte) charsetIndex);
        messageWriter.writeBytes(FILLER);
        if (user == null) {
            messageWriter.writeByte((byte) 0);
        } else {
            messageWriter.writeWithNull(user.getBytes());
        }
        if (password == null) {
            messageWriter.writeByte((byte) 0);
        } else {
            messageWriter.writeWithLength(password);
        }
        if (database == null) {
            messageWriter.writeByte((byte) 0);
        } else {
            messageWriter.writeWithNull(database.getBytes());
        }
        if (authPluginName != null) {
            messageWriter.writeWithNull(authPluginName);
        }
        return buffer;
    }

    private int calcPacketSize() {
        int size = 32;// 4+4+1+23;
        size += (user == null) ? 1 : user.length() + 1;
        size += (password == null) ? 1 : getLength(password);
        size += (database == null) ? 1 : database.length() + 1;
        size += (authPluginName == null) ? 1 : authPluginName.length + 1;
        return size;
    }

    public static final int getLength(byte[] src) {
        int length = src.length;
        if (length < 251) {
            return 1 + length;
        } else if (length < 0x10000L) {
            return 3 + length;
        } else if (length < 0x1000000L) {
            return 4 + length;
        } else {
            return 9 + length;
        }
    }

    @Override
    public String toString() {
        return "AuthPacket{" +
                "packetId=" + packetId +
                ", clientFlags=" + clientFlags +
                ", maxPacketSize=" + maxPacketSize +
                ", charsetIndex=" + charsetIndex +
                ", extra=" + Arrays.toString(extra) +
                ", user='" + user + '\'' +
                ", passwordLength=" + passwordLength +
                ", password=" + Arrays.toString(password) +
                ", database='" + database + '\'' +
                ", authPluginName=" + Arrays.toString(authPluginName) +
                '}';
    }
}