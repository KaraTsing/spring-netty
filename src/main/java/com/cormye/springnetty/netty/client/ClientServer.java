package com.cormye.springnetty.netty.client;

import com.cormye.springnetty.config.NettyClientProperties;
import com.cormye.springnetty.netty.client.handler.ClientHandler;
import com.cormye.springnetty.netty.server.handler.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Auther: cormye
 * @Date: 2019-04-09 14:04
 * @Description:
 */

public class ClientServer  {



    public static void main(String[] args) throws Exception{
        {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                Bootstrap serverBootstrap = new Bootstrap();
                serverBootstrap
                        .group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel socketChannel) throws Exception {
                                ChannelPipeline pipeline = socketChannel.pipeline();
                                pipeline.addLast("decoder", new StringDecoder());
                                pipeline.addLast("encoder", new StringEncoder());
                                pipeline.addLast(new ClientHandler());
                            }
                        });

                ChannelFuture f = serverBootstrap.connect("localhost", 9911).sync(); // (5)

                // Wait until the connection is closed.
                f.channel().closeFuture().sync();
            } finally {
                workerGroup.shutdownGracefully();
            }
        }
    }
}
