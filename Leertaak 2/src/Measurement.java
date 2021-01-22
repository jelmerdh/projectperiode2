public class Measurement {

	private int stn;		// station
	private String date;
	private String time;
	private float temp;		// graden celsius
	private float dewp; 	// dauwpunt in graden celsius
	private float stp;		// Luchtdruk op stationsniveau in millibar
	private float slp;		// Luchtdruk op zeeniveau in millibar
	private float visib;	// Zichtbaarheid in kilometers
	private float wdsp;		// Windsnelheid in kilometers per uur
	private float prcp;		// Neerslag in centimeters
	private float sndp;		// Gevallen sneeuw in centimeters
	private String frshtt;	// vries, regen, sneeuw, hagel, onweer, tornado
	private float cldp;
	private int winddir;

	public Measurement(int stn, String date, String time, float temp, float dewp, float stp, float slp, float visib, float wdsp, float prcp, float sndp, String frshtt, float cldp, int winddir) {
		this.stn = stn;
		this.date = date;
		this.time = time;
		this.temp = temp;
		this.dewp = dewp;
		this.stp = stp;
		this.slp = slp;
		this.visib = visib;
		this.wdsp = wdsp;
		this.prcp = prcp;
		this.sndp = sndp;
		this.frshtt = frshtt;
		this.cldp = cldp;
		this.winddir = winddir;
	}

	public Measurement(){

	}

	public void printer(){
		System.out.println(getStn() + "\n" + getDate() + "\n" + getTime() + "\n" + getTemp() + "\n" + getDewp() + "\n" + getStp() + "\n" + getSlp() + "\n" + getVisib() + "\n" + getWdsp() + "\n" + getPrcp() + "\n" + getSndp() + "\n" + getFrshtt() + "\n" + getCldp() + "\n" + getWinddir());
	}

	public int getStn() {return stn;}
	public void setStn(int stn) {this.stn = stn;}
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	public float getTemp() {return temp;}
	public void setTemp(float temp) {this.temp = temp;}
	public float getDewp() {return dewp;}
	public void setDewp(float dewp) {this.dewp = dewp;}
	public float getStp() {return stp;}
	public void setStp(float stp) {this.stp = stp;}
	public float getSlp() {return slp;}
	public void setSlp(float slp) {this.slp = slp;}
	public float getVisib() {return visib;}
	public void setVisib(float visib) {this.visib = visib;}
	public float getWdsp() {return wdsp;}
	public void setWdsp(float wdsp) {this.wdsp = wdsp;}
	public float getPrcp() {return prcp;}
	public void setPrcp(float prcp) {this.prcp = prcp;}
	public float getSndp() {return sndp;}
	public void setSndp(float sndp) {this.sndp = sndp;}
	public String getFrshtt() {return frshtt;}
	public void setFrshtt(String frshtt) {this.frshtt = frshtt;}
	public float getCldp() {return cldp;}
	public void setCldp(float cldp) {this.cldp = cldp;}
	public int getWinddir() {return winddir;}
	public void setWinddir(int winddir) {this.winddir = winddir;}
}
