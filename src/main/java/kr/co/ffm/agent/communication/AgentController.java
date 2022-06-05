package kr.co.ffm.agent.communication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.ffm.agent.work.MotorUtil;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveWatertankControl(@RequestBody Control control) {
        Gson response = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", "200");
        jsonObject.addProperty("message", "null");
        jsonObject.addProperty("action", control.getAction());
        jsonObject.addProperty("target", control.getTarget());
        jsonObject.addProperty("value", control.getValue());

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
