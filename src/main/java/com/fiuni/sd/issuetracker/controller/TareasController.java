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

import com.fiuni.sd.issuetracker.dto.TablerosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosResultDTO;
import com.fiuni.sd.issuetracker.dto.TareasDTO;
import com.fiuni.sd.issuetracker.dto.TareasResultDTO;
import com.fiuni.sd.issuetracker.service.tarea.ITareasService;

@RestController
@RequestMapping("/tareas")
public class TareasController {
	@Autowired
	ITareasService tareasService;

	
	@GetMapping("/{id}")
	public TareasDTO getById(@PathVariable Long id) {
		System.out.println(id);
		TareasDTO u= tareasService.getById(id);
		return tareasService.getById(id);
	}

	@GetMapping(path = "/page/{page_num}")
	public TareasResultDTO getUsers(@PathVariable(value = "page_num")Integer pageNum) {
		return tareasService.getAll(PageRequest.of((pageNum-1), 30));
	}

	@GetMapping(path = "/find/{search}/{page_num}")
	public TareasResultDTO findUser(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		return tareasService.findALL(PageRequest.of((pageNum-1), 30) , search); 
	}
/*	
	@PostMapping(path = "addroll/{user_id}/{proyecto_id}/{rol_id}")
	public GruposDTO addRoll(@PathVariable(value = "user_id")int user_id,@PathVariable(value = "proyecto_id")int proyecto_id, @PathVariable(value = "rol_id") int rol_id) {
		return grupoService.addUserRol(user_id,proyecto_id, rol_id) ;
	}*/
	@PostMapping("/{proyecto_id}")
	public TareasDTO save(@Valid @RequestBody TareasDTO t, @PathVariable(value = "proyecto_id")Long proyecto_id) {
		return tareasService.save(t);
	}
}
