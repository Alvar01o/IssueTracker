package com.fiuni.sd.issuetracker.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
public abstract class BaseDTO implements Serializable  {

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		_id = id;
	}


	private Integer _id;

}
