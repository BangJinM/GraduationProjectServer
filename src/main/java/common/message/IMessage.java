package common.message;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;


public abstract class IMessage {
    public abstract void write(ByteBufOutputStream os) throws IOException;

    public abstract void read(ByteBufInputStream os) throws IOException;
}
