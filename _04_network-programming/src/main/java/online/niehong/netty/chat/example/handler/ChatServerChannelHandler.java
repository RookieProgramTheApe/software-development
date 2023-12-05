package online.niehong.netty.chat.example.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 聊天服务器通道处理程序
 *
 * @author NieHong
 * @date 2023/12/05
 */
public class ChatServerChannelHandler extends SimpleChannelInboundHandler<String> {


    /**
     * 活跃的通道列表
     */
    private static List<Channel> channels = new ArrayList<>();


    /**
     * 通道就绪（活跃）
     *
     * @param ctx 事件处理器上下文对象
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        channels.add(ctx.channel());
        System.out.println("当前在线人数：" + channels.size() + ", 上线信息：" + ctx.channel().remoteAddress().toString().substring(1));
    }

    /**
     * 通道未就绪（不活跃）
     *
     * @param ctx 事件处理器上下文对象
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        channels.remove(ctx.channel());
        System.out.println("当前在线人数：" + channels.size() + ", 下线信息：" + ctx.channel().remoteAddress().toString().substring(1));
    }

    /**
     * 通道read0
     *
     * @param ctx 事件处理器上下文对象
     * @param msg 消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel itselfChannel = ctx.channel();

        // 为Consumer指定具体行为
        Consumer<Channel> channelConsumer = channel -> {
            if (channel != itselfChannel) {
                channel.writeAndFlush(itselfChannel.remoteAddress().toString().substring(1) + "说：" + msg);
            }
        };

        // 当客户端有请求过来后，通过循环方式进行广播通知
        channels.forEach(channelConsumer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
