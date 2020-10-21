package clasesEclipse;

import processing.core.PApplet;
import processing.core.PImage;

public class Elephant extends Character{
	
	PImage elefanteDer, elefanteIzq, elephantlife;
	String type = "elephant";

	
	//constructor
	public Elephant(float posx, float posy, PApplet app) {
		super(posx, posy, app);
		
		elefanteDer = app.loadImage("img/elefanteDer.png");
		elefanteIzq = app.loadImage("img/elefanteIzq.png");
		elephantlife =app.loadImage("img/elephanlife.png");
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
		
		app.image(elephantlife, 12, 19);
	}

	@Override
	public void agregarBalas() {
		// TODO Auto-generated method stub
		balas.add(new Water(posx, posy, this.dir, app));
		
	}
	

}
