package online.niehong.netty.chat.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import online.niehong.netty.chat.example.handler.ChatServerChannelHandler;

/**
 * 聊天服务端
 *
 * @author NieHong
 * @date 2023/12/05
 */
@Data
public class ChatServer {
    /**
     * 端口
     */
    private int port;

    public static void main(String[] args) {
        // 启动服务端
        new ChatServer().bootstrap();
    }

    /**
     * 服务端服务启动引导
     */
    private void bootstrap() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 50)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 解码器
                        pipeline.addLast("decoder", new StringDecoder());
                        // 编码器
                        pipeline.addLast("encoder", new StringEncoder());
                        // 添加自定义Handler处理类
                        ch.pipeline().addLast(new ChatServerChannelHandler());
                    }
                });

        try {
            System.out.println("服务端启动中...");
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("服务启动成功");
            // 关闭通道，关闭线程组
            channelFuture.channel().closeFuture().sync();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public ChatServer() {
        this(9998);
    }

    public ChatServer(int port) {
        this.port = port;
    }
}
