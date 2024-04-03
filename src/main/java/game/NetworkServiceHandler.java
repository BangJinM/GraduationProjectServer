package game;

import common.network.Session;
import common.network.serialize.ChannelAttributeKey;
import common.network.serialize.NetMessage;

import org.apache.log4j.Logger;

import game.message.ReqLogin;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class NetworkServiceHandler extends ChannelInboundHandlerAdapter {
    private final static Logger logger = Logger.getLogger(NetworkServiceHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NetMessage message = (NetMessage) msg;
        if (message.getMessageID() == 1034) {
            ReqLogin login = new ReqLogin();

            ByteBufInputStream bis = new ByteBufInputStream(message.getByteBuf());
            login.read(bis);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        Session session = (Session) channel.attr(ChannelAttributeKey.SESSION).get();
        if (session == null) {
            session = new Session();
            session.setChannel(channel);
            channel.attr(ChannelAttributeKey.SESSION).set(session);
        }
        logger.info("onChannelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        logger.info("断开连接：" + channel.toString());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Channel channel = ctx.channel();
        IdleState state;
        if ((evt instanceof IdleStateEvent)
                && (state = ((IdleStateEvent) evt).state()) == IdleState.READER_IDLE) {
            logger.warn("连接闲置，主动断开连接,channel:" + channel + " state:" + state);
            ctx.close();
            // Session session = (Session) channel.attr(ChannelAttributeKey.SESSION).get();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("发生错误:" + ctx.channel() + " " + cause.getMessage());
    }
}
