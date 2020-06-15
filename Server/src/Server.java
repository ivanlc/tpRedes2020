
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	public static void main(String[] args) {
		ServerSocket servidor = null;
		Socket sc = null;
		DataInputStream in;
		DataOutputStream out;

		final int PUERTO = 3000;

		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("Servidor iniciado");

			while (true) {
				sc = servidor.accept();
				Scanner scanner = new Scanner(sc.getInputStream());
				
				
				String line = "line";
				
				while(scanner.hasNextLine() && !"".equals(line) && !"".equals(line = scanner.nextLine())) {
					System.out.println(line);
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
