package game.login;

import common.BaseController;
import common.IdSession;
import common.SocketModel;

public class LoginController extends BaseController {
    public LoginController() {
        super(1);
    }

    @Override
    public void receivedMsg(IdSession session, SocketModel req) {
        System.out.println("receivedMsg" + req.getMessage());
    }
}
