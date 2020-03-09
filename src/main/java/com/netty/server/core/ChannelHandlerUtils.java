package com.netty.server.core;

import io.netty.channel.ChannelHandlerContext;

public class ChannelHandlerUtils {
	public static void WriteMessage(ChannelHandlerContext ctx,int typeProtocol,int loginProtocol,int command,String message){
		SocketModel wS = new SocketModel();
		wS.setType(typeProtocol);
		wS.setArea(loginProtocol);
		wS.setCommand(command);
		wS.setMessage(message);
		ctx.writeAndFlush(wS);
	}

	public static void WriteMessage(ChannelHandlerContext ctx, SocketModel message){
		ctx.writeAndFlush(message);
	}
}
