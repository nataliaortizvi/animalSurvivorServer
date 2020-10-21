package clasesEclipse;

import processing.core.PApplet;

public abstract class Bullet {
	
	//atributos
	protected float px, py;
	protected int velo, dire;
	protected String tipo;
	protected PApplet app;
	
	//constructor
	public Bullet (float posx, float posy, int dire, PApplet app) {
		this.px = posx;
		this.py = posy;
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
	public float getPx() {
		return px;
	}

	public void setPx(float px) {
		this.px = px;
	}

	public float getPy() {
		return py;
	}

	public void setPy(float py) {
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
