package br.com.gabriel.primeiraapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabriel.primeiraapi.entity.Curso;
import br.com.gabriel.primeiraapi.entity.Materia;
import br.com.gabriel.primeiraapi.exception.AlunoException;
import br.com.gabriel.primeiraapi.exception.CursoException;
import br.com.gabriel.primeiraapi.exception.MateriaException;
import br.com.gabriel.primeiraapi.repository.CursoRepository;

@Service
public class CursoService {
	
	protected static final String MENSAGEM_LISTA_CURSO_VAZIA = "O curso não pode ser vazio.";
	protected static final String MENSAGEM_CURSO_NULL = "O curso não pode ser nulo.";
	protected static final String MENSAGEM_CURSO_ID_NAO_PREENCHIDO = "O Id do curso deve ser informado.";
	protected static final String MENSAGEM_NOME_CURSO_NAO_PREENCHIDO = "O nomde do curso deve ser informado.";
	protected static final String MENSAGEM_PERIODO_CURSO_NAO_PREENCHIDO = "O periodo do curso deve ser informado";
	protected static final String MENSAGEM_ID_CURSO_NULL = "O id do curso deve ser informado";

	@Autowired
	CursoRepository cursoRepository;
	
	public Curso criar(Curso curso) {
		if(curso == null){
			throw new CursoException(MENSAGEM_CURSO_NULL);
		}else if (curso.getNome() == null || curso.getNome().isBlank()) {
			throw new CursoException(MENSAGEM_NOME_CURSO_NAO_PREENCHIDO);
		}else if(curso.getPeriodo() == null || curso.getPeriodo().isBlank()) {
			throw new CursoException(MENSAGEM_PERIODO_CURSO_NAO_PREENCHIDO);
		}
		return cursoRepository.save(curso);
	}
	
	public Curso atualizar(Curso curso) {
		if(curso == null){
			throw new CursoException(MENSAGEM_CURSO_NULL);
		}else if ( curso.getNome() == null || curso.getNome().isBlank()) {
			throw new CursoException(MENSAGEM_NOME_CURSO_NAO_PREENCHIDO);
		}else if(curso.getPeriodo() == null || curso.getPeriodo().isBlank()) {
			throw new CursoException(MENSAGEM_PERIODO_CURSO_NAO_PREENCHIDO);
		}else if (curso.getId() == null) {
			throw new CursoException(MENSAGEM_CURSO_ID_NAO_PREENCHIDO);
		}
		return cursoRepository.save(curso);
	}
	
	public void deletar(Integer id) {
		if(id == null) {
			throw new CursoException(MENSAGEM_CURSO_ID_NAO_PREENCHIDO);
		}
		cursoRepository.deleteById(id);
	}
	
	public List<Curso> findAll() {
		List<Curso> listaCurso = cursoRepository.findAll();
		if(listaCurso.isEmpty()) {
			throw new CursoException(MENSAGEM_LISTA_CURSO_VAZIA);
		}
		return cursoRepository.findAll();
	}
	
	public Curso findById(Integer id){
		Optional<Curso> curso = cursoRepository.findById(id);
		if(id == null) {
			throw new CursoException(MENSAGEM_ID_CURSO_NULL);
		}
		if(!curso.isPresent()) {
			throw new CursoException(MENSAGEM_LISTA_CURSO_VAZIA);
		}
		return curso.get();
	}
	
	public List<Curso> findByNomeLike(String nome) {
		if(nome.isBlank()) {
			throw new CursoException(MENSAGEM_NOME_CURSO_NAO_PREENCHIDO);
		}
		return cursoRepository.findByNomeLike(nome);
	}
}
