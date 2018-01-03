package at.restental.andreas.distance_sensor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

public class DistanceSensor extends Thread {
	protected Path measure;
	protected Vector<DistanceSensorListener> listeners;
	protected boolean exit;

	/**
	 * Constructor for Distance Sensor. Resolves the directory in which the
	 * specified sensor files will sit
	 * 
	 * @param pin1
	 *            sensor pin 1
	 * @param pin2
	 *            sensor pin 2
	 * @param timeout
	 *            timeout for the sensor
	 */
	public DistanceSensor(int pin1, int pin2, int timeout) {
		Path sensor_root = FileSystems.getDefault().getPath("/sys/class/distance-sensor/");
		this.measure = sensor_root.resolve("distance_" + pin1 + "_" + pin2 + "/measure");
		this.listeners = new Vector<DistanceSensorListener>();
		exit = false;
		this.start();
	}

	public void attachListener(DistanceSensorListener dl) {
		listeners.add(dl);
	}

	/**
	 * gets the distance out of the measure file
	 */
	public void run() {
		while (!exit) {
			try {
				for (int i = 0; i < listeners.size(); i++) {
					listeners.get(i).eventReceived(new DistanceSensorEvent(Files.readAllBytes(measure), this));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cleanup() {
		exit = true;
	}
}
