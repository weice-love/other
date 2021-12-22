package cn.weicelove;

import io.netty.channel.ChannelHandlerContext;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/12/22 16:24
 */
public enum State {

    UN_AUTH {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {
            new HandPacket()

            processor.setState(AUTHING);
        }
    },
    AUTHING {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {

        }
    },
    AUTHED {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {

        }
    };

    abstract void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket);
}
