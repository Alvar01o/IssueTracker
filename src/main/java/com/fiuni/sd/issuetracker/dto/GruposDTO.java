package com.fiuni.sd.issuetracker.dto;

import java.util.ArrayList;
import java.util.Date;

import com.fiuni.sd.issuetracker.beans.User;

public class GruposDTO extends BaseDTO {
	private String nombre;
	private Date creacion;
	private ArrayList<UserDTO> users = new ArrayList<UserDTO>();
	
	public ArrayList<UserDTO> getUsusarios(){
		return this.users;
	}
	
	public void agregarUsuario(UserDTO t) {
		this.users.set(t.getId(), t);
	}
	
	public boolean quitarUsuario(int id) {
		for(UserDTO tab : this.users) {
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
