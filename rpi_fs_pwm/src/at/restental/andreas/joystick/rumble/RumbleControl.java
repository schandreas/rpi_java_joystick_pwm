package at.restental.andreas.joystick.rumble;


public class RumbleControl {
	/**
	 * Loads the rumble library from the system.
	 */
	static {
		System.loadLibrary("rumble"); 
	}
	
	/**
	 * Constructor calls the setuprumble function in the librumble library
	 * Writes the basic forcefeedback event to the controller
	 */
	public RumbleControl() {
		this.setuprumble();
	}
	
	private native void setuprumble();
	
	public native void startrumble();
	
	public native void stoprumble();
}