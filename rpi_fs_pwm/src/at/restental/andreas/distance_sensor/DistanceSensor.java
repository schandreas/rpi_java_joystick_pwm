package at.restental.andreas.distance_sensor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class DistanceSensor {
	protected Path measure;

	public DistanceSensor(int pin1, int pin2, int timeout) throws IOException {
		Path sensor_root = FileSystems.getDefault().getPath("/sys/class/distance-sensor/");
		this.measure = sensor_root.resolve("distance_" + pin1 + "_" + pin2 + "/measure");
	}

	public int getDistance() {
		try {
			return new Integer(new String(Files.readAllBytes(measure)));
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
