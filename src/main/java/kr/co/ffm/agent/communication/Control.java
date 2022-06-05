package kr.co.ffm.agent.communication;

import lombok.Data;

import java.io.Serializable;

@Data
public class Control implements Serializable {
    String control;
    String action;
    String target;
    double value;

    @Override
    public String toString() {
        return "Control{" +
                "control='" + control + '\'' +
                ", action='" + action + '\'' +
                ", target='" + target + '\'' +
                ", value=" + value +
                '}';
    }
}
