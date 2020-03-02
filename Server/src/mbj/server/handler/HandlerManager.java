package mbj.server.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.netty.channel.ChannelHandlerContext;
import mbj.server.handler.utils.ChannelHandlerUtils;
import mbj.server.protocol.LoginProtocol;
import mbj.server.protocol.TypeProtocol;


/**
 * @����
 * �������ӣ��˺ŵ�¼Ψһ
 */
public class HandlerManager {
	
	/**
	 *	�������ID������ ��Ψһ��ֻ����һ���豸�ϵ�¼
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
	 * ���channelHandler ��������Ϣ
	 * @param playerID ���ID��
	 * @param channelHandler ctx;
	 */
	public void AddChannelHandlerContext(Integer playerID,ChannelHandlerContext channelHandler){
		//�ж��������Ƿ��и�key����ң�����У��ж��Ƿ��ǵ�ǰ����
		if(handles.containsKey(playerID)&&channelHandler==handles.get(playerID))
			return;
		if(handles.containsKey(playerID)){
			ChannelHandlerUtils.WriteMessage(channelHandler, TypeProtocol.TYPE_LOGIN, LoginProtocol.Login_DisConnect, LoginProtocol.Login_DisConnect, null);
			handles.get(playerID).close();
			handles.replace(playerID, channelHandler);
			return;
		}
		//���赱ǰ�����Ѿ���¼��һ���˺ţ��������������˺ŵ�¼�����Ƚ���ǰ�����Ƴ�������µ�����
		Set<Integer> keySet= handles.keySet();
		for (Integer integer : keySet) {
			if(handles.get(integer)==channelHandler&&integer!=playerID){
				handles.remove(integer);
			}
		}
		handles.put(playerID, channelHandler);
	}
	
	/**
	 * ɾ��channelHandler ��������Ϣ
	 * @param playerID ���ID��
	 */
	public void RemoveChannelHandlerContext(Integer playerID){
		if(!handles.containsKey(playerID))
			return;
		handles.remove(playerID);
	}
	
	/**
	 * ɾ��channelHandler ��������Ϣ
	 * @param ChannelHandlerContext channelHandler
	 */
	public void RemoveChannelHandlerContext(ChannelHandlerContext channelHandler){
		Set<Integer> keySet= handles.keySet();
		for (Integer integer : keySet) {
			if(handles.get(integer)==channelHandler){
				System.out.println("�˺�Ϊ��"+integer+"���û�"+"�Ͽ����ӣ�");
				handles.remove(integer);
			}
		}
	}
}
