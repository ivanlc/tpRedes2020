import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread implements Runnable {

	private Socket sc;
	private int id;
	private BufferedReader in;
	private PrintWriter out;
	private static Scanner scanner;

	public ClientThread(Socket socket, int id) throws IOException {
		this.sc = socket;
		this.id = id;
		out = new PrintWriter(sc.getOutputStream(), true);
	}

	public void run() {

		System.out.println("***************************************");
		System.out.println("Cliente Nro. " + id + " conectado");
		System.out.println("***************************************");
		System.out.println("Esperando datos...");

		try {
			String inMsj = "";
			in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			inMsj = in.readLine();

			if (inMsj == null) {
				closeConection();
			} else {
				if (!inMsj.isEmpty()) {
//					inMsj = inMsj.replace(":", "");
					if (inMsj.equalsIgnoreCase("X")) {
						closeConection();
					} else {
						System.out.println("Cliente Nro." + id + ": " + inMsj);

						scanner = new Scanner(System.in);
						System.out.println("Server: ");
						String rta = scanner.nextLine();

						if (rta.equalsIgnoreCase("X")) {
							System.out.println("Finalizo la conexión del cliente " + id);
							out.println("Conexion finalizada");
							closeConection();
							System.exit(0);
						} else {
							System.out.println("Server " + id + ": " + rta);
							out.println(rta);
							out.flush();
						}
					}
				}
			}

		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void closeConection() throws IOException {
		try {
			out.println("Conexión finalizada");
			System.out.println("Cliente Nro.  " + id + "Desconectado");
			sc.close();
			in.close();
			out.close();

		} catch (IOException e) {
			System.out.println("Error al cerrar la conexion del cliente " + id);
			out.println("Error al cerrar en la conexion");
		}
	}

}
