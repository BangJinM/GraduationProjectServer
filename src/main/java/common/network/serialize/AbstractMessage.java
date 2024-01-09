package common.network.serialize;

public abstract class AbstractMessage {
    private int msgID = -1;
    private int length = -1;
    private byte[] bytes;

    public int getMsgID() {
        return msgID;
    }

    public void setMsgID(int msgID) {
        this.msgID = msgID;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public abstract byte[] encode();

    public abstract void decode();
}