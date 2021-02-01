public class Execute{

	/*
	Deze class start het hele proces
	@Author Jorian Koning
	 */

	public static void main(String[] args) {
		new Thread(new Listener(7789)).start();			// start met luisteren naar inkomenden netwerkverzoeken
		Thread m = new Thread(new CsvDelete(true));		// csv Mexico updaten zodat er geen out-dated data in zit
		Thread a = new Thread(new CsvDelete(false));	// csv midden Amerika updaten zodat er geen out-dated data in zit

		while(true){
			if(!(m.isAlive()))m.start();	// als deze thread niet al bezig is, wordt hij nu gestart
			if(!(a.isAlive()))a.start();	// als deze thread niet al bezig is, wordt hij nu gestart
		}
	}
}
