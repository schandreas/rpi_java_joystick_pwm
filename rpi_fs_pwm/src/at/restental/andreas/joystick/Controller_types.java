package at.restental.andreas.joystick;

public enum Controller_types {
	UNKNOWN(4, 1), DS4(5, 1), XBOX360(4, 1);

	private final int axis_left;
	private final int axis_right;

	/**
	 * Constructor for the Controller type enum
	 * 
	 * @param axis_left
	 *            the axis to use for the left channel
	 * @param axis_right
	 *            the axis to use for the right channel
	 */
	private Controller_types(int axis_left, int axis_right) {
		this.axis_left = axis_left;
		this.axis_right = axis_right;
	}

	/**
	 * Getter for the left axis index
	 * 
	 * @return the left axis index
	 */
	public int axis_left() {
		return this.axis_left;
	}

	/**
	 * Getter for the right axis index
	 * 
	 * @return the right axis index
	 */
	public int axis_right() {
		return this.axis_right;
	}

}
