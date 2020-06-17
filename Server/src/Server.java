
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
		int clientCont = 1;

		final int PUERTO = 3000;
		
		try {
			servidor = new ServerSocket(PUERTO);
			while (true) {			
				System.out.println("Servidor iniciado");
				sc = servidor.accept();
				ClientThread ct = new ClientThread(sc,clientCont);
				clientCont++;
				Thread thread = new Thread(ct);
				thread.start();
			}

		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
