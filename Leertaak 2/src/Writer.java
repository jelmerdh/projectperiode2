import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Writer {
	public static void main(String[] args) {
		try(
				ServerSocket serverSocket = new ServerSocket(7789);
				Socket clientSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
		){
			String data;
			while ((data = in.readLine()) != null){
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
