package at.restental.andreas.robot;

import at.restental.andreas.distance_sensor.DistanceSensor;
import at.restental.andreas.distance_sensor.DistanceSensorEvent;
import at.restental.andreas.distance_sensor.DistanceSensorListener;
import at.restental.andreas.rpi_fs_pwm.PWMController;

public class RoboController implements DistanceSensorListener {
	protected PWMController con;
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
	public void drive(int a, int b) {
		System.out.println("Received " + a + " " + b);
//		int j = 0;
//		if(b < 3 &&)
//		if (a < 3 && a > 1) {
//			for (int i = 0; i < mode; i++) {
//				if (i % 2 == j)
//					con.setPWM(left[i], 7000, 0);
//				else
//					con.setPWM(left[i], 7000, 0);
//			}
//		}
//		
//		for (int i = 0; i < mode; i++) {
//			if (i % 2 == j)
//				con.setPWM(left[i], 7000, 0);
//			else
//				con.setPWM(left[i], 7000, 0);
//		}

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
