package com.fiuni.sd.issuetracker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiuni.sd.issuetracker.domain.Proyectos;
import com.fiuni.sd.issuetracker.dto.GruposDTO;
import com.fiuni.sd.issuetracker.dto.GruposResultDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosDTO;
import com.fiuni.sd.issuetracker.dto.ProyectosResultDTO;
import com.fiuni.sd.issuetracker.dto.TablerosDTO;
import com.fiuni.sd.issuetracker.dto.TablerosResultDTO;
import com.fiuni.sd.issuetracker.service.proyecto.IProyectosService;
import com.fiuni.sd.issuetracker.service.tablero.ITableroService;
import com.fiuni.sd.issuetracker.utils.Settings;

@RestController
@RequestMapping("/tablero")
public class TablerosController {
	@Autowired
	ITableroService tablerosService;
	@Autowired
	IProyectosService proyectoService;
	@Autowired 
	private CacheManager cacheManager;
	
	
	@GetMapping("/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER"  })	
	public TablerosDTO getById(@PathVariable Long id) {
		System.out.println(id);
		TablerosDTO u= tablerosService.getById(id);
		return tablerosService.getById(id);
	}

	@GetMapping(path = "/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER"  })	
	public TablerosResultDTO getTableros(@PathVariable(value = "page_num")Integer pageNum) {
		return tablerosService.getAll(PageRequest.of((pageNum-1),  Settings.PAGINACION));
	}
	
	@GetMapping(path = "/find/{search}/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER"  })
	public TablerosResultDTO findTablero(@PathVariable(value = "search") String search , @PathVariable(value = "page_num")Integer pageNum) {
		return tablerosService.findALL(PageRequest.of((pageNum-1),  Settings.PAGINACION) , search); 
	}

	@PostMapping("/{proyecto_id}")
	@Secured({ "ROLE_ADMIN", "ROLE_MANAGER" , "ROLE_DEVELOPER"  })	
	public TablerosDTO save(@Valid @RequestBody TablerosDTO t, @PathVariable(value = "proyecto_id")Long proyecto_id) {
		cacheManager.getCache(Settings.CACHE_NAME).evict("api_proyecto_" + proyecto_id);
		return tablerosService.addTablero( t,proyecto_id);
	}
}
