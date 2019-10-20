package com.fiuni.sd.issuetracker.domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;

public class Grupos extends BaseDomain {
	private String nombre;
	private Date creacion;
	private ArrayList<User> users = new ArrayList<User>();
	
	public ArrayList<User> getUsuarios(){
		return this.users;
	}
	
	public void agregarUsuario(User t) {
		this.users.set(t.getId(), t);
	}
	
	public boolean quitarUsuario(int id) {
		for(User tab : this.users) {
			if(tab.getId() == id) {
				this.users.remove(id);
				return true;
			}
		}
		return false;
	}
	
	public void setNombre(String n) {
		this.nombre= n;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Date getCreacion() {
		return this.creacion;
	}
	
	public void setCreacion(Date c) {
		this.creacion = c;
	}
	
}
