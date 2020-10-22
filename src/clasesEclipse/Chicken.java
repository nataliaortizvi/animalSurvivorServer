package clasesEclipse;

import processing.core.PApplet;
import processing.core.PImage;

public class Chicken extends Character{
	
	PImage polloDer, polloIzq, gallolife;
	String type = "chicken";

	//constructor
	public Chicken(float posx, float posy, PApplet app) {
		super(posx, posy, app);
		
		polloDer = app.loadImage("img/galloDer.png");
		polloIzq = app.loadImage("img/galloIzq.png");
		gallolife = app.loadImage("img/gallolife.png");
		
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
		balas.add(new Eggs(this.posx, this.posy, this.dir, app));
		
		
	}

}
