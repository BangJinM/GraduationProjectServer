package mbj.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import mbj.server.handler.utils.ChannelHandlerUtils;
import mbj.server.model.SocketModel;

public class ServerHandler extends ChannelHandlerAdapter{
	// HandlerManager handlerManager=HandlerManager.GetIntance();
	// LoginDispatch loginDispatch;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception  //当客户端连上服务器的时候会触发此函数
    {
    	// loginDispatch=new LoginDispatch();
    	System.out.println("clinet:" + ctx.channel().id() + " join server");
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception//当客户端断开连接的时候触发函数
    {
        System.out.println("clinet:" + ctx.channel().id() + " leave server");
        // loginDispatch.DisConnection(ctx);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception//当客户端发送数据到服务器会触发此函数
    {
    	SocketModel message =(SocketModel) msg;
        ChannelHandlerUtils.WriteMessage(ctx, message);
    }
    
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
    	// loginDispatch.DisConnection(ctx);
        cause.printStackTrace();
        ctx.close();
    }

}