package com.scorer.clientPhone.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WatchServerNIO {

    @Autowired
    ApplicationContext applicationContext;

    public static Map<String, Channel> userDeviceChannel = new ConcurrentHashMap<>();
    public static Map<Channel, String> userChannelKey = new ConcurrentHashMap<>();
    public static Map<Channel, String> userChannelDevice = new ConcurrentHashMap<>();

    @PostConstruct
    public void initSelf() {
        System.out.println("StartNIO");
        server();
    }

    public void server() {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        System.out.println(Thread.currentThread().getName() + ",服务器初始化通道...");
                        ch.pipeline().addLast("decoder", new SelfByteDecoder());
                        ch.pipeline().addLast("inHandler", applicationContext.getBean(InBoundHandler.class));
                        ch.pipeline().addLast("idleState", new IdleStateHandlerInitializer());
                        ch.pipeline().addLast("encoder", new SelfByteEncoder());
                    }
                });
        serverBootstrap.bind(20001);
    }

}
