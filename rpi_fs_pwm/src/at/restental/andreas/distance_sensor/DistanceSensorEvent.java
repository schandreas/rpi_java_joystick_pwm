package at.restental.andreas.distance_sensor;

public class DistanceSensorEvent {

	protected int value;
	protected DistanceSensor src;

	/**
	 * Constructor for a Distance Sensor Event. Converts raw value to an int value.
	 * src is copied and passed without changing
	 * 
	 * @param raw_value
	 *            raw byte array read out of the file
	 * @param src
	 *            Distance Sensor from which the event was generated
	 */
	public DistanceSensorEvent(byte[] raw_value, DistanceSensor src) {
		this.value = new Integer(new String(raw_value).trim());
		this.src = src;
	}

	/**
	 * Getter for the sensor value
	 * 
	 * @return the sensor value attached to this event
	 */
	public int value() {
		return value;
	}

	/**
	 * Getter for the event source
	 * 
	 * @return the Distance sensor that this event came from
	 */
	public DistanceSensor src() {
		return src;
	}
}
