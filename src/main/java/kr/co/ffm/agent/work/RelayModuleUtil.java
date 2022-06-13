package kr.co.ffm.agent.work;

import com.pi4j.io.gpio.*;

public class RelayModuleUtil {
    private GpioController gpio = GpioFactory.getInstance();

    public void heating() {
        GpioPinDigitalOutput pinHeating = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(5), PinState.LOW);

        try {
            pinHeating.low();

            Thread.sleep(5000);

            pinHeating.high();
        } catch (Exception e) {
            e.printStackTrace();
        }

        gpio.shutdown();
        gpio.unprovisionPin(pinHeating);
    }

    public void pump() {
        GpioPinDigitalOutput pinPump = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(6), PinState.LOW);

        try {
            pinPump.low();

            Thread.sleep(10000);

            pinPump.high();
        } catch (Exception e) {
            e.printStackTrace();
        }

        gpio.shutdown();
        gpio.unprovisionPin(pinPump);
    }
}
