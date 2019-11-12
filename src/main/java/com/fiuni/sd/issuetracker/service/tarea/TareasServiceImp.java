package com.fiuni.sd.issuetracker.service.tarea;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.issuetracker.dao.ITablerosDao;
import com.fiuni.sd.issuetracker.dao.ITareasDao; 
import com.fiuni.sd.issuetracker.domain.Tableros;
import com.fiuni.sd.issuetracker.domain.Tareas;
import com.fiuni.sd.issuetracker.dto.TareasDTO;
import com.fiuni.sd.issuetracker.dto.TareasResultDTO;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
import com.fiuni.sd.issuetracker.utils.Settings;
@Service
public class TareasServiceImp extends BaseServiceImpl<TareasDTO, Tareas, TareasResultDTO> implements ITareasService {
	@Autowired
	private ITareasDao tareasDap;
	@Autowired
	private ITablerosDao tablerosDap;
	@Autowired 
	private CacheManager cacheManager;
	
	@Override
	public TareasDTO save(TareasDTO dto) {
		final Tareas t = convertDtoToDomain(dto);
		final Tareas td = tareasDap.save(t);
		TareasDTO us = convertDomainToDto(td);
		if(us.getId() == null) {
			cacheManager.getCache(Settings.CACHE_NAME).put("api_tarea_"+us.getId()  , us );
		}
		return convertDomainToDto(td);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = Settings.CACHE_NAME , key = "'api_tarea_'+ #id")
	public TareasDTO getById(Long id) {
		return convertDomainToDto(tareasDap.findById(id.intValue()).get() );
	}

	@Override
	public TareasResultDTO getAll(Pageable pageable) {
		final List<TareasDTO> ts = new ArrayList<>();
		Page<Tareas> results=tareasDap.findAll(pageable);
		results.forEach(us->ts.add(convertDomainToDto(us)));
		final TareasResultDTO tResult = new TareasResultDTO();
		tResult.setTareas(ts);
		return tResult;
	}

	@Override
	@Transactional(readOnly = true) 
	public TareasResultDTO findALL(Pageable pageable, String search) {
		final List<TareasDTO> users = new ArrayList<>();
		Page<Tareas> results=tareasDap.findByNombreIgnoreCaseOrDescripcionIgnoreCase(search, search, pageable);
		results.forEach((us)-> {
			TareasDTO ud = convertDomainToDto(us);
			users.add(convertDomainToDto(us));
			cacheManager.getCache(Settings.CACHE_NAME).put("api_tarea_"+ud.getId()  , ud );
		}
		);
		final TareasResultDTO tResult = new TareasResultDTO();
		tResult.setTareas(users);
		return tResult;
	}

	@Override
	protected TareasDTO convertDomainToDto(Tareas domain) {
		final TareasDTO dto = new TareasDTO();
		dto.setNombre(domain.getNombre());
		dto.setDescripcion(domain.getDescripcion());
		dto.setCreacion(domain.getCreacion());
		dto.setEstado(domain.getEstado());
		dto.setId(domain.getId());
		dto.setLimite(domain.getlimite());
		dto.setPrioridad(domain.getPrioridad());
		return dto;
	}

	@Override
	protected Tareas convertDtoToDomain(TareasDTO dto) {
		Tareas t = new Tareas();
		t.setCreacion(dto.getCreacion());
		t.setDescripcion(dto.getDescripcion());
		t.setEstado(dto.getEstado());
		t.setId(dto.getId());
		t.setNombre(dto.getNombre());
		t.setLimite(dto.getLimite());
		t.setPrioridad(dto.getPrioridad());
		return t;
	}

	@Override
	public TareasDTO addTareaATablero(TareasDTO t, Long tablero_id) {
		Tableros tablero = tablerosDap.findById(tablero_id.intValue()).get();
		Tareas ta = new Tareas();
		ta.setCreacion(t.getCreacion());
		ta.setDescripcion(t.getDescripcion());
		ta.setEstado(t.getEstado());
		ta.setPrioridad(t.getPrioridad());
		ta.setLimite(t.getLimite());
		ta.setNombre(t.getNombre());
		ta.setId(t.getId());
		Tareas tf = tareasDap.save(ta);
		tablero.addTarea(ta);
		tablerosDap.save(tablero);
		return convertDomainToDto(tf);
	}

}
