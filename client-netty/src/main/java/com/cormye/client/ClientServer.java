package com.cormye.client;

import com.cormye.client.handler.ChannelHandlerHolder;
import com.cormye.client.handler.ConnectionWatchdog;
import com.cormye.client.init.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: cormye
 * @Date: 2019-04-09 14:04
 * @Description:
 */
@Slf4j
@Order(2)
@Component
public class ClientServer implements CommandLineRunner {


    protected final HashedWheelTimer timer = new HashedWheelTimer();

    private Bootstrap bootstrap;


    /**
     * netty连接类
     *
     * @param host
     * @param port
     * @throws Exception
     */
    public void connect(String host, int port) throws Exception {
        //初始化配置
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO));

        final ConnectionWatchdog watchdog = new ConnectionWatchdog(bootstrap, timer, host, port, true) {
            public ChannelHandler[] handlers() {
                return new ChannelHandler[]{
                        this,
                        new ClientInitializer()
                };
            }
        };

        ChannelFuture future;
        //连接
        try {
            synchronized (bootstrap) {
                bootstrap.handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(watchdog.handlers());
                    }
                });
                future=bootstrap.connect(host,port);
            }

        } catch (Throwable throwable) {
            log.error("connected fails");
        }

    }

    @Override
    public void run(String... args) throws Exception {
        log.info("ClientServer启动......");
        //connect("localhost",9911);
        connect("192.168.8.160",9911);

//        try {
//            Bootstrap serverBootstrap = new Bootstrap();
//            serverBootstrap
//                    .group(workerGroup)
//                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.SO_KEEPALIVE, true)
//                    .handler(new ClientInitializer());
//            ChannelFuture f = serverBootstrap.connect("localhost", 9911).sync(); // (5)
//
//            // Wait until the connection is closed.
//            f.channel().closeFuture().sync();
//
//        } finally {
//            workerGroup.shutdownGracefully();
//        }
    }
}
