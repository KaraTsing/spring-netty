package com.cormye.client.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: cormye
 * @Date: 2019-04-17 17:30
 * @Description: 自动重连类
 */
@Slf4j
@ChannelHandler.Sharable
public abstract class ConnectionWatchdog extends ChannelInboundHandlerAdapter implements TimerTask, ChannelHandlerHolder {

    private final Bootstrap bootstrap;
    private final Timer timer;
    private final int port;

    private final String host;

    private volatile boolean reconnect = true;
    private int attempts;


    public ConnectionWatchdog(Bootstrap bootstrap, Timer timer,  String host, int port,boolean reconnect) {
        this.bootstrap = bootstrap;
        this.timer = timer;
        this.port = port;
        this.host = host;
        this.reconnect = reconnect;
    }

    /**
     * 连接成功，清空连接次数啊
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接成功！{}",ctx.channel().remoteAddress());
        attempts=0;
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接断开");
        if(reconnect){
            log.info("重新连接中");
            if(attempts<12){
                attempts++;
                //重连时间
                int timeout=5;
                timer.newTimeout(this, timeout, TimeUnit.SECONDS);

            }
        }
        ctx.fireChannelActive();
    }

    /**
     *
     * @param timeout
     * @throws Exception
     */
    @Override
    public void run(Timeout timeout) throws Exception {
        log.info("connected...");
        ChannelFuture future;
        //
        synchronized (bootstrap){
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(handlers());
                }
            });
            future = bootstrap.connect(host,port);
        }

        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()){
                log.info("重连成功");
            }else{
                log.info("重连失败");
                future1.channel().pipeline().fireChannelInactive();
            }
        });




    }
}
