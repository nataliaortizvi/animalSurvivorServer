package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Elephant extends Character{
	
	PImage elefanteDer, elefanteIzq;

	public Elephant(int posx, int posy, PApplet app) {
		super(posx, posy, app);
		this.name = "elephant";
		
		elefanteDer = app.loadImage("img/elefanteDer.png");
		elefanteIzq = app.loadImage("img/elefanteIzq.png");
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
		//mirando a la derecha
		if(dir == 1) {
			app.image(elefanteDer, posx, posy,80,90);
		}
						
		//mirando a la izquierda
		if(dir == 2) {
			app.image(elefanteIzq, posx, posy,80,90);
		}
		
	}

	@Override
	public void agregarBalas() {
		// TODO Auto-generated method stub
		balas.add(new Water(posx, posy, this.dir, app));
		
	}
	

}
