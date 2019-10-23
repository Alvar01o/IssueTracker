package com.fiuni.sd.issuetracker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosResultDTO;
import com.fiuni.sd.issuetracker.dto.UserDTO;
import com.fiuni.sd.issuetracker.dto.UserResultDTO;
import com.fiuni.sd.issuetracker.service.grupo.IGrupoService;
import com.fiuni.sd.issuetracker.service.proyecto.IProyectosService;

@RestController
@RequestMapping("/proyecto")
public class ProyectosController {
	@Autowired
	IProyectosService proyectoService;
	@Autowired
	IGrupoService gruposService;

	@GetMapping("/{id}")
	public ProyectosDTO getById(@PathVariable Long id) {
		ProyectosDTO u= proyectoService.getById(id);
		return proyectoService.getById(id);
	}

	@GetMapping(path = "/page/{page_num}")
	public ProyectosResultDTO getUsers(@PathVariable(value = "page_num")Integer pageNum) {
		return proyectoService.getAll(PageRequest.of((pageNum-1), 30));
	}

	@GetMapping(path = "/find/{search}/{page_num}")
	public ProyectosResultDTO findUser(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		return proyectoService.findALL(PageRequest.of((pageNum-1), 30) , search); 
	}
	/*
	@PostMapping(path = "addroll/{user_id}/{proyecto_id}/{rol_id}")
	public ProyectosDTO addRoll(@PathVariable(value = "user_id")int user_id,@PathVariable(value = "proyecto_id")int proyecto_id, @PathVariable(value = "rol_id") int rol_id) {
		return proyectoService.addUserRol(user_id,proyecto_id, rol_id) ;
	}*/
	@PostMapping("/{grupo_id}")
	public ProyectosDTO save(@Valid @RequestBody ProyectosDTO pro,@PathVariable(value = "grupo_id")Long grupo_id ) {
		GruposDTO g = gruposService.getById(grupo_id);
		pro.setGrupo(g);
		System.out.println(pro.toString());
		return proyectoService.save(pro);
	}
}
