package kr.co.ffm.agent.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.Map;

@Service
public class CommunicationUtil {
    private Logger logger = LogManager.getLogger(CommunicationUtil.class);

    public void temperatureMeasure() {
    }

    public void phMeasure() {
    }

    public void oxygenMeasure() {
    }

    public void autoStatusWatch() {
        logger.info("Send WatertankStatus!");
    }

    public void activeStatusWatch() {
    }

    public Map<String, String> parseResponseCode(String response) {
        Map<String, String> responseParse = new Hashtable<String, String>();

        responseParse.put("code", response.split(":")[1].split("\"")[1]);
        responseParse.put("message", response.split(":")[2].split("\"")[1]);

        return responseParse;
    }
}
