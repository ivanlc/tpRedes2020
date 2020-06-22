import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket sc;
	private BufferedReader in = null;
	private PrintWriter out = null;

	private static Scanner scanner;
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		Client client = new Client();

		String port = "";
		String ip = "";
		boolean valid = false;

		while (!valid) {
			scanner = new Scanner(System.in);
			System.out.println("Ingresar IP: ");
			ip = scanner.nextLine();

			System.out.println("Puerto: ");
			port = scanner.nextLine();

			if (!ip.equals("") && !port.equals("")) {
				valid = true;
			} else {
				System.out.println("***************************************");
				System.out.println("Datos incorrectos, vuelva a intentarlo.");
				System.out.println("***************************************");
			}
		}

		client.connect(ip, port);
		client.send();

	}

	public void connect(String ip, String port) {

		Thread hilo = new Thread(new Runnable() {
			public void run() {

				try {
					sc = new Socket(ip, Integer.parseInt(port));

					in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
					out = new PrintWriter(sc.getOutputStream(), true);
					out.flush();

				} catch (Exception e) {
					System.out.println("Excepción al levantar conexión: " + e.getMessage());
					System.exit(0);
				}

				String st = "";

				try {
					while (true) {
						st = (String) in.readLine();
						System.out.println(st);

						if (st.equals("Conexion finalizada")) {
							closeConection();
							System.exit(0);

						} else {
							System.out.print("Cliente: ");
						}
					}
				} catch (IOException e) {
					System.out.println("Conexión finalizada");
					System.exit(0);
				}
			}
		});
		hilo.start();
	}

	public void closeConection() {
		try {
			sc.close();
			in.close();
			out.close();
			input.close();

		} catch (IOException e) {
			System.out.println("Error al cerrar la conexion");
		}

	}

	public void send() {
		String inServer = "";

		while (true) {
			System.out.print("Server: ");
			inServer = input.nextLine();

			if (inServer.length() > 0) {
				out.println(inServer);
				out.flush();
				if (inServer.equalsIgnoreCase("X")) {
					closeConection();
					System.exit(0);
				}
			}
		}
	}

}
