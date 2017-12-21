package at.restental.andreas.joystick;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Vector;
import at.restental.andreas.joystick.Dualshock4.DS4ColorController;
import at.restental.andreas.joystick.XBOX360.Xbox360PatternController;



public class Joystick extends Thread {
	Vector<JoystickListener> listeners;
	protected Path file;
	protected InputStream in;
	protected boolean running;
	protected Controller_types type;
	protected JoystickLED led;

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

	public void addListener(JoystickListener jl) {
		listeners.add(jl);
	}

	@Override
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

	public void setColor(int red, int green, int blue) {
		led.setColor(red, green, blue);
	}

	public void setPattern(int pattern) {
		led.setPattern(pattern);
	}
	
	public Controller_types getControllerType() {
		return type;
	}

	public void cleanup() {
		this.running = false;
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
