package cn.weicelove;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author DIDIBABA_CAR_QPW Create in 2020/6/8 11:11
 */
public class TimeClient {

    private static final int PORT = 3306;
//    private static final int PORT = 33006;

//    private static final String HOST = "192.168.75.128";
    private static final String HOST = "150.158.199.215";

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    NioEventLoopGroup group = new NioEventLoopGroup();

    public static void main(String[] args) {
        try {
            new TimeClient().connect(HOST, PORT);
        } catch (InterruptedException e) {
            System.out.println("client error: " + e.getMessage());
        }
    }

    public void connect(String host, int port) throws InterruptedException {
        // 配置客户端NIO线程
//        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建客户端辅助启动类
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    // 配置TCP参数
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 协议解析
                            socketChannel.pipeline().addLast(new PacketDecoder());
                            // 连接成功后，保存握手信息
                            socketChannel.pipeline().addLast(new PacketHandler(new StateProcessor(State.UN_AUTH)));
                            // 连接后进行认证
//                            socketChannel.pipeline().addLast(new LoginAuthReqHandler());
//                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
//            // 发起异步连接操作
//            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
//            // 等待客户端链路关闭
//            channelFuture.channel().closeFuture().sync();
            // 绑定本地端口，用于重复登录保护，从产品角度不允许系统随便使用随机端口
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println("错误" + e.getMessage());
        } finally {
//            // 优雅退出，释放NIO线程
//            group.shutdownGracefully();
            // 所有资源释放完成之后，清空资源，再次发起重连操作

            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    // 发起重连操作
                    connect(HOST, PORT);
                } catch (Exception e) {
                    System.out.println("client 重连失败: " + e.getMessage());
                }
            });
        }

    }

}