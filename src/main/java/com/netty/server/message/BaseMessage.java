package com.netty.server.message;

import com.netty.server.core.SocketModel;
import io.netty.channel.ChannelHandlerContext;

public abstract class BaseMessage {
    public int id = -1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract void ReadMessage(ChannelHandlerContext ctx, SocketModel model);
}
