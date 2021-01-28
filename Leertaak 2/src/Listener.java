import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Listener implements Runnable{

	/*
	Deze class luistert naar binnenkomende serveraanvragen en laat ze afhandelen in andere thread
	 */

	private final int port;					// poort waar op geluisterd wordt.
	private final ExecutorService executor;	// voor de threadpool

	public Listener(int port){
		this.port = port;
		executor = Executors.newFixedThreadPool(900);	// er wordt een threadpool gemaakt bestaande uit 900 threads
	}
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {	// server socket laten luisteren op poort
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert serverSocket != null;
		while (true) try {
			Socket clientSocket = serverSocket.accept();	// binnenkomende aanvraag
			Runnable wr = new WorkerRunnable(clientSocket);	// aanvraag afhandelaar
			executor.execute(wr);							// afhandelaar starten in aparte thread
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // TODO: programma stoppen als er geen data meer binnen komt.
}
