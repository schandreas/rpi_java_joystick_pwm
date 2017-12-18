package at.restental.andreas.joystick;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Vector;

/**
 * bkybb
 * 
 * @author ascharnr
 *
 */
public class Joystick extends Thread {
	Vector<JoystickListener> listeners;
	protected Path file;
	protected InputStream in;
	protected boolean running;
	public Joystick(String path_to_js) throws IOException {
		file = FileSystems.getDefault().getPath(path_to_js);
		in = Files.newInputStream(file);
		listeners = new Vector<JoystickListener>();
		running = true;
		this.start();
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
				//e.printStackTrace();
			}
		}
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

