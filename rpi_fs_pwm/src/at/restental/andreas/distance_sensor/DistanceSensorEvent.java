package at.restental.andreas.distance_sensor;


public class DistanceSensorEvent {

	protected int value;
	protected DistanceSensor src;
	
	public DistanceSensorEvent(byte[] raw_value, DistanceSensor src) {
		this.value = new Integer(new String(raw_value).trim());
		this.src = src;
	}
	
	
	public int value() {
		return value;
	}
	
	
	public DistanceSensor src() {
		return src;
	}
}
