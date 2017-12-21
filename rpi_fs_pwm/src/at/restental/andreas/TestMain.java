package at.restental.andreas;

import at.restental.andreas.joystick.Joystick;
import at.restental.andreas.joystick.Dualshock4.LEDControll;
import at.restental.andreas.robot.RoboController;
import at.restental.andreas.rpi_fs_pwm.PWMController;

public class TestMain {

	public static void main(String[] args) {
		Joystick js0;
		PWMController con0;
		RoboController rb;
		int motors = 0;
		LEDControll leds = new LEDControll();
		
		
		try {
			con0 = new PWMController("/sys/class/pwm/pwmchip0/");
			js0 = new Joystick("/dev/input/js0");
		} catch (Exception e) {
			System.out.println("Error creating Joystick/PWM Object");
			e.printStackTrace();
			return;
		}
		if (motors > 0) {
			rb = new RoboController(con0, motors);
			for (int i = 0; i < 2 * motors; i++)
				con0.InitChannel(i);
		} else {
			rb = new RoboController(con0, 2);
			for (int i = 0; i < 4; i++)
				con0.InitChannel(i);
		}
		js0.addListener(rb);
		leds.setColor(0, 128, 64);
		while (!rb.is_exit_detected()) {

		}
		leds.setColor(0, 0, 1);
		js0.cleanup();
		con0.cleanup();
	}

}
