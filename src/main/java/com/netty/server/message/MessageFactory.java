package com.netty.server.message;

public class MessageFactory {
    public static BaseMessage GetMessage(int messageID){
        switch (messageID){
            case MessageConstants.BusinessLogic:

            case MessageConstants.MainLogic:

        }
    if(messageID == MessageConstants.BusinessLogic)
        return new BusinessLogic();
    else
        return new MainLogic();
    }
}
