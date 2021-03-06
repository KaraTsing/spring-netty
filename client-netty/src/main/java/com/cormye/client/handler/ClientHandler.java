package com.cormye.client.handler;

import com.cormye.common.proto.ClientOnline;
import com.cormye.common.proto.PackType;
import com.cormye.common.proto.TransProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: cormye
 * @Date: 2019-04-09 14:10
 * @Description:
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("connect server success！");

        TransProtocol message = TransProtocol.newBuilder()
                .setPackType(PackType.CLIENT_ONLINE)
                .setClientOnline(ClientOnline.newBuilder()
                        .setTx2Id("1weqweqwsd")
                        .setTx2Ip("192.168.8.157").build())
                .build();

        ctx.channel().writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("ClientHandler:{}", msg.toString());
        ctx.writeAndFlush("我是ClientHandler");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
