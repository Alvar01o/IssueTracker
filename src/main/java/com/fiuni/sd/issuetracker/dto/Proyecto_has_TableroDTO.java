package com.fiuni.sd.issuetracker.dto;

public class Proyecto_has_TableroDTO extends BaseDTO{
	private ProyectosDTO p;
	private TablerosDTO t;

	public TablerosDTO getTablero() {
		return this.t;
	}
	
	public void setTablero(TablerosDTO t) {
		this.t = t;
	}
	
	public void setProyecto(ProyectosDTO pr) {
		this.p = pr;
	}
	
	public ProyectosDTO getProyecto() {
		return this.p;
	}
}
