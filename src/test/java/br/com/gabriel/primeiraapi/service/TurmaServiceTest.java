package br.com.gabriel.primeiraapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;  
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.gabriel.primeiraapi.entity.Curso;
import br.com.gabriel.primeiraapi.entity.StatusEnum;
import br.com.gabriel.primeiraapi.entity.Turma;
import br.com.gabriel.primeiraapi.exception.ProfessorException;
import br.com.gabriel.primeiraapi.exception.TurmaException;
import br.com.gabriel.primeiraapi.exception.TurmaException;
import br.com.gabriel.primeiraapi.repository.TurmaRepository;

@ExtendWith(MockitoExtension.class)
public class TurmaServiceTest {
	
	@Mock //crio um obj simulado de turma, contendo tds os metodos de TurmaRepository (JPA)
	TurmaRepository turmaRepository;
	@InjectMocks //injeto o obj simulado em @mock no service, compindo tds as regras, o metodo é executado
	TurmaService turmaService;
	
	@Test
	public void testCriarTurmaComTurmaNull() {
		//crio cenario
		Turma turma = null;
		assertThrows(TurmaException.class,
				() -> turmaService.criar(turma));
	}
	
	@Test
	public void testCriarTurmaComNomeNull() {
		Turma turma = new Turma();
		Curso curso = new Curso();
		curso.setId(1);
		turma.setStatus(StatusEnum.A);
		turma.setCurso(curso);
		turma.setNome(null);
		assertThrows(TurmaException.class, 
				() -> turmaService.criar(turma));
	}
	@Test
	public void testCriarTurmaComNomeVazio() {
		Turma turma = new Turma();
		Curso curso = new Curso();
		curso.setId(1);
		turma.setCurso(curso);
		turma.setNome("  ");
		turma.setStatus(StatusEnum.A);
		assertThrows(TurmaException.class, 
				() -> turmaService.criar(turma));
	}
	
	@Test
	public void testCriarTurmaComCursoNull() {
		Turma turma = new Turma();
		Curso curso = null;
		turma.setCurso(curso);
		turma.setNome("ADS1");
		turma.setStatus(StatusEnum.A);
		assertThrows(TurmaException.class, 
				() -> turmaService.criar(turma));
	}
	@Test
	public void testCriarTurmaComIDCursoNull() {
		Turma turma = new Turma();
		Curso curso = new Curso();
		curso.setId(null);
		turma.setCurso(curso);
		turma.setNome("ADS1");
		turma.setStatus(StatusEnum.A);
		assertThrows(TurmaException.class, 
				() -> turmaService.criar(turma));
	}
	
	@Test
	public void criar() {
		Turma t = new Turma();
		Curso c = new Curso();
		c.setId(1);
		t.setCurso(c);
		t.setNome("ADS1");
		t.setStatus(StatusEnum.A);
		
		Turma turmaEsperada = new Turma();
		Curso curso = new Curso();
		curso.setId(1);
		turmaEsperada.setCurso(c);
		turmaEsperada.setNome("ADS1");
		turmaEsperada.setStatus(StatusEnum.A);
		
		Mockito.when(turmaRepository.save(t)).thenReturn(turmaEsperada);
		
		Turma turmaRetornada = new Turma();
		turmaRetornada = turmaService.criar(t);
		
		assertEquals(turmaRetornada,turmaEsperada);
	}
	
	@Test
	public void testAtualizarComTurmaNull() {
		Turma t = null;
		assertThrows(TurmaException.class, 
				() -> turmaService.atualizar(t)); 
	}
	@Test
	public void testAtualizarComTurmaIDNull() {
		Turma t = new Turma();
		Curso c = new Curso();
		t.setId(null);
		c.setId(1);
		t.setCurso(c);
		t.setStatus(StatusEnum.A);
		t.setNome(null);
		assertThrows(TurmaException.class, 
				() -> turmaService.atualizar(t));
	}
	@Test
	public void testAtualizarComTurmaNomeNull() {
		Turma t = new Turma();
		Curso c = new Curso();
		t.setId(1);
		c.setId(1);
		t.setCurso(c);
		t.setStatus(StatusEnum.A);
		t.setNome(null);
		assertThrows(TurmaException.class, 
				() -> turmaService.atualizar(t));
	}
	@Test
	public void testAtualizarComTurmaNomeVazio() {
		Turma t = new Turma();
		Curso c = new Curso();
		t.setId(1);
		c.setId(1);
		t.setCurso(c);
		t.setStatus(StatusEnum.A);
		t.setNome("	");
		assertThrows(TurmaException.class, 
				() -> turmaService.atualizar(t));
	}
	@Test
	public void testAtualizarComTurmaCursoNull() {
		Turma t = new Turma();
		Curso c = null;
		t.setId(1);
		t.setCurso(c);
		t.setStatus(StatusEnum.A);
		t.setNome("TurmaA");
		assertThrows(TurmaException.class, 
				() -> turmaService.atualizar(t));
	}
	
	@Test
	public void testAtualizarComTurmaIDCursoNull() {
		Turma t = new Turma();
		Curso c = new Curso();
		t.setId(1);
		c.setId(null);
		t.setCurso(c);
		t.setStatus(StatusEnum.A);
		t.setNome("TurmaA");
		assertThrows(TurmaException.class, 
				() -> turmaService.atualizar(t));
	}
	
	@Test
	public void testAtualizar() throws ProfessorException {
		Turma t = new Turma();
		Curso c = new Curso();
		c.setId(1);
		t.setNome("ADS1");
		t.setCurso(c);
		t.setStatus(StatusEnum.A);
		t.setId(1);
		Turma turmaEsperada = new Turma();
		Curso cursoEsperado = new Curso();
		cursoEsperado.setId(1);
		turmaEsperada.setNome("ADS1");
		turmaEsperada.setCurso(cursoEsperado);
		turmaEsperada.setStatus(StatusEnum.A);
		turmaEsperada.setId(1);
		
		Mockito.when(turmaRepository.save(t)).thenReturn(turmaEsperada);
		
		Turma turmaRetornada = turmaService.atualizar(t);
		assertEquals(turmaEsperada,turmaRetornada);
	}
	@Test
	public void testDeletarComTurmaIdNull() {
		Integer t = null;
		assertThrows(TurmaException.class,
				() -> turmaService.deletarByID(t));
	}
	@Test
	public void testDeletar() throws ProfessorException {
		Integer t = 1; //montar o cenario, instaciar o obj e setar os valores do obj, td que precisa para a execução do teste.
	    turmaService.deletarByID(t); //executar!
	    //validação, verificar se a execução é o que se espera. caminho correto ou Exception.
	    ArgumentCaptor<Integer> capturarId = ArgumentCaptor.forClass(Integer.class);
	    Mockito.verify(turmaRepository).deleteById(capturarId.capture()); //comparar se o que foi passado em do tipo Long
	    assertEquals(t,capturarId.getValue()); //comparação dos valores.
	}
	
	@Test
	public void testFindByIdComValorNulo() {
		//Cenário
		Integer id = null;
		//simular o comportamento do objeto
		assertThrows(TurmaException.class, 
	    		() -> turmaService.findById(id));
	}
	
	@Test
	public void testFindByIdComListaVazia() {
		Integer id = 1;
		Mockito.when(turmaRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(TurmaException.class, 
	    		() -> turmaService.findById(id));
	}
	
	@Test
	public void testFindById() {
		//Cenário
	    Integer id = 1;

	    Turma turmaEsperada = new Turma();
	    turmaEsperada.setId(id);
	    
	    Mockito.when(turmaRepository.findById(id)).thenReturn(Optional.of(turmaEsperada));
	    
	    //Executa o método a ser testado
	    Turma turmaRetornada = turmaService.findById(id);

	    //Verifica se o aluno retornado é igual ao aluno esperado
	    assertEquals(turmaEsperada, turmaRetornada);
	}
	
	@Test
	public void testFindByNomeNull() {
		Turma turmaEsperada = new Turma();
		Curso c = new Curso();
		turmaEsperada.setId(1);
		turmaEsperada.setCurso(c);
		turmaEsperada.setNome(null);
		//turmaEsperada.setTurma("ADS1");
		turmaEsperada.setStatus(StatusEnum.A);
	    
	    List<Turma> listaEsperada = new ArrayList<>();
	    listaEsperada.add(turmaEsperada);

	    assertThrows(TurmaException.class, 
	    		() -> turmaService.findByNomeLike(turmaEsperada.getNome()));
	}
	

	@Test
	public void testFindByNomeVazio() {
		Turma turmaEsperada = new Turma();
		Curso c = new Curso();
		c.setId(1);
		turmaEsperada.setCurso(c);
		turmaEsperada.setNome("	");
		turmaEsperada.setStatus(StatusEnum.A);

	    List<Turma> listaEsperada = new ArrayList<>();
	    listaEsperada.add(turmaEsperada);

	    assertThrows(TurmaException.class, 
	    		() -> turmaService.findByNomeLike(turmaEsperada.getNome()));
	}
	@Test
	public void testFindByNome() {
		Turma turmaEsperada = new Turma();
		Curso c = new Curso();
		c.setId(1);
		turmaEsperada.setCurso(c);
		turmaEsperada.setNome("ADS1");
		turmaEsperada.setStatus(StatusEnum.A);

	    List<Turma> listaEsperada = new ArrayList<>();
	    listaEsperada.add(turmaEsperada);

	    Mockito.when(turmaRepository.findByNomeLike(turmaEsperada.getNome())).thenReturn(listaEsperada);

	    // Executa o método a ser testado
	    List<Turma> listaRetornada = turmaService.findByNomeLike(turmaEsperada.getNome());

	    // Verifica se a lista retornada é igual à lista esperada
	    assertEquals(listaEsperada, listaRetornada);
	}
	@Test
	public void testFindAllVazio(){
		List<Turma> listaTurma = new ArrayList<Turma>();
		Mockito.when(turmaRepository.findAll()).thenReturn(listaTurma);
		assertThrows(TurmaException.class, 
				() -> turmaService.findAll());
	}
	@Test
	public void testFindAll(){
		List<Turma> listaEsperada = new ArrayList<Turma>();
		Turma turma = new Turma();
		Curso c = new Curso();
		turma.setId(1);
		c.setId(1);
		turma.setNome("ADS");
		turma.setCurso(c);
		turma.setStatus(StatusEnum.A);
		listaEsperada.add(turma);
		Turma turma2 = new Turma();
		Curso c2 = new Curso();
		turma2.setId(2);
		c2.setId(1);
		turma2.setNome("ADS");
		turma2.setCurso(c);
		turma2.setStatus(StatusEnum.A);
		listaEsperada.add(turma2);
		Turma turma3 = new Turma();
		Curso c3 = new Curso();
		turma3.setId(1);
		c3.setId(3);
		turma3.setNome("ADS");
		turma3.setCurso(c);
		turma3.setStatus(StatusEnum.A);
		listaEsperada.add(turma3);
		
		Mockito.when(turmaRepository.findAll()).thenReturn(listaEsperada);
		List<Turma> listaRteornada = turmaService.findAll();
		assertEquals(listaEsperada,listaRteornada);
	}
}
