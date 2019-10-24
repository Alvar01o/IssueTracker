package com.fiuni.sd.issuetracker.service.tarea;
import com.fiuni.sd.issuetracker.service.base.IBaseService;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.issuetracker.domain.Tareas;
import com.fiuni.sd.issuetracker.dto.TareasDTO;
import com.fiuni.sd.issuetracker.dto.TareasResultDTO;



public interface ITareasService extends IBaseService<TareasDTO, Tareas, TareasResultDTO>{
	public TareasResultDTO findALL(Pageable pageable ,String search);

	public TareasDTO addTareaATablero(TareasDTO t, Long tablero_id);
}
