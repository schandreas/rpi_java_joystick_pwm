package at.restental.andreas.rpi_fs_pwm;

import java.nio.file.*;

public class PWMController {
	protected Path conroot;
	protected PWMChannel channels[];

	public PWMController(String path_to_pwmchip) throws InvalidPathException{
		conroot = FileSystems.getDefault().getPath(path_to_pwmchip);
		channels = new PWMChannel[16];
	}

	public Path getControllerPath() {
		return conroot;
	}
	public void InitChannel(int number) {
		try {
			channels[number] = new PWMChannel(this, number);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setPWM(int channel, int period, int duty_cycle) {
		try {
			channels[channel].setPeriod(period);
			channels[channel].setDuty(duty_cycle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void cleanup() {
		for(int i = 0; i < 16; i++) {
			try {
				Files.write(conroot.resolve("unexport"), Integer.toString(i).getBytes());
			} catch (Exception e) {
				
			}
		}
	}
}
