package at.restental.andreas.rpi_fs_pwm;

import at.restental.andreas.joystick.Joystick;

public class TestMain {
	static int x = 1;
	static int right = 0;
	static int left = 0;

	public static void main(String[] args) {
		Joystick js0;
		PWMController con0;
		RoboController rb;
		try {
			con0 = new PWMController("/sys/class/pwm/pwmchip0/");
			js0 = new Joystick("/dev/input/js0");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		rb = new RoboController(con0);
		con0.InitChannel(0);
		con0.InitChannel(2);
		js0.addListener(rb);
		while (!rb.is_exit_detected()) {
		}
		js0.cleanup();
		con0.cleanup();
	}

}
