package main;

import model.Chicken;
import model.Elephant;
import model.Pig;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
	
	int pantalla;
	PImage pantInicio, pantPlayer, pantControl, pantInstru, pantJuego, pantGanador, jugar, jugarP,
	instru, instruP, contro, controP, pasto;
	
	Pig cerdito;
	Chicken pollito;
	Elephant elefantico;
	
	TCPLauncher launcher;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");

	}
	
	public void settings() {
		size(1048,450);
	}
	
	public void setup() {
		//variables 
		pantalla = 4;
		cerdito = new Pig (300,345,this);
		pollito = new Chicken (600, 340, this);
		elefantico = new Elephant (450, 340, this);
		
		launcher = TCPLauncher.getInstance();
		launcher.setObserver(this);
		
		
		//imagenes
		pantInicio = loadImage("img/pantallaInicio.png");
		pantPlayer = loadImage("img/pantallaPlayer.png");
		//pantInstru = loadImage("img/");
		//pantControl = loadImage("img/");
		pantJuego = loadImage("img/pantallaJuego.png");
		pantGanador = loadImage("img/pantallaGanador.png");
		jugar = loadImage("img/jugar.png");
		jugarP = loadImage("img/jugarPress.png");
		instru = loadImage("img/instrucciones.png");
		instruP = loadImage("img/instruccionesPress.png");
		contro = loadImage("img/controles.png");
		controP = loadImage("img/controlesPress.png");
		pasto = loadImage("img/pasto.png");
		
		
	}
	
	public void draw() {
		background(255);
		
		
		switch(pantalla) {
		case 0:
			//pantalla inicio
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
			
			break;
		case 2:
			//pantalla instrucciones
			
			break;
		case 3:
			//pantalla elegir jugadores
			image(pantPlayer,0,0);
			
			break;
		case 4:
			//pantalla de juego
			image(pantJuego,0,0);
			
			for(int i = 0; i <launcher.getSesiones().size(); i++) {
				//Session sesion = launcher.getSesiones().get(i);
			}
			
			
			cerdito.pintar();
			cerdito.pintarBalas();
			
			pollito.pintar();
			pollito.pintarBalas();
			

			
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
	
	public void keyPressed() {
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
	}
	
	public void mousePressed(){
		switch(pantalla) {
		case 0:
			//pantalla inicio
			
			//boton jugar
			if(mouseX > 730 && mouseX < 940 && mouseY > 175 && mouseY < 220) {
				pantalla = 3;
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
		case 2:
			//pantalla instrucciones
			
			break;
		case 5:
			//pantalla ganador-perdedor
			
			break;
		}
		
	}

	public void mensajeLlegando(Session session, String line) {
		System.out.println(line);
		
	}
	

}
