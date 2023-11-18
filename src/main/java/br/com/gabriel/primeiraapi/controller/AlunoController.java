package br.com.gabriel.primeiraapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.sym.Name;

import br.com.gabriel.primeiraapi.entity.Aluno;
import br.com.gabriel.primeiraapi.exception.AlunoException;
import br.com.gabriel.primeiraapi.service.AlunoService;

@RestController
@RequestMapping(path = "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public List<Aluno> buscar(@RequestParam(name = "nome", required = false) String nome) {
		if(nome != null) {
			return alunoService.findByNomeLike(nome);
		}
		List<Aluno> aluno = alunoService.findAll();
		return aluno;
	}
	
	@GetMapping("/{id}")
	public Aluno consultar (@PathVariable Integer id) {
		Aluno aluno = alunoService.findById(id);
		return aluno;
	}
	
	@PostMapping
	public Aluno criar(@RequestBody Aluno aluno) {
		return alunoService.criar(aluno);
	}
	
	@PutMapping
	public Aluno atualizar(@RequestBody Aluno aluno) {
		return alunoService.atualizar(aluno);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		alunoService.delete(id);
	}
}
