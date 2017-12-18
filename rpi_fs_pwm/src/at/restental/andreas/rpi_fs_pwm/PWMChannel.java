package at.restental.andreas.rpi_fs_pwm;

import java.io.IOException;
import java.nio.file.*;

public class PWMChannel {
	protected Path duty;
	protected Path period;
	protected Path chan_root;

	public PWMChannel(PWMController parent, int number) throws IOException, ArrayIndexOutOfBoundsException {
		chan_root = parent.getControllerPath();
		if (number < 0 || number > 15) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Files.write(chan_root.resolve("export"), Integer.toString(number).getBytes());
		chan_root = chan_root.resolve("pwm" + number);
		period = chan_root.resolve("period");
		duty = chan_root.resolve("duty_cycle");
	}

	public void setPeriod(int period) {
		try {
			Files.write(this.period, (Integer.toString(period) + "000").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDuty(int duty) {
		try {
			Files.write(this.duty, (Integer.toString(duty) + "000").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
