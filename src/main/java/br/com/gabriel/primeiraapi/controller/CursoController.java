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
import br.com.gabriel.primeiraapi.entity.Curso;
import br.com.gabriel.primeiraapi.service.CursoService;

@RestController
@RequestMapping(path = "curso") //path = caminho
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	@PostMapping
	public Curso Criar(@RequestBody Curso curso) { //RequestBody, o que sera passado no copo da requisição. EX: JSON.
		return cursoService.criar(curso);
	} 
	
	@PutMapping
	public Curso atualizar(@RequestBody Curso curso) {
		return cursoService.atualizar(curso);
	}
	
	@GetMapping("/{id}")
	public Curso consultar (@PathVariable Integer id) {
		Curso curso = cursoService.findById(id);
		return curso;
	}
	
	@GetMapping
	public List<Curso> buscar(@RequestParam(name = "nome", required = false) String nome) {
		if(nome != null) {
			return cursoService.findByNomeLike(nome);
		}
		List<Curso> curso = cursoService.findAll();
		return curso;
	}
	
	@DeleteMapping("/{id}")
	public void deletar (@RequestBody Integer id) {
		cursoService.deletar(id);
	}
}
