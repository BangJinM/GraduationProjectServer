package com.netty.server.netty;

import com.netty.server.core.HandlersManager;
import com.netty.server.core.SocketModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class GameServerHandler extends ChannelInboundHandlerAdapter {
    public HandlersManager handlersManager = HandlersManager.GetIntance();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        handlersManager.ReadMessage(ctx, (SocketModel) msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        handlersManager.RemoveChannelHandlerContext(ctx);
        super.channelUnregistered(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("已经5秒未收到客户端的消息了！");
        if (evt instanceof IdleStateEvent) {
            handlersManager.RemoveChannelHandlerContext(ctx);
            System.out.println("关闭不活跃通道");
            ctx.close();
        }
        super.userEventTriggered(ctx, evt);
    }
}
