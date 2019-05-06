#ifndef  _Protocol_BaseProtocol_H_
#define  _Protocol_BaseProtocol_H_
#include "Stream.h"
namespace network{
const short SE_CLIENT_FIRST = 10000;	//客户服务间协议起始编号

const short SE_HEARTBEAT = SE_CLIENT_FIRST + 1; //心跳

struct HeartBeat
{
	enum { ID = SE_HEARTBEAT};
	short message;
	HeartBeat():message(0) {}
	friend NIStream& operator<<(NIStream& os, const HeartBeat& ca)
	{
		os<<ca.message;
		return os;
	}
	friend NOStream& operator>>(NOStream& bos, HeartBeat& ca)
	{
		bos >> ca.message;
		return bos;
	}
};

}
#endif