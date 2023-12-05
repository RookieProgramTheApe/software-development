package online.niehong.netty.chat.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import online.niehong.netty.chat.example.handler.ChatClientChannelHandler;

import java.util.Scanner;

/**
 * 聊天客户端
 *
 * @author NieHong
 * @date 2023/12/05
 */
@Data
@SuppressWarnings("InfiniteLoopStatement")
public class ChatClient {
    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    public static void main(String[] args) {
        // 启动客户端
        new ChatClient().bootstrap();
    }

    /**
     * 客户端服务启动引导
     */
    private void bootstrap() {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 解码器
                        pipeline.addLast("decoder", new StringDecoder());
                        // 编码器
                        pipeline.addLast("encoder", new StringEncoder());
                        pipeline.addLast(new ChatClientChannelHandler());
                    }
                });

        try {
            System.out.println("客户端启动中...");
            ChannelFuture channelFuture = bootstrap.connect(this.host, this.port).sync();
            Channel channel=channelFuture.channel();
            System.out.println("客户端启动成功，本机服务地址信息：" + channel.localAddress().toString().substring(1));
            Scanner scanner=new Scanner(System.in);
            System.out.println("聊天已建立<-------------->\n");
            while (true){
                String msg=scanner.nextLine();
                channel.writeAndFlush(msg+"\r\n");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

}

    public ChatClient() {
        this("127.0.0.1", 9998);
    }

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
