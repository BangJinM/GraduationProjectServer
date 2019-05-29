#include "ClientManager.h"
namespace UVSERVER{
	ClientManager* clientManager;
	bool ClientManager::addClient(int id, Client *client)
	{
		if(clients.find(id) != clients.end())
			return false;
		clients.insert(std::map<int, Client*>::value_type(id, client));
		return true;
	}
	bool ClientManager::deleteClient(int id)
	{
		return false;
	}
	int ClientManager::getAvailClientID()
	{
		short index = 1;
		while (true && index <= INT_MAX)
		{
			if (clients.find(index) == clients.end())
				return index;
			++index;
		}
		return -1;
	}
	ClientManager* ClientManager::getInstance() {
	if (clientManager == nullptr)
		clientManager = new ClientManager();
	return clientManager;
}

}