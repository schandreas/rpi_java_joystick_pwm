package at.restental.andreas.joystick;

public class JoystickEvent {
	private final static int TIME0 = 0;
	private final static int TIME1 = 1;
	private final static int TIME2 = 2;
	private final static int TIME3 = 3;
	private final static int LSB = 4;
	private final static int MSB = 5;
	private final static int TYPE = 6;
	private final static int ID = 7;
	public long timestamp;
	public short value;
	public byte type;
	public byte id;

	/**
	 * Converts an event in the format normally read out of the js file of a
	 * joystick to a series of readable values
	 * 
	 * @param raw
	 *            the event to be converted. Should be 8 bytes big
	 */
	public JoystickEvent(byte[] raw) {
		this.timestamp = (long) (((raw[TIME0] & 0xFF)) | ((raw[TIME1] & 0xFF) << 8) | ((raw[TIME2] & 0xFF) << 16)
				| ((raw[TIME3] & 0xFF) << 24));
		this.value = (short) (((raw[LSB] & 0xFF)) | ((raw[MSB] & 0xFF) << 8));
		this.id = raw[ID];
		this.type = raw[TYPE];
	}

	/**
	 * Checks if this event matches the specifies parameters
	 * 
	 * @param id
	 *            id to be checked against
	 * @param type
	 *            type to be checked against
	 * @return true if the events macth. false if not
	 */
	public boolean match(int id, int type) {
		if (this.id == id && this.type == type)
			return true;
		else
			return false;
	}

	/**
	 * Checks if this event matches the specifies parameters
	 * 
	 * @param id
	 *            id to be checked against
	 * @param type
	 *            type to be checked against
	 * @param value
	 *            value to be checked against
	 * @return true if the events macth. false if not
	 */
	public boolean match(int id, int type, int value) {
		if (this.id == id && this.type == type && this.value == value)
			return true;
		else
			return false;
	}
}
