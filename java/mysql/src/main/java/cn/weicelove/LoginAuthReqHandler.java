package cn.weicelove;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * 握手认证, 客户端
 * @author DIDIBABA_CAR_QPW Create in 2020/6/11 14:41
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(null);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("LoginAuthReqHandler exceptionCaught: " + cause.getMessage());
        ctx.fireExceptionCaught(cause);
    }
}