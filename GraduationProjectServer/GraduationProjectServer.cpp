// GraduationProjectServer.cpp: 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "BaseProtocol.h"
#include <string>
using namespace network;
using namespace std;
int main(){
	HeartBeat heartBeat;
	heartBeat.message = 423;
	char* buf = (char*)malloc(sizeof(HeartBeat));
	IStream* is = new IStream(buf,sizeof(HeartBeat));
	*is << heartBeat;
	char * bytes = is->getBytes();
	OStream* os = new OStream(bytes,sizeof(HeartBeat));
	*os >> heartBeat;
    return 0;
}

