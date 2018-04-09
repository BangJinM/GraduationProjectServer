package mbj.server.protocol;
/**
 * @马邦进
 * SocketModel的第一个属性 Type，它定义了总的协议的数据类型，比如登录协议，游戏协议
 */
public class TypeProtocol {

    public static final int TYPE_LOGIN = 1;

    public static final int TYPE_USER = 2;

    public static final int TYPE_WIZARD = 3;

    public static final int TYPE_BATTLE = 4;

    public static final int TYPE_HEATBEAT=5;
}