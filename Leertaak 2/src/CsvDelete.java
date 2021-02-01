import java.io.*;
import java.time.LocalDate;


public class CsvDelete implements Runnable{

	private final boolean isMexico;

	public CsvDelete(boolean isMexico) {
		this.isMexico = isMexico;
	}

	@Override
	public void run() {
		String i = LocalDate.now().minusDays(28).toString();
		File inputFile = new File("TempData.csv");
		File tempFile = new File("TempoData.csv");
		if(isMexico) {
			i = LocalDate.now().minusDays(3).toString();
			inputFile = new File("FullWeatherData.csv");
			tempFile = new File("TempoMexico.csv");
		}
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
			assert reader != null;
			while ((currentLine = reader.readLine()) != null) {
				String[] currParts = currentLine.split(",");

				try {
					curRem = Integer.parseInt(currParts[1].replace("-", ""));
					if (curRem < dateToRemove) {
						currentRemove = currentLine;

					}
				} catch (Exception ignored) {

				}
				// trim newline when comparing with lineToRemove
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

		if (inputFile.delete()) {
			//System.out.println("Deleted the file: " + inputFile.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
		boolean success = tempFile.renameTo(inputFile);

		if (!success) {
			System.out.println("failed to rename input file");
		}
		try {
			Thread.sleep(86400000);	// 1 dag
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
