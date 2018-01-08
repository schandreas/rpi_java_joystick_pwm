package at.restental.andreas;

import java.io.IOException;

import at.restental.andreas.RPI_Server.RPI_Server;
import at.restental.andreas.distance_sensor.DistanceSensor;
import at.restental.andreas.robot.RoboController;
import at.restental.andreas.rpi_fs_pwm.PWMController;

public class TestMain {

	public static void main(String[] args) {
		PWMController con0;
		RoboController rb;
		RPI_Server server;
		String msg = "a";
		String[] raw;
		DistanceSensor ds1 = new DistanceSensor(16, 19, 3000);
		DistanceSensor ds2 = new DistanceSensor(20, 21, 3000);
		int motors = 0;
		try {
			motors = new Integer(args[0]);
		} catch (Exception e) {

		}
		try {
			con0 = new PWMController("/sys/class/pwm/pwmchip0/");
			server = new RPI_Server(3344);
		} catch (Exception e) {
			System.out.println("Error creating PWM Object");
			e.printStackTrace();
			return;
		}
		if (motors > 0) {
			rb = new RoboController(con0, motors);
			for (int i = 0; i < 2 * motors; i++)
				con0.InitChannel(i);
		} else {
			rb = new RoboController(con0, 2);
			for (int i = 0; i < 4; i++)
				con0.InitChannel(i);
		}
		ds1.attachListener(rb);
		ds2.attachListener(rb);
		while (msg.compareTo("end") != 0) {
			try {
				msg = server.in.readLine();
			} catch (IOException e) {
				
			}
			try {
				raw = msg.split(" ");
				rb.drive(Integer.parseInt(raw[1]), Integer.parseInt(raw[0]));
			} catch (Exception e) {

			}
		}
		ds1.cleanup();
		ds2.cleanup();
		con0.cleanup();
		server.cleanup();
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
