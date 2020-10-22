package clasesEclipse;

import processing.core.PApplet;
import processing.core.PImage;

public class Pig extends Character{
	
	PImage cerdoDer, cerdoIzq, piglife; 
	String type = "pig";

	//constructor
	public Pig(float posx, float posy, PApplet app) {
		super(posx, posy, app);
		
		cerdoDer = app.loadImage("img/cerdoDer.png");
		cerdoIzq = app.loadImage("img/cerdoIzq.png");
		piglife = app.loadImage("img/piglife.png");
		
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
