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
            // 解析握手包
            HandPacket handPacket = new HandPacket(binaryPacket);
            handPacket.parse();
            processor.setState(AUTHING);
        }
    },
    AUTHING {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {
            processor.setState(AUTHED);
        }
    },
    AUTHED {
        @Override
        void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket) {

        }
    };

    abstract void process(StateProcessor processor, ChannelHandlerContext ctx, BinaryPacket binaryPacket);
}
