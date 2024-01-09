package game;

import org.apache.log4j.Logger;

import common.network.INetworkListener;
import common.network.Session;
import common.network.serialize.AbstractMessage;
import common.network.serialize.ChannelAttributeKey;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class GameNetworkListener implements INetworkListener {
    private final static Logger logger = Logger.getLogger(GameNetworkListener.class);
    GameNoticeManager nGameNoticeManager;
    GameProcessorManager gameProcessorManager;

    public GameNetworkListener(GameNoticeManager nGameNoticeManager, GameProcessorManager gameProcessorManager) {
        this.nGameNoticeManager = nGameNoticeManager;
        this.gameProcessorManager = gameProcessorManager;
    }

    @Override
    public void onChannelActive(ChannelHandlerContext ctx) {
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
    public void onUserEventTriggered(ChannelHandlerContext ctx, Object evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onUserEventTriggered'");
    }

    @Override
    public void onRead(ChannelHandlerContext ctx, Object msg) {
        Session session = (Session) ctx.channel().attr(ChannelAttributeKey.SESSION).get();
        if (session != null)
            return;
        AbstractMessage absMsg = (AbstractMessage) msg;
    }

    @Override
    public void onExceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onExceptionCaught'");
    }

    @Override
    public void onChannelInactive(ChannelHandlerContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChannelInactive'");
    }
}
