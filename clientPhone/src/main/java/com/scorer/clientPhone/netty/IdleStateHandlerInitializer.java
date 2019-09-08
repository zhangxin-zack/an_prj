package com.scorer.clientPhone.netty;

import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class IdleStateHandlerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        System.out.println("空连接计时器启动...");
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(0, 0, 30, TimeUnit.SECONDS));  //1
        pipeline.addLast(new HeartbeatHandler(ch));
    }

    public static final class HeartbeatHandler extends ChannelInboundHandlerAdapter {

        private Channel channel;

        HeartbeatHandler(Channel channel) {
            this.channel=channel;
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) {
                System.out.println("发送保持连接LK");
                P_Message p_message = new P_Message(WatchServerNIO.userChannelKey.get(channel),WatchServerNIO.userChannelDevice.get(channel),2L,"LK");
                WatchServerNIO.userDeviceChannel.get(p_message.getRing_no()).writeAndFlush(p_message).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }
    }
}