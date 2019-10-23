package com.fiuni.sd.issuetracker.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class TablerosResultDTO extends BaseResultDTO<TablerosDTO>{

	public List<TablerosDTO> getTareas() {
		return getList();
	}

	public void setTableros(List<TablerosDTO> dtos) {
		super.setList(dtos);
	}

	public void setLastPage(Integer lastPage) {
		super.setLastPage(lastPage);
	}

	private static final long serialVersionUID = 1L;
}
