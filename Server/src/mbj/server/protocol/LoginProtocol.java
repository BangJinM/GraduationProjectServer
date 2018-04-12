package mbj.server.protocol;

/**
 * @����
 * �����˵�¼�����е������¼�id����¼�����޸��˺��Լ����ص��¼�id����Ч���롢��Ч�˻���������󣬵�¼�ɹ���
 */
public class LoginProtocol {
    //---------------------Area---------------------------//
    public static final int RegisterRequest = 5; //����ע��
    public static final int LoginRequest = 1;     // �����½


    

    //---------------------��¼---------------------------//

    public static final int Login_InvalidMessage = 1;//��Ч��Ϣ
    public static final int Login_InvalidUsername = 2;//��Ч�û���
    public static final int Login_InvalidPassword = 3;//�������
    public static final int Login_DisConnect = 4;      //�ڶ����ͷ��˵�½�Ͽ�����
    public static final int Login_Succeed = 10;//��½�ɹ�

    //---------------------ע��---------------------------//
    
    public static final int RegisterFail = 6;//ע��ʧ��
    public static final int RegisterSuccess = 7;//ע��ɹ�
    public static final int Register_InvalidUsername = 8;

}