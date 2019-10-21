package com.fiuni.sd.issuetracker.domain;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Proyectos extends BaseDomain{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	@Column
	private String nombre;
	
    @JoinColumn(name = "grupo_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Grupos grupo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "proyecto_tableros", joinColumns = @JoinColumn(name = "proyecto_id"), inverseJoinColumns = @JoinColumn(name = "tablero_id"))
	private Set<Tableros> tableros;
	
	public void setGrupo(Grupos g) {
		this.grupo = g;
	}
	
	public Grupos getGrupo() {
		return this.grupo;
	}
	
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Set<Tableros> getTareas() {
		return tableros;
	}

	public void setTareas(Set<Tableros> tableros) {
		this.tableros = tableros;
	}

	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", nombre=" + nombre + ", grupo_id=" + grupo + ", tableros=" + tableros + "]";
	}
}
