import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Listener implements Runnable{

	private final int port;
	private final ExecutorService executor;

	public Listener(int port){
		this.port = port;
		executor = Executors.newFixedThreadPool(900);//creating a pool of 900 threads
	}
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert serverSocket != null;
		while (true) try {
			Socket clientSocket = serverSocket.accept();
			Runnable wr = new WorkerRunnable(clientSocket);
			executor.execute(wr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
