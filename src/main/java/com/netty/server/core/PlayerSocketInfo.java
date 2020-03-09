package com.netty.server.core;

import io.netty.channel.ChannelHandlerContext;
import org.luaj.vm2.Globals;

public class PlayerSocketInfo {
    ChannelHandlerContext channelHandlerContext;
    Globals globals;
    public PlayerSocketInfo(ChannelHandlerContext ctx, Globals globals){
        this.channelHandlerContext = ctx;
        this.globals = globals;
    }

    public void setGlobals(Globals globals) {
        this.globals = globals;
    }

    public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public Globals getGlobals() {
        return globals;
    }
}
