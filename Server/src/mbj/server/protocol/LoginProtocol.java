package mbj.server.protocol;

/**
 * @马邦进
 * 定义了登录过程中的所有事件id，登录请求、修改账号以及返回的事件id，无效密码、无效账户，密码错误，登录成功等
 */
public class LoginProtocol {
    //---------------------Area---------------------------//
    public static final int RegisterRequest = 5; //请求注册
    public static final int LoginRequest = 1;     // 请求登陆


    

    //---------------------登录---------------------------//

    public static final int Login_InvalidMessage = 1;//无效消息
    public static final int Login_InvalidUsername = 2;//无效用户名
    public static final int Login_InvalidPassword = 3;//密码错误
    public static final int Login_DisConnect = 4;      //第二个客服端登陆断开连接
    public static final int Login_Succeed = 10;//登陆成功

    //---------------------注册---------------------------//
    
    public static final int RegisterFail = 6;//注册失败
    public static final int RegisterSuccess = 7;//注册成功
    public static final int Register_InvalidUsername = 8;

}