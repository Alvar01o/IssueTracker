package com.fiuni.sd.issuetracker.dto;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


public class TareasResultDTO extends BaseResultDTO<TareasDTO>{

	public List<TareasDTO> getTareas() {
		return getList();
	}

	public void setTareas(List<TareasDTO> dtos) {
		super.setList(dtos);
	}

	public void setLastPage(Integer lastPage) {
		super.setLastPage(lastPage);
	}

	private static final long serialVersionUID = 1L;
}
