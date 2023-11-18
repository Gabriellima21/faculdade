package br.com.gabriel.primeiraapi.service;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabriel.primeiraapi.entity.Aluno;
import br.com.gabriel.primeiraapi.entity.StatusEnum;
import br.com.gabriel.primeiraapi.exception.AlunoException;
import br.com.gabriel.primeiraapi.exception.ProfessorException;
import br.com.gabriel.primeiraapi.kafka.producer.AlunoProducer;
import br.com.gabriel.primeiraapi.repository.AlunoRepository;
import br.com.gabriel.primeiraapi.util.Data;

@Service
public class AlunoService {

	protected static final String MENSAGEM_LISTA_ALUNOS_VAZIA = "Lista de alunos vazia.";
	protected static final String MENSAGEM_ALUNO_NULO = "Aluno não encontrado.";
	protected static final String MENSAGEM_ID_ALUNO_NAO_ENCONTRADO = "Informe o ID do aluno.";	
	protected static final String MENSAGEM_CURSO_ALUNO_NAO_ENCONTRADO = "Curso não encontrado.";	
	protected static final String MENSAGEM_TURMA_ALUNO_NAO_ENCONTRADO = "Turma não encontrada.";
	protected static final String MENSAGEM_NOME_ALUNO_NAO_INFORMADO = "Informe o nome do aluno.";
	protected static final String MENSAGEM_SEXO_ALUNO_INCORRETO = "Sexo incorreto.";
	protected static final String MENSAGEM_STATUS_ALUNO_INCORRETO = "Status incorreto.";
	protected static final String MENSAGEM_DATA_CADASTRO_ALUNO_NULL = "Data do cadastro não informada.";
	
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	AlunoProducer alunoProducer;
	
	@Autowired
	private Data data;
	
	private LocalDate dataAtual;
	
	public List<Aluno> findAll() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos = alunoRepository.findAll(); //variavel alunos do tipo List recebendo o retorno do metodo findAll(). 
		if(alunos.isEmpty() || alunos == null){ // Se a Lista alunos for vazia, lança a exceção
			throw new AlunoException(MENSAGEM_LISTA_ALUNOS_VAZIA);
		}
        return alunos;
	}
	
	public List<Aluno> findByNomeLike(String nome) {
		if(nome.isBlank()) {
			throw new AlunoException(MENSAGEM_NOME_ALUNO_NAO_INFORMADO);
		}
		return alunoRepository.findByNomeLike(nome);
	}
	
	public Aluno findById(Integer id) {
		Optional<Aluno> aluno = alunoRepository.findById(id); //metodo findById retorno um tipo Optional
		if(id == null) {
			throw new AlunoException(MENSAGEM_ID_ALUNO_NAO_ENCONTRADO);
		}
		if(!aluno.isPresent()) { //isPresent() verifica se a um valor presente em aluno.
			throw new AlunoException(MENSAGEM_LISTA_ALUNOS_VAZIA);
		}
		return aluno.get(); //retorno o valor presente em aluno
	}
	
	public Aluno criar(Aluno aluno) {
	    if (aluno == null) {
	        throw new AlunoException(MENSAGEM_ALUNO_NULO);
	    } else if (aluno.getCurso() == null) {
	    	throw new AlunoException(MENSAGEM_CURSO_ALUNO_NAO_ENCONTRADO);
	    }else if (aluno.getCurso().getId() == null) {
	        throw new AlunoException(MENSAGEM_CURSO_ALUNO_NAO_ENCONTRADO);
	    } else if (aluno.getTurma() == null) {
	        throw new AlunoException(MENSAGEM_TURMA_ALUNO_NAO_ENCONTRADO);
	    } else if (aluno.getTurma().getId() == null) {
	        throw new AlunoException(MENSAGEM_TURMA_ALUNO_NAO_ENCONTRADO);
	    } else if (aluno.getNome() == null || aluno.getNome().isBlank()) {
	        throw new AlunoException(MENSAGEM_NOME_ALUNO_NAO_INFORMADO);
	    }else if (aluno.getStatus() == null) {
	    	throw new AlunoException(MENSAGEM_STATUS_ALUNO_INCORRETO);
	    }
	    dataAtual = data.dataAtual();
	    aluno.setData(dataAtual);
	    Aluno alunoRetornado = alunoRepository.save(aluno);
	    alunoProducer.enviar(alunoRetornado);
	    return alunoRepository.save(aluno);
	}
	
	public Aluno atualizar(Aluno aluno) {
		if(aluno == null) {
			throw new AlunoException(MENSAGEM_ALUNO_NULO);
		}else if(aluno.getCurso() == null || aluno.getCurso().getId() == null) {
			throw new AlunoException(MENSAGEM_CURSO_ALUNO_NAO_ENCONTRADO);
		}else if(aluno.getTurma() == null) {
			throw new AlunoException(MENSAGEM_TURMA_ALUNO_NAO_ENCONTRADO);
		}else if(aluno.getTurma().getId() == null) {
			throw new AlunoException(MENSAGEM_TURMA_ALUNO_NAO_ENCONTRADO);
		}else if (aluno.getNome() == null || aluno.getNome().isBlank()) {
			throw new AlunoException(MENSAGEM_NOME_ALUNO_NAO_INFORMADO);
		}else if(!StatusEnum.F.equals(aluno.getSexo()) && !StatusEnum.M.equals(aluno.getSexo())) {
			throw new AlunoException(MENSAGEM_SEXO_ALUNO_INCORRETO);
		}else if(!StatusEnum.A.equals(aluno.getStatus()) && !StatusEnum.I.equals(aluno.getStatus())) {
			throw new AlunoException(MENSAGEM_STATUS_ALUNO_INCORRETO);
		}
		return alunoRepository.save(aluno);
	}

	public void delete(Integer id) {
		if(id == null) {
			throw new AlunoException(MENSAGEM_ID_ALUNO_NAO_ENCONTRADO);
		}
		alunoRepository.deleteById(id);
	}
}
