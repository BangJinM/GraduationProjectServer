package common;

import java.util.HashMap;
import java.util.Map;

public class MessageDispatcher{
    public Map<Integer, BaseController> baseControllerMap;
    public MessageDispatcher(){
        baseControllerMap = new HashMap<>();
    }

    private void registerController(BaseController bc){
        if(baseControllerMap.containsKey(bc.type))
            baseControllerMap.put(bc.type, bc);
        else
            baseControllerMap.replace(bc.type, bc);
    }

    public void dispatch(){

    }

}