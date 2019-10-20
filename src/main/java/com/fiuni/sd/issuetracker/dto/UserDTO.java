package com.fiuni.sd.issuetracker.dto;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.issuetracker.domain.Rol;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;
import java.util.Set;


@XmlRootElement(name = "user")
public class UserDTO  extends BaseDTO{
	private String nombre;
	private String apellido;
	private String email;
	private Date creacion;
	private Set<RolDTO> roles;
	private String pass;
	@XmlElement
	public Set<RolDTO> getRoles() {
		return roles;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	@XmlElement
	public String getPass() {
		return this.pass;
	}
	
	public void setRoles(Set<RolDTO> roles) {
		this.roles = roles;
	}
	public void setNombre(String n) {
		this.nombre= n;
	}
	@XmlElement
	public String getNombre() {
		return this.nombre;
	}
	
	public void setApellido(String ap) {
		this.apellido = ap;
	}
	@XmlElement
	public String getApellido() {
		return this.apellido;
	}
	@XmlElement
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public Date getCreacion() {
		return this.creacion;
	}
	
	public void setCreacion(Date c) {
		this.creacion = c;
	}

}
