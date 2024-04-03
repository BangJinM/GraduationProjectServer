package common.network.serialize;

import io.netty.buffer.ByteBuf;

public class NetMessage {
    private int messageID = -1;
    private int length = -1;
    private ByteBuf byteBuf;

    NetMessage(int messageID, int length) {
        this.messageID = messageID;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setByteBuf(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    public ByteBuf getByteBuf() {
        return this.byteBuf;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getMessageID() {
        return messageID;
    }
}