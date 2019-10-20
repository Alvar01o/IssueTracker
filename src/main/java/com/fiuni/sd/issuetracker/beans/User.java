package com.fiuni.sd.issuetracker.beans;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import org.hibernate.annotations.GenericGenerator;

@Entity
public class User extends BaseBean{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private String email;
	@Column
	private Date creacion;
	@Column
	private String pass;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Rol> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass(String pass) {
		return this.pass;
	}
	
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + nombre + ", lastName=" + apellido + ", email=" + email
				+ ", username=" + email + ", password=" + pass + ", roles=" + roles + "]";
	}
	
}
