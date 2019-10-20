package com.fiuni.sd.issuetracker.dto;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;


@XmlRootElement(name = "user")
public class UserDTO  extends BaseDTO{
	private String nombre;
	private String apellido;
	private String email;
	private Date creacion;
	
	private RolDTO rol;
	
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
	@XmlElement
	public RolDTO getRol () {
		return this.rol;
	}
	
	public void setRol(RolDTO rol) {
		this.rol = rol;
	}
}
