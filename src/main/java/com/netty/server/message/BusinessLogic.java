package com.netty.server.message;

import com.netty.server.core.SocketModel;
import io.netty.channel.ChannelHandlerContext;

public class BusinessLogic extends BaseMessage {
    public static int TYPE = MessageConstants.BusinessLogic;
    @Override
    public void ReadMessage(ChannelHandlerContext ctx, SocketModel model) {
        String str = model.getMessage();
    }
}
