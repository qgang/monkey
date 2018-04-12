package com.steel.nettyaction.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 通过ChannelHandler来实现服务器的逻辑
 * 注意: ChannelHandler 是给不同类型的事件调用
 * @author steelqin
 * @date 18/4/10
 */
/*标记这类的实例之间可以在channel里面共享*/
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server received: " + in.toString(CharsetUtil.UTF_8));

        /*将所接受到的消息返给发送者。注意,这里还没有冲刷数据*/
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        /*冲刷所有待审消息到远程节点, 然后添加关闭通道事件, 操作完成。*/
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

        /*通常难以重错误中恢复, 所以关闭通道*/
        ctx.close();
    }
}
