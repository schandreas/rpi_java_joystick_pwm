package at.restental.andreas.rpi_fs_pwm;

import java.io.IOException;
import java.nio.file.*;

public class PWMChannel {
	protected Path duty;
	protected Path period;
	protected Path chan_root;

	/**
	 * Constructor for PWMChannel
	 * 
	 * @param parent
	 *            the parent controller for this channel. Needed for the parent
	 *            directory
	 * @param number
	 *            the number (index) of this channel
	 * @throws IOException
	 *             if paths cannot get resolved and/or there is an error when
	 *             exporting the channels
	 */
	public PWMChannel(PWMController parent, int number) throws IOException {
		chan_root = parent.getControllerPath();
		Files.write(chan_root.resolve("export"), Integer.toString(number).getBytes());
		chan_root = chan_root.resolve("pwm" + number);
		period = chan_root.resolve("period");
		duty = chan_root.resolve("duty_cycle");
	}

	/**
	 * Writes period to the period file of this channel
	 * 
	 * @param period
	 *            value to be written
	 */
	public void setPeriod(int period) {
		try {
			Files.write(this.period, (Integer.toString(period) + "000").getBytes());
		} catch (IOException e) {
			System.out.println("!!Error setting period. Something is very wrong");
		}
	}

	/**
	 * Writes duty cycle to the duty_cycle file of this channel
	 * 
	 * @param duty
	 *            value to be written
	 */
	public void setDuty(int duty) {
		try {
			Files.write(this.duty, (Integer.toString(duty) + "000").getBytes());
		} catch (IOException e) {
			System.out.println("!!Error setting duty. Something is very wrong");
		}
	}

}
