package kr.co.ffm.agent.communication;

import lombok.Data;

import java.io.Serializable;

@Data
public class WatertankStatus implements Serializable {
    private int no;
    private String watertankId;
    private double temperature;
    private double ph;
    private double oxygen;
    private String measureTime;

    WatertankStatus(double temperature, double ph, double oxygen) {
        this.temperature = temperature;
        this.ph = ph;
        this.oxygen = oxygen;
    }
}
