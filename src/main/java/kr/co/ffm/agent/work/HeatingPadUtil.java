package kr.co.ffm.agent.work;

import com.pi4j.io.gpio.*;

public class HeatingPadUtil {
    private static GpioController gpio = GpioFactory.getInstance();
    private static GpioPinDigitalOutput pinHeating = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(5), PinState.LOW);

    public void heating() {
        try {
            pinHeating.high();

            Thread.sleep(30000);

            pinHeating.low();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
