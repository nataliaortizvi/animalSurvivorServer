package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

public class Session extends Thread{
	
	private String id;
	private BufferedWriter writer;
	private BufferedReader reader;
	private Socket socket;
	private Main observer;
	
	public void setObserver(Main observer) {
		this.observer = observer;
	}
	
	public Session (Socket socket) {
		this.socket = socket;
		this.id = UUID.randomUUID().toString();
	}
	
	@Override
	public void run() {
		try {
			//emisor
			OutputStream os = socket.getOutputStream();
			writer = new BufferedWriter (new OutputStreamWriter(os));
			
			//receptor
			InputStream is = socket.getInputStream();
			reader = new BufferedReader (new InputStreamReader(is));
			
			while(true) {
				//recibe constantemente
				String line = reader.readLine();
				System.out.println(line);
				 
				observer.cuandoLlegueElMensaje(line);
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

	
	public String getID() {
		return this.id;
	}

	
	
	

	public void setId(String id) {
		this.id = id;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Main getObserver() {
		return observer;
	}
	
}
