package common.processor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class BaseProcessorManager {
    private final static Logger logger = Logger.getLogger(BaseProcessorManager.class);

    protected Map<Integer, BaseProcessor> processors;

    public BaseProcessorManager() {
        processors = new HashMap();
    }

    public void registerProcessor(BaseProcessor processor) {
        this.processors.put(processor.getProcessorID(), processor);
    }

    public BaseProcessor getProcessor(int processorID) {
        return this.processors.get(processorID);
    }

    public void execute(int processorID, Runnable command) {
        this.processors.get(processorID).execute(command);
    }
}
