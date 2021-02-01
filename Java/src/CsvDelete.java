import java.io.*;
import java.time.LocalDate;


public class CsvDelete implements Runnable{

	private final boolean isMexico;

	public CsvDelete(boolean isMexico) {
		this.isMexico = isMexico;
	}

	@Override
	public void run() {
		//set de datum op de te datum die verwijderd moet worden, pakt ook mexico of midden america
		String i = LocalDate.now().minusDays(28).toString();
		File inputFile = new File("TempData.csv");
		File tempFile = new File("TempoData.csv");
		if(isMexico) {
			i = LocalDate.now().minusDays(3).toString();
			inputFile = new File("FullWeatherData.csv");
			tempFile = new File("TempoMexico.csv");
		}
		//set de datum om naar een int
		int dateToRemove=Integer.parseInt(i.replace("-", ""));
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			writer = new BufferedWriter(new FileWriter(tempFile));
		} catch (IOException e) {
			e.printStackTrace();
		}


		String currentLine;
		String currentRemove = "temp";
		int curRem = 0;
		try {
			//leest lijn voor lijn de csv's door
			assert reader != null;
			while ((currentLine = reader.readLine()) != null) {
				String[] currParts = currentLine.split(",");

				try {
					//set de datum uit de csv om naar een int
					curRem = Integer.parseInt(currParts[1].replace("-", ""));
					if (curRem < dateToRemove) {
						currentRemove = currentLine;

					}
				} catch (Exception ignored) {

				}
				//vergelijkt de datums, als de datum ouder is dan de te verwijderen data dan word die niet naar het nieuwe document geschreven
				String trimmedLine = currentLine.trim();
				if (trimmedLine.equals(currentRemove)) continue;
				try {
					assert writer != null;
					writer.write(currentLine + System.getProperty("line.separator"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		try {
			assert writer != null;
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// verwijderd het orginele document
		if (inputFile.delete()) {

		} else {
			System.out.println("Failed to delete the file.");
		}
		// vernoemt het temp document naar het orginele document
		boolean success = tempFile.renameTo(inputFile);

		if (!success) {
			System.out.println("failed to rename input file");
		}
		//zorgt dat de code 1 keer per dag wordt uitgevoerd
		try {
			Thread.sleep(86400000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
