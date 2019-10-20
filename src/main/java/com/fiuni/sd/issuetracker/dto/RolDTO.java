package com.fiuni.sd.issuetracker.dto;

public class RolDTO  extends BaseDTO {
	private int valor ;
	private String nombre;
	
	public void setNombre(String n) {
		this.nombre= n;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public void setValor(int i) {
		this.valor = i;
	}
	
	public int getValor() {
		return this.valor;
	}
}
