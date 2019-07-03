#ifndef TCPLIBUV_CLIENTMANAGER_H
#define TCPLIBUV_CLIENTMANAGER_H
#include "Client.h"
#include <map>
namespace UVSERVER {
	
	class ClientManager
	{
	public:
		~ClientManager() {}
		/**
		*@brief 向manager中插入Client
		*@param int key
		*@param Client* Client
		*@return 返回是否成功
		**/
		bool addClient(int, Client*);
		/**
		*@brief 根据key删除对应Client
		*@param int key
		*@return 返回是否成功
		**/
		bool deleteClient(int);

		/**
		*@brief 获取可利用的客户端ID
		*@return 最小的可利用ID -1为不存在可利用ID
		**/
		int getAvailClientID();

		static ClientManager* getInstance();
	private:
		ClientManager() {}

		std::map<int, Client*> clients;//存储所有的客户端信息

	};
}
#endif