package com.fiuni.sd.issuetracker.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



public class ProyectosDTO  extends BaseDTO{
	private String nombre;
	private GruposDTO grupo;
	//tableros
	public void setGrupo(GruposDTO g) {
		this.grupo = g;
	}

	public GruposDTO getGrupo() {
		return this.grupo;
	}
	
	public void setNombre(String n) {
		this.nombre = n;
	}

	public String getNombre() {
		return this.nombre;
	}
}
