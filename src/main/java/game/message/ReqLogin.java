package game.message;

import common.message.AbstractMessage;
import common.network.serialize.NetMessage;

import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

public class ReqLogin extends AbstractMessage {
    private String loginName = "";
    private int sID;
    private int pid;
    private int client;
    private String idNumber;
    private String ip;
    private int version;
    private String channelId;
    private String qudao;
    private int isAdult;
    private String token;

    @Override
    public void write(ByteBufOutputStream os) throws IOException {
        os.writeUTF(loginName);
    }

    @Override
    public void read(ByteBufInputStream os) throws IOException {
        this.loginName = this.readUTF(os);
        this.sID = this.readInt(os);
        this.pid = this.readInt(os);
        this.client = this.readInt(os);
        this.idNumber = this.readUTF(os);
        this.ip = this.readUTF(os);
        this.version = this.readInt(os);
        this.channelId = this.readUTF(os);
        this.qudao = this.readUTF(os);
        this.isAdult = this.readInt(os);
        this.token = this.readUTF(os);
    }
}
