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
import br.com.gabriel.primeiraapi.entity.Turma;
import br.com.gabriel.primeiraapi.service.TurmaService;

@RestController
@RequestMapping(path = "/turma")
public class TurmaController {
	
	@Autowired
	TurmaService turmaService;
	
	@PostMapping
	public Turma criar(@RequestBody Turma turma) {
		return turmaService.criar(turma);
	}
	
	@PutMapping
	public Turma atualizar(@RequestBody Turma turma) {
		return turmaService.atualizar(turma);
	}
	
	@DeleteMapping(path = "{id}")
	public void deletarByID (Integer id) {
		turmaService.deletarByID(id);
	}
	
	@GetMapping(path = "{id}")
	public Turma consultar(@PathVariable Integer id) {
		return turmaService.findById(id);
	}
	
	@GetMapping
	public List<Turma> findAll(@RequestParam(name = "nome", required = false) String nome){
		if(nome != null) {
			return turmaService.findByNomeLike(nome);
		}
		List<Turma> turma = turmaService.findAll();
		return turma;
	}

}
