package common.task;

public class MsgDistributeTask extends AbstractDistributeTask {
    public MsgDistributeTask(){
        this.distributeKey = 1;
    }
    @Override
    public void action() {
        System.out.println("public void action()" );
    }
}
