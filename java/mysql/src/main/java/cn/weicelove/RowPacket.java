package cn.weicelove;

import cn.weicelove.constants.CharSetEnum;
import cn.weicelove.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RowPacket {

    private static final Logger log = LoggerFactory.getLogger(RowPacket.class);

    public List<byte[]> rows = new ArrayList<>();

    public void  parse(BinaryPacket binaryPacket) {
        log.info("start parse column definition packet!!!");
        MessageReader messageReader = new MessageReader(binaryPacket.getPacketBodyLength(), binaryPacket.getData());
        while (messageReader.hasNext()) {
            byte[] bytes = readLenStr(messageReader);
            rows.add(bytes);
        }
        log.info("parse row success!!! data: {}", this.toString());
    }

    private byte[] readLenStr(MessageReader messageReader) {
        int length = messageReader.readByte() & 0xff;
        if (length < 0xfc) {
            return messageReader.readStringFixLength(length);
        } else if (length == 0xfc) {
            return messageReader.readStringFixLength(messageReader.readUB2());
        } else if (length == 0xfd) {
            return messageReader.readStringFixLength(messageReader.readUB3());
        } else {
            return messageReader.readStringFixLength((int)messageReader.readUB8());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nRowPacket{\n");
        for (int i = 0; i < rows.size(); i++) {
               builder.append("rows(" +i+")=" + ByteUtil.byte2String(rows.get(i), CharSetEnum.latin1.getCharacterSetName()) + '\n');
        }
        builder.append('}');
        return builder.toString();
    }
}
