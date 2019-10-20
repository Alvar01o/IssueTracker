package com.fiuni.sd.issuetracker.service.base;

import com.fiuni.sd.issuetracker.beans.BaseBean;
import com.fiuni.sd.issuetracker.dto.BaseDTO;
import com.fiuni.sd.issuetracker.dto.BaseResultDTO;

public interface IBaseService<DTO extends BaseDTO, BEAN extends BaseBean, R extends BaseResultDTO<DTO>> {

	public DTO save(DTO dto);

	public DTO getById(Long id);

	public R getAll(Integer page, Integer size);

}
