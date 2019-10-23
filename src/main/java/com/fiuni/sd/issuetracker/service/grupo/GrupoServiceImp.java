package com.fiuni.sd.issuetracker.service.grupo;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fiuni.sd.issuetracker.dao.IGruposDao;
import com.fiuni.sd.issuetracker.domain.Grupos;
import com.fiuni.sd.issuetracker.domain.Tableros;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.GruposResultDTO;
import com.fiuni.sd.issuetracker.dto.TablerosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosResultDTO;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
public class GrupoServiceImp extends BaseServiceImpl<GruposDTO, Grupos, GruposResultDTO> implements IGrupoService {
	@Autowired
	private IGruposDao gruposDao;
	
	
	@Override
	public GruposDTO save(GruposDTO dto) {
		final Grupos t = convertDtoToDomain(dto);
		final Grupos td = gruposDao.save(t);
		return convertDomainToDto(td);
	}

	@Override
	public GruposDTO getById(Long id) {
		return convertDomainToDto(gruposDao.findById(id.intValue()).get() );
	}

	@Override
	public GruposResultDTO getAll(Pageable pageable) {
		final List<GruposDTO> ts = new ArrayList<>();
		Page<Grupos> results=gruposDao.findAll(pageable);
		results.forEach(us->ts.add(convertDomainToDto(us)));
		final GruposResultDTO tResult = new GruposResultDTO();
		tResult.setGrupos(ts);
		return tResult;
	}

	@Override
	public GruposResultDTO findALL(Pageable pageable, String search) {
		final List<GruposDTO> tablrs = new ArrayList<>();
		Page<Grupos> results=gruposDao.findByNombreIgnoreCase(search,  pageable);
		results.forEach(us->tablrs.add(convertDomainToDto(us)));
		final GruposResultDTO tResult = new GruposResultDTO();
		tResult.setGrupos(tablrs);
		return tResult;
	}

	@Override
	protected GruposDTO convertDomainToDto(Grupos bean) {
		final GruposDTO dto = new GruposDTO();
		dto.setCreacion(bean.getCreacion());
		dto.setId(bean.getId());
		dto.setNombre(bean.getNombre());
		//set roles
		return dto;
	}

	@Override
	protected Grupos convertDtoToDomain(GruposDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
