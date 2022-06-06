package kr.co.ffm.agent.communication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.ffm.agent.work.MotorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
public class AgentController {
    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private CommunicationUtil communicationUtil;

    private Logger logger = LogManager.getLogger(AgentController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveWatertankControl(@RequestBody Control control) {
        logger.info("서버로 부터 온 control :" + control.toString());

        Control responseControl = communicationService.receiveControl(control);

        Gson response = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", "200");
        jsonObject.addProperty("message", "null");
        jsonObject.addProperty("action", responseControl.getAction());
        jsonObject.addProperty("target", responseControl.getTarget());
        jsonObject.addProperty("value", responseControl.getValue());

        return response.toJson(jsonObject);
    }

    @GetMapping()
    public String receiveFeedingControl() {
        MotorUtil motorUtil = new MotorUtil();
        motorUtil.move();

        Gson response = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", "200");
        jsonObject.addProperty("message", "null");

        return response.toJson(jsonObject);
    }

}
