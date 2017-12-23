package at.restental.andreas.distance_sensor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class DistanceSensor {
	protected Path measure;

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
	}

	/**
	 * gets the distance out of the measure file
	 * 
	 * @return the distance the sensor detects
	 */
	public int getDistance() {
		try {
			return new Integer(new String(Files.readAllBytes(measure)).trim());
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
