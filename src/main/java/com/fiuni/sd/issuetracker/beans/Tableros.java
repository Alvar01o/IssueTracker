package com.fiuni.sd.issuetracker.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.Set;


@Entity
public class Tableros extends BaseBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	@Column
	private String nombre; 
	@Column
	private String descripcion;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tablero_tareas", joinColumns = @JoinColumn(name = "tablero_id"), inverseJoinColumns = @JoinColumn(name = "tarea_id"))
	private Set<Tareas> tareas;
	
	public void setNombre(String n) {
		this.nombre= n;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setDescripcion(String d) {
		this.descripcion= d;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}	

	public Set<Tareas> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tareas> tareas) {
		this.tareas = tareas;
	}

	@Override
	public String toString() {
		return "Tablero [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + " , tareas= "+tareas+"]";
	}
}
