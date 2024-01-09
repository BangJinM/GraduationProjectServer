package common.network.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {
    IMessageManager messageFactory;

    public MessageDecoder(IMessageManager messageFactory) {
        this.messageFactory = messageFactory;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
            List<Object> obj) throws Exception {
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        AbstractMessage message = this.messageFactory.GetMessage();
        message.setLength(data.length);
        message.setBytes(data);
        message.decode();
        obj.add(message);
    }
}