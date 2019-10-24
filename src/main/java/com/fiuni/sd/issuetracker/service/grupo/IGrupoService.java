package com.fiuni.sd.issuetracker.service.grupo;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.issuetracker.domain.Grupos;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.GruposResultDTO;
import com.fiuni.sd.issuetracker.service.base.IBaseService;
@Service
public interface IGrupoService extends IBaseService<GruposDTO, Grupos, GruposResultDTO>{
	public GruposResultDTO findALL(Pageable pageable ,String search);
	public GruposDTO addUserToGrupo(int grupo_id,int user_id);
}
