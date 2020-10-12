package model;

import processing.core.PApplet;

public class Eggs extends Bullet{

	public Eggs(int px, int py, int dire, PApplet app) {
		super(px, py, dire, app);
		this.tipo = "eggs";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
		
		app.fill(255);
		app.ellipse(px+20, py+30, 20, 20);
	}

}
