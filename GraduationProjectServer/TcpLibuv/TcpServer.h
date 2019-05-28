#include <uv.h>
#include "ClientManager.h"
using namespace UVSERVER;
#define DEFAULT_PORT 19999
enum SEVERIPTYPE
{
	IPV4,IPV6
};
void acceptConnection(uv_stream_t* server, int status);
class TcpServer
{
private:
	uv_tcp_t _server;
	uv_loop_t *_loop;
	char* logPath;
	ClientManager *clientManager;
public:
	static void startLog(const char* logpath = nullptr);//启动日志，必须启动才会生成日志
public:
	TcpServer(uv_loop_t* loop = uv_default_loop());
	virtual ~TcpServer();

	bool init(int status = UV_RUN_DEFAULT);
	bool bind(char* ip, int port, SEVERIPTYPE type);
	bool listen(int backlog);
	bool start(char* ip, int port,SEVERIPTYPE type);
	bool run(int status = UV_RUN_DEFAULT);
};

