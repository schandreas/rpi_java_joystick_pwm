package at.restental.andreas.rpi_fs_pwm;

import java.nio.file.*;

public class PWMController {
	protected Path conroot;
	protected PWMChannel channels[];

	/**
	 * Constructor for PWMController
	 * 
	 * @param path_to_pwmchip
	 *            path to the root directory of the pwmchip device (most often:
	 *            /sys/class/pwm/pwmchip0)
	 * @throws InvalidPathException
	 *             if the path specified is invalid
	 */
	public PWMController(String path_to_pwmchip) throws InvalidPathException {
		conroot = FileSystems.getDefault().getPath(path_to_pwmchip);
		channels = new PWMChannel[16];
	}

	/**
	 * Returns the path to the root directory of the controller
	 * 
	 * @return the path to the root directory of this controller
	 */
	public Path getControllerPath() {
		return conroot;
	}

	/**
	 * Constructs a new PWMChannel Object and stores it whithin this class
	 * 
	 * @param number
	 *            the number (index) of the channel to be initialized
	 */
	public void InitChannel(int number) {
		try {
			channels[number] = new PWMChannel(this, number);
		} catch (Exception e) {
			System.out.println("Error opening Channel " + number);
		}
	}

	/**
	 * Sets duty cycle and period of a specified channel
	 * 
	 * @param channel
	 *            the channel to be modified
	 * @param period
	 *            desired period
	 * @param duty_cycle
	 *            desired duty_cycle
	 */
	public void setPWM(int channel, int period, int duty_cycle) {
		channels[channel].setPeriod(period);
		channels[channel].setDuty(duty_cycle);
	}

	/**
	 * unexports all PWMChannels
	 */
	public void cleanup() {
		for (int i = 0; i < 16; i++) {
			try {
				Files.write(conroot.resolve("unexport"), Integer.toString(i).getBytes());
			} catch (Exception e) {

			}
		}
	}
}
