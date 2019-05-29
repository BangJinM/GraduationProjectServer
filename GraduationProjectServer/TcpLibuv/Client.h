#ifndef TCPLIBUV_CLIENT_H
#define TCPLIBUV_CLIENT_H
namespace UVSERVER {
	class Client
	{
	public:
		Client(int ID):clientID(ID) {}
		~Client() {}

	public:
		int clientID;//ΨһID
		uv_tcp_t* clientHandle;
	};
}
#endif