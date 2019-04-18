package com.cormye.client.handler;

import com.cormye.common.proto.PackType;
import com.cormye.common.proto.TransProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: cormye
 * @Date: 2019-04-17 16:32
 * @Description:
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush(TransProtocol.newBuilder().setPackType(PackType.PINGER));
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.info("active {}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        log.info("inactive {}", ctx.channel().remoteAddress());

    }
}
