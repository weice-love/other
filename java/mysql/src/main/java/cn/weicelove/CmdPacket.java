package cn.weicelove;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class CmdPacket {

    private static final Logger log = LoggerFactory.getLogger(CmdPacket.class);

    public byte packetId;
    private byte commandType;
    private byte[] command;

    private CmdPacket() {}

    public static CmdPacket writePacket(byte commandType, String command) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("start write command packet...");
        CmdPacket authPacket = new CmdPacket();
        authPacket.packetId = 0;
        authPacket.commandType = commandType;
        authPacket.command = command.getBytes();
        return authPacket;
    }

    public ByteBuf transferByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        MessageWriter messageWriter = new MessageWriter(buffer);
        messageWriter.writeUB3(calcPacketSize());
        messageWriter.writeByte(packetId);
        messageWriter.writeByte(commandType);
        messageWriter.writeBytes(command);
        return buffer;
    }

    private int calcPacketSize() {
        return 1 + command.length;
    }
}