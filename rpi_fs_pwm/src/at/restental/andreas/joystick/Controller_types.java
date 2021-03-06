package at.restental.andreas.joystick;

public enum Controller_types {
	UNKNOWN(4, 1), DS4(5, 1), XBOX360(4, 1);

	private final int axis_left;
	private final int axis_right;

	private Controller_types(int axis_1, int axis_2) {
		this.axis_left = axis_1;
		this.axis_right = axis_2;
	}

	public int axis_left() {
		return this.axis_left;
	}

	public int axis_right() {
		return this.axis_right;
	}

}
