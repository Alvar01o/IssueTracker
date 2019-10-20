package com.fiuni.sd.issuetracker.dto;

import java.util.ArrayList;

import com.fiuni.sd.issuetracker.beans.Tableros;

public class ProyectosDTO  extends BaseDTO{
	private String nombre;
	private GruposDTO grupo;
	private ArrayList<TablerosDTO> tableros = new ArrayList<TablerosDTO>();
	
	public ArrayList<TablerosDTO> getTableros(){
		return this.tableros;
	}
	
	public void agregarTablero(TablerosDTO t) {
		this.tableros.set(t.getId(), t);
	}
	
	public boolean quitarTablero(int id) {
		for(TablerosDTO tab : this.tableros) {
			if(tab.getId() == id) {
				this.tableros.remove(id);
				return true;
			}
		}
		return false;
	}	
	public void setGrupo(GruposDTO g) {
		this.grupo = g;
	}
	
	public GruposDTO getGrupo() {
		return this.grupo;
	}
	
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
