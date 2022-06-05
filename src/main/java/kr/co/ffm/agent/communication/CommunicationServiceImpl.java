package kr.co.ffm.agent.communication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Service
public class CommunicationServiceImpl implements CommunicationService {
    @Autowired
    private CommunicationUtil communicationUtil;

    public static boolean isWatertankInfoSaved = false;
    private static Properties watertankInfo;
    private static Properties systemInfo;

    private Logger logger = LogManager.getLogger(CommunicationServiceImpl.class);

    static {
        String watertankPath = "properties/watertank.properties";
        String systemPath = "properties/system.properties";

        watertankInfo = new Properties();
        systemInfo = new Properties();

        try {
            watertankInfo.load(Resources.getResourceAsStream(watertankPath));
            systemInfo.load(Resources.getResourceAsStream(systemPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String receiveControl(Control control) {
        return control.toString();
    }

    @Override
    public void sendFeeding() {

    }

    @Override
    public void sendWatertankStatus(WatertankStatus watertankStatus) {
        String watertankId = CommunicationServiceImpl.watertankInfo.getProperty("id");
        String url = "http://" + systemInfo.getProperty("system.ipaddress") + "/status";

        Gson statusInfo = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("watertankId", watertankId);
        jsonObject.addProperty("farmedFishNo", watertankInfo.getProperty("farmedFishNo"));
        jsonObject.addProperty("temperature", watertankStatus.getTemperature());
        jsonObject.addProperty("ph", watertankStatus.getPh());
        jsonObject.addProperty("oxygen", watertankStatus.getOxygen());

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), statusInfo.toJson(jsonObject));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Map<String, String> responseParse = communicationUtil.parseResponseCode(response.body().string());

            if ("200".equals(responseParse.get("code"))) {
                logger.info("");
                logger.info("====================================================");
                logger.info("");
                logger.info("                Send Watertank Status");
                logger.info("                watertankId : " + watertankId);
                logger.info("                temperature : " + watertankStatus.getTemperature());
                logger.info("                ph : " + watertankStatus.getPh());
                logger.info("                oxygen : " + watertankStatus.getOxygen());
                logger.info("");
                logger.info("====================================================");
            } else {
                logger.error(responseParse.get("message"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("IOException Occurred in method sendWatertankStatus");
        }
    }

    @Override
    public void sendWatertank() {
        String url = "http://" + systemInfo.getProperty("system.ipaddress") + "/watertank/info";

        Gson info = new Gson();
        JsonObject jsonObject = new JsonObject();

        Set<Object> list = watertankInfo.keySet();
        for (Object object : list) {
            jsonObject.addProperty(object.toString(), watertankInfo.getProperty(object.toString()));
        }

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), info.toJson(jsonObject));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Map<String, String> responseParse = communicationUtil.parseResponseCode(response.body().string());

            if ("200".equals(responseParse.get("code"))) {
                logger.info("");
                logger.info("====================================================");
                logger.info("");
                logger.info("              Send Watertank Information");
                logger.info("");
                logger.info("====================================================");

                isWatertankInfoSaved = true;
            } else {
                logger.error(responseParse.get("message"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
