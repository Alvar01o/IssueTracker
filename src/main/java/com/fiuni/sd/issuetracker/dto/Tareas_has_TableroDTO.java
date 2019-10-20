package com.fiuni.sd.issuetracker.dto;

public class Tareas_has_TableroDTO  extends BaseDTO{
	private TareasDTO tarea;
	private TablerosDTO tablero;

	public void setTablero(TablerosDTO tabl) {
		this.tablero = tabl;
	}
	
	public void setTarea(TareasDTO t) {
		this.tarea = t;
	}
	
	public TablerosDTO getTablero() {
		return this.tablero;
	}
	
	public TareasDTO getTarea() {
		return this.tarea;
	}
}
