package br.com.gabriel.primeiraapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabriel.primeiraapi.entity.Aluno;
import br.com.gabriel.primeiraapi.entity.Materia;
import br.com.gabriel.primeiraapi.exception.AlunoException;
import br.com.gabriel.primeiraapi.exception.MateriaException;
import br.com.gabriel.primeiraapi.repository.MateriaRepository;
import net.bytebuddy.build.Plugin.Engine.Dispatcher.Materializable;

@Service
public class MateriaService {
	
	protected static final String MENSAGEM_MATERIA_NULL = "Matéria não encontrada.";
	protected static final String MENSAGEM_ID_NAO_ENCONTRADO = "Id não encontrado, informe o ID da matéria.";
	protected static final String MENSAGEM_CURSO_NAO_ENCONTADO = "Curso não encontrado, informe o curso relacionado a matéria.";
	protected static final String MENSAGEM_NOME_NAO_INFORMADO = "Nome não encontrado, informe o nome da materia.";
	protected static final String MENSAGEM_PROFESSOR_NAO_ENCONTADO = "Professor não encontrado, informe um professor para da matéria.";
	protected static final String MENSAGEM_LISTA_MATERIA_VAZIA = "Não foi possível listar as matérias.";
	protected static final String MENSAGEM_LISTA_VAZIA = "Lista Vazia";
	protected static final String MENSAGEM_LISTA_NULL = "Lista Null";
	protected static final String MENSAGEM_TURMA_NAO_ENCONTRADA = "Turma não encontrado.";
	
	@Autowired
	MateriaRepository materiaRepository;
	
	public Materia criar(Materia materia) {
		
	    if (materia == null) {
	        throw new MateriaException(MENSAGEM_MATERIA_NULL);
	    } else if (materia.getCurso() == null || materia.getCurso().getId() == null) {
	        throw new MateriaException(MENSAGEM_CURSO_NAO_ENCONTADO);
	    } else if (materia.getNome() == null || materia.getNome().isEmpty()) {
	        throw new MateriaException(MENSAGEM_NOME_NAO_INFORMADO);
	    } else if (materia.getProfessor() == null || materia.getProfessor().getId() == null) {
	        throw new MateriaException(MENSAGEM_PROFESSOR_NAO_ENCONTADO);
	    } else if (materia.getTurma() == null) {
	    	  throw new MateriaException(MENSAGEM_TURMA_NAO_ENCONTRADA);
	    } else if(materia.getTurma().getId() == null) {
	    	 throw new MateriaException(MENSAGEM_TURMA_NAO_ENCONTRADA);
	    }
	    return materiaRepository.save(materia);
	}
	
	public Materia atualizar(Materia materia) {
		if(materia == null) {
			throw new MateriaException(MENSAGEM_MATERIA_NULL);
		}else if(materia.getCurso() == null) {
			throw new MateriaException(MENSAGEM_CURSO_NAO_ENCONTADO);
		}else if ( materia.getCurso().getId() == null){
			throw new MateriaException(MENSAGEM_CURSO_NAO_ENCONTADO);
		} if(materia.getNome() == null || materia.getNome().isEmpty()) {
			throw new MateriaException(MENSAGEM_NOME_NAO_INFORMADO);
		}else if (materia.getProfessor() == null || materia.getProfessor().getId()== null) {
			throw new MateriaException(MENSAGEM_PROFESSOR_NAO_ENCONTADO);
		}else if (materia.getTurma() == null || materia.getTurma().getId() == null) {
			throw new MateriaException(MENSAGEM_TURMA_NAO_ENCONTRADA);
		}else if (materia.getId() == null) {
			throw new MateriaException(MENSAGEM_ID_NAO_ENCONTRADO);
		}
		return materiaRepository.save(materia);
	}
	
	public void deletar(Integer id) {
		if(id == null) {
			throw new MateriaException(MENSAGEM_ID_NAO_ENCONTRADO);
		}
		materiaRepository.deleteById(id);
	}
	
	public List<Materia> findAll() {
		List<Materia> listaMateria = materiaRepository.findAll();
		if(listaMateria.isEmpty()) {
			throw new MateriaException(MENSAGEM_LISTA_MATERIA_VAZIA);
		}
		return materiaRepository.findAll();
	}
	
	public Materia findById(Integer id) {
	    if (id == null) {
	        throw new MateriaException(MENSAGEM_LISTA_NULL);
	    }
	    Optional<Materia> Materia = materiaRepository.findById(id);
	    if (!Materia.isPresent()) {
	        throw new MateriaException(MENSAGEM_LISTA_VAZIA);
	    }
	    return Materia.get();
	}
	
	public List<Materia> findByNomeLike(String nome) {
		if(nome == null || nome.isBlank()) {
			throw new MateriaException(MENSAGEM_NOME_NAO_INFORMADO);
		}
		return materiaRepository.findByNomeLike(nome);
	}
	
}
