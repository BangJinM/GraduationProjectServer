package common;

public abstract class BaseController {
    public int type;
    public BaseController(int type){
        this.type = type;
    }
    public abstract void receivedMsg(IdSession session, SocketModel req);
}