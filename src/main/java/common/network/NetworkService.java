package common.network;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.mysql.cj.protocol.a.NativeServerSession;

import common.network.serialize.MessageDecoder;
import common.network.serialize.MessageEncoder;
import common.network.serialize.websocket.WebMessageDecoder;
import common.network.serialize.websocket.WebMessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;

public class NetworkService {
    private final static Logger logger = Logger.getLogger(NetworkService.class);

    NetworkServiceData networkServiceData;

    ServerBootstrap bootstrap;
    EventLoopGroup bossGroup;
    EventLoopGroup workGroup;

    public NetworkService() {
    }

    public void start() {
        ChannelFuture f;
        try {
            f = this.bootstrap.bind(this.networkServiceData.port).sync();

            if (f.isSuccess()) {
                System.out.println("Server starts success at port:" + this.networkServiceData.port);
            }
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.stop();
        }
    }

    public void stop() {
        Future<?> bf = this.bossGroup.shutdownGracefully();
        Future<?> wf = this.workGroup.shutdownGracefully();

        try {
            bf.get(5000, TimeUnit.MILLISECONDS);
            wf.get(5000, TimeUnit.MILLISECONDS);
        } catch (Exception var4) {
            logger.info("Netty服务器关闭失败", (Throwable) var4);
        }
        logger.info("Netty Server on port:" + Integer.valueOf(this.networkServiceData.port) + " is closed");
    }

    public void bind(NetworkServiceData dServiceData) throws Exception {
        this.networkServiceData = dServiceData;

        this.bossGroup = new NioEventLoopGroup(this.networkServiceData.bossGroupCount);
        this.workGroup = new NioEventLoopGroup(this.networkServiceData.workGroupCount);
        this.bootstrap = new ServerBootstrap();
        this.bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

                        if (dServiceData.isWeb) {
                            pipeline.addLast("HttpServerCodec", new HttpServerCodec());
                            pipeline.addLast("HttpObjectAggregator",
                                    new HttpObjectAggregator(NativeServerSession.CLIENT_MULTI_STATEMENTS));
                            pipeline.addLast("WebSocketServerProtocolHandler",
                                    new WebSocketServerProtocolHandler("/", true));
                            pipeline.addLast("WebMessageDecoder", new WebMessageDecoder());
                            pipeline.addLast("WebMessageEncoder", new WebMessageEncoder());
                        }

                        pipeline.addLast("IdleStateHandler", new IdleStateHandler(10, 10, 10));
                        pipeline.addLast("LengthFieldBasedFrameDecoder",
                                new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        pipeline.addLast("MessageDecoder", new MessageDecoder(dServiceData.messageFactory));
                        pipeline.addLast("MessageEncoder", new MessageEncoder());
                        pipeline.addLast("NetworkServiceHandler",
                                new NetworkServiceHandler(dServiceData.lNetworkListener));
                    }
                });
    }
}
