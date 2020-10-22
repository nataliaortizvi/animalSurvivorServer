package main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import clasesEclipse.Chicken;
import clasesEclipse.Elephant;
import clasesEclipse.Pig;
import modelo.CoorAnimal;
import modelo.LlegandoBullet;
import modelo.NameAnimal;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements OnMessageListener{
	
	int pantalla, gameTime= 120, players = 0;
	PImage pantUno, prim, primP, pantInicio, pantPlayer, pantControl, pantInstru, pantJuego, pantGanador, jugar, jugarP,
	instru, instruP, contro, controP, pasto, atras, atrasP, jugarPP, jugarPPP, player1, player2;
	
	String ip, j1,j2;
	Pig cerdito1, cerdito2;
	Chicken pollito1, pollito2;
	Elephant elefantico1, elefantico2;
	
	
	
	Boolean j1pig = false, j1elef = false, j1chic = false, j1live = false, 
			j2pig = false, j2elef = false, j2chic = false, j2live = false, 
			cayendo = false;

	private TCPSingletonJ1 tcpJ1;
	private TCPSingletonJ2 tcpJ2;
	

	
	private float xJ1=50, yJ1=350, xJ2=1000, yJ2=350;
	

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
		
		tcpJ1 = TCPSingletonJ1.getInstance();
		tcpJ1.setObserver(this);
		tcpJ1.start();
		
		tcpJ2 = TCPSingletonJ2.getInstance();
		tcpJ2.setObserver(this);
		tcpJ2.start();
		
		//2 minutos para que alguien gane o pierda
			new Thread(
				()-> {
					while(gameTime > 0) {
						if(pantalla == 4) {
							gameTime --;
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			).start();
		
		
		
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
	
		
		for (int i=0; i<width; i++) {
		    for (int j=0; j<height; j++) {
		    	int c = get(i, j);
		    	
		    	if ( c == color(108,75,34)) {
		    		c = color (255,255,255);
		    		
		    		
		    	} else {
	
		    		c = color(0);
		    		stroke (c);
		    		point (i, j);
		    		
		    	}
		    }		
		}
	
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
				text(j1, 400,230);
			}
			if(j2==null) {
				
			}else {
				text(j2, 400,300);
			}
			
			if(mouseX > 930 && mouseX < 1030 && mouseY > 10 && mouseY < 53) {
				image(jugarPPP, 930,10);
			}
			
			if (players < 1) {
				fill(350,100,150);
				textSize(20);
				text("(Conecta 2 controles para empezar a jugar)", 340,388);
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
				image(player1, 470,373);
			}
			if(j1elef==true) {
				image(player1, 770,373);
			}
			
			if(j2pig==true) {
				image(player2, 160,373);
			}
			if(j2chic==true) {
				image(player2, 470,373);
			}
			if(j2elef==true) {
				image(player2, 770,373);
			}
			
			
			break;
		case 4:
			//pantalla de juego
			image(pantJuego,0,0);
			
			
			//pintar jugador 1
			if(j1pig==true) {
				cerdito1.pintar();
				cerdito1.pintarBalas();
			}
			if(j1chic==true) {
				pollito1.pintar();
				pollito1.pintarBalas();
			}
			if(j1elef==true) {
				elefantico1.pintar();
				elefantico1.pintarBalas();
			}
			
			
			//pintar jugador 2
			if(j2pig==true) {
				cerdito2.pintar();
			}
			if(j2chic==true) {
				pollito2.pintar();
			}
			if(j2elef==true) {
				elefantico2.pintar();
			}
			
			fill(21,118,147);
			textSize(15);
			textAlign(CENTER);
			text(gameTime, 572,43);
			
			
			
			image(pasto,0,385);
			if(gameTime == 0) {
				pantalla = 5;
			}
			break;
		case 5:
			//pantalla ganador-perdedor
			image(pantGanador,0,0);
			
			break;
		}
		 
		fill(355,0,0);
		text("x:"+mouseX+"y:"+mouseY, mouseX, mouseY);
		
	}
	
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
			
			
			//debe de haber 2 controles conectados
			//boton jugar
			if(mouseX > 930 && mouseX < 1030 && mouseY > 10 && mouseY < 53) {
				if(players == 1) {
					pantalla = 0;
				}
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
				
				//tiene que haber por lo menos 1 personaje seleccionado por juagador
				if( j1pig == true || j1elef == true || j1chic == true ||
						j2pig == true || j2elef == true || j2chic == true ) {
							
							pantalla = 4;
							tcpJ1.enviarMensaje("play");
							cayendo = true;
				}
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
		
		
		switch(pantalla) {
		case 1:
			//controles
			if(msg.contains("Jugador1")) {
				j1 = msg;
				players += 1;
			}
			if(msg.contains("Jugador2")) {
				j2 = msg;
				players += 1;
			}
			break;
		case 3:
			//seleccion personajes
			
			String[] mensajeR = msg.split("_");
			
			String jugador = mensajeR[0];
			String mensaje = mensajeR[1];
			
			Gson gson = new Gson();
			NameAnimal name = gson.fromJson(mensaje, NameAnimal.class);
			String tipo = name.getName();
			System.out.println(tipo);
			
			
			//si la seleccion es del jugador 1
			if(jugador.contains("Jugador1")) {
				
				switch(tipo) {
				case "pig":
					cerdito1 = new Pig(xJ1, yJ1, this);
		
					j1pig = true;
					j1elef = false;
					j1chic = false;
					j1live = true;
					break;
					
				case "chicken":
					pollito1 = new Chicken(xJ1, yJ1,this);
					
					j1pig = false;
					j1elef = false;
					j1chic = true;
					j1live = true;
					break;
					
				case "elephant":
					elefantico1 = new Elephant(xJ1, yJ1,this);
					
					j1pig = false;
					j1elef = true;
					j1chic = false;
					j1live = true;
					break;
				}
			}
			
			//si la seleccion es del jugador 2
			if(jugador.contains("Jugador2")) {
				switch(tipo) {
				case "pig":
					cerdito2 = new Pig(xJ2, yJ2,this);
					j2pig = true;
					j2elef = false;
					j2chic = false;
					j2live = true;
					break;
				case "chicken":
					pollito2 = new Chicken(xJ2, yJ2, this);
					j2pig = false;
					j2elef = false;
					j2chic = true;
					j2live = true;
					break;
				case "elephant":
					elefantico2 = new Elephant(xJ2, yJ2,this);
					j2pig = false;
					j2elef = true;
					j2chic = false;
					j2live = true;
					break;
				}
			}
			
			break;
		case 4:
			//MOVIMIENTO JUGADOR (PANTALLA DE JUEGO)
			
            String[] mensajeRs = msg.split("_");
			
			String jugadors = mensajeRs[0];
			String mensajes = mensajeRs[1];
			
			Gson gsons = new Gson();
			CoorAnimal coords = gsons.fromJson(mensajes, CoorAnimal.class);
			
			Gson gsond = new Gson();
			LlegandoBullet shoots = gsond.fromJson(mensajes, LlegandoBullet.class);
			
			System.out.println(shoots.getLlega());
			
			//si se mueve el jugador 1
			if (jugadors.contains("Jugador1")) {
				//moverJ1 
				
				
				if (j1pig == true) {
					
					if (pantJuego.get((int) cerdito1.getPosx(), (int) cerdito1.getPosy()-77) != color(108, 75, 34)) {
						

						cerdito1.setPosx(coords.getPosx());
						cerdito1.setPosy(coords.getPosy());
						
						if(coords.getType().contains("left")) {
							cerdito1.setDir(2);
						}
						if(coords.getType().contains("right")) {
							cerdito1.setDir(1);
						}
						
							
						
					}
				}
				
				if (j1elef == true) {
					elefantico1.setPosx(coords.getPosx());
					elefantico1.setPosy(coords.getPosy());
					
					if(coords.getType().contains("left")) {
						elefantico1.setDir(2);
					}
					if(coords.getType().contains("right")) {
						elefantico1.setDir(1);
					}
				
					}
				
				
				if (j1chic == true) {
					pollito1.setPosx(coords.getPosx());
					pollito1.setPosy(coords.getPosy());
					
					if(coords.getType().contains("left")) {
						pollito1.setDir(2);
					}
					if(coords.getType().contains("right")) {
						pollito1.setDir(1);
					}
					
				}
				
				
			}
			
			//si se mueve el jugador 2
			if (jugadors.contains("Jugador2")) {
				//moverJ2
				
				if (j2pig == true) {
					cerdito2.setPosx(coords.getPosx());
					cerdito2.setPosy(coords.getPosy());
					
					if(coords.getType().contains("left")) {
						cerdito2.setDir(2);
					}
					if(coords.getType().contains("right")) {
						cerdito2.setDir(1);
					}
				}
				
				if (j2elef == true) {
					elefantico2.setPosx(coords.getPosx());
					elefantico2.setPosy(coords.getPosy());
					
					if(coords.getType().contains("left")) {
						elefantico2.setDir(2);
					}
					if(coords.getType().contains("right")) {
						elefantico2.setDir(1);
					}
				}
				
				if (j2chic == true) {
					pollito2.setPosx(coords.getPosx());
					pollito2.setPosx(coords.getPosx());
					
					if(coords.getType().contains("left")) {
						pollito2.setDir(2);
					}
					if(coords.getType().contains("right")) {
						pollito2.setDir(1);
					}
				}
				
			}
			
		break;
		}
	}
	

}
