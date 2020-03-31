package netty.server.core.entity;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class NettySession implements IdSession {

    /** 网络连接channel */
    private Channel channel;

    /** 拓展用，保存一些个人数据  */
    private Map<String, Object> attrs = new HashMap<>();

    public NettySession(Channel channel) {
        super();
        this.channel = channel;
    }

    @Override
    public void sendPacket(SocketModel packet) {
        channel.writeAndFlush(packet);
    }

    @Override
    public long getOwnerId() {
        if (attrs.containsKey(ID)) {
            return (long) attrs.get(ID);
        }
        return 0;
    }

    @Override
    public Object setAttribute(String key, Object value) {
        attrs.put(key.toString(), value);
        return value;
    }

    @Override
    public Object getAttribute(String key) {
        return attrs.get(key.toString());
    }

}
