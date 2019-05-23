#include <uv.h>
#include "TcpServer.h"
#include "log4z.h"
TcpServer::TcpServer(uv_loop_t* loop)
{
	_loop = loop;
}

void TcpServer::startLog(const char * logpath)
{
	zsummer::log4z::ILog4zManager::getInstance()->setLoggerMonthdir(LOG4Z_MAIN_LOGGER_ID, true);
	zsummer::log4z::ILog4zManager::getInstance()->setLoggerDisplay(LOG4Z_MAIN_LOGGER_ID, true);
	zsummer::log4z::ILog4zManager::getInstance()->setLoggerLevel(LOG4Z_MAIN_LOGGER_ID, LOG_LEVEL_DEBUG);
	zsummer::log4z::ILog4zManager::getInstance()->setLoggerLimitsize(LOG4Z_MAIN_LOGGER_ID, 100);
	if (logpath) {
		zsummer::log4z::ILog4zManager::getInstance()->setLoggerPath(LOG4Z_MAIN_LOGGER_ID, logpath);
	}
	zsummer::log4z::ILog4zManager::getInstance()->start();
}
TcpServer::~TcpServer()
{
}

bool TcpServer::init(int status)
{
	if (!_loop)
		return false;
	bool iret = uv_tcp_init(_loop, &_server);
	if (iret) {
		return false;
	}
	return true;
}

bool TcpServer::bind(char * ip, int port, SEVERIPTYPE type)
{
	if(type == SEVERIPTYPE::IPV4){
		struct sockaddr_in bind_addr;
		int iret = uv_ip4_addr(ip, port, &bind_addr);
		if (iret) {
			return false;
		}
		iret = uv_tcp_bind(&_server, (const struct sockaddr*)&bind_addr, 0);
		if (iret) {
			return false;
		}
		LOGI("server bind ip=" << ip << ", port=" << port);
		return true;
	}
	else {
		struct sockaddr_in6 bind_addr;
		int iret = uv_ip6_addr(ip, port, &bind_addr);
		if (iret) {
			return false;
		}
		iret = uv_tcp_bind(&_server, (const struct sockaddr*)&bind_addr, 0);
		if (iret) {
			return false;
		}
		LOGI("server bind ip=" << ip << ", port=" << port);
		return true;
	}
}

bool TcpServer::listen(int backlog, void acceptConnection(uv_stream_t* server, int status))
{
	int r = uv_listen((uv_stream_t*)&_server, 128, acceptConnection);
	if (r) {
		return false;
	}
	LOGI("server listen");
	return true; 
}

bool TcpServer::start(char * ip, int port, SEVERIPTYPE type, void acceptConnection(uv_stream_t* server, int status))
{
	acceptConnetion = acceptConnection;
	if (!init()) {
		return false;
	}
	if (!bind(ip, port, type)) {
		return false;
	}
	if (!listen(SOMAXCONN, acceptConnection)) {
		return false;
	}
	if (!run()) {
		return false;
	}
	return true;
}

bool TcpServer::run(int status)
{
	LOGI("server runing.");
	int iret = uv_run(_loop, (uv_run_mode)status);
	if (iret) {
		LOGE("server runing Error");
		return false;
	}
	return true;
}
