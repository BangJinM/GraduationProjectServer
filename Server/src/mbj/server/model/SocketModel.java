package mbj.server.model;

public class SocketModel {
    private int type=-1;
    private int area=-1;
    private int command=-1;
    private String message;
 
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }
    public int getCommand() {
        return command;
    }
    public void setCommand(int command) {
        this.command = command;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}