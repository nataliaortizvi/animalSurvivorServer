package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Popo extends Bullet{
	
	PImage poop;

	public Popo(int px, int py, int dire, PApplet app) {
		super(px, py, dire, app);
		this.tipo = "popo";
		// TODO Auto-generated constructor stub
		
		poop = app.loadImage("img/poop.png");
		
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
		app.image(poop, px,py);
		
	}

}
