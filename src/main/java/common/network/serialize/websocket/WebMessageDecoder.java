package common.network.serialize.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

public class WebMessageDecoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof BinaryWebSocketFrame) {
            super.channelRead(ctx, ((BinaryWebSocketFrame) msg).content());
        } else {
            super.channelRead(ctx, msg);
        }
    }
}
