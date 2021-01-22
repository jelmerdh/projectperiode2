import java.io.*;
import java.net.Socket;

public class WorkerRunnable implements Runnable{

	private Socket clientSocket = null;
	private final Parser parser;

	public WorkerRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
		parser = new Parser();
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String data;
			while ((data = in.readLine()) != null){
				if(data.equals("\t<MEASUREMENT>")){
					Measurement m = new Measurement();
					int i = 0;
					while(!(data).equals("\t</MEASUREMENT>")){
						data = in.readLine();
						if(!(parser.parse(m, data, i))){
							break;
						}
						i++;
					}
					m.printer();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
