package clasesEclipse;

import processing.core.PApplet;

public abstract class Bullet {
	
	//atributos
	protected int px, py, velo, dire;
	protected String tipo;
	protected PApplet app;
	
	//constructor
	public Bullet (int px, int py, int dire, PApplet app) {
		this.px = px;
		this.py = py;
		this.dire = dire;
		this.velo = 5;
		this.app = app;
	}
	
	public abstract void pintar();
	
	public void mover() {
		
		switch(dire) {
		case 1:
			px += velo;
			break;
		case 2:
			px -= velo;
			break;
		}
		
		
	}
	
	
	//getters y setters
	public int getPx() {
		return px;
	}

	public void setPx(int px) {
		this.px = px;
	}

	public int getPy() {
		return py;
	}

	public void setPy(int py) {
		this.py = py;
	}

	public int getVelo() {
		return velo;
	}

	public void setVelo(int velo) {
		this.velo = velo;
	}

	public int getDire() {
		return dire;
	}

	public void setDire(int dire) {
		this.dire = dire;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

}
