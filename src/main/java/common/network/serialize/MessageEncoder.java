package common.network.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<NetMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, NetMessage message, ByteBuf out)
            throws Exception {
        ByteBuf buf = message.getByteBuf();
        byte[] content = buf.array();
        int length = content.length;
        out.writeInt(message.getMessageID());
        out.writeInt(length);
        out.writeBytes(content);
    }
}