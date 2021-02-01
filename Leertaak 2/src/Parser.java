import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

public class Parser {

	/*
	Deze klasse stipt de data uit de XML gegevens
	@Author Jorian Koning
	 */

	public boolean parse(FullMeasurement m, String line, int n) {	// parser voor mexico data, returnt false als er kritieke data mist, anders true
		if(m == null){return false;}	// als m == null, er is ergens een keer iets fout gegaan, return false
		line = line.replaceAll("[^0-9?!.\\-:]", "");	// de xml jas uit trekken
		if(line.equals("")){	// missende data
			if((n >= 3 && n <= 10) || n == 12){
				line = String.valueOf(extrapolate(m.getStn(), n));	// missende data wordt ingeschat op basis van extrapolatie
			}
		}
		switch(n) { // nth data dat aangepast moet worden binnen de FullMeasurement class
			case 0:
				if(line.equals("")){
					m = null;		// wanneer er geen stationnummer beschikbaar is, wordt de FullMeasurement class leeg gehaald.
					return false;	// return false om aan te geven dat er iets fout gegaan is.
				}
				m.setStn(Integer.parseInt(line));
				return true;
			case 1:
				if(line.equals("")) {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					m.setDate(dtf.format(LocalDateTime.now()));		// datum is niet ontvangen dus huidige datum wordt gebruikt.
				}
				else {
					m.setDate(line);
				}
				return true;
			case 2:
				if(line.equals("")) {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
					m.setDate(dtf.format(LocalDateTime.now()));	// tijd is niet ontvangen dus huidige tijd wordt gebruikt.
				}
				else {
					m.setTime(line);
				}
				return true;
			case 3:	// temperatuur wordt gecontrolleerd voordat het opgeslagen wordt
				m.setTemp(round(correctTemp(m.getStn(), true, Float.parseFloat(line)),1));
				return true;
			case 4:
				m.setDewp(round(Float.parseFloat(line),1));return true;
			case 5:
				m.setStp(round(Float.parseFloat(line),1));return true;
			case 6:
				m.setSlp(round(Float.parseFloat(line),1));return true;
			case 7:
				m.setVisib(round(Float.parseFloat(line),1));return true;
			case 8:
				m.setWdsp(round(Float.parseFloat(line),1));return true;
			case 9:
				m.setPrcp(round(Float.parseFloat(line),2));return true;
			case 10:
				m.setSndp(round(Float.parseFloat(line),1));return true;
			case 11:
				if(line.equals("")){m.setFrshtt("000000");}	// als er geen data over evenementen binnen is gekomen, wordt er aangenomen dat er niks aan de hand is.
				else{m.setFrshtt(line);}return true;
			case 12:
				m.setCldp(Float.parseFloat(line));return true;
			case 13:
				if(line.equals("")){m.setWinddir(-1);}		// wanneer de winddirectie niet binnen komt, wordt er -1 oppgeslagen zodat het programma weet dat het niet klopt
				else{m.setWinddir(Integer.parseInt(line));}return true;
			default:
				return true;
		}
	}

	public boolean parse(TempMeasurement m, String line, int n) {	// parser voor midden America data, returnt false als er kritieke data mist, anders true
		if (m == null) {return false;}
		line = line.replaceAll("[^0-9?!.\\-:]", "");
		switch(n) {
			case 0:
				if (line.equals("")) {
					m = null;		// wanneer er geen stationnummer beschikbaar is, wordt de FullMeasurement class leeg gehaald.
					return false;	// return false om aan te geven dat er iets fout gegaan is.
				}
				m.setStn(Integer.parseInt(line));
				return true;
			case 1:
				if(line.equals("")) {	// als de datum niet binnen komt, wordt huidige datum gebruikt
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					m.setDate(dtf.format(LocalDateTime.now()));
				}
				else {
					m.setDate(line);
				}
				return true;
			case 2:
				if(line.equals("")) {	// als de tijd niet binnen komt, wordt huidige tijd gebruikt
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
					m.setDate(dtf.format(LocalDateTime.now()));
				}
				else {
					m.setTime(line);
				}
				return true;
			case 3:
				m.setTemp(correctTemp(m.getStn(), false, Float.parseFloat(line)));	// temperatuur wordt gecontrolleerd voordat het opgeslagen wordt
				return true;
			default:
				return true;
		}
	}

	private float correctTemp(int stn, boolean isMexico, float temp) {	// temperatuur wordt aangepast wanneer het te hoog of te laag is.
		final float maxTempDifference = 4;		// maximale graden C verschil tussen twee metingen

		float extrapolation = 0;				// voorspelling wat ongeveer de volgende waarde zou zijn

		if(isMexico) extrapolation = extrapolate(stn, 4);	// extrapolatie met csv van Mexico data
		else extrapolation = extrapolate(stn, 3);			// extrapolatie met csv van m-Amerika data

		if(temp > extrapolation + maxTempDifference){			// wanneer de temperatuur veel hoger is dan verwacht
			return extrapolation + maxTempDifference;			// temperatuur wordt max maxTempDifference graden hoger
		}
		if(temp < extrapolation - maxTempDifference){			// wanneer de temperatuur veel lager is dan verwacht
			return extrapolation - maxTempDifference;			// temperatuur wordt max maxTempDifference graden lager
		}
		return temp;											// temperatuur past binnen de lijnen
	}

	private float extrapolate(int stn, int n){
		// uiterst deskundige manier om een extrapolatie te berekenen
		float ext = 0;
		Stack<Float> stack = new Stack30<>();	// stack waar maximaal 30 waardes in passen
		String filename;
		if(n == 3)filename = "TempData.csv";	// meting komt uit midden America
		else {
			filename = "FullWeatherData.csv";			// meting komt uit Mexico
			n--;
		}
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(filename));	// csv uitlezen
			String row;
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if(data[0].equals(String.valueOf(stn))){		// data[0] = stationsnummer
					stack.push(Float.parseFloat(data[n]));		// als het overeen komt met gevraagde station, temperatuur in stack
				}
			}
			if(stack.size() == 30){		// als de stack helemaal gevuld is
				// na uren research (letterlijk) kwam ik er achter dat dit het mooiste resultaat geeft.
				// je moet hier maar gewoon mijn woord voor nemen.
				ext = ((stack.get(0)+stack.get(1)+stack.get(2)) / 3) * 2;
				for(int i = 3; i < 30; i += 3){
					ext += stack.get(i)+stack.get(i+1)+stack.get(i+2);
					ext = (ext/5) * 2;
				}
				ext /= 2;
				return ext;	//geëxtrapoleerde data
			}
			else if(stack.size() == 0){
				return 0;	// geen gegevens beschikbaar, geen extrapolatie mogelijk
			}
			else{	// geen compleet gevulde stack, minder deskundig, geeft ook een redelijke extrapolatie
				ext = stack.get(0);
				for(int i = 1; i < stack.size(); i++){
					ext = (ext + stack.get(i)) / 2;
				}
				return ext;	//geëxtrapoleerde data
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return 0;
	}

	private float round (float value, int precision) {	// float afronden op n decimalen
		int scale = (int) Math.pow(10, precision);
		return (float) Math.round(value * scale) / scale;
	}

	private class Stack30<T> extends Stack<T> {

		/*
		Een stack class maar nu met een maximum van 30 waarden.
		@Author Jorian Koning
		 */

		private Stack30() {
			super();
		}

		@Override
		public T push(T object) {
			int maxSize = 30;
			if (this.size() == maxSize) {	// FIFO, als er meer dan 30 in de stack zitten, gaat de oudste data er uit
				this.remove(0);
			}
			return super.push(object);
		}
	}

}