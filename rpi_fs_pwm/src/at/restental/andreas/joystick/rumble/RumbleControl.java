package at.restental.andreas.joystick.rumble;


public class RumbleControl {
	static {
		System.loadLibrary("rumble"); 
	}
	
	public RumbleControl() {
		this.setuprumble();
	}
	
	private native void setuprumble();
	
	public native void startrumble();
	
	public native void stoprumble();
}