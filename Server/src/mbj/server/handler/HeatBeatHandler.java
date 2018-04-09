package mbj.server.handler;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public class HeatBeatHandler extends IdleStateHandler {

	public HeatBeatHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds,
			TimeUnit seconds) {
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds, seconds);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("已经5秒未收到客户端的消息了！");
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE) {
				HandlerManager.GetIntance().RemoveChannelHandlerContext(ctx);
				System.out.println("关闭这个不活跃通道！");
				ctx.close();
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

	@Override
	protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
		// TODO Auto-generated method stub
		userEventTriggered(ctx, evt);
	}
}
