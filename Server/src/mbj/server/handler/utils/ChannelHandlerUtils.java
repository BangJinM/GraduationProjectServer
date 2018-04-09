package mbj.server.handler.utils;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import mbj.server.model.SocketModel;

public class ChannelHandlerUtils {
	/**
	 * ·¢ËÍÏûÏ¢
	 */
	public static void WriteMessage(ChannelHandlerContext ctx,int typeProtocol,int loginProtocol,int command,List<String> message){
		SocketModel wS = new SocketModel();
		wS.setType(typeProtocol);
		wS.setArea(loginProtocol);
		wS.setCommand(command);
		List<String> strings = new ArrayList<>();
		wS.setMessage(message);
		ctx.writeAndFlush(wS);
	}
}
