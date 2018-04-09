package mbj.server;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import mbj.server.codec.LengthDecoder;
import mbj.server.codec.MessageDecoder;
import mbj.server.codec.MessageEncoder;
import mbj.server.handler.HeatBeatHandler;
import mbj.server.handler.ServerHandler;

/**
 * @马邦进
 * 程序入口，开启服务
 */
public class GameServer {
	/**
	 * 开启服务
	 */
	public void bind(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();// 线程组
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();// server启动管理配置
			b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)// 最大客户端连接数为1024
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new HeatBeatHandler(10, 0, 0, TimeUnit.SECONDS));// 实现心跳检测
							ch.pipeline().addLast(new LengthDecoder(1024, 0, 4, 0, 4));
							ch.pipeline().addLast(new MessageEncoder());
							ch.pipeline().addLast(new MessageDecoder());
							ch.pipeline().addLast(new ServerHandler());
						}
					});
			ChannelFuture f = b.bind(port).sync();
			if (f.isSuccess()) {
				System.out.println("Server starts success at port:" + port);
			}
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8080;
		if (args.length != 0)
			port = Integer.parseInt(args[0]);
		new GameServer().bind(port);

	}

}