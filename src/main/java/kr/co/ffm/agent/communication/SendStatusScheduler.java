package kr.co.ffm.agent.communication;

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

    @Scheduled(initialDelay = 1000 * 15, fixedDelay = 1000 * 10)
    public void autoSendData() {
        if (!CommunicationServiceImpl.isWatertankInfoSaved) {
            communicationService.sendWatertank();
        } else {
            communicationUtil.autoStatusWatch();
        }
    }
}
