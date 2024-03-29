package com.fiuni.sd.issuetracker.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


public class UserResultDTO extends BaseResultDTO<UserDTO>{

	public List<UserDTO> getUsers() {
		return getList();
	}

	public void setUsers(List<UserDTO> dtos) {
		super.setList(dtos);
	}

	public void setLastPage(Integer lastPage) {
		super.setLastPage(lastPage);
	}

	private static final long serialVersionUID = 1L;

}
