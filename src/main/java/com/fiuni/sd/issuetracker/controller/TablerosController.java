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
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosResultDTO;
import com.fiuni.sd.issuetracker.service.proyecto.IProyectosService;
import com.fiuni.sd.issuetracker.service.tablero.ITableroService;

@RestController
@RequestMapping("/tablero")
public class TablerosController {
	@Autowired
	ITableroService tablerosService;
	@Autowired
	IProyectosService proyectoService;

	@GetMapping("/{id}")
	public TablerosDTO getById(@PathVariable Long id) {
		System.out.println(id);
		TablerosDTO u= tablerosService.getById(id);
		return tablerosService.getById(id);
	}

	@GetMapping(path = "/page/{page_num}")
	public TablerosResultDTO getUsers(@PathVariable(value = "page_num")Integer pageNum) {
		return tablerosService.getAll(PageRequest.of((pageNum-1), 30));
	}

	@GetMapping(path = "/find/{search}/{page_num}")
	public TablerosResultDTO findUser(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		return tablerosService.findALL(PageRequest.of((pageNum-1), 30) , search); 
	}
/*	
	@PostMapping(path = "addroll/{user_id}/{proyecto_id}/{rol_id}")
	public GruposDTO addRoll(@PathVariable(value = "user_id")int user_id,@PathVariable(value = "proyecto_id")int proyecto_id, @PathVariable(value = "rol_id") int rol_id) {
		return grupoService.addUserRol(user_id,proyecto_id, rol_id) ;
	}*/
	@PostMapping("/{proyecto_id}")
	public TablerosDTO save(@Valid @RequestBody TablerosDTO t, @PathVariable(value = "proyecto_id")Long proyecto_id) {
		return tablerosService.save(t);
	}
}
