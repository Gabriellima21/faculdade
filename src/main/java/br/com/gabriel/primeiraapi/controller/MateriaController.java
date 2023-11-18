package br.com.gabriel.primeiraapi.controller;

import java.util.List; 
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.gabriel.primeiraapi.entity.Materia;
import br.com.gabriel.primeiraapi.service.MateriaService;

@RestController
@RequestMapping(path = "/materia")
public class MateriaController {
	
	@Autowired
	MateriaService materiaService;
	
	@PostMapping
	public Materia criar (@RequestBody Materia materia) {
		return materiaService.criar(materia);
	}
	
	@PutMapping
	public Materia atualizar(@RequestBody Materia materia) {
		return materiaService.atualizar(materia);
	}
	
	@GetMapping("/{id}")
	public Materia consultar (@PathVariable Integer id) {
		Materia materia = materiaService.findById(id);
		return materia;
	}
	
	@GetMapping
	public List<Materia> buscar(@RequestParam(name = "nome", required = false) String nome) {
		if(nome != null) {
			return materiaService.findByNomeLike(nome);
		}
		List<Materia> materia = materiaService.findAll();
		return materia;
	}
	
	@DeleteMapping("/{id}")
	public void deletar (@RequestBody Integer id) {
		materiaService.deletar(id);
	}

}
