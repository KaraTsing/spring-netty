package com.cormye.common.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: cormye
 * @Date: 2019-04-09 14:10
 * @Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private  static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("ClientHandler:{}",msg.toString());
        ctx.writeAndFlush("cormye");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
