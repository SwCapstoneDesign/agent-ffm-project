package kr.co.ffm.agent.communication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work")
public class AgentController {
    public String receiveWatertankControl() {
        return null;
    }
}
