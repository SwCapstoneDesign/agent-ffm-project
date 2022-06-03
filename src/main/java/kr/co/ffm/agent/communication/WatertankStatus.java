package kr.co.ffm.agent.communication;

import lombok.Data;

import java.io.Serializable;

@Data
public class WatertankStatus implements Serializable {
    private double temperature;
    private double ph;
    private double oxygen;

    WatertankStatus(double temperature, double ph, double oxygen) {
        this.temperature = temperature;
        this.ph = ph;
        this.oxygen = oxygen;
    }
}
