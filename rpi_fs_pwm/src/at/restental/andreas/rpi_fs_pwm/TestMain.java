package at.restental.andreas.rpi_fs_pwm;

import at.restental.andreas.joystick.Joystick;
import at.restental.andreas.joystick.JoystickEvent;
import at.restental.andreas.joystick.JoystickListener;

public class TestMain implements JoystickListener {
	static int x = 1;
	static PWMController con0;

	public static void main(String[] args) {
		Joystick js0;
		try {
			con0 = new PWMController("/sys/class/pwm/pwmchip0/");
			js0 = new Joystick("/dev/input/js0");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		con0.InitChannel(0);
		con0.InitChannel(3);
		con0.InitChannel(4);
		con0.InitChannel(7);
		js0.addListener(new TestMain());
		while (x != 0) {
			
		}
		js0.cleanup();
		con0.cleanup();
	}

	@Override
	public void eventReceived(JoystickEvent e) {
		if(e.match(6, 1, 1))x = 0;
		if (e.type == 2) {
			switch (e.id) {
			case 4:
				con0.setPWM(4, 0xFFFF, e.value + 0x7FFF + 1);
				con0.setPWM(7, 0xFFFF, e.value + 0x7FFF + 1);
				break;
			case 1:
				con0.setPWM(0, 0xFFFF, e.value + 0x7FFF + 1);
				con0.setPWM(3, 0xFFFF, e.value + 0x7FFF + 1);
				break;
			default:
				break;
			}
		}
	}

}
