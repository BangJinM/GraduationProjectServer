package common.network;

public interface INetworkListener {
    void onChannelActive();

    void onUserEventTriggered();

    void onRead();

    void onExceptionCaught();

    void onChannelInactive();
}
