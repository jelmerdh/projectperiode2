import java.net.*;
import java.io.*;

public class Listener implements Runnable{

	private final int port;
	private final Parser parser;

	public Listener(int port){
		this.port = port;
		parser = new Parser();
	}
	@Override
	public void run() {
		try(
				ServerSocket serverSocket = new ServerSocket(port);
				Socket clientSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
		){
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
