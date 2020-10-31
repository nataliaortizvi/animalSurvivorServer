package main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import clasesEclipse.Bullet;
import clasesEclipse.Chicken;
import clasesEclipse.Elephant;
import clasesEclipse.Pig;
import modelo.CoorAnimal;
import modelo.NameAnimal;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements OnMessageListener{
	
	int pantalla, gameTime= 320, players = 0, danno = 5;
	PImage pantUno, prim, primP, pantInicio, pantPlayer, pantControl, pantInstru, pantJuego, pantGanador, jugar, jugarP,
	instru, instruP, contro, controP, pasto, atras, atrasP, jugarPP, jugarPPP, player1, player2,
	vidaPig, vidaElef, vidaPollo, mapabw, cerditu, pollitu, elefanticu;
	
	String ip, j1,j2;
	Pig cerdito1, cerdito2;
	Chicken pollito1, pollito2;
	Elephant elefantico1, elefantico2;
	
	
	
	Boolean j1pig = false, j1elef = false, j1chic = false, j1live = false, 
			j2pig = false, j2elef = false, j2chic = false, j2live = false,
			pigwin = false, pollowin = false, elefwin = false;
	
	Boolean salto1 = false, salto2 = false, salto3 = false;

	private TCPSingletonJ1 tcpJ1;
	private TCPSingletonJ2 tcpJ2;
	
	Boolean noPuede = false;
	Boolean noPuede2 = false;
	Boolean noPuede3 = false;
	Boolean noPuede4 = false;
	

	
	private float xJ1=50, yJ1=320, xJ2=960, yJ2=320;
	private float gravity = (float) 0.5;
	

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
		pantJuego = loadImage("img/pantJuego.png");
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
		vidaPig = loadImage("img/piglife.png");
		vidaPollo = loadImage("img/gallolife.png");
		vidaElef = loadImage("img/elephanlife.png");
		mapabw = loadImage("img/mapabw.png");
		cerditu = loadImage("img/cerdoChiqui.png");
		pollitu = loadImage("img/galloChiqui.png");
		elefanticu = loadImage("img/elefanteChiqui.png");
	
		
		for (int i=0; i<width; i++) {
		    for (int j=0; j<height; j++) {
		    	int c = get(i, j);
		    	
		    	if ( c == color(255,255,255)) {
		    		
		    	} else {
	
		    		c = color(0);
		    		//stroke (c);
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
			image(mapabw, 0, 0);
			image(pantJuego,0,0);
			
			
			//pintar jugador 1
			if(j1pig==true) {
				tcpJ1.enviarMensaje("chosenPig");
				
				cerdito1.pintar();
				cerdito1.pintarBalas();
				image(vidaPig, 4,11,40,40);
				fill(70,355,70);
				noStroke();
				rect(51,(float) 27.5,cerdito1.getVida(),10);
				
				//daño de las balas al oponente y desaparicion de balas cuando le caen al jugador opuesto
				for(int i = 0; i < cerdito1.getBalas().size(); i++) {
					Bullet bala = cerdito1.getBalas().get(i);
					
					if(j2chic) {
						if(cerdito1.getVida()>(pollito2.getVida()+93)) {
							pigwin = true;
							pollowin = false;
							elefwin = false;
						}
					if(dist(bala.getPx(), bala.getPy(), pollito2.getPosx(), pollito2.getPosy()) < 40) {
						pollito2.setVida(pollito2.getVida() + danno);
						cerdito1.getBalas().remove(i);
						
					}
				}
					
					if(j2elef) {
						if(cerdito1.getVida()>(elefantico2.getVida()+93)) {
							pigwin = true;
							pollowin = false;
							elefwin = false;
						}
					if(dist(bala.getPx(), bala.getPy(), elefantico2.getPosx(), elefantico2.getPosy()) < 40) {
						elefantico2.setVida(elefantico2.getVida() + danno);
						cerdito1.getBalas().remove(i);
						
						
					}
				}
			}
		}
			
			if(j1chic==true) {
				tcpJ1.enviarMensaje("chosenChic");
				
				pollito1.pintar();
				pollito1.pintarBalas();
				image(vidaPollo, 4,11,40,40);
				fill(70,355,70);
				noStroke();
				rect(51,(float) 27.5,pollito1.getVida(),10);
				
				
				//daño de las balas al oponente y desaparicion de balas cuando le caen al jugador opuesto
				for(int i = 0; i < pollito1.getBalas().size(); i++) {
					Bullet bala = pollito1.getBalas().get(i);
					
					if(j2pig) {
						if(pollito1.getVida()>(cerdito2.getVida()+93)) {
							pigwin = false;
							pollowin = true;
							elefwin = false;
						}
					if(dist(bala.getPx(), bala.getPy(), cerdito2.getPosx(), cerdito2.getPosy()) < 40) {
						cerdito2.setVida(cerdito2.getVida() + danno);
						pollito1.getBalas().remove(i);
						
						
					}
				}
					
					
					if(j2elef) {
						if(pollito1.getVida()>(elefantico2.getVida()+93)) {
							pigwin = false;
							pollowin = true;
							elefwin = false;
						}
					if(dist(bala.getPx(), bala.getPy(), elefantico2.getPosx(), elefantico2.getPosy()) < 40) {
						elefantico2.setVida(elefantico2.getVida() + danno);
						pollito1.getBalas().remove(i);
						
						
					}
				}
			}
		}
			
			if(j1elef==true) {
				tcpJ1.enviarMensaje("chosenElef");
				
				elefantico1.pintar();
				elefantico1.pintarBalas();
				image(vidaElef, 4,11,40,40);
				fill(70,355,70);
				noStroke();
				rect(51,(float) 27.5,elefantico1.getVida(),10);
				

				//daño de las balas al oponente y desaparicion de balas cuando le caen al jugador opuesto
				for(int i = 0; i < elefantico1.getBalas().size(); i++) {
					Bullet bala = elefantico1.getBalas().get(i);
					
					if(j2pig) {
						if(elefantico1.getVida()>(cerdito2.getVida()+93)) {
							pigwin = false;
							pollowin = false;
							elefwin = true;
						}
					if(dist(bala.getPx(), bala.getPy(), cerdito2.getPosx(), cerdito2.getPosy()) < 40) {
						cerdito2.setVida(cerdito2.getVida() + danno);
						elefantico1.getBalas().remove(i);
						
						
					}
				}
					
					if(j2chic) {
						if(elefantico1.getVida()>(pollito2.getVida()+93)) {
							pigwin = false;
							pollowin = false;
							elefwin = true;
						}
					if(dist(bala.getPx(), bala.getPy(), pollito2.getPosx(), pollito2.getPosy()) < 40) {
						pollito2.setVida(pollito2.getVida() + danno);
						elefantico1.getBalas().remove(i);
						
						
					}
				}
			}
		}
			
			
			//pintar jugador 2
			if(j2pig==true) {
				tcpJ2.enviarMensaje("chosenPig");
				
				cerdito2.pintar();
				cerdito2.pintarBalas();
				image(vidaPig, 1004,11,40,40);
				fill(70,355,70);
				noStroke();
				rect(899,(float) 27.5,93,10);
				fill(58,89,109);
				rect(899,(float) 27.5,cerdito2.getVida()-93,10);
				
				//daño de las balas al oponente y desaparicion de balas cuando le caen al jugador opuesto
				for(int i = 0; i < cerdito2.getBalas().size(); i++) {
					Bullet bala2 = cerdito2.getBalas().get(i);
					
					if(j1chic) {
						if((cerdito2.getVida()+93)>pollito1.getVida()) {
							pigwin = true;
							pollowin = false;
							elefwin = false;
						}
					if(dist(bala2.getPx(), bala2.getPy(), pollito1.getPosx(), pollito1.getPosy()) < 40) {
						pollito1.setVida(pollito2.getVida() - danno);
						cerdito2.getBalas().remove(i);
						
						
					}
				}
					
					if(j1elef) {
						if((cerdito2.getVida()+93)>elefantico1.getVida()) {
							pigwin = true;
							pollowin = false;
							elefwin = false;
						}
					if(dist(bala2.getPx(), bala2.getPy(), elefantico1.getPosx(), elefantico1.getPosy()) < 40) {
						elefantico1.setVida(elefantico2.getVida() - danno);
						cerdito2.getBalas().remove(i);
						
						
					}
				}
			}
		}
			if(j2chic==true) {
				tcpJ2.enviarMensaje("chosenChic");
				
				pollito2.pintar();
				pollito2.pintarBalas();
				image(vidaPollo, 1004,11,40,40);
				fill(70,355,70);
				noStroke();
				rect(899,(float) 27.5,93,10);
				fill(58,89,109);
				rect(899,(float) 27.5,pollito2.getVida()-93,10);
				
				//daño de las balas al oponente y desaparicion de balas cuando le caen al jugador opuesto
				for(int i = 0; i < pollito2.getBalas().size(); i++) {
					Bullet bala2 = pollito2.getBalas().get(i);
					
					if(j1pig) {
						if((pollito2.getVida()+93)>cerdito1.getVida()) {
							pigwin = false;
							pollowin = true;
							elefwin = false;
						}
					if(dist(bala2.getPx(), bala2.getPy(), cerdito1.getPosx(), cerdito1.getPosy()) < 40) {
						cerdito1.setVida(cerdito1.getVida() - danno);
						pollito2.getBalas().remove(i);
						
						
					}
				}
					
					if(j1elef) {
						if((pollito2.getVida()+93)>elefantico1.getVida()) {
							pigwin = false;
							pollowin = true;
							elefwin = false;
						}
					if(dist(bala2.getPx(), bala2.getPy(), elefantico1.getPosx(), elefantico1.getPosy()) < 40) {
						elefantico1.setVida(elefantico1.getVida() - danno);
						pollito2.getBalas().remove(i);
						
						
					}
				}
			}
		}
			if(j2elef==true) {
				tcpJ2.enviarMensaje("chosenElef");
				
				elefantico2.pintar();
				elefantico2.pintarBalas();
				image(vidaElef, 1004,11,40,40);
				fill(70,355,70);
				noStroke();
				rect(899,(float) 27.5,93,10);
				fill(58,89,109);
				rect(899,(float) 27.5,elefantico2.getVida()-93,10);
				
				//daño de las balas al oponente y desaparicion de balas cuando le caen al jugador opuesto
				for(int i = 0; i < elefantico2.getBalas().size(); i++) {
					Bullet bala2 = elefantico2.getBalas().get(i);
					
					if(j1pig) {
						if((elefantico2.getVida()+93)>cerdito1.getVida()) {
							pigwin = false;
							pollowin = false;
							elefwin = true;
						}
					if(dist(bala2.getPx(), bala2.getPy(), cerdito1.getPosx(), cerdito1.getPosy()) < 40) {
						cerdito1.setVida(cerdito1.getVida() - danno);
						elefantico2.getBalas().remove(i);
						
						
					}
				}
					
					if(j1chic) {
						if((elefantico2.getVida()+93)>pollito1.getVida()) {
							pigwin = false;
							pollowin = false;
							elefwin = true;
						}
					if(dist(bala2.getPx(), bala2.getPy(), pollito1.getPosx(), pollito1.getPosy()) < 40) {
						pollito1.setVida(pollito1.getVida() - danno);
						elefantico2.getBalas().remove(i);
						
						
					}
				}
			}
		}
			
			fill(21,118,147);
			textSize(15);
			textAlign(CENTER);
			text(gameTime, 572,43);
			
			/*
			if(pigwin = true) {
				image(cerditu,460,20);
			}
			if(pollowin = true) {
				image(pollitu,460,20);
			}
			if(elefwin = true) {
				image(elefanticu,460,23);
			}
			
			System.out.println("cerdis"+pigwin);
			System.out.println("elefantis"+pollowin);
			System.out.println("gallis"+elefwin);
			*/
			
			
			image(pasto,0,385);
			if(gameTime == 0) {
				pantalla = 5;
				tcpJ1.enviarMensaje("end");
				tcpJ2.enviarMensaje("end");
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
				tcpJ2.enviarMensaje("escoger");
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

				if(players >= 1) {
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
				tcpJ2.enviarMensaje("escoger");
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
							
							////player one
							tcpJ1.enviarMensaje("play");
							
							////player one
							tcpJ2.enviarMensaje("play");
							
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
					cerdito2.setDir(2);
					j2pig = true;
					j2elef = false;
					j2chic = false;
					break;
				case "chicken":
					pollito2 = new Chicken(xJ2, yJ2, this);
					pollito2.setDir(2);
					j2pig = false;
					j2elef = false;
					j2chic = true;
					break;
				case "elephant":
					elefantico2 = new Elephant(xJ2, yJ2,this);
					elefantico2.setDir(2);
					j2pig = false;
					j2elef = true;
					j2chic = false;
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
			
			
			//si se mueve el jugador 1
			if (jugadors.contains("Jugador1")) {
				
				
				if (j1pig == true) {
					
					//direccion
					if(coords.getType().contains("left")) {
						cerdito1.setDir(2);
					}
					if(coords.getType().contains("right")) {
						cerdito1.setDir(1);
					}
					
					//disparo
					if(coords.getType().contains("paw")) {
						cerdito1.agregarBalas();
					}
					
					
					
					if (mapabw.get((int) cerdito1.getPosx()+35, (int) cerdito1.getPosy()+75) == color(255,255,255)) {
						
						cerdito1.setPosx(coords.getPosx());
						System.out.println("cerdo esta aqui  "+ cerdito1.getPosy());
						
						
						if(coords.getType().contains("ap")) {
							cerdito1.setPosy(cerdito1.getPosy()-coords.getPosy());
							noPuede = false;
						}else {
							noPuede = true;
						}
						
						new Thread(
			                    ()->{
			                    	while(noPuede == true){
			                    		if (mapabw.get((int) cerdito1.getPosx()+35, (int) cerdito1.getPosy()+75) == color(255,255,255)) {
			                    			//System.out.println("estoy bajando  "+gravity);
			                    			cerdito1.setPosy(cerdito1.getPosy()+gravity);
			                    		}
			                    		
			                    	try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			                    }
			                 }
			              ).start();
						
						/*if(coords.getType().contains("dawn")) {
							cerdito1.setPosy(cerdito1.getPosy()+coords.getPosy());
						}*/
						
					} else {
						cerdito1.setPosx(coords.getPosx());
						
						noPuede = false;
						
						if(coords.getType().contains("ap")) {
							cerdito1.setPosy(cerdito1.getPosy()-coords.getPosy());
						}
						
						/*if (mapabw.get((int) cerdito1.getPosx()+38, (int) cerdito1.getPosy()+50) == color(255,255,255)) {
							if(coords.getType().contains("dawn")) {
								cerdito1.setPosy(cerdito1.getPosy()+coords.getPosy());
							}
						}*/
					}
				}
				
				if (j1elef == true) {
					
					//direccion
					if(coords.getType().contains("left")) {
						elefantico1.setDir(2);
					}
					if(coords.getType().contains("right")) {
						elefantico1.setDir(1);
					}
					
					//disparo
					if(coords.getType().contains("paw")) {
						elefantico1.agregarBalas();
					}

					if (mapabw.get((int) elefantico1.getPosx()+35, (int) elefantico1.getPosy()+75) == color(255,255,255)) {
						
						elefantico1.setPosx(coords.getPosx());
						System.out.println("elef esta aqui  "+ elefantico1.getPosy());
						
						
						if(coords.getType().contains("ap")) {
							elefantico1.setPosy(elefantico1.getPosy()-coords.getPosy());
							noPuede = false;
						}else {
							noPuede = true;
						}
						
						new Thread(
			                    ()->{
			                    	while(noPuede == true){
			                    		if (mapabw.get((int) elefantico1.getPosx()+35, (int) elefantico1.getPosy()+75) == color(255,255,255)) {
			                    			//System.out.println("estoy bajando  "+gravity);
			                    			elefantico1.setPosy(elefantico1.getPosy()+gravity);
			                    		}
			                    		
			                    	try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			                    }
			                 }
			              ).start();
						
					} else {
						elefantico1.setPosx(coords.getPosx());
						
						noPuede = false;
						
						if(coords.getType().contains("ap")) {
							elefantico1.setPosy(elefantico1.getPosy()-coords.getPosy());
						}


					}
				}
				
				if (j1chic == true) {
					
					
					//direccion
					if(coords.getType().contains("left")) {
						pollito1.setDir(2);
					}
					if(coords.getType().contains("right")) {
						pollito1.setDir(1);
					}
					
					//disparo
					if(coords.getType().contains("paw")) {
						pollito1.agregarBalas();
					}
					
if 					(mapabw.get((int) pollito1.getPosx()+35, (int) pollito1.getPosy()+75) == color(255,255,255)) {
						
						pollito1.setPosx(coords.getPosx());
						System.out.println("chic esta aqui  "+ pollito1.getPosy());
						
						
						if(coords.getType().contains("ap")) {
							pollito1.setPosy(pollito1.getPosy()-coords.getPosy());
							noPuede = false;
						}else {
							noPuede = true;
						}
						
						new Thread(
			                    ()->{
			                    	while(noPuede == true){
			                    		if (mapabw.get((int) pollito1.getPosx()+35, (int) pollito1.getPosy()+75) == color(255,255,255)) {
			                    			//System.out.println("estoy bajando  "+gravity);
			                    			pollito1.setPosy(pollito1.getPosy()+gravity);
			                    		}
			                    		
			                    	try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			                    }
			                 }
			              ).start();
						
					} else {
						pollito1.setPosx(coords.getPosx());
						
						noPuede = false;
						
						if(coords.getType().contains("ap")) {
							pollito1.setPosy(pollito1.getPosy()-coords.getPosy());
						}


					}

				}
			}
			
			//si se mueve el jugador 2
			if (jugadors.contains("Jugador2")) {
				//moverJ2
				
				if (j2pig == true) {
					
					//direccion
					if(coords.getType().contains("left")) {
						cerdito2.setDir(2);
					}
					if(coords.getType().contains("right")) {
						cerdito2.setDir(1);
					}
					
					//disparo
					if(coords.getType().contains("paw")) {
						cerdito2.agregarBalas();

					}
					
					if (mapabw.get((int) cerdito2.getPosx()+35, (int) cerdito2.getPosy()+75) == color(255,255,255)) {
						
						cerdito2.setPosx(coords.getPosx());
						System.out.println("cerdo esta aqui  "+ cerdito2.getPosy());
						
						
						if(coords.getType().contains("ap")) {
							cerdito2.setPosy(cerdito2.getPosy()-coords.getPosy());
							noPuede2 = false;
						}else {
							noPuede2 = true;
						}
						
						new Thread(
			                    ()->{
			                    	while(noPuede2 == true){
			                    		if (mapabw.get((int) cerdito2.getPosx()+35, (int) cerdito2.getPosy()+75) == color(255,255,255)) {
			                    			//System.out.println("estoy bajando  "+gravity);
			                    			cerdito2.setPosy(cerdito2.getPosy()+gravity);
			                    		}
			                    		
			                    	try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			                    }
			                 }
			              ).start();
						
					
						
					} else {
						cerdito2.setPosx(coords.getPosx());
						
						noPuede2 = false;
						
						if(coords.getType().contains("ap")) {
							cerdito2.setPosy(cerdito2.getPosy()-coords.getPosy());
						}
						
						
					}
					
				}

				
				
				if (j2elef == true) {
				
					System.out.println(elefantico2.getPosy());
					
					//direccion
					if(coords.getType().contains("left")) {
						elefantico2.setDir(2);
					}
					if(coords.getType().contains("right")) {
						elefantico2.setDir(1);
					}
					
					//disparo
					if(coords.getType().contains("paw")) {
						elefantico2.agregarBalas();
					}
					
					if (mapabw.get((int) elefantico2.getPosx()+35, (int) elefantico2.getPosy()+75) == color(255,255,255)) {
						
						elefantico2.setPosx(coords.getPosx());
						System.out.println("ele2 esta aqui  "+ elefantico2.getPosy());
						
						
						if(coords.getType().contains("ap")) {
							elefantico2.setPosy(elefantico2.getPosy()-coords.getPosy());
							noPuede3 = false;
						}else {
							noPuede3 = true;
						}
						
						new Thread(
			                    ()->{
			                    	while(noPuede3 == true){
			                    		if (mapabw.get((int) elefantico2.getPosx()+35, (int) elefantico2.getPosy()+75) == color(255,255,255)) {
			                    			//System.out.println("estoy bajando  "+gravity);
			                    			elefantico2.setPosy(elefantico2.getPosy()+gravity);
			                    		}
			                    		
			                    	try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			                    }
			                 }
			              ).start();
						
					
						
					} else {
						elefantico2.setPosx(coords.getPosx());
						
						noPuede3 = false;
						
						if(coords.getType().contains("ap")) {
							elefantico2.setPosy(elefantico2.getPosy()-coords.getPosy());
						}
						
						
					}

					}	

				
				
				if (j2chic == true) {
					//movimiento
					pollito2.setPosx(coords.getPosx());
					pollito2.setPosy(coords.getPosy());
					
					//direccion
					if(coords.getType().contains("left")) {
						pollito2.setDir(2);
					}
					if(coords.getType().contains("right")) {
						pollito2.setDir(1);
					}
					
					//disparo
					if(coords.getType().contains("paw")) {
						pollito2.agregarBalas();
					}

					if (mapabw.get((int) pollito2.getPosx()+35, (int) pollito2.getPosy()+75) == color(255,255,255)) {
						
						pollito2.setPosx(coords.getPosx());
						System.out.println("chic2 esta aqui  "+ pollito2.getPosy());
						
						
						if(coords.getType().contains("ap")) {
							pollito2.setPosy(pollito2.getPosy()-coords.getPosy());
							noPuede4 = false;
						}else {
							noPuede4 = true;
						}
						
						new Thread(
			                    ()->{
			                    	while(noPuede4 == true){
			                    		if (mapabw.get((int) pollito2.getPosx()+35, (int) pollito2.getPosy()+75) == color(255,255,255)) {
			                    			//System.out.println("estoy bajando  "+gravity);
			                    			pollito2.setPosy(pollito2.getPosy()+gravity);
			                    		}
			                    		
			                    	try {
										Thread.sleep(30);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			                    }
			                 }
			              ).start();
						
					
						
					} else {
						pollito2.setPosx(coords.getPosx());
						
						noPuede4 = false;
						
						if(coords.getType().contains("ap")) {
							pollito2.setPosy(pollito2.getPosy()-coords.getPosy());
						}
						
						
					}
				}
			}
			
		break;
		}
	}
}
