package at.restental.andreas.RPI_Server;

import java.net.*;
import java.io.*;

public class RPI_Server extends Thread {

	protected Socket client;
	protected ServerSocket server;
	protected BufferedReader in;
	protected PrintWriter out;
	protected boolean exit;

	public RPI_Server(int port) throws IOException {
		server = new ServerSocket(port);
		client = server.accept();
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream());
		exit = false;
		this.start();
	}

	@Override
	public void run() {
		
	}

	public void cleanup() {
		exit = true;
		out.close();
		try {
			in.close();
			client.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
