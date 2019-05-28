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
	ClientManager* ClientManager::getInstance() {
	if (clientManager == nullptr)
		clientManager = new ClientManager();
	return clientManager;
}

}