package com.fiuni.sd.issuetracker.service.tablero;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fiuni.sd.issuetracker.dao.ITablerosDao;
import com.fiuni.sd.issuetracker.dao.ITareasDao;
import com.fiuni.sd.issuetracker.domain.Tableros;
import com.fiuni.sd.issuetracker.domain.Tareas;
import com.fiuni.sd.issuetracker.dto.TablerosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosResultDTO;
import com.fiuni.sd.issuetracker.dto.TareasDTO;
import com.fiuni.sd.issuetracker.dto.TareasResultDTO;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
public class TableroServiceImp extends BaseServiceImpl<TablerosDTO, Tableros, TablerosResultDTO> implements ITableroService {
	@Autowired
	private ITablerosDao tablerosDap;
	
	@Override
	public TablerosDTO save(TablerosDTO dto) {
		final Tableros t = convertDtoToDomain(dto);
		final Tableros td = tablerosDap.save(t);
		return convertDomainToDto(td);
	}

	@Override
	public TablerosDTO getById(Long id) {
		return convertDomainToDto(tablerosDap.findById(id.intValue()).get() );
	}

	@Override
	public TablerosResultDTO getAll(Pageable pageable) {
		final List<TablerosDTO> ts = new ArrayList<>();
		Page<Tableros> results=tablerosDap.findAll(pageable);
		results.forEach(us->ts.add(convertDomainToDto(us)));
		final TablerosResultDTO tResult = new TablerosResultDTO();
		tResult.setTableros(ts);
		return tResult;
	}

	@Override
	protected TablerosDTO convertDomainToDto(Tableros bean) {
		final TablerosDTO dto = new TablerosDTO();
		dto.setDescripcion(bean.getDescripcion());
		dto.setNombre(bean.getNombre());
		dto.setId(bean.getId());
		//set Tareas
		return dto;
	}

	@Override
	protected Tableros convertDtoToDomain(TablerosDTO dto) {
		Tableros T = new Tableros();
		T.setDescripcion(dto.getDescripcion());
		T.setId(dto.getId());
		T.setNombre(dto.getNombre());
		//set tareas
		return T;
	}

	@Override
	public TablerosResultDTO findALL(Pageable pageable, String search) {
		final List<TablerosDTO> tablrs = new ArrayList<>();
		Page<Tableros> results=tablerosDap.findByNombreIgnoreCaseOrDescripcionIgnoreCase(search, search, search, pageable);
		results.forEach(us->tablrs.add(convertDomainToDto(us)));
		final TablerosResultDTO tResult = new TablerosResultDTO();
		tResult.setTableros(tablrs);
		return tResult;
	}

}
