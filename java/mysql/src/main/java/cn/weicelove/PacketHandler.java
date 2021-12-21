package cn.weicelove;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * <p> @author     :  清风
 * <p> description :  包解析
 * <p> create date :  2021/12/21 15:59
 */
public abstract class PacketHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BinaryPacket binaryPacket = (BinaryPacket) msg;

    }
}
