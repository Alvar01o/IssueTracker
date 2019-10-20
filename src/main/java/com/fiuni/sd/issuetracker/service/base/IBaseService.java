package com.fiuni.sd.issuetracker.service.base;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.issuetracker.domain.BaseDomain;
import com.fiuni.sd.issuetracker.dto.BaseDTO;
import com.fiuni.sd.issuetracker.dto.BaseResultDTO;

public interface IBaseService<DTO extends BaseDTO, BEAN extends BaseDomain, R extends BaseResultDTO<DTO>> {

	public DTO save(DTO dto);

	public DTO getById(Long id);

	public R getAll(Pageable pageable);

}
