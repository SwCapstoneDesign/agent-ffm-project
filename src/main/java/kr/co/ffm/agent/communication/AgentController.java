package kr.co.ffm.agent.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentController {
    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private CommunicationUtil communicationUtil;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveWatertankControl(Control control) {
        String code  = communicationService.receiveControl(control);

        communicationUtil.activeStatusWatch();

        return code;
    }
}
