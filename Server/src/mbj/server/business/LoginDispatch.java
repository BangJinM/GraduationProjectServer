package mbj.server.business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import mbj.server.db.OperationDB;
import mbj.server.db.PlayerInformation;
import mbj.server.handler.HandlerManager;
import mbj.server.handler.utils.ChannelHandlerUtils;
import mbj.server.model.SocketModel;
import mbj.server.protocol.LoginProtocol;
import mbj.server.protocol.TypeProtocol;

public class LoginDispatch {
	public PlayerInformation playerInformation=null;
	private HandlerManager handlerManager=HandlerManager.GetIntance();
	public void dispatch(ChannelHandlerContext ctx, SocketModel message) {
		switch (message.getArea()) {
		case LoginProtocol.LoginRequest:
			checkLogin(ctx, message);
			break;
		default:
			break;
		}
	}

	
	/**
	 * ����¼
	 * 
	 */
	private void checkLogin(ChannelHandlerContext ctx, SocketModel message) {
		List<String> messageList = message.getMessage();
		System.out.println(messageList.get(0)+"  "+messageList.get(1));
		List<PlayerInformation> lists = OperationDB.QueryPlayerWithAccountName(messageList.get(0));
		if (!lists.isEmpty()) {
			playerInformation=lists.get(0);
			//�˺ź����������ݿ�Ƚ�
			if (playerInformation.getPlayer_Account().compareTo(messageList.get(0))==0 && 
					playerInformation.getPlayer_Password().compareTo(messageList.get(1))==0) {
				handlerManager.AddChannelHandlerContext(playerInformation.player_ID, ctx);
				//���͵�½�ɹ�
				ChannelHandlerUtils.WriteMessage(ctx, TypeProtocol.TYPE_LOGIN, LoginProtocol.Login_Succeed, 1, null);
			}else {
				//�����������
				ChannelHandlerUtils.WriteMessage(ctx, TypeProtocol.TYPE_LOGIN, LoginProtocol.Login_InvalidPassword, 1, null);
			}
		}else {
			//�����˺Ų�����
			ChannelHandlerUtils.WriteMessage(ctx, TypeProtocol.TYPE_LOGIN, LoginProtocol.Login_InvalidUsername, 1, null);
		}
	}


	
	/**
	 * �ڹ��������Ƴ�����
	 */
	public void DisConnection(ChannelHandlerContext ctx){
		if(playerInformation!=null)
			handlerManager.RemoveChannelHandlerContext(playerInformation.getPlayer_ID());
	}
}