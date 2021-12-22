package cn.weicelove;

import io.netty.channel.ChannelHandlerContext;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/12/21 17:48
 */
public class StateProcessor {

    private volatile State state;

    public StateProcessor(State state) {
        this.state = state;
    }

    public void process(ChannelHandlerContext ctx, BinaryPacket binaryPacket) {
        state.process(this, ctx, binaryPacket);
    }

    public void setState(State state) {
        this.state = state;
    }
}
