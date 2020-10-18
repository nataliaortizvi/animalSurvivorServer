package model;

import java.util.ArrayList;

import processing.core.PApplet;

public abstract class Character {
	
	//atributos
	protected int posx, posy, vel, dir, vida;
	protected String name;
	protected PApplet app;
	protected ArrayList <Bullet> balas;
	
	//constructor
	public Character(int posx, int posy, PApplet app) {
		this.posx = posx;
		this.posy = posy;
		this.vel = 2;
		this.dir = 1;
		this.vida = 100;
		this.app = app;
		
		balas = new ArrayList <Bullet>();
	}
	
	public void pintarBalas() {
		for(int i=0; i<balas.size(); i++) {
			balas.get(i).pintar();
			balas.get(i).mover();
			
			if(balas.get(i).getPx() > 1060) {
				balas.remove(i);
			}
		}
	}
	
	public abstract void agregarBalas();
	
	public abstract void pintar();
	
	//getters y setters
	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

}
