package com.fiuni.sd.issuetracker.service.base;

import com.fiuni.sd.issuetracker.domain.BaseDomain;
import com.fiuni.sd.issuetracker.dto.BaseDTO;
import com.fiuni.sd.issuetracker.dto.BaseResultDTO;

public abstract class BaseServiceImpl<DTO extends BaseDTO, BEAN extends BaseDomain, R extends BaseResultDTO<DTO>>
		implements IBaseService<DTO, BEAN, R> {

	protected abstract DTO convertDomainToDto(BEAN bean);

	protected abstract BEAN convertDtoToDomain(DTO dto);
}
