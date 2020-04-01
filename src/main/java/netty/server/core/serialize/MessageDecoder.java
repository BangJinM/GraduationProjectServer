package netty.server.core.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import common.SocketModel;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {
    private Schema<SocketModel> schema = RuntimeSchema.getSchema(SocketModel.class);
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> obj) throws Exception {
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        SocketModel message = new SocketModel();
        ProtostuffIOUtil.mergeFrom(data, message, schema);
        obj.add(message);
    }

}