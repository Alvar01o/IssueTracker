package com.fiuni.sd.issuetracker.service.proyecto;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.issuetracker.dao.IGruposDao;
import com.fiuni.sd.issuetracker.dao.IProyectosDao;
import com.fiuni.sd.issuetracker.domain.Grupos;
import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosResultDTO;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;

@Service
public class ProyectosServiceImp extends BaseServiceImpl<ProyectosDTO, Proyectos, ProyectosResultDTO> implements IProyectosService {
	@Autowired
	private IProyectosDao proyectosDao;
	@Autowired
	private IGruposDao gruposDao;
	@Override
	public ProyectosDTO save(ProyectosDTO dto) {
		final Proyectos t = convertDtoToDomain(dto);
		final Proyectos td = proyectosDao.save(t);
		return convertDomainToDto(td);
	}

	@Override
	public ProyectosDTO getById(Long id) {
		return convertDomainToDto(proyectosDao.findById(id.intValue()).get() );
	}

	@Override
	public ProyectosResultDTO getAll(Pageable pageable) {
		final List<ProyectosDTO> ts = new ArrayList<>();
		Page<Proyectos> results=proyectosDao.findAll(pageable);
		results.forEach(us->ts.add(convertDomainToDto(us)));
		final ProyectosResultDTO tResult = new ProyectosResultDTO();
		tResult.setProyectos(ts);
		return tResult;
	}

	@Override
	public ProyectosResultDTO findALL(Pageable pageable, String search) {
		final List<ProyectosDTO> tablrs = new ArrayList<>();
		Page<Proyectos> results=proyectosDao.findByNombreIgnoreCaseOrDescripcionIgnoreCase(search, search, pageable);
		results.forEach(us->tablrs.add(convertDomainToDto(us)));
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
		GruposDTO g = new GruposDTO();
		g.setCreacion(bean.getGrupo().getCreacion());
		g.setId(bean.getGrupo().getId());
		g.setNombre(bean.getGrupo().getNombre());
		dto.setGrupo(g);
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
		} else {
			System.out.println("no hay grupo");
		}
		//set tableros
		return P;
	}

}
