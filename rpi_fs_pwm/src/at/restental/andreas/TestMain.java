package at.restental.andreas;

import at.restental.andreas.distance_sensor.DistanceSensor;
import at.restental.andreas.joystick.Joystick;
import at.restental.andreas.robot.RoboController;
import at.restental.andreas.rpi_fs_pwm.PWMController;

public class TestMain {

	public static void main(String[] args) {
		Joystick js0;
		PWMController con0;
		RoboController rb;
		DistanceSensor ds1 = new DistanceSensor(16, 19, 3000);
		DistanceSensor ds2 = new DistanceSensor(20, 21, 3000);
		int motors = 0;
		try {
			motors = new Integer(args[0]);
		} catch (Exception e) {

		}
		try {
			con0 = new PWMController("/sys/class/pwm/pwmchip0/");
			js0 = new Joystick("/dev/input/js0");
		} catch (Exception e) {
			System.out.println("Error creating Joystick/PWM Object");
			e.printStackTrace();
			return;
		}
		if (motors > 0) {
			rb = new RoboController(con0, motors, js0.getControllerType());
			for (int i = 0; i < 2 * motors; i++)
				con0.InitChannel(i);
		} else {
			rb = new RoboController(con0, 2, js0.getControllerType());
			for (int i = 0; i < 4; i++)
				con0.InitChannel(i);
		}
		js0.addListener(rb);
		ds1.attachListener(rb);
		ds2.attachListener(rb);	
		js0.setColor(128, 32, 0);
		while (!rb.is_exit_detected()) {
		}
		ds1.cleanup();
		ds2.cleanup();
		js0.setColor(0, 1, 0);
		js0.cleanup();
		con0.cleanup();
	}

}
