package com.fiuni.sd.issuetracker.service.grupo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.issuetracker.dao.IGruposDao;
import com.fiuni.sd.issuetracker.dao.IUserDao;
import com.fiuni.sd.issuetracker.domain.Grupos;
import com.fiuni.sd.issuetracker.domain.Tableros;
import com.fiuni.sd.issuetracker.domain.User;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.GruposResultDTO;

import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;
import com.fiuni.sd.issuetracker.service.user.IUserService;
import com.fiuni.sd.issuetracker.utils.Settings;
@Service
public class GrupoServiceImp extends BaseServiceImpl<GruposDTO, Grupos, GruposResultDTO> implements IGrupoService {
	@Autowired
	private IGruposDao gruposDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserService userService;	
	@Autowired 
	private CacheManager cacheManager;
	
	@Override
	public GruposDTO save(GruposDTO dto) {
		final Grupos t = convertDtoToDomain(dto); 
		final Grupos td = gruposDao.save(t);
		GruposDTO us = convertDomainToDto(td);
		if(dto.getId() == null) {
			cacheManager.getCache(Settings.CACHE_NAME).put("api_grupo_"+us.getId()  , us );
		}
		return convertDomainToDto(td);
	}

	@Override
	@Cacheable(value = Settings.CACHE_NAME , key = "'api_grupo_'+ #id")
	public GruposDTO getById(Long id) {
		return convertDomainToDto(gruposDao.findById(id.intValue()).get() );
	}
	
	@Override
	@Transactional(readOnly = true)
	public GruposResultDTO getAll(Pageable pageable) {
		final List<GruposDTO> ts = new ArrayList<>();
		final GruposResultDTO gResult = new GruposResultDTO();
		Page<Grupos> results=gruposDao.findAll(pageable);
		results.getContent().forEach((us)->{
			GruposDTO gdt = convertDomainToDto(us);
			ts.add(gdt);
			cacheManager.getCache(Settings.CACHE_NAME).put("api_grupo_"+us.getId()  , gdt );
		}
		);

		gResult.setGrupos(ts);
		gResult.setCurrentPage(results.getNumber());
		gResult.setLastPage(results.getTotalPages());
		gResult.setCurrentPageTotalItems(results.getNumberOfElements());
		gResult.setTotalItems(results.getTotalElements());
		return gResult;
	}

	@Override
	@Transactional(readOnly = true)
	public GruposResultDTO findALL(Pageable pageable, String search) {
		final List<GruposDTO> tablrs = new ArrayList<>();
		Page<Grupos> results=gruposDao.findByNombreIgnoreCase(search,  pageable);
		results.forEach(us->tablrs.add(convertDomainToDto(us)));
		final GruposResultDTO tResult = new GruposResultDTO();
		tResult.setGrupos(tablrs);
		return tResult;
	}

	@Override
	protected GruposDTO convertDomainToDto(Grupos domain) {
		final GruposDTO dto = new GruposDTO();
		dto.setCreacion(domain.getCreacion());
		dto.setId(domain.getId());
		dto.setNombre(domain.getNombre());
		if(domain.getUsers() != null) {
			Set<UserDTO> usuarios = new HashSet<UserDTO>();
	           domain.getUsers().forEach((u) -> {
	        	   UserDTO us = new UserDTO();
	        	   us.setNombre(u.getNombre());
	        	   us.setApellido(u.getApellido());
	        	   us.setEmail(u.getEmail());
	        	   us.setCreacion(u.getCreacion());
	        	   us.setId(u.getId());
	        	   usuarios.add(us);
	           });
	           if(!usuarios.isEmpty()) {
	               dto.setUsers(usuarios); // set Result on this vblock
	           }
		}
		return dto;
	}

	@Override
	protected Grupos convertDtoToDomain(GruposDTO dto) {
		Grupos  g =new Grupos();
		g.setCreacion(dto.getCreacion());
		g.setId(dto.getId());
		g.setNombre(dto.getNombre());
		if(dto.getUsers() != null) {
			Set<User> usuarios = new HashSet<User>();
			dto.getUsers().forEach((u) -> {
				User us = new User();
	        	   us.setNombre(u.getNombre());
	        	   us.setApellido(u.getApellido());
	        	   us.setEmail(u.getEmail());
	        	   us.setCreacion(u.getCreacion());
	        	   us.setId(u.getId());
	        	   usuarios.add(us);
	           });
            g.setUsers(usuarios); 
		}
		return g;
	}

	@Override
	@Transactional
	public GruposDTO addUserToGrupo(int grupo_id, int user_id) {
		User user = new User();
		//		User user = userDao.findById(user_id).get();
		UserDTO userdto = userService.getById(new Long(user_id));
		user.setApellido(userdto.getApellido());
		user.setId(userdto.getId());
		user.setCreacion(userdto.getCreacion());
		user.setEmail(userdto.getEmail());
		user.setNombre(userdto.getNombre());
		Grupos g = gruposDao.findById(grupo_id).get();
		g.addUser(user);
		gruposDao.save(g);
		GruposDTO result = convertDomainToDto(g);
		cacheManager.getCache(Settings.CACHE_NAME).put("api_grupo_"+result.getId()  , result );
		return convertDomainToDto(g);
	}

	@Override
	public void removeById(int id) {
		gruposDao.deleteById(id); 
	}

}
