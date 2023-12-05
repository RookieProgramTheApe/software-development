package online.niehong.netty.chat.demo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ServerNettyHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    /**
     * 读取从客户端发来的信息
     * @param ctx 事件处理上下文对象
     * @param msg 消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端说：" + buf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 数据读取完毕，回复客户端
     * @param ctx 事件处理上下文对象
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ByteBuf buf = Unpooled.copiedBuffer("你是谁啊？", CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
