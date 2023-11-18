package br.com.gabriel.primeiraapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabriel.primeiraapi.entity.Professor;
import br.com.gabriel.primeiraapi.entity.Turma;
import br.com.gabriel.primeiraapi.exception.ProfessorException;
import br.com.gabriel.primeiraapi.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping
	public Professor criar(@RequestBody Professor professor) throws ProfessorException {
		return professorService.criar(professor);
	}
	
	@PatchMapping
	public Professor atualizar (@RequestBody Professor professor) throws ProfessorException {
		return professorService.atualizar(professor);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) throws ProfessorException {
		professorService.deletar(id);
	}
	@GetMapping
	public List<Professor> consultar (@RequestParam(name = "nome", required = false) String nome) throws ProfessorException {
		if(nome != null) {
		return professorService.findByNomeLike(nome);
		}
		List<Professor> professor = professorService.consultar();
		return professor;
	}
	
	@GetMapping(path = "{id}")
	public Optional<Professor> consultarPorId (@PathVariable Long id) throws ProfessorException {
		return professorService.consultarPorID(id);
	}
}
