package kr.co.ffm.agent.work;

import com.pi4j.io.gpio.*;

public class RelayModuleUtil {
    private GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalOutput pinHeating = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(5), PinState.LOW);
    private GpioPinDigitalOutput pinPump = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(6), PinState.LOW);

    public void heating() {
        try {
            pinHeating.high();

            Thread.sleep(5000);

            pinHeating.low();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            gpio.shutdown();
            gpio.unprovisionPin(pinHeating);
        }
    }

    public void pump() {
        try {
            pinPump.high();

            Thread.sleep(10000);

            pinPump.low();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            gpio.shutdown();
            gpio.unprovisionPin(pinPump);
        }
    }
}
