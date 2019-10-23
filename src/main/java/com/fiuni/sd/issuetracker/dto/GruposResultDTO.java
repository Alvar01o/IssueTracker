package com.fiuni.sd.issuetracker.dto;

import java.util.List;

public class GruposResultDTO extends BaseResultDTO<GruposDTO>{

	public List<GruposDTO> getGrupos() {
		return getList();
	}

	public void setGrupos(List<GruposDTO> dtos) {
		super.setList(dtos);
	}

	public void setLastPage(Integer lastPage) {
		super.setLastPage(lastPage);
	}

	private static final long serialVersionUID = 1L;
}
