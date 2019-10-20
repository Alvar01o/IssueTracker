package com.fiuni.sd.issuetracker.service.base;

import com.fiuni.sd.issuetracker.beans.BaseBean;
import com.fiuni.sd.issuetracker.dto.BaseDTO;
import com.fiuni.sd.issuetracker.dto.BaseResultDTO;

public abstract class BaseServiceImpl<DTO extends BaseDTO, BEAN extends BaseBean, R extends BaseResultDTO<DTO>>
		implements IBaseService<DTO, BEAN, R> {

	protected abstract DTO convertBeanToDto(BEAN bean);

	protected abstract BEAN convertDtoToBean(DTO dto);
}
