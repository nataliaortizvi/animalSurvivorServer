package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Pig extends Character{
	
	PImage cerdoDer, cerdoIzq;

	public Pig(int posx, int posy, PApplet app) {
		super(posx, posy, app);
		this.name = "pig";
		
		cerdoDer = app.loadImage("img/cerdoDer.png");
		cerdoIzq = app.loadImage("img/cerdoIzq.png");
		
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
		
		//mirando a la derecha
		if(dir == 1) {
			app.image(cerdoDer, posx, posy,80,80);
		}
		
		//mirando a la izquierda
		if(dir == 2) {
			app.image(cerdoIzq, posx, posy,80,80);
		}
		
	}

	@Override
	public void agregarBalas() {
		// TODO Auto-generated method stub
		balas.add(new Popo(posx, posy, this.dir, app));
		
	}

}
