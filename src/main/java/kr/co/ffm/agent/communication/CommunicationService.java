package kr.co.ffm.agent.communication;

import org.springframework.stereotype.Service;

@Service
public interface CommunicationService {
    public Control receiveControl(Control control);
    public void sendFeeding();
    public void sendWatertankStatus(WatertankStatus watertankStatus);
    public void sendWatertank();
}
