package mbj.server.protocol;

/**
 * @马邦进
 * 定义了登录过程中的所有事件id，登录请求、修改账号以及返回的事件id，无效密码、无效账户，密码错误，登录成功等
 */
public class LoginProtocol {
    
    /*
     * Login_Area
     * **/
    public static final int Area_LoginRequest = 1; 	// 登陆请求
    public static final int Area_LoginResponse = 2; 	//登录应答
    /*
     * Login_Command
     * **/
    public static final int Login_InvalidMessage = 1;	//无效消息
    public static final int Login_InvalidUsername = 2;	//无效用户名
    public static final int Login_InvalidPassword = 3;	//密码错误
    public static final int Login_DisConnect=4;			//第二个客服端登陆断开连接
    
    public static final int Login_Succeed = 10;			//登陆成功
}