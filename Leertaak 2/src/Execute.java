public class Execute{

	public void run() {
		new Thread(new Listener(7789)).start();
	}

	public static void main(String[] args) {
		new Execute().run();
	}
}
