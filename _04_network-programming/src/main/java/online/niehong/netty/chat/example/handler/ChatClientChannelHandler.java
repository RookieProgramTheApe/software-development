package online.niehong.netty.chat.example.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 聊天客户端通道处理程序
 *
 * @author NieHong
 * @date 2023/12/05
 */
public class ChatClientChannelHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 通道read0
     *
     * @param ctx 事件处理器上下文对象
     * @param msg 消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
