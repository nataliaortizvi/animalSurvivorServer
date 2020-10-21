package clasesEclipse;

import processing.core.PApplet;
import processing.core.PImage;

public class Water extends Bullet{
	
	PImage water;

	public Water(float px, float py, int dire, PApplet app) {
		super(px, py, dire, app);
		this.tipo = "water";
		// TODO Auto-generated constructor stub
		
		water = app.loadImage("img/water.png");
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
	
		app.image(water, px, py);
		
	}

}
