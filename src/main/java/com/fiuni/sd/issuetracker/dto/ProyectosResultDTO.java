package com.fiuni.sd.issuetracker.dto;

import java.util.List;

public class ProyectosResultDTO extends BaseResultDTO<ProyectosDTO>{

	public List<ProyectosDTO> getProyectos() {
		return getList();
	}

	public void setProyectos(List<ProyectosDTO> dtos) {
		super.setList(dtos);
	}

	public void setLastPage(Integer lastPage) {
		super.setLastPage(lastPage);
	}

	private static final long serialVersionUID = 1L;
}
