package common;

import game.login.LoginController;

public class MessageDispatcherManager {
    private static MessageDispatcher messageDispatcher;
    
    public static MessageDispatcher getInstance(){
        if(messageDispatcher == null)
        {
            messageDispatcher = new MessageDispatcher();
        }
        return messageDispatcher;
    }

    private MessageDispatcherManager(){

    }

    public void init(){
        LoginController loginController = new LoginController();
        messageDispatcher.registerController(loginController);
    }
}
