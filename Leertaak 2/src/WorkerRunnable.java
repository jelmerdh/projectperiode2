import java.io.*;
import java.net.Socket;

public class WorkerRunnable implements Runnable{

	/*
	Deze class handeld binnenkomende data af
	 */

	private final Socket clientSocket;
	private final Parser parser;

	public WorkerRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
		parser = new Parser();				// parser zorgt er voor dat binnenkomende data omgezet wordt naar variabelen
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	// binnenkomende data
			String data;
			while ((data = in.readLine()) != null) {
				if (data.equals("\t<MEASUREMENT>")) {
					int stn;
					if ((stn = Integer.parseInt(in.readLine().replaceAll("[^0-9?!.\\-:]", ""))) >= 760013 && stn <= 769043) {	// Mexicaanse data
						FullMeasurement fm = new FullMeasurement();
						int i = 0;
						data = String.valueOf(stn);
						while (!(data).equals("\t</MEASUREMENT>")) {
							if (!(parser.parse(fm, data, i))) {	// data opslaan in FullMearsurement class
								break;							// als er iets mis is gegaan met de data, niks meer opgeslagen
							}
							i++;
							data = in.readLine();
						}
						fm.writeData();
					}
					else if ((stn >= 785830 && stn <= 788078) || (stn >= 749025 && stn <= 749035)){		// data uit midden Amerika
						TempMeasurement tm = new TempMeasurement();
						int i = 0;
						data = String.valueOf(stn);
						while (!(data).equals("\t</MEASUREMENT>")) {
							if(i<=3){								// alleen de eerste 4 waardes hoeven opgeslagen worden
								if (!(parser.parse(tm, data, i))) {	// data opslaan in TempMearsurement class
									break;							// als er iets mis is gegaan met de data, niks meer opgeslagen
								}
							}
							i++;
							data = in.readLine();
						}
						tm.writeData();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
