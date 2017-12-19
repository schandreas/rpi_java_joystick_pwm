package at.restental.andreas.robot;

import at.restental.andreas.joystick.JoystickEvent;
import at.restental.andreas.joystick.JoystickListener;
import at.restental.andreas.rpi_fs_pwm.PWMController;

public class RoboController implements JoystickListener {
	protected PWMController con;
	protected boolean exit_detected = false;

	public RoboController(PWMController con) {
		this.con = con;
	}

	@Override
	public void eventReceived(JoystickEvent e) {
		if (e.match(6, 1, 1))
			exit_detected = true;
		if (e.type == 2 && e.value >= 0) {
			switch (e.id) {
			case 5:
				con.setPWM(0, 0x4000, (e.value >> 8) * (e.value >> 8));
				con.setPWM(1, 0x4000, 0);
				break;
			case 1:
				con.setPWM(2, 0x4000, (e.value >> 8) * (e.value >> 8));
				con.setPWM(3, 0x4000, 0);
				break;
			default:
				break;
			}
		}
		if (e.type == 2 && e.value < 0) {
			switch (e.id) {
			case 5:
				con.setPWM(1, 0x4000, (e.value >> 8) * (e.value >> 8));
				con.setPWM(0, 0x4000, 0);
				break;
			case 1:
				con.setPWM(3, 0x4000, (e.value >> 8) * (e.value >> 8));
				con.setPWM(2, 0x4000, 0);
				break;
			default:
				break;
			}
		}
	}

	public boolean is_exit_detected() {
		return exit_detected;
	}
}
