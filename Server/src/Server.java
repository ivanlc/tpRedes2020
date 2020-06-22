
import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	public static void main(String[] args) {
		ServerSocket server = null;
		Socket sc = null;
		int id = 1;

		final int PORT = 3000;
		PrintWriter out;
		
		try {
			server = new ServerSocket(PORT);;
			System.out.println("*********************");
			System.out.println("Servidor iniciado");
			System.out.println("*********************");
			System.out.println("Esperando conexion");
			while (true) {							
				sc = server.accept();
				ClientThread ct = new ClientThread(sc,id);			
				Thread thread = new Thread(ct);
				thread.start();
				out = new PrintWriter(sc.getOutputStream(), true);
				out.println("Ingresaste con el ID " + id + " - Ingrese X para salir del programa");
				id++;
		
			}
			
		}
		catch (BindException e) {
			System.out.println("El puerto ya se encuentra en uso.");
			System.out.println("Programa finalizado.");
		}
		catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		} 		
	}
}
