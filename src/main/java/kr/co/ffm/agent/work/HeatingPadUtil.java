package kr.co.ffm.agent.work;

import com.pi4j.io.gpio.*;

public class HeatingPadUtil {
    private static GpioController gpio = GpioFactory.getInstance();
    private static GpioPinDigitalOutput pinWarnNotice = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(5), PinState.LOW);

    public void heating() {
        try {
            pinWarnNotice.high();

            Thread.sleep(10000);

            pinWarnNotice.low();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
