import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WorkerRunnable implements Runnable{

	private final Socket clientSocket;
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
			while ((data = in.readLine()) != null) {
				if (data.equals("\t<MEASUREMENT>")) {
					int stn;
					if ((stn = Integer.parseInt(in.readLine().replaceAll("[^0-9?!.\\-:]", ""))) >= 760013 && stn <= 769043) {
						FullMeasurement m = new FullMeasurement();
						int i = 0;
						data = String.valueOf(stn);
						while (!(data).equals("\t</MEASUREMENT>")) {
							if (!(parser.parse(m, data, i))) {
								break;
							}
							i++;
							data = in.readLine();
						}
						m.writeData();
					}
					else if ((stn >= 785830 && stn <= 788078) || (stn >= 749025 && stn <= 749035)){
						TempMeasurement m = new TempMeasurement();
						int i = 0;
						data = String.valueOf(stn);
						while (!(data).equals("\t</MEASUREMENT>")) {
							if(i<=3){
								if (!(parser.parse(m, data, i))) {
									break;
								}
							}
							i++;
							data = in.readLine();
						}
						m.writeData();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
