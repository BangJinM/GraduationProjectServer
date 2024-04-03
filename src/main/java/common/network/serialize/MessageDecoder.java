package common.network.serialize;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> obj)
            throws Exception {
        int length = in.readInt();
        int messageID = in.readInt();
        in.readShort();

        byte[] data = new byte[length - 10];
        in.readBytes(data);
        ByteBuf buf = Unpooled.buffer(data.length);
        buf.writeBytes(data);

        NetMessage message = new NetMessage(messageID, length);
        message.setByteBuf(buf);
        obj.add(message);
    }
}