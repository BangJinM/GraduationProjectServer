package common.network;

import common.network.serialize.IMessageManager;

public class NetworkServiceData {
    public int port = 0;
    public int bossGroupCount = 0;
    public int workGroupCount = 0;

    /**
     * web平台
     */
    public boolean isWeb = true;

    public INetworkListener lNetworkListener;
    public IMessageManager messageFactory;
}
