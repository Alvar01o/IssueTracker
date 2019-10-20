package com.fiuni.sd.issuetracker.domain;

import java.util.ArrayList;

import javax.persistence.Entity;

public class Proyectos extends BaseDomain{
	private String nombre;
	private Grupos grupo;
	
	private ArrayList<Tableros> tableros = new ArrayList<Tableros>();
	
	public ArrayList<Tableros> getTableros(){
		return this.tableros;
	}
	
	public void agregarTablero(Tableros t) {
		this.tableros.set(t.getId(), t);
	}
	
	public boolean quitarTablero(int id) {
		for(Tableros tab : this.tableros) {
			if(tab.getId() == id) {
				this.tableros.remove(id);
				return true;
			}
		}
		return false;
	}
	
	public void setGrupo(Grupos g) {
		this.grupo = g;
	}
	
	public Grupos getGrupo() {
		return this.grupo;
	}
	
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
