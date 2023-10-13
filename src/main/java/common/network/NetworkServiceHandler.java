package common.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class NetworkServiceHandler extends ChannelInboundHandlerAdapter {
    private final static Logger logger = Logger.getLogger(NetworkServiceHandler.class);

    private INetworkListener networkListener;

    public NetworkServiceHandler(INetworkListener listener) {
        this.networkListener = listener;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.networkListener.onRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.networkListener.onChannelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.networkListener.onChannelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        this.networkListener.onUserEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        this.networkListener.onExceptionCaught(ctx, cause);
    }
}
