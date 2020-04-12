package test.lua;

import common.task.MsgDistributeTask;
import common.task.TaskHandlerContext;
import common.task.TimerDistributeTask;

public class TaskTest {
    public static void main(String[] args) throws Exception {
        MsgDistributeTask msgDistributeTask = new MsgDistributeTask();
        TaskHandlerContext.INSTANCE.initialize();
        TaskHandlerContext.INSTANCE.acceptTask(msgDistributeTask);
    }
}
