package model;

import processing.core.PApplet;

public class Popo extends Bullet{

	public Popo(int px, int py, int dire, PApplet app) {
		super(px, py, dire, app);
		this.tipo = "popo";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pintar() {
		// TODO Auto-generated method stub
		app.fill(0);
		app.ellipse(px+20, py+30, 20, 20);
		
	}

}
