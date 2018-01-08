package at.restental.andreas.RPI_Server;

import java.net.*;
import java.io.*;

public class RPI_Server extends ServerSocket {

	protected Socket client;
	public BufferedReader in;
	public PrintWriter out;
	protected boolean exit;

	public RPI_Server(int port) throws IOException {
		super(port);
		client = this.accept();
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream());
		exit = false;
	}


	public void cleanup() {
		exit = true;
		out.close();
		try {
			in.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
