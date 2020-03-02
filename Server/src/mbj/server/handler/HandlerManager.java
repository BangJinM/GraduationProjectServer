package mbj.server.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.netty.channel.ChannelHandlerContext;
import mbj.server.handler.utils.ChannelHandlerUtils;
import mbj.server.protocol.LoginProtocol;
import mbj.server.protocol.TypeProtocol;


/**
 * @马邦进
 * 管理链接，账号登录唯一
 */
public class HandlerManager {
	
	/**
	 *	储存玩家ID和链接 ，唯一，只能在一个设备上登录
	 */
	Map<Integer, ChannelHandlerContext> handles;
	private static HandlerManager handlerManager;
	
	public static HandlerManager GetIntance(){
		if(handlerManager==null)
			handlerManager=new HandlerManager();
		return handlerManager;
	}
	
	private HandlerManager() {
		// TODO Auto-generated constructor stub
		handles=new HashMap<Integer, ChannelHandlerContext>();
	}
	

	/**
	 * 添加channelHandler 上下文信息
	 * @param playerID 玩家ID；
	 * @param channelHandler ctx;
	 */
	public void AddChannelHandlerContext(Integer playerID,ChannelHandlerContext channelHandler){
		//判断连接内是否有该key的玩家，如果有，判断是否是当前连接
		if(handles.containsKey(playerID)&&channelHandler==handles.get(playerID))
			return;
		if(handles.containsKey(playerID)){
			ChannelHandlerUtils.WriteMessage(channelHandler, TypeProtocol.TYPE_LOGIN, LoginProtocol.Login_DisConnect, LoginProtocol.Login_DisConnect, null);
			handles.get(playerID).close();
			handles.replace(playerID, channelHandler);
			return;
		}
		//假设当前连接已经登录过一个账号，现在又用其他账号登录，则先将当前连接移除再添加新的连接
		Set<Integer> keySet= handles.keySet();
		for (Integer integer : keySet) {
			if(handles.get(integer)==channelHandler&&integer!=playerID){
				handles.remove(integer);
			}
		}
		handles.put(playerID, channelHandler);
	}
	
	/**
	 * 删除channelHandler 上下文信息
	 * @param playerID 玩家ID；
	 */
	public void RemoveChannelHandlerContext(Integer playerID){
		if(!handles.containsKey(playerID))
			return;
		handles.remove(playerID);
	}
	
	/**
	 * 删除channelHandler 上下文信息
	 * @param ChannelHandlerContext channelHandler
	 */
	public void RemoveChannelHandlerContext(ChannelHandlerContext channelHandler){
		Set<Integer> keySet= handles.keySet();
		for (Integer integer : keySet) {
			if(handles.get(integer)==channelHandler){
				System.out.println("账号为："+integer+"的用户"+"断开连接！");
				handles.remove(integer);
			}
		}
	}
}
