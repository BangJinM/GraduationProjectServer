package common.processor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BaseProcessor {
    private Executor executor;
    private int processorID;

    BaseProcessor(String nameString, int id) {
        this.processorID = id;
        this.executor = Executors.newSingleThreadExecutor(r -> {
            return new Thread(r, nameString);
        });
    }

    public int getProcessorID() {
        return this.processorID;
    }

    void execute(Runnable command) {
        if (this.executor == null)
            return;

        this.executor.execute(command);
    }
}
