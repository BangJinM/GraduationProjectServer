#ifndef  _Test_ProtocolTest_H_
#define  _Test_ProtocolTest_H_
#include "../BaseProtocol.h"
#include <string>
using namespace network;
using namespace std;

class ProtocolTest
{
public:
	static void Test() {
		HeartBeat heartBeat;
		heartBeat.message = 423;
		char* buf = (char*)malloc(sizeof(HeartBeat));
		NIStream* is = new NIStream(buf, sizeof(HeartBeat));
		*is << heartBeat;
		char * bytes = is->getBytes();
		NOStream* os = new NOStream(bytes, sizeof(HeartBeat));
		*os >> heartBeat;
	}
};

#endif