package com.netty.server.message;

import com.netty.server.core.SocketModel;
import io.netty.channel.ChannelHandlerContext;

public class MainLogic extends BaseMessage {
    public static int TYPE = MessageConstants.LoginRequest;
    @Override
    public void ReadMessage(ChannelHandlerContext ctx, SocketModel model) {
        String str = model.getMessage();
    }
}