package mbj.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import mbj.server.business.LoginDispatch;
import mbj.server.model.SocketModel;
import mbj.server.protocol.TypeProtocol;

public class ServerHandler extends ChannelHandlerAdapter{
	HandlerManager handlerManager=HandlerManager.GetIntance();
	LoginDispatch loginDispatch;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception  //���ͻ������Ϸ�������ʱ��ᴥ���˺���
    {
    	loginDispatch=new LoginDispatch();
    	System.out.println("clinet:" + ctx.channel().id() + " join server");
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception//���ͻ��˶Ͽ����ӵ�ʱ�򴥷�����
    {
        System.out.println("clinet:" + ctx.channel().id() + " leave server");
        loginDispatch.DisConnection(ctx);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception//���ͻ��˷������ݵ��������ᴥ���˺���
    {
    	SocketModel message =(SocketModel) msg;
        switch (message.getType()) {
        case TypeProtocol.TYPE_LOGIN:
        	loginDispatch.dispatch(ctx, message);
            break;
        default:
        	break;
        }
    }
    
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
    	loginDispatch.DisConnection(ctx);
        cause.printStackTrace();
        ctx.close();
    }

}