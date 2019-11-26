package com.fiuni.sd.issuetracker.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.GruposResultDTO;
import com.fiuni.sd.issuetracker.dto.MessageDTO;
import com.fiuni.sd.issuetracker.service.grupo.IGrupoService;
import com.fiuni.sd.issuetracker.utils.Settings;


@RestController
@RequestMapping("/grupo")
public class GruposController {
	@Autowired
	IGrupoService grupoService;

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })	
	public MessageDTO deleteById(@PathVariable Long id) {
		try	{
			grupoService.removeById(id.intValue());
			return new MessageDTO("Grupo Eliminado");
		} catch(IllegalArgumentException e) {
			return new MessageDTO("Error al eliminar usuario.");			
		} catch(EmptyResultDataAccessException e1) {
			return new MessageDTO("No se encontro el usuario.");			
		}
	}

	@GetMapping("/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER"  })	
	public GruposDTO getById(@PathVariable Long id) {
		GruposDTO u = new GruposDTO();
		try {
			u = grupoService.getById(id);
		} catch(Exception e) {
			logger.info("Grupo con id " + id  + " no existe");
		}
		return u;
	}

	@GetMapping(path = "/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER"  })	
	public GruposResultDTO getUsers(@PathVariable(value = "page_num")Integer pageNum) {
		return grupoService.getAll(PageRequest.of((pageNum-1), Settings.PAGINACION2));
	}

	@GetMapping(path = "/find/{search}/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER"  })	
	public GruposResultDTO findUser(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		return grupoService.findALL(PageRequest.of((pageNum-1), Settings.PAGINACION2) , search); 
	}
	
	@PostMapping(path = "/adduser/{grupo_id}/{user_id}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER"  })	
	public GruposDTO addUser(@PathVariable(value = "grupo_id")int grupo_id,@PathVariable(value = "user_id")int user_id) {
		GruposDTO grupo = new GruposDTO();
		try {
			grupo = grupoService.addUserToGrupo(grupo_id,user_id);
		} catch(NoSuchElementException ex ) {
			throw new NoSuchElementException(); // para error en transaccion
		}
		return grupo;
	}
	
	@PostMapping()
	public GruposDTO save(@Valid @RequestBody GruposDTO grupo) {
		GruposDTO g = new GruposDTO();
		try	{
			g = grupoService.save(grupo);
		} catch (Exception ex) {
			logger.info(ex.toString());
		}
		return g;
	}
	
}
