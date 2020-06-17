import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread implements Runnable {

	private Socket sc;
	private int number;

	public ClientThread(Socket socket,int number) {
		this.sc = socket;
		this.number = number;
	}

	public void run() {
		
		System.out.println("Cliente " + number + " conectado");

		try {

			while (true) {
				Scanner scanner = new Scanner(sc.getInputStream());

				String line = "line";

				while (scanner.hasNextLine() && !"".equals(line) && !"".equals(line = scanner.nextLine())) {
					System.out.println("Cliente " + number + " : " + line);
					if (line.equals("exit")) {
						break;
					};
				}

				sc.close();
				System.out.println("Cliente desconectado");

			}

		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
