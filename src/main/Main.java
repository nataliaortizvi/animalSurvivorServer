package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
	
	int pantalla;
	PImage pantInicio, pantPlayer, pantControl, pantInstru, pantJuego, pantGanador;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");

	}
	
	public void settings() {
		size(1048,450);
	}
	
	public void setup() {
		//variables 
		pantalla = 0;
		
		//imagenes
		pantInicio = loadImage("img/pantallaInicio.png");
		pantPlayer = loadImage("img/pantallaPlayer.png");
		//pantInstru = loadImage("img/");
		//pantControl = loadImage("img/");
		pantJuego = loadImage("img/pantallaJuego.png");
		pantGanador = loadImage("img/pantallaGanador.png");
		
		
	}
	
	public void draw() {
		background(255);
		
		switch(pantalla) {
		case 0:
			//pantalla inicio
			image(pantInicio,0,0);
			
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
			
			break;
		case 5:
			//pantalla ganador-perdedor
			image(pantGanador,0,0);
			
			break;
		}
		
	}
	
	public void mousePressed(){
		switch(pantalla) {
		case 0:
			//pantalla inicio
			
			break;
		case 2:
			//pantalla instrucciones
			
			break;
		case 5:
			//pantalla ganador-perdedor
			
			break;
		}
		
	}
	

}
