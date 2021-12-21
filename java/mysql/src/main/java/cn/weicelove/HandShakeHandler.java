package cn.weicelove;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import static cn.weicelove.constants.ClientStateConstants.CLIENT_INITED;
import static cn.weicelove.constants.ClientStateConstants.CLIENT_UNINIT;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/12/21 10:45
 */
public class HandShakeHandler extends ChannelHandlerAdapter {

    private static volatile int CLIENT_STATE = CLIENT_UNINIT;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        switch (CLIENT_STATE) {
            case CLIENT_UNINIT:
                System.out.println("未初始化");
                auth(ctx, msg);
                CLIENT_STATE = CLIENT_INITED;
                break;
            case CLIENT_INITED:
                System.out.println("已初始化");
                break;
        }
    }

    private void auth(ChannelHandlerContext ctx, Object msg) {
        HandPacket handPacket = (HandPacket) msg;
        handPacket.parse();

    }
}
