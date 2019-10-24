package com.fiuni.sd.issuetracker.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
@Entity
public class Grupos extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	@Column
	private String nombre;
	@Basic(optional = false)
	@CreationTimestamp
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creacion;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "grupos_users", joinColumns = @JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> usuarios;

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

	public Date getCreacion() {
		return this.creacion;
	}
	
	public void setCreacion(Date c) {
		this.creacion = c;
	}
	public void setUsers(Set<User> users) {
		this.usuarios =users;
	}
	public Set<User> getUsers() {
		return usuarios;
	}
	 public void addUser(User r) {
			this.usuarios.add(r);
		}
	@Override
	public String toString() {
		return "Grupo [id=" + id + ", nombre=" + nombre + ", creacion=" + creacion + ", usuarios=" + usuarios+ "]";
	}
	
}
