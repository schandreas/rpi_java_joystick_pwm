package at.restental.andreas.joystick;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Vector;
import at.restental.andreas.joystick.Dualshock4.DS4ColorController;
import at.restental.andreas.joystick.XBOX360.Xbox360PatternController;

public class Joystick extends Thread {
	protected Vector<JoystickListener> listeners;
	protected Path file;
	protected InputStream in;
	protected boolean running;
	protected Controller_types type;
	protected JoystickLED led;

	/**
	 * Constructor for Joystick. Events will be read from the File specified by the
	 * path in path_to_js
	 * 
	 * @param path_to_js
	 *            The path to the jsX file. Most likely /dev/input/js0
	 * @throws IOException
	 */
	public Joystick(String path_to_js) throws IOException {
		file = FileSystems.getDefault().getPath(path_to_js);
		in = Files.newInputStream(file);
		listeners = new Vector<JoystickListener>();
		running = true;
		this.start();
		try {
			type = Controller_types.DS4;
			led = new DS4ColorController();
		} catch (Exception e) {
			try {
				type = Controller_types.XBOX360;
				led = new Xbox360PatternController();
			} catch (Exception e2) {
				System.out.println("Error initializing LED Control");
				type = Controller_types.UNKNOWN;
			}
		}
	}

	/**
	 * Adds a Joystick Listener to be notified
	 * 
	 * @param jl
	 *            The Listener to be notified
	 */
	public void addListener(JoystickListener jl) {
		listeners.add(jl);
	}

	@Override
	/**
	 * Periodically polls the file specified in path_to_js and notifies the
	 * listeners if it pulls a valid event
	 */
	public void run() {
		byte event[] = new byte[8];
		while (running) {
			try {
				if (in.read(event) == 8) {
					for (int i = 0; i < listeners.size(); i++) {
						listeners.get(i).eventReceived(new JoystickEvent(event));
					}
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Sets the Color for the Joystick LEDs. ONLY AVAILABLE FOR DS4 CONTROLLERS
	 * 
	 * @param red
	 *            typically 0-255. specifies the red brightness
	 * @param green
	 *            typically 0-255. specifies the green brightness
	 * @param blue
	 *            typically 0-255. specifies the blue brightness
	 */
	public void setColor(int red, int green, int blue) {
		led.setColor(red, green, blue);
	}

	/**
	 * Sets the pattern to be executed by the Joystick LEDs. NOT YET IMPLEMENTED.
	 * ONLY AVAILABLE FOR XBOX360 CONTROLLERS
	 * 
	 * @param pattern
	 *            the pattern to be executed
	 */
	public void setPattern(int pattern) {
		led.setPattern(pattern);
	}

	/**
	 * Returns the Controller type
	 * 
	 * @return the type of the Controller
	 */
	public Controller_types getControllerType() {
		return type;
	}

	/**
	 * Stops the main loop and stops reading of the js file
	 */
	public void cleanup() {
		this.running = false;
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
