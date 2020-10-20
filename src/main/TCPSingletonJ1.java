package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPSingletonJ1 extends Thread {
	
	private static TCPSingletonJ1 unicaInstancia;
	
	private TCPSingletonJ1() {}
	
	public static TCPSingletonJ1 getInstance() {
		if(unicaInstancia == null) {
			unicaInstancia = new TCPSingletonJ1();
		}
		return unicaInstancia;
	}
	
	//valores globales tcp
	ServerSocket server;
	BufferedWriter writer;
	Socket socket;
	OnMessageListener observer;
	
	//metodo de suscripcion
	public void setObserver(OnMessageListener observer) {
		this.observer = observer;
	}
	
	@Override
	public void run() {
		
		try {
			//Conexion
			server = new ServerSocket(5000);
			System.out.println("Esperando en el 5000...");
			
			socket = server.accept();
			System.out.println("Conectamos el J1");
			observer.cuandoLlegueElMensaje("Jugador1 conectado");
			
			//emisor
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			//receptor
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			//Recepcion
			while(true) {
				String lastMessage = reader.readLine();

				observer.cuandoLlegueElMensaje("Jugador1 _"+lastMessage);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(String msg) {
		new Thread(
				()-> {
					try {
						writer.write(msg + "\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		).start();
	}

}
