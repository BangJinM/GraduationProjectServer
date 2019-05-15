#include "TcpConnection.h"



TcpConnection::TcpConnection()
{
	uv_loop_t* loop = uv_default_loop();

	uv_tcp_t server;
	uv_tcp_init(loop, &server);
	sockaddr_in addr;
	uv_ip4_addr("0.0.0.0", DEFAULT_PORT, &addr);

	uv_tcp_bind(&server, (const struct sockaddr*)&addr, 0);
}



TcpConnection::~TcpConnection()
{
}

void connectionListen(uv_stream_t* server, int status) {
	printf("dasjldjl");
}


int TcpConnection::run() {
	int r = uv_listen((uv_stream_t*)&server, 128, connectionListen);
	if (r) {
		fprintf(stderr, "Listen error %s\n", uv_strerror(r));
		return 1;
	}
	return uv_run(loop, UV_RUN_DEFAULT);
}
