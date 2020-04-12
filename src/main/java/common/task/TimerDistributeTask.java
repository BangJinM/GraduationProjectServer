package common.task;

public class TimerDistributeTask extends AbstractDistributeTask {
    @Override
    public void action() {

    }

    @Override
    public void actionSuccess() {
        TaskHandlerContext.INSTANCE.acceptTask(new TimerDistributeTask());
    }
}
