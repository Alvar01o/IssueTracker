package com.fiuni.sd.issuetracker.service.proyecto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.issuetracker.dao.IGruposDao;
import com.fiuni.sd.issuetracker.dao.IProyectosDao;
import com.fiuni.sd.issuetracker.dao.ITablerosDao;
import com.fiuni.sd.issuetracker.domain.Grupos;
import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.domain.Tableros;
import com.fiuni.sd.issuetracker.domain.User;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosResultDTO;
import com.fiuni.sd.issuetracker.dto.TablerosDTO;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
import com.fiuni.sd.issuetracker.utils.Settings;

@Service
public class ProyectosServiceImp extends BaseServiceImpl<ProyectosDTO, Proyectos, ProyectosResultDTO> implements IProyectosService {
	@Autowired
	private IProyectosDao proyectosDao;
	@Autowired
	private IGruposDao gruposDao;
	@Autowired 
	private CacheManager cacheManager;
	@Autowired
	private ITablerosDao tablerosDao;
	@Override
	public ProyectosDTO save(ProyectosDTO dto) {
		final Proyectos t = convertDtoToDomain(dto);
		final Proyectos td = proyectosDao.save(t);
		ProyectosDTO us = convertDomainToDto(td);
		if(td.getId() == null) {
			cacheManager.getCache(Settings.CACHE_NAME).put("api_proyecto_"+td.getId()  , us );
		}
		return convertDomainToDto(td);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = Settings.CACHE_NAME , key = "'api_proyecto_'+ #id")
	public ProyectosDTO getById(Long id) {
		System.out.println(id);
		return convertDomainToDto(proyectosDao.findById(id.intValue()).get() );
	}

	@Override
	@Transactional(readOnly = true)	
	public ProyectosResultDTO getAll(Pageable pageable) {
		final List<ProyectosDTO> ts = new ArrayList<>();
		Page<Proyectos> results=proyectosDao.findAll(pageable);
		results.forEach(us->ts.add(convertDomainToDto(us)));
		final ProyectosResultDTO tResult = new ProyectosResultDTO();
		tResult.setProyectos(ts);
		return tResult;
	}

	@Override
	@Transactional(readOnly = true)
	public ProyectosResultDTO findALL(Pageable pageable, String search) {
		final List<ProyectosDTO> tablrs = new ArrayList<>();
		Page<Proyectos> results=proyectosDao.findByNombreIgnoreCaseOrDescripcionIgnoreCase(search, search, pageable);
		results.forEach((us)->{
			ProyectosDTO pdto = convertDomainToDto(us);
			tablrs.add(pdto);
			cacheManager.getCache(Settings.CACHE_NAME).put("api_proyecto_"+us.getId()  , pdto );			
		});
		final ProyectosResultDTO tResult = new ProyectosResultDTO();
		tResult.setProyectos(tablrs);
		return tResult;
	}

	@Override
	protected ProyectosDTO convertDomainToDto(Proyectos bean) {
		ProyectosDTO dto = new ProyectosDTO();
		dto.setId(bean.getId());
		dto.setNombre(bean.getNombre());
		dto.setDescripcion(bean.getDescripcion());
		if(bean.getGrupo()!=null) {
			GruposDTO g = new GruposDTO();
			g.setCreacion(bean.getGrupo().getCreacion());
			g.setId(bean.getGrupo().getId());
			g.setNombre(bean.getGrupo().getNombre());
			dto.setGrupo(g);
		}

		if(bean.getTableros() != null) {
			Set<TablerosDTO> tableros = new HashSet<TablerosDTO>();
			bean.getTableros().forEach((u) -> {
	        	   TablerosDTO us = new TablerosDTO();
	        	   us.setNombre(u.getNombre());
	        	   us.setDescripcion(u.getDescripcion());
	        	   us.setId(u.getId());
	        	   tableros.add(us);
	           });
	           if(!tableros.isEmpty()) {
	               dto.setTableros(tableros); // set Result on this vblock
	           }
		}
		return dto;
	}

	@Override
	protected Proyectos convertDtoToDomain(ProyectosDTO dto) {
		Proyectos P = new Proyectos();
		P.setId(dto.getId());
		P.setNombre(dto.getNombre());
		P.setDescripcion(dto.getDescripcion());
		GruposDTO g = dto.getGrupo();
		if(dto.getGrupo() != null) {
			Grupos g1 = new Grupos();
			g1.setId(g.getId());
			g1.setCreacion(g.getCreacion());
			g1.setNombre(g.getNombre());
			P.setGrupo(g1);
		} 
		//set tableros
		return P;
	}

	@Override
	public ProyectosResultDTO findByGrupoId(PageRequest pageable, Integer grupo_id) {
		final List<ProyectosDTO> tablrs = new ArrayList<>();
		Page<Proyectos> results=proyectosDao.findByGrupoId( grupo_id, pageable);
		results.forEach(us->tablrs.add(convertDomainToDto(us)));
		final ProyectosResultDTO tResult = new ProyectosResultDTO();
		tResult.setProyectos(tablrs);
		return tResult;
	}


}
