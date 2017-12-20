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
		LEDControll leds = new LEDControll();
		try {
			con0 = new PWMController("/sys/class/pwm/pwmchip0/");
			js0 = new Joystick("/dev/input/js0");
		} catch (Exception e) {
			System.out.println("Error creating Joystick/PWM Object");
			e.printStackTrace();
			return;
		}
		rb = new RoboController(con0);
		con0.InitChannel(0);
		con0.InitChannel(1);
		con0.InitChannel(2);
		con0.InitChannel(3);
		js0.addListener(rb);
		leds.setColor(0, 128, 0);
		while (!rb.is_exit_detected()) {
		}
		leds.setColor(1, 1, 0);
		js0.cleanup();
		con0.cleanup();
	}

}
