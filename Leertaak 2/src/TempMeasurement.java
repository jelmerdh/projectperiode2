import java.io.FileWriter;
import java.io.IOException;

public class TempMeasurement {
	private int stn;		// station
	private String date;
	private String time;
	private float temp;		// graden celsius

	public synchronized void writeData(){
		try {
			FileWriter csvWriter = new FileWriter("TempData.csv", true);
			csvWriter.append(String.valueOf(stn)).append(",").append(date).append(",").append(time).append(",").append(String.valueOf(temp)).append("\n");
			csvWriter.flush();
			csvWriter.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void printer(){
		System.out.println(stn + "\n" + date + "\n" + time + "\n" + temp);
	}

	public int getStn() {return stn;}
	public void setStn(int stn) {this.stn = stn;}
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	public float getTemp() {return temp;}
	public void setTemp(float temp) {this.temp = temp;}
}
