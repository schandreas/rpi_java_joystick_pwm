package at.restental.andreas.robot;

import at.restental.andreas.joystick.Controller_types;
import at.restental.andreas.joystick.JoystickEvent;
import at.restental.andreas.joystick.JoystickListener;
import at.restental.andreas.rpi_fs_pwm.PWMController;

public class RoboController implements JoystickListener {
	protected PWMController con;
	protected boolean exit_detected = false;
	protected int[] left;
	protected int[] right;
	protected int mode;
	protected Controller_types con_type;

	public RoboController(PWMController con, int mode, Controller_types con_type) {
		this.con = con;
		this.mode = mode;
		left = new int[mode];
		right = new int[mode];
		for (int i = 0; i < mode; i++) {
			left[i] = i;
			right[i] = mode + i;
		}
		this.con_type = con_type;
	}

	@Override
	public void eventReceived(JoystickEvent e) {
		if (e.match(6, 1, 1))
			exit_detected = true;
		if (e.id == con_type.axis_left() && e.type == 2) {
			int j = 0;
			if(e.value < 0) {
				j = 1;
				e.value *= -1;
			}
			for(int i = 0; i < mode; i ++) {
				if(i % 2 == j)con.setPWM(left[i], 0x7fff, e.value);
				else con.setPWM(left[i], 0x7fff, 0);
			}
		}
		if (e.id == con_type.axis_right() && e.type == 2) {
			int j = 0;
			if(e.value < 0) {
				j = 1;
				e.value *= -1;
			}
			for(int i = 0; i < mode; i ++) {
				if(i % 2 == j)con.setPWM(right[i], 0x7fff, e.value);
				else con.setPWM(right[i], 0x7fff, 0);

			}
		}
	}

	public boolean is_exit_detected() {
		return exit_detected;
	}
}
