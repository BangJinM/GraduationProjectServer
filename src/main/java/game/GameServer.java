package game;

import common.network.NetworkService;
import common.network.NetworkServiceData;

public class GameServer {
    public void start() {
        NetworkServiceData serviceData = new NetworkServiceData();
        GameNoticeManager gameNoticeManager = new GameNoticeManager();
        GameProcessorManager gameProcessorManager = new GameProcessorManager();

        serviceData.lNetworkListener = new GameNetworkListener(gameNoticeManager, gameProcessorManager);
        serviceData.port = 12345;
        try {
            NetworkService ns = new NetworkService();
            ns.bind(serviceData);
            ns.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
