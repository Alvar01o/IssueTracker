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
import com.fiuni.sd.issuetracker.dto.GruposResultDTO;
import com.fiuni.sd.issuetracker.service.grupo.IGrupoService;


@RestController
@RequestMapping("/grupo")
public class GruposController {
	@Autowired
	IGrupoService grupoService;
	
	@GetMapping("/{id}")
	public GruposDTO getById(@PathVariable Long id) {
		GruposDTO u = grupoService.getById(id);
		return grupoService.getById(id);
	}

	@GetMapping(path = "/page/{page_num}")
	public GruposResultDTO getUsers(@PathVariable(value = "page_num")Integer pageNum) {
		return grupoService.getAll(PageRequest.of((pageNum-1), 30));
	}

	@GetMapping(path = "/find/{search}/{page_num}")
	public GruposResultDTO findUser(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		return grupoService.findALL(PageRequest.of((pageNum-1), 30) , search); 
	}
	
	@PostMapping(path = "/adduser/{grupo_id}/{user_id}")
	public GruposDTO addUser(@PathVariable(value = "grupo_id")int grupo_id,@PathVariable(value = "user_id")int user_id) {
		return grupoService.addUserToGrupo(grupo_id,user_id);
	}
	
	@PostMapping()
	public GruposDTO save(@Valid @RequestBody GruposDTO grupo) {
		return grupoService.save(grupo);
	}
	
}
