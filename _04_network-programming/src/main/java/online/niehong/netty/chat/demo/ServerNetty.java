package online.niehong.netty.chat.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import online.niehong.netty.chat.demo.handler.ServerNettyHandler;

public class ServerNetty {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 180)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new ServerNettyHandler());
                    }
                });

        System.out.println("服务端启动中...");
        ChannelFuture channelFuture = serverBootstrap.bind(9998).sync();
        System.out.println("服务端启动成功，端口：9998");

        // 关闭通道，关闭线程组
        channelFuture.channel().closeFuture().sync(); //异步
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
