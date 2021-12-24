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

public class CmdPacket {

    private static final Logger log = LoggerFactory.getLogger(CmdPacket.class);

    private static final byte[] FILLER = new byte[23];
    private static final long MAX_PACKET_SIZE = 1024 * 1024 * 16;

    public byte packetId;
    private byte commandType;

    private CmdPacket() {}

    public static CmdPacket writePacket(byte commandType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("start write auth packet...");
        CmdPacket authPacket = new CmdPacket();
        authPacket.packetId = 0;
        return null;
    }
}