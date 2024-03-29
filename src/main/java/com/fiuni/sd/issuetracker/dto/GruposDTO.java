package com.fiuni.sd.issuetracker.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiuni.sd.issuetracker.domain.User;

public class GruposDTO extends BaseDTO {
	private String nombre;
	@JsonFormat(pattern = "dd/MM/yyyy")
	// Allows dd/MM/yyyy date to be passed into GET request in JSON
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date creacion;
	//usuarios
    private Set<UserDTO> users;
    public void addUser(UserDTO r) {
		this.users.add(r);
	}
	public void setUsers(Set<UserDTO> us) {
		this.users = us;
	}	
	public Set<UserDTO> getUsers() {
		return users;
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

	@Override
	public String toString() {
		return "Grupo [nombre=" + nombre + ", creacion=" + creacion + "]";
	}	
}
