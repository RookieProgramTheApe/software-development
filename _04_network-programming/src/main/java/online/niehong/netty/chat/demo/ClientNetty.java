package online.niehong.netty.chat.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import online.niehong.netty.chat.demo.handler.ClientNettyHandler;

public class ClientNetty {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new ClientNettyHandler());
                    }
                });

        System.out.println("启动中...");
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9998).sync();
        System.out.println("客户端启动成功");

        // 关闭连接(异步非阻塞)
        channelFuture.channel().closeFuture().sync();
    }
}
