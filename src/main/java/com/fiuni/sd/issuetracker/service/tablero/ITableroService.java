package com.fiuni.sd.issuetracker.service.tablero;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.issuetracker.domain.Tableros;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosResultDTO;
import com.fiuni.sd.issuetracker.service.base.IBaseService;

public interface ITableroService extends IBaseService<TablerosDTO, Tableros, TablerosResultDTO>{
	public TablerosResultDTO findALL(Pageable pageable ,String search);
	public TablerosDTO addTablero(TablerosDTO t , Long p );
}
