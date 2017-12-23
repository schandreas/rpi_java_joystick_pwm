package at.restental.andreas.joystick.rumble;


public class RumbleControl {
	static {
		System.loadLibrary("rumble"); 
	}
	public native void rumble();
	
}