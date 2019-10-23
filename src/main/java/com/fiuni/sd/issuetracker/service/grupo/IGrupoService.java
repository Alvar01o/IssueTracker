package com.fiuni.sd.issuetracker.service.grupo;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.issuetracker.domain.Grupos;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.GruposResultDTO;
import com.fiuni.sd.issuetracker.service.base.IBaseService;

public interface IGrupoService extends IBaseService<GruposDTO, Grupos, GruposResultDTO>{
	public GruposResultDTO findALL(Pageable pageable ,String search);
}
