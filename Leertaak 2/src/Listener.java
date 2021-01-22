import java.net.*;
import java.io.*;

public class Listener implements Runnable{

	private final int port;

	public Listener(int port){
		this.port = port;
	}
	@Override
	public void run() {
		try{
			ServerSocket serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			new Thread(new WorkerRunnable(clientSocket)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
