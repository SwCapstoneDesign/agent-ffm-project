package kr.co.ffm.agent.communication;

import kr.co.ffm.agent.work.TemperatureUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SendStatusScheduler {
    @Autowired
    CommunicationService communicationService;
    @Autowired
    CommunicationUtil communicationUtil;

    private Logger logger = LogManager.getLogger(SendStatusScheduler.class);

    @Scheduled(initialDelay = 1000 * 15, fixedDelay = 1000 * 20)
    public void autoSendData() {
        if (!CommunicationServiceImpl.isWatertankInfoSaved) {
            communicationService.sendWatertank();
        } else {
            TemperatureUtil temperatureUtil = new TemperatureUtil(4);
            WatertankStatus watertankStatus = new WatertankStatus(temperatureUtil.measure(), 7.5, 22);
            communicationService.sendWatertankStatus(watertankStatus);
        }
    }
}
