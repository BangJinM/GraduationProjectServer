package common;

import java.util.HashMap;
import java.util.Map;

public class MessageDispatcher{
    private Map<Integer, BaseController> baseControllerMap;
    public MessageDispatcher(){
        baseControllerMap = new HashMap<>();
    }

    public void registerController(BaseController bc){
        if(baseControllerMap.containsKey(bc.type))
            baseControllerMap.put(bc.type, bc);
        else
            baseControllerMap.replace(bc.type, bc);
    }

    public void dispatch(IdSession idSession, SocketModel socketModel){
        BaseController baseController = baseControllerMap.get(socketModel.getArea());
        baseController.receivedMsg(idSession, socketModel);
    }

}