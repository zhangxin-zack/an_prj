package com.scorer.clientPhone.netty;


import com.google.gson.Gson;
import com.scorer.clientPhone.service.PhoneSocketService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope("prototype")
public class InBoundHandler extends SimpleChannelInboundHandler<P_Message> {

    @Resource
    private PhoneSocketService phoneSocketService;

    private static Logger logger = LoggerFactory.getLogger(InBoundHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("CLIENT" + getRemoteAddress(ctx) + " 接入连接");
        System.out.println("CLIENT" + getRemoteAddress(ctx) + " 接入连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //删除Channel Map中的失效Client
        System.out.println("CLIENT" + getRemoteAddress(ctx) + " 断开连接");
        final String deviceInfo = WatchServerNIO.userChannelDevice.get(ctx.channel());
        WatchServerNIO.userChannelDevice.remove(ctx.channel());
        WatchServerNIO.userChannelKey.remove(ctx.channel());
        WatchServerNIO.userDeviceChannel.remove(deviceInfo);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, P_Message msg) {
        System.out.println("In-->"+new Gson().toJson(msg));
        WatchServerNIO.userDeviceChannel.put(msg.getRing_no(), ctx.channel());
        WatchServerNIO.userChannelKey.put(ctx.channel(), msg.getKey());
        WatchServerNIO.userChannelDevice.put(ctx.channel(), msg.getRing_no());
        phoneSocketService.ReceiveMSG(msg);
        phoneSocketService.SendUnSettings(msg,ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String socketString = ctx.channel().remoteAddress().toString();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                logger.info("Client: " + socketString + " READER_IDLE 读超时");
                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                logger.info("Client: " + socketString + " WRITER_IDLE 写超时");
                ctx.disconnect();
            } else if (event.state() == IdleState.ALL_IDLE) {
                logger.info("Client: " + socketString + " ALL_IDLE 总超时");
                ctx.disconnect();
            }
        }
    }

    private static String getRemoteAddress(ChannelHandlerContext ctx) {
        String socketString;
        socketString = ctx.channel().remoteAddress().toString();
        return socketString;
    }



}