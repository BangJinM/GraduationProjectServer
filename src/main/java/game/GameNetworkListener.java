package game;

import com.mysql.cj.util.TimeUtil;

import common.network.INetworkListener;
import common.network.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class GameNetworkListener implements INetworkListener {
    GameNoticeManager nGameNoticeManager;
    GameProcessorManager gameProcessorManager;

    public GameNetworkListener(GameNoticeManager nGameNoticeManager, GameProcessorManager gameProcessorManager) {
        this.nGameNoticeManager = nGameNoticeManager;
        this.gameProcessorManager = gameProcessorManager;
    }

    @Override
    public void onChannelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        Session session = (Session) channel.attr(AttributeKey.newInstance("SESSION")).get();
        if (session == null) {
            session = new Session();
            session.setChannel(channel);
            channel.attr(AttributeKey.newInstance("SESSION")).set(session);
            return;
        }
    }

    @Override
    public void onUserEventTriggered(ChannelHandlerContext ctx, Object evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onUserEventTriggered'");
    }

    @Override
    public void onRead(ChannelHandlerContext ctx, Object msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onRead'");
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
