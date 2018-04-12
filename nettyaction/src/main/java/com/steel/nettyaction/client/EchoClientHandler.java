package com.steel.nettyaction.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 通过ChannelHandler来实现客户端逻辑
 * @author steelqin
 * @date 18/4/11
 */
/*标记这类的实例之间可以在channel里面共享*/
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*当被通知该Channel是活动的时候就发送信息*/
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    /*注意: 由服务器所发送的消息可以以块的形式被接收, 即channelRead0可能被调用多次*/
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                ByteBuf in) throws Exception {
        System.out.println(
                "Client received: "
                + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        cause.printStackTrace();

        ctx.close();
    }
}
