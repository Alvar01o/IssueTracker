package com.fiuni.sd.issuetracker.service.proyecto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosResultDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;
import com.fiuni.sd.issuetracker.service.base.IBaseService;

public interface IProyectosService extends IBaseService<ProyectosDTO, Proyectos, ProyectosResultDTO>{
	public ProyectosResultDTO findALL(Pageable pageable ,String search);
	public ProyectosResultDTO findByGrupoId(PageRequest pageable, Integer grupo_id);
}
