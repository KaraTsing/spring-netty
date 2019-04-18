package com.cormye.server.handler;

import com.cormye.common.proto.TransProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

/**
 * @Auther: cormye
 * @Date: 2019-04-01 11:44
 * @Description:
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("client {} send heart",ctx.channel().remoteAddress());
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到来自：{} 的消息",ctx.channel().remoteAddress());

        TransProtocol msg1=(TransProtocol)msg;
        System.out.println(msg1.getPackType().getNumber());
        System.out.println(msg1.toString());


    }


//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, TranData.TransProtocol msg) throws Exception {
//        log.info("收到来自：{} 的消息",ctx.channel().remoteAddress());
//        System.out.println(msg.getPackType());
//        System.out.println(msg.toString());
//
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}客户端连接服务端", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 引发异常时关闭连接。
        // cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


}
