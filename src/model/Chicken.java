package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Chicken extends Character{
	
	PImage polloDer, polloIzq;

	public Chicken(int posx, int posy, PApplet app) {
		super(posx, posy, app);
		this.name = "chicken";
		
		polloDer = app.loadImage("img/galloDer.png");
		polloIzq = app.loadImage("img/galloIzq.png");
		
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
		
		//mirando a la derecha
		if(dir == 1) {
			app.image(polloDer, posx, posy,60,90);
		}
				
		//mirando a la izquierda
		if(dir == 2) {
			app.image(polloIzq, posx, posy,60,90);
		}
		
	}

	@Override
	public void agregarBalas() {
		// TODO Auto-generated method stub
		balas.add(new Eggs(posx, posy, this.dir, app));
		
		
	}

}
