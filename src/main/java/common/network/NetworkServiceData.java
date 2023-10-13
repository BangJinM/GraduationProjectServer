package common.network;

import common.network.serialize.IMessageManager;

public class NetworkServiceData {
    public int port = 123456;
    public int bossGroupCount = 0;
    public int workGroupCount = 0;

    public INetworkListener lNetworkListener;
    public IMessageManager messageFactory;
}
