package mbj.server.protocol;

/**
 * @����
 * �����˵�¼�����е������¼�id����¼�����޸��˺��Լ����ص��¼�id����Ч���롢��Ч�˻���������󣬵�¼�ɹ���
 */
public class LoginProtocol {
    
    /*
     * Login_Area
     * **/
    public static final int Area_LoginRequest = 1; 	// ��½����
    public static final int Area_LoginResponse = 2; 	//��¼Ӧ��
    /*
     * Login_Command
     * **/
    public static final int Login_InvalidMessage = 1;	//��Ч��Ϣ
    public static final int Login_InvalidUsername = 2;	//��Ч�û���
    public static final int Login_InvalidPassword = 3;	//�������
    public static final int Login_DisConnect=4;			//�ڶ����ͷ��˵�½�Ͽ�����
    
    public static final int Login_Succeed = 10;			//��½�ɹ�
}