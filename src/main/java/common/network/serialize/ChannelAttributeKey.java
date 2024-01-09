package common.network.serialize;

import common.network.Session;
import io.netty.util.AttributeKey;

public class ChannelAttributeKey {
    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("SESSION");
}