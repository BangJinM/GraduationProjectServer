package common.message;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

public abstract class AbstractMessage extends IMessage {
    public void writeUTF(ByteBufOutputStream os, String str) throws IOException {
        os.writeUTF(str);
    }

    public void writeInt(ByteBufOutputStream os, int value) throws IOException {
        os.writeInt(value);
    }

    public void writeLong(ByteBufOutputStream os, long value) throws IOException {
        os.writeLong(value);
    }

    public void writeFloat(ByteBufOutputStream os, float value) throws IOException {
        os.writeFloat(value);
    }

    public void writeDouble(ByteBufOutputStream os, double value) throws IOException {
        os.writeDouble(value);
    }

    public void writeByte(ByteBufOutputStream os, byte value) throws IOException {
        os.writeByte(value);
    }

    public void writeBoolean(ByteBufOutputStream os, boolean value) throws IOException {
        os.writeBoolean(value);
    }

    public void writeBytes(ByteBufOutputStream os, byte[] value) throws IOException {
        os.write(value);
    }

    public String readUTF(ByteBufInputStream is) throws IOException {
        return is.readUTF();
    }

    public int readInt(ByteBufInputStream is) throws IOException {
        return is.readInt();
    }

    public long readLong(ByteBufInputStream is) throws IOException {
        return is.readLong();
    }

    public float readFloat(ByteBufInputStream is) throws IOException {
        return is.readFloat();
    }

    public double readDouble(ByteBufInputStream is) throws IOException {
        return is.readDouble();
    }

    public byte readByte(ByteBufInputStream is) throws IOException {
        return is.readByte();
    }

    public boolean readBoolean(ByteBufInputStream is) throws IOException {
        return is.readBoolean();
    }

    public byte[] readBytes(ByteBufInputStream is, int length) throws IOException {
        byte[] bytes = new byte[length];
        is.read(bytes);
        return bytes;
    }
}