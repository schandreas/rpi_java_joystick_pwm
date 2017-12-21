package at.restental.andreas.robot;

import at.restental.andreas.joystick.JoystickEvent;
import at.restental.andreas.joystick.JoystickListener;
import at.restental.andreas.rpi_fs_pwm.PWMController;

public class RoboController implements JoystickListener {
	protected PWMController con;
	protected boolean exit_detected = false;
	protected int mode;

	public RoboController(PWMController con, int mode) {
		this.con = con;
		this.mode = mode;
	}

	@Override
	public void eventReceived(JoystickEvent e) {
		if (e.match(6, 1, 1))
			exit_detected = true;
		if (e.type == 2 && e.value >= 0) {
			switch (e.id) {
			case 5:
				con.setPWM(0, 0x7fff, e.value);
				con.setPWM(1, 0x7fff, 0);
				if (mode == 4) {
					con.setPWM(2, 0x7fff, e.value);
					con.setPWM(3, 0x7fff, 0);
				}
				break;
			case 1:
				if (mode == 4) {
					con.setPWM(4, 0x7fff, e.value);
					con.setPWM(5, 0x7fff, 0);
					con.setPWM(6, 0x7fff, e.value);
					con.setPWM(7, 0x7fff, 0);
				} else {
					con.setPWM(2, 0x7fff, e.value);
					con.setPWM(3, 0x7fff, 0);
				}
				break;
			default:
				break;
			}
		}
		if (e.type == 2 && e.value < 0) {
			switch (e.id) {
			case 5:
				con.setPWM(1, 0x7fff, -1 * e.value);
				con.setPWM(0, 0x7fff, 0);
				if (mode == 4) {
					con.setPWM(3, 0x7fff, -1 * e.value);
					con.setPWM(2, 0x7fff, 0);
				}
				break;
			case 1:
				if (mode == 4) {
					con.setPWM(5, 0x7fff, -1 * e.value);
					con.setPWM(4, 0x7fff, 0);
					con.setPWM(7, 0x7fff, -1 * e.value);
					con.setPWM(6, 0x7fff, 0);
				} else {
					con.setPWM(3, 0x7fff, -1 * e.value);
					con.setPWM(2, 0x7fff, 0);
				}
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
