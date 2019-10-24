package com.fiuni.sd.issuetracker.dto;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiuni.sd.issuetracker.domain.UserRoles;



public class UserDTO  extends BaseDTO{
	private String nombre;
	private String apellido;
	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	// Allows dd/MM/yyyy date to be passed into GET request in JSON
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date creacion;

	private String pass;

    private Set<UserRolDTO> user_roles;
    public void addRol(UserRolDTO r) {
		this.user_roles.add(r);
	}
	
	public Set<UserRolDTO> getUserRoles() {
		return user_roles;
	}

	public void setUserRoles(Set<UserRolDTO> roles) {
		this.user_roles = roles;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}		
	public String getPass() {
		return this.pass;
	}
	

	public void setNombre(String n) {
		this.nombre= n;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setApellido(String ap) {
		this.apellido = ap;
	}

	public String getApellido() {
		return this.apellido;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreacion() {
		return this.creacion;
	}
	
	public void setCreacion(Date c) {
		this.creacion = c;
	}
	@Override
	public String toString() {
		return "User [   nombre=" + nombre + ", lastName=" + apellido + ", email=" + email
				+ ", username=" + email + ", password=" + pass + ", user_roles=" + user_roles + "]";
	}
}
