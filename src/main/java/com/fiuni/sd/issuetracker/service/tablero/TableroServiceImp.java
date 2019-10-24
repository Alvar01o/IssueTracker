package com.fiuni.sd.issuetracker.service.tablero;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fiuni.sd.issuetracker.dao.ITablerosDao;
import com.fiuni.sd.issuetracker.domain.Grupos;
import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.domain.Tableros;
import com.fiuni.sd.issuetracker.domain.Tareas;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosResultDTO;
import com.fiuni.sd.issuetracker.dto.TareasDTO;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;

@Service
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
	protected TablerosDTO convertDomainToDto(Tableros domain) {
		final TablerosDTO dto = new TablerosDTO();
		dto.setDescripcion(domain.getDescripcion());
		dto.setNombre(domain.getNombre());
		dto.setId(domain.getId());
		if(domain.getTareas() != null) {
			Set<TareasDTO> tareas = new HashSet<TareasDTO>();
	           domain.getTareas().forEach((u) -> {
	        	   TareasDTO t = new TareasDTO();
	        	   t.setId(u.getId());
	        	   t.setNombre(u.getNombre());
	        	   t.setDescripcion(u.getDescripcion());
	        	   t.setEstado(u.getEstado());
	        	   t.setPrioridad(u.getPrioridad());
	        	   t.setCreacion(u.getCreacion());
	        	   t.setLimite(u.getlimite());
	        	   tareas.add(t);
	           });
	           if(!tareas.isEmpty()) {
	               dto.setTareas(tareas); // set Result on this vblock
	           }
		}
		return dto;
	}

	/**
	 *
	 */
	@Override
	protected Tableros convertDtoToDomain(TablerosDTO dto) {
		Tableros T = new Tableros();
		T.setId(dto.getId());
		T.setDescripcion(dto.getDescripcion());
		T.setNombre(dto.getNombre());
		if(dto.getTareas() != null) {
			Set<Tareas> tareas = new HashSet<Tareas>();
			dto.getTareas().forEach((u) -> {
					Tareas t = new Tareas();
	        	   t.setId(u.getId());
	        	   t.setNombre(u.getNombre());
	        	   t.setDescripcion(u.getDescripcion());
	        	   t.setEstado(u.getEstado());
	        	   t.setPrioridad(u.getPrioridad());
	        	   t.setCreacion(u.getCreacion());
	        	   t.setLimite(u.getLimite());
	        	   tareas.add(t);
	           });
	           if(!tareas.isEmpty()) {
	               T.setTareas(tareas); // set Result on this vblock
	           }
		}
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
