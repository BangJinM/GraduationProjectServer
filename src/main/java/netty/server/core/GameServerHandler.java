package netty.server.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import netty.server.core.entity.IdSession;
import netty.server.core.entity.NettySession;
import netty.server.core.utils.ChannelUtils;
import org.apache.log4j.Logger;

public class GameServerHandler extends ChannelInboundHandlerAdapter {
    private final static Logger logger = Logger.getLogger(GameServerHandler.class);
    public HandlersManager handlersManager = HandlersManager.GetIntance();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final Channel channel = ctx.channel();
        IdSession userSession = ChannelUtils.getSessionBy(channel);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (!ChannelUtils.addChannelSession(ctx.channel(), new NettySession(channel))) {
            ctx.channel().close();
            logger.error(String.format("Duplicate session,IP=[{}]", ChannelUtils.getIp(channel)));
        }
        IdSession userSession = ChannelUtils.getSessionBy(channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final Channel channel = ctx.channel();
        IdSession userSession = ChannelUtils.getSessionBy(channel);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        logger.info("已经5秒未收到客户端的消息了！");
        if (evt instanceof IdleStateEvent) {
            ctx.close();
        }
    }
}
