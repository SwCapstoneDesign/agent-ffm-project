package kr.co.ffm.agent.communication;

import org.springframework.stereotype.Service;

@Service
public interface CommunicationService {
    public String receiveControl(Control control);
    public void sendFeeding();
    public void sendWatertankStatus();
    public void sendWatertank();
}
