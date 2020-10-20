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

public class TCPSingletonJ2 extends Thread{
	private static TCPSingletonJ2 unicaInstancia;
	
	private TCPSingletonJ2() {}
	
	public static TCPSingletonJ2 getInstance() {
		if(unicaInstancia == null) {
			unicaInstancia = new TCPSingletonJ2();
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
			server = new ServerSocket(6000);
			System.out.println("Esperando en el 6000...");
			
			socket = server.accept();
			System.out.println("Conectamos");
			
			//emisor
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			//receptor
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			//Recepcion
			while(true) {
				String lastMessage = reader.readLine();
				System.out.println("lastMessage");
				observer.cuandoLlegueElMensaje("Jugador 2 dice: "+lastMessage);
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
