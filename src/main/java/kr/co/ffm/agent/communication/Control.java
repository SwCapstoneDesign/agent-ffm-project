package kr.co.ffm.agent.communication;

import lombok.Data;

@Data
public class Control {
    String control;
    String action;
    String target;
    double value;
}
