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
		*@brief ��manager�в���Client
		*@param int key
		*@param Client* Client
		*@return �����Ƿ�ɹ�
		**/
		bool addClient(int, Client*);
		/**
		*@brief ����keyɾ����ӦClient
		*@param int key
		*@return �����Ƿ�ɹ�
		**/
		bool deleteClient(int);

		/**
		*@brief ��ȡ�����õĿͻ���ID
		*@return ��С�Ŀ�����ID -1Ϊ�����ڿ�����ID
		**/
		int getAvailClientID();

		static ClientManager* getInstance();
	private:
		ClientManager() {}

		std::map<int, Client*> clients;//�洢���еĿͻ�����Ϣ

	};
}
#endif