package com.steel.nettyaction.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导服务器
 * 1. 监听和接收进来的连接请求
 * 2. 配置Channel来通知一个关于入站消息的EchoServerHandler实例
 * @author steelqin
 * @date 18/4/11
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println(
                    "Usage: " + EchoServer.class.getSimpleName()
                    + " <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

            /*设置端口, 呼叫服务端的start方法*/
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)  //指定使用NIO的传输Channel
                    .localAddress(new InetSocketAddress(port)) //设置socket地址使用所选择的端口
                    .childHandler(new ChannelInitializer<SocketChannel>() { //添加EchoServerHandler到Channel的ChannelPipeline

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture f = b.bind().sync();
            System.out.println(
                    EchoServer.class.getName()
                    + " start and listen on "
                    + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            /*关闭EventLoopGroup, 释放所有资源*/
            group.shutdownGracefully();
        }
    }
}
