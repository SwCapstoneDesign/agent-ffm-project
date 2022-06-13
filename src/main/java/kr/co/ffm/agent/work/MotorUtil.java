package kr.co.ffm.agent.work;

import com.pi4j.io.gpio.*;
import kr.co.ffm.agent.communication.CommunicationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MotorUtil {
    private int stepDuration = 10;
    private static PinState LOW = PinState.LOW;
    private static PinState HIGH = PinState.HIGH;

    private Logger logger = LogManager.getLogger(MotorUtil.class);
    private GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalOutput[] motorPins = new GpioPinDigitalOutput[4];

    static PinState motorSequence[][] = new PinState[][] {
            { LOW, LOW, LOW, HIGH },
            { LOW, LOW, HIGH, LOW },
            { LOW, HIGH, LOW, LOW },
            { HIGH, LOW, LOW, LOW },
            { LOW, LOW, LOW, HIGH },
            { LOW, LOW, HIGH, LOW },
            { LOW, HIGH, LOW, LOW },
            { HIGH, LOW, LOW, LOW }
    };

    public void move () {
        motorPins[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Pin A", LOW);
        motorPins[1] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pin B", LOW);
        motorPins[2] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pin C", LOW);
        motorPins[3] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Pin D", LOW);

        try {
            logger.info("Move Motor");
            System.out.println("Start");
            foldAction();

            Thread.sleep(3000);

            System.out.println("Reverse");
            unfoldAction();

            System.out.println("Stop");
        } catch (Exception e) {
            e.printStackTrace();
        }

        gpio.shutdown();
        gpio.unprovisionPin(motorPins);
    }

    private void writeSequence(int sequenceNo) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            motorPins[i].setState(motorSequence[sequenceNo][i]);
        }
        Thread.sleep(stepDuration);
    }

    private void step(int noOfSteps) throws InterruptedException {
        if (noOfSteps > 0) {
            for (int currentStep = noOfSteps; currentStep > 0; currentStep--) {
                int currentSequenceNo = currentStep % 8;

                writeSequence(currentSequenceNo);
            }
        } else {
            for (int currentStep = 0; currentStep < Math.abs(noOfSteps); currentStep++) {
                int currentSequenceNo = currentStep % 8;

                writeSequence(currentSequenceNo);
            }
        }
    }

    private void foldAction() throws InterruptedException {
        int steps;
        steps = (356 * 4 * 200) / 360;

        step(steps);
    }

    private void unfoldAction() throws InterruptedException {
        int steps;
        steps = (356 * 4 * -200) / 360;

        step(steps);
    }
}
