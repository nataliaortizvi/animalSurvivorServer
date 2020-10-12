package model;

import processing.core.PApplet;

public class Water extends Bullet{

	public Water(int px, int py, int dire, PApplet app) {
		super(px, py, dire, app);
		this.tipo = "water";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
	
		app.fill(100,355,120);
		app.ellipse(px+20, py+30, 20, 20);
		
	}

}
