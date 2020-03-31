package com.netty.server.netty;

import com.netty.server.core.CoderUtil;
import com.netty.server.core.SocketModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class MessageEncoder extends MessageToByteEncoder<SocketModel> {
    private Schema<SocketModel> schema = RuntimeSchema.getSchema(SocketModel.class);
    @Override
    protected void encode(ChannelHandlerContext ctx, SocketModel message,
                          ByteBuf out) throws Exception {
        System.out.println("encode");
        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        byte[] data = ProtobufIOUtil.toByteArray(message, schema, buffer);
        ByteBuf buf = Unpooled.copiedBuffer(CoderUtil.intToBytes(data.length),data);//前四个字节为长度字节
        out.writeBytes(buf);
        System.out.println("MessageEncoder Encode");
    }
}