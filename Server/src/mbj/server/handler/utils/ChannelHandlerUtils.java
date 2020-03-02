package mbj.server.handler.utils;
import io.netty.channel.ChannelHandlerContext;
import mbj.server.model.SocketModel;

public class ChannelHandlerUtils {
	/**
	 * ·¢ËÍÏûÏ¢
	 */
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
