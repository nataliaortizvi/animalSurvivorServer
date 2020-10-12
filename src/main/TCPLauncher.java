 package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPLauncher extends Thread{
	
	//constructor privado
	private TCPLauncher(){}
	

	//unica instancia de TcpSingleton
	private static TCPLauncher unicaInstancia;
	
	//metodo estático que devuelve la unica instancia
	public static TCPLauncher getInstance(){
		if(unicaInstancia == null){
			unicaInstancia = new TCPLauncher();
			unicaInstancia.start();
		}
		return unicaInstancia;
	}
		
	//patron observer
    private Main observer;
    
    public void setObserver(Main observer) {
    	this.observer = observer;
    }
    public Main getObserver() {
		return observer;
	}
    
    //variables globales tcp
   	ServerSocket server;
   	Socket socket;
   	ArrayList <Session> sesiones;
    
   	
    @Override
    public void run() {
		try {
			
			//conexion
			sesiones = new ArrayList<Session>();
			server = new ServerSocket(5000);
			
			while(true) {
			System.out.println("esperando conexion");
			
			socket = server.accept();
			Session session = new Session(socket);
			session.setObserver(observer);
			session.start();
			
			sesiones.add(session);
			System.out.println("cliente conectado");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    
	public ServerSocket getServer() {
		return server;
	}
	public void setServer(ServerSocket server) {
		this.server = server;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ArrayList<Session> getSesiones() {
		return sesiones;
	}
	public void setSesiones(ArrayList<Session> sesiones) {
		this.sesiones = sesiones;
	}

}
