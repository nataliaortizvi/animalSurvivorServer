package clasesEclipse;

import java.util.ArrayList;

import processing.core.PApplet;

public abstract class Character {
	
	//atributos
	protected float posx, posy;
	protected int vel, dir, vida;
	protected String type;
	protected PApplet app;
	protected ArrayList <Bullet> balas;
	
	//constructor
	public Character(float posx, float posy, PApplet app) {
		this.posx = posx;
		this.posy = posy;
		this.vel = 2;
		this.dir = 1;
		this.vida = 93;
		this.app = app;
		
		balas = new ArrayList <Bullet>();
	}
	
	
	public void pintarBalas() {
		for(int i=0; i<balas.size(); i++) {
			balas.get(i).pintar();
			balas.get(i).mover();
			
			if(balas.get(i).getPx() > 1500 || balas.get(i).getPx() < -50) {
				balas.remove(i);
			}
			
		}
	}
	
	public void quitarBala() {
		for(int i=0; i<balas.size(); i++) {
		balas.remove(i);
		}
	}
	
	public int getVida() {
		return vida;
	}


	public void setVida(int vida) {
		this.vida = vida;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public ArrayList<Bullet> getBalas() {
		return balas;
	}


	public void setBalas(ArrayList<Bullet> balas) {
		this.balas = balas;
	}


	public abstract void agregarBalas();
	
	public abstract void pintar();
	
	//getters y setters
	public float getPosx() {
		return posx;
	}

	public void setPosx(float posx) {
		this.posx = posx;
	}

	public float getPosy() {
		return posy;
	}

	public void setPosy(float posy) {
		this.posy = posy;
	}

	public int getVel() {
		return vel;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

}
