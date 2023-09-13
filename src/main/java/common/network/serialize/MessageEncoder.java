package common.network.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<AbstractMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractMessage message,
            ByteBuf out) throws Exception {
        byte[] content = message.encode();
        int length = content.length;
        out.writeInt(length);
        out.writeBytes(content);
    }
}