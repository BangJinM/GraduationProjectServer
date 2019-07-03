#ifndef TCPLIBUV_CLIENT_H
#define TCPLIBUV_CLIENT_H
#include <uv.h>
namespace UVSERVER {
	class Client
	{
	public:
		Client(int ID,bool isUse = true):clientID(ID),isUse(isUse) {}
		~Client() {}

	public:
		int clientID; //ΨһID
		bool isUse = false;

		uv_tcp_t* clientHandle;

	public:
		static void receive(uv_handle_t* handle, size_t suggested_size, uv_buf_t* buf);
		static void afterRecv(uv_stream_t* handle, ssize_t nread, const uv_buf_t* buf);
	};

	void Client::receive(uv_handle_t* handle, size_t suggested_size, uv_buf_t* buf) {}
	void Client::afterRecv(uv_stream_t* handle, ssize_t nread, const uv_buf_t* buf) {}
}
#endif