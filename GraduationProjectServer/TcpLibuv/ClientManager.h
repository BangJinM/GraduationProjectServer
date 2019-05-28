#ifndef TCPLIBUV_CLIENTMANAGER_H
#define TCPLIBUV_CLIENTMANAGER_H
#include "Client.h"
#include <map>
namespace UVSERVER {
	
	class ClientManager
	{
	public:


		~ClientManager() {}
		bool addClient(int, Client*);
		bool deleteClient(int);

		static ClientManager* getInstance();
	private:
		ClientManager() {}
		std::map<int, Client*> clients;
	};
}
#endif