#include <uv.h>
#define DEFAULT_PORT 19999
enum SEVERIPTYPE
{
	IPV4,IPV6
};
class TcpServer
{
private:
	uv_tcp_t _server;
	uv_loop_t *_loop;
	void(*acceptConnetion)(uv_stream_t* server, int status);
public:
	static void startLog(const char* logpath = nullptr);//������־�����������Ż�������־
public:
	TcpServer(uv_loop_t* loop = uv_default_loop());
	virtual ~TcpServer();

	bool init(int status = UV_RUN_DEFAULT);
	bool bind(char* ip, int port, SEVERIPTYPE type);
	bool listen(int backlog, void acceptConnection(uv_stream_t* server, int status));
	bool start(char* ip, int port,SEVERIPTYPE type,void acceptConnection(uv_stream_t* server, int status));
	bool run(int status = UV_RUN_DEFAULT);
};

