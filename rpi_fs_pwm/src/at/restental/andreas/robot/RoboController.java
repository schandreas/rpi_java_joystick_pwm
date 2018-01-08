package at.restental.andreas.robot;

import at.restental.andreas.distance_sensor.DistanceSensor;
import at.restental.andreas.distance_sensor.DistanceSensorEvent;
import at.restental.andreas.distance_sensor.DistanceSensorListener;
import at.restental.andreas.rpi_fs_pwm.PWMController;

public class RoboController implements DistanceSensorListener {
	protected PWMController con;
	private static final int period = 10000;
	protected boolean exit_detected = false;
	protected int[] left;
	protected int[] right;
	protected int mode;
	protected DistanceSensor prev;

	/**
	 * Constructor for RoboController
	 * 
	 * @param con
	 *            PWMController Object that will be used
	 * @param mode
	 *            specifies if the RoboController is in 2 or 4 motor mode
	 * @param con_type
	 *            the type of joystick that is going to be used. Specifies the
	 *            bindings for the axies
	 */
	public RoboController(PWMController con, int mode) {
		this.con = con;
		this.mode = mode;
		left = new int[mode];
		right = new int[mode];
		for (int i = 0; i < mode; i++) {
			left[i] = i;
			right[i] = mode + i;
		}
	}

	/*
	 * 
	 */
	public void drive(int direction_left, int direction_right) {
		
		direction_left = (direction_left - 90) % 361;
		direction_right = (direction_right - 90) % 361;
		
		//System.out.println("Received " + direction_left + " " + direction_right);
		
		if (direction_left > 15) {
			for (int i = 0; i < mode; i++) {
				if (i % 2 == 1)
					con.setPWM(left[i], period, (direction_left % 100) * 100);
				else
					con.setPWM(left[i], period, 0);
			}

		} else if (direction_left < -15) {
			for (int i = 0; i < mode; i++) {
				if (i % 2 == 0)
					con.setPWM(left[i], period, -(direction_left % 100) * 100);
				else
					con.setPWM(left[i], period, 0);
			}

		} else {
			for (int i = 0; i < mode; i++)
				con.setPWM(left[i], period, 0);
		}

		if (direction_right > 15) {
			for (int i = 0; i < mode; i++) {
				if (i % 2 == 1)
					con.setPWM(right[i], period, (direction_right % 100) * 100);
				else
					con.setPWM(right[i], period, 0);
			}

		} else if (direction_right < -15) {
			for (int i = 0; i < mode; i++) {
				if (i % 2 == 0)
					con.setPWM(right[i], period, -(direction_right % 100) * 100);
				else
					con.setPWM(right[i], period, 0);
			}

		} else {
			for (int i = 0; i < mode; i++)
				con.setPWM(right[i], period, 0);
		}
	}

	/**
	 * Checks if the exit button was pressed
	 * 
	 * @return returns true if the exit button was pressed on the controller.
	 *         returns false otherwise
	 */
	public boolean is_exit_detected() {
		return exit_detected;
	}

	@Override
	public void eventReceived(DistanceSensorEvent e) {

	}
}
