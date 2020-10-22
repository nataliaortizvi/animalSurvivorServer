package clasesEclipse;

import processing.core.PApplet;
import processing.core.PImage;

public class Eggs extends Bullet{
	
	PImage egg;

	public Eggs(float px, float py, int dire, PApplet app) {
		super(px, py, dire, app);
		this.tipo = "eggs";
		// TODO Auto-generated constructor stub
		
		egg = app.loadImage("img/egg.png");
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
		
		app.image(egg, this.px, this.py);
	}

}
