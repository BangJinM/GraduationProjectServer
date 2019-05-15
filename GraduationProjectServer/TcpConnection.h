#pragma once
#include <uv.h>
#define DEFAULT_PORT 19999
class TcpConnection
{
	uv_loop_t* loop;
	uv_tcp_t *server;
	sockaddr_in addr;
public:
	TcpConnection();
	~TcpConnection();
	int run();
	//void connectionListen(uv_stream_t* server, int status);
};

