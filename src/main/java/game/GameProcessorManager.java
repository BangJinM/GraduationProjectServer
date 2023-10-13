package game;

import common.processor.BaseProcessor;
import common.processor.BaseProcessorManager;

public class GameProcessorManager extends BaseProcessorManager {

    GameProcessorManager() {
        super();
        this.registerProcessor(new BaseProcessor("登录线程", 1));
    }
}
