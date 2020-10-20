package main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import clasesEclipse.Chicken;
import clasesEclipse.Elephant;
import clasesEclipse.Pig;
import modelo.CoorAnimal;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements OnMessageListener{
	
	int pantalla;
	PImage pantUno, prim, primP, pantInicio, pantPlayer, pantControl, pantInstru, pantJuego, pantGanador, jugar, jugarP,
	instru, instruP, contro, controP, pasto, atras, atrasP, jugarPP, jugarPPP, player1, player2;
	
	String ip, j1,j2;
	Pig cerdito;
	Chicken pollito;
	Elephant elefantico;
	
	Boolean j1pig = false, j1elef = false, j1chic = false;
	
	private TCPSingletonJ1 tcpJ1;
	private TCPSingletonJ2 tcpJ2;
	
	//private Boolean j1pig = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");

	}
	
	public void settings() {
		size(1048,450);
	}
	
	public void setup() {
		//variables 
		pantalla = -1;
		
		/*cerdito = new Pig (300,345,this);
		pollito = new Chicken (600, 340, this);
		elefantico = new Elephant (450, 340, this);*/
		
		tcpJ1 = TCPSingletonJ1.getInstance();
		tcpJ1.setObserver(this);
		tcpJ1.start();
		
		tcpJ2 = TCPSingletonJ2.getInstance();
		tcpJ2.setObserver(this);
		tcpJ2.start();
		
		
		
		//saber cual es mi ip para colocarla en el socket del cliente
		 try {
	            InetAddress n = InetAddress.getLocalHost();
	            ip = n.getHostAddress();
	            //System.out.println(ip);

	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        }
		
		
		//imagenes
		pantUno = loadImage("img/inicio.png");
		prim = loadImage("img/prim.png");
		primP = loadImage("img/primP.png");
		pantInicio = loadImage("img/pantallaInicio.png");
		pantPlayer = loadImage("img/pantallaPlayer.png");
		pantInstru = loadImage("img/instru.png");
		atras = loadImage("img/atras.png");
		atrasP = loadImage("img/atrasP.png");
		jugarPP = loadImage("img/jugarP.png");
		jugarPPP = loadImage("img/jugarPP.png");
		pantControl = loadImage("img/control.png");
		pantJuego = loadImage("img/pantallaJuego.png");
		pantGanador = loadImage("img/pantallaGanador.png");
		jugar = loadImage("img/jugar.png");
		jugarP = loadImage("img/jugarPress.png");
		instru = loadImage("img/instrucciones.png");
		instruP = loadImage("img/instruccionesPress.png");
		contro = loadImage("img/controles.png");
		controP = loadImage("img/controlesPress.png");
		pasto = loadImage("img/pasto.png");
		player1 = loadImage("img/jugador1.png");
		player2 = loadImage("img/jugador2.png");
		
		
		
		
	}
	
	public void draw() {
		background(255);
		
		
		switch(pantalla) {
		case -1:
			//pantalla welcome
			image(pantUno,0,0);
			image(prim, 395,350);
			
			if(mouseX > 395 && mouseX < 650 && mouseY > 350 && mouseY < 400) {
				image(primP, 395,350);
			}
			
			break;
		case 0:
			//pantalla home
			image(pantInicio,0,0);
			image(jugar, 730,175);
			
			if(mouseX > 730 && mouseX < 940 && mouseY > 175 && mouseY < 220) {
				image(jugarP, 730,176);
			}
			image(contro, 100, 140);
			if(mouseX > 100 && mouseX < 310 && mouseY > 140 && mouseY < 185) {
				image(controP, 100,141);
			}
			image(instru, 100, 220);
			if(mouseX > 100 && mouseX < 310 && mouseY > 220 && mouseY < 265) {
				image(instruP, 100,220);
			}
			
			
			
			
			break;
		case 1:
			//pantalla controles
			image(pantControl,0,0);
			image(jugarPP, 930,10);
			fill(21,118,147);
			textSize(20);
			text(ip, 460,130);
			
			textSize(30);
			if(j1==null) {
				
			}else {
				text(j1, 380,230);
			}
			if(j2==null) {
				
			}else {
				text(j2, 380,300);
			}
			
			if(mouseX > 930 && mouseX < 1030 && mouseY > 10 && mouseY < 53) {
				image(jugarPPP, 930,10);
			}
			
			
			
			break;
		case 2:
			//pantalla instrucciones
			image(pantInstru,0,0);
			image(jugarPP, 930,10);
			image(atras, 20,10);
			
			if(mouseX > 20 && mouseX < 120 && mouseY > 10 && mouseY < 53) {
				image(atrasP, 20,10);
			}
			if(mouseX > 930 && mouseX < 1030 && mouseY > 10 && mouseY < 53) {
				image(jugarPPP, 930,10);
			}
			
			
			break;
		case 3:
			//pantalla elegir jugadores
			image(pantPlayer,0,0);
			image(jugarPP, 930,10);
			image(atras, 20,10);
			
			if(mouseX > 20 && mouseX < 120 && mouseY > 10 && mouseY < 53) {
				image(atrasP, 20,10);
			}
			if(mouseX > 930 && mouseX < 1030 && mouseY > 10 && mouseY < 53) {
				image(jugarPPP, 930,10);
			}
			
			
			if(j1pig==true) {
				image(player1, 160,373);
			}
			if(j1chic==true) {
				image(player1, 360,373);
			}
			if(j1elef==true) {
				image(player1, 860,373);
			}
			
			
			break;
		case 4:
			//pantalla de juego
			image(pantJuego,0,0);
			
			
			if(j1pig==true) {
				cerdito.pintar();
			}
			if(j1chic==true) {
				pollito.pintar();
			}
			if(j1elef==true) {
				elefantico.pintar();
			}
			
			/*elefantico.pintarBalas();
			cerdito.pintarBalas();
			pollito.pintarBalas();*/
			
			
			image(pasto,0,365);
			break;
		case 5:
			//pantalla ganador-perdedor
			image(pantGanador,0,0);
			
			break;
		}
		 
		fill(355,0,0);
		text("x:"+mouseX+"y:"+mouseY, mouseX, mouseY);
		
	}
	
	/*public void keyPressed() {
		if(key == 'l' || key == 'L') {
			cerdito.agregarBalas();
		}
		if(key == 'a' || key == 'A') {
			cerdito.setDir(2);
		}
		if(key == 'd' || key == 'D') {
			cerdito.setDir(1);
		}
		
		
		
		if(key == 'k' || key == 'K') {
			pollito.agregarBalas();
		}
		if(keyCode == LEFT) {
			pollito.setDir(2);
		}
		if(keyCode == RIGHT) {
			pollito.setDir(1);
		}
		


		if(key == 'm' || key == 'M') {
			elefantico.agregarBalas();
		}
		if(keyCode == LEFT) {
			elefantico.setDir(2);
		}
		if(keyCode == RIGHT) {
			elefantico.setDir(1);
		}
		
	}*/
	
	public void mousePressed(){
		switch(pantalla) {
		case -1:
			//pantalla welcome
			if(mouseX > 395 && mouseX < 650 && mouseY > 350 && mouseY < 400) {
				pantalla = 1;
			}
			break;
		case 0:
			//pantalla home
			
			//boton jugar
			if(mouseX > 730 && mouseX < 940 && mouseY > 175 && mouseY < 220) {
				pantalla = 3;
				tcpJ1.enviarMensaje("escoger");
				//tcpJ2.enviarMensaje("escoger");
			}
			//boton control
			if(mouseX > 100 && mouseX < 310 && mouseY > 140 && mouseY < 185) {
				pantalla = 1;
			}
			//boton instrucciones
			if(mouseX > 100 && mouseX < 310 && mouseY > 220 && mouseY < 265) {
				pantalla = 2;
				
			}
			
			break;
		case 1:
			//pantalla controles
			
			//boton jugar
			if(mouseX > 930 && mouseX < 1030 && mouseY > 10 && mouseY < 53) {
				pantalla = 0;
			}
			break;
		case 2:
			//pantalla instrucciones
			
			//boton home
			if(mouseX > 20 && mouseX < 120 && mouseY > 10 && mouseY < 53) {
				pantalla = 0;
			}
			
			//boton jugar
			if(mouseX > 930 && mouseX < 1030 && mouseY > 10 && mouseY < 53) {
				pantalla = 3;
				
				tcpJ1.enviarMensaje("escoger");
				//tcpJ2.enviarMensaje("escoger");
			}
			
			break;
		case 3:
			//pantalla jugadores
			
			//boton home
			if(mouseX > 20 && mouseX < 120 && mouseY > 10 && mouseY < 53) {
				pantalla = 0;
			}
			
			//boton jugar
			if(mouseX > 930 && mouseX < 1030 && mouseY > 10 && mouseY < 53) {
				pantalla = 4;
			}
			break;
		case 5:
			//pantalla ganador-perdedor
			
			break;
		}
		
	}

	@Override
	public void cuandoLlegueElMensaje(String msg) {
		// TODO Auto-generated method stub
		
		System.out.println("llego un mensaje:  "+msg);
		
		switch(pantalla) {
		case 1:
			//controles
			if(msg.contains("Jugador1")) {
				j1 = msg;
			}
			if(msg.contains("Jugador2")) {
				j2 = msg;
			}
			break;
		case 3:
			//seleccion personajes
			
			
			Gson gson = new Gson();
			CoorAnimal coord = gson.fromJson(msg, CoorAnimal.class);
			String tipo = coord.getType();
			System.out.println(tipo);
			
			switch(tipo) {
				case "pig":
					cerdito = new Pig(coord.getPosx(), coord.getPosy(),this);
					j1pig = true;
					j1elef = false;
					j1chic = false;
					break;
				case "chicken":
					pollito = new Chicken(coord.getPosx(), coord.getPosy(),this);
					j1pig = false;
					j1elef = false;
					j1chic = true;
					break;
				case "elephant":
					elefantico = new Elephant(coord.getPosx(), coord.getPosy(),this);
					j1pig = false;
					j1elef = true;
					j1chic = false;
					break;
			
				}
			
			
			break;
		}
		
	}



}
