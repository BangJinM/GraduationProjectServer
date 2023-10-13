package common.network;

import io.netty.channel.ChannelHandlerContext;

public interface INetworkListener {
    void onChannelActive(ChannelHandlerContext ctx);

    void onUserEventTriggered(ChannelHandlerContext ctx, Object evt);

    void onRead(ChannelHandlerContext ctx, Object msg);

    void onExceptionCaught(ChannelHandlerContext ctx, Throwable cause);

    void onChannelInactive(ChannelHandlerContext ctx);
}
