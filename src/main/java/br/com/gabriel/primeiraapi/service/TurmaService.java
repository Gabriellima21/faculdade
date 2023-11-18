package br.com.gabriel.primeiraapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabriel.primeiraapi.entity.Aluno;
import br.com.gabriel.primeiraapi.entity.StatusEnum;
import br.com.gabriel.primeiraapi.entity.Turma;
import br.com.gabriel.primeiraapi.exception.AlunoException;
import br.com.gabriel.primeiraapi.exception.TurmaException;
import br.com.gabriel.primeiraapi.exception.TurmaException;
import br.com.gabriel.primeiraapi.repository.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
	TurmaRepository turmaRepository;
	
	protected static final String MENSAGEM_TURMA_ID_NAO_INFORMADO = "O ID da turma não foi informado!";
	protected static final String MENSAGEM_TURMA_NAO_INFORMADA = "A turma não foi informada corretamente!";
	protected static final String MENSAGEM_NOME_TURMA_NAO_INFORMADO = "O nome da turma não foi informado!";
	protected static final String MENSAGEM_CURSO_TURMA_NAO_INFORMADO = "O curso relacionado a turma não foi informado!";
	protected static final String MENSAGEM_STATUS_TURMA_NAO_INFORMADO = "O status referente a turma não foi informado!";
	protected static final String MENSAGEM_LISTA_TURMA_VAZIA = "A lista de turma esta vazia";
	
	public Turma criar (Turma turma) {
		if(turma == null) {
			throw new TurmaException(MENSAGEM_TURMA_ID_NAO_INFORMADO);
		}else if(turma.getNome() == null || turma.getNome().isBlank()) {
			throw new TurmaException(MENSAGEM_NOME_TURMA_NAO_INFORMADO);
		}else if (turma.getCurso() == null){
			throw new TurmaException(MENSAGEM_CURSO_TURMA_NAO_INFORMADO);
		}else if ( turma.getCurso().getId() == null){
			throw new TurmaException(MENSAGEM_CURSO_TURMA_NAO_INFORMADO);
		}
			return turmaRepository.save(turma);
	}
	
	public Turma atualizar (Turma turma) {
		if(turma == null) {
			throw new TurmaException(MENSAGEM_TURMA_ID_NAO_INFORMADO);
		}else if(turma.getId() == null) {
			throw new TurmaException(MENSAGEM_TURMA_ID_NAO_INFORMADO);
		}else if(turma.getNome() == null) {
			throw new TurmaException(MENSAGEM_NOME_TURMA_NAO_INFORMADO);
		}else if (turma.getNome().isBlank()) {
			throw new TurmaException(MENSAGEM_NOME_TURMA_NAO_INFORMADO);
		}else if (turma.getCurso() == null){
			throw new TurmaException(MENSAGEM_CURSO_TURMA_NAO_INFORMADO);
		}else if(turma.getCurso().getId() == null) {
			throw new TurmaException(MENSAGEM_CURSO_TURMA_NAO_INFORMADO);
		}
		return turmaRepository.save(turma);
	}
	
	public Turma findById (Integer id) {
		if(id == null) {
			throw new TurmaException(MENSAGEM_TURMA_ID_NAO_INFORMADO);
		} 
		Optional<Turma> turma = turmaRepository.findById(id);
		if(!turma.isPresent()) {
			throw new TurmaException(MENSAGEM_LISTA_TURMA_VAZIA);
		}
		return turma.get();
	}
	
	public List<Turma> findAll() {
		List<Turma> turma = turmaRepository.findAll(); 
		if(turma.isEmpty()){ 
			throw new TurmaException(MENSAGEM_TURMA_NAO_INFORMADA);
		}
        return turma;
	}
	
	public List<Turma> findByNomeLike(String nome) {
		if(nome == null || nome.isBlank()) {
			throw new TurmaException(MENSAGEM_TURMA_NAO_INFORMADA );
		}
		return turmaRepository.findByNomeLike(nome);
	}
	
	public void deletarByID(Integer id) {
		if(id == null) {
			throw new TurmaException(MENSAGEM_TURMA_ID_NAO_INFORMADO);
		}
		turmaRepository.deleteById(id);
	}
}
