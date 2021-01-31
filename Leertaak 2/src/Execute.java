public class Execute{

	/*
	Deze class start het hele proces
	 */

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Listener(7789)).start();
		Thread m = new Thread(new CsvDelete(true));
		Thread a = new Thread(new CsvDelete(false));

		while(true){
			if(!(m.isAlive()))m.start();
			if(!(a.isAlive()))a.start();
		}

		//CsvWrite w = new CsvWrite();

	}
}
