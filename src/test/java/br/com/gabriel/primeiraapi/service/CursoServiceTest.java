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
import br.com.gabriel.primeiraapi.exception.CursoException;
import br.com.gabriel.primeiraapi.exception.ProfessorException;
import br.com.gabriel.primeiraapi.repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
	
	@Mock
	private CursoRepository cursoRepository;
	
	@InjectMocks
	private CursoService cursoService;
	
	@Test
	public void testCriarComCursoNull() {
		Curso curso = null;
		assertThrows(CursoException.class, 
				() -> cursoService.criar(curso));
		
	}
	@Test
	public void testCursoComNomeNull() {
		Curso curso = new Curso();
		curso.setPeriodo("Noturno");
		curso.setNome(null);
		curso.setValor(500.00);
		assertThrows(CursoException.class, 
				() -> cursoService.criar(curso));
	}
	@Test
	public void testCursoComValorNull() {
		Curso curso = new Curso();
		curso.setPeriodo("Noturno");
		curso.setNome("ADS");
		curso.setValor(null);
		assertThrows(CursoException.class, 
				() -> cursoService.criar(curso));
	}
	@Test
	public void testCursoComNomeVazio() {
		Curso curso = new Curso();
		curso.setNome(" ");
		curso.setPeriodo("Noturno");
		curso.setValor(500.00);
		assertThrows(CursoException.class, 
				() -> cursoService.criar(curso));
	}
	@Test
	public void testCursoComPeriodoNull() {
		Curso curso = new Curso();
		curso.setNome("ADS");
		curso.setPeriodo(null);
		curso.setValor(500.00);
		assertThrows(CursoException.class, 
				() -> cursoService.criar(curso));
	}
	@Test
	public void testCursoComPeriodoVazio() {
		Curso curso = new Curso();
		curso.setNome("ads");
		curso.setPeriodo(" ");
		curso.setValor(500.00);
		assertThrows(CursoException.class, 
				() -> cursoService.criar(curso));
	}
	@Test
	public void testCriar() {
		Curso curso = new Curso ();
		curso.setNome("ADS");
		curso.setPeriodo("Noturno");
		curso.setValor(500.00);
		Curso cursoEsperado = new Curso ();
		cursoEsperado.setNome("ADS");
		cursoEsperado.setPeriodo("Vespertino");
		cursoEsperado.setValor(500.00);
		Mockito.when(cursoRepository.save(curso)).thenReturn(cursoEsperado);
		
		Curso cursoRetornado = cursoService.criar(curso);
		assertEquals(cursoEsperado,cursoRetornado);
	}
	
	@Test
	public void testAtualizarCursoNull() {
		Curso cursoEsperada = null;
		assertThrows(CursoException.class, 
				() -> cursoService.atualizar(cursoEsperada));
	}
	
	@Test
	public void testAtualizarCursoIdNull() {
		Curso cursoEsperada = new Curso();
		cursoEsperada.setId(null);
		assertThrows(CursoException.class, 
				() -> cursoService.atualizar(cursoEsperada));
	}
	
	@Test
	public void testAtualizarCursoNomeNull() {
		Curso cursoEsperado = new Curso();
		cursoEsperado.setId(1);
		cursoEsperado.setNome(null);
		cursoEsperado.setPeriodo("Noturno");
		cursoEsperado.setValor(500.00);
		assertThrows(CursoException.class,
				() -> cursoService.atualizar(cursoEsperado));
	}
	
	@Test
	public void testAtualizarNomeVazio() {
		Curso cursoEsperado = new Curso();
		cursoEsperado.setId(1);
		cursoEsperado.setNome(" ");
		cursoEsperado.setPeriodo("Noturno");
		cursoEsperado.setValor(500.00);
		assertThrows(CursoException.class,
				() -> cursoService.atualizar(cursoEsperado));
	}
	
	@Test
	public void testAtualizaPeriodoNull() {
		Curso cursoEsperado = new Curso();
		cursoEsperado.setId(1);
		cursoEsperado.setNome("Noturno");
		cursoEsperado.setPeriodo(null);
		cursoEsperado.setValor(500.00);
		assertThrows(CursoException.class,
				() -> cursoService.atualizar(cursoEsperado));
	}
	
	@Test
	public void testAtualizaPeriodoVazio() {
		Curso cursoEsperado = new Curso();
		cursoEsperado.setId(1);
		cursoEsperado.setNome("ads");
		cursoEsperado.setPeriodo(" ");
		cursoEsperado.setValor(500.00);
		assertThrows(CursoException.class,
				() -> cursoService.atualizar(cursoEsperado));
	}
	
	@Test
	public void testAtualizarComCursoIdNull() {
		Curso cursoEsperado = new Curso();
		cursoEsperado.setId(null);
		cursoEsperado.setNome("ads");
		cursoEsperado.setPeriodo("Noturno");
		cursoEsperado.setValor(500.00);
		assertThrows(CursoException.class,
				()-> cursoService.atualizar(cursoEsperado));
	}
	@Test
	public void testAtualizarComValorIdNull() {
		Curso cursoEsperado = new Curso();
		cursoEsperado.setId(null);
		cursoEsperado.setNome("ads");
		cursoEsperado.setPeriodo("Noturno");
		cursoEsperado.setValor(null);
		assertThrows(CursoException.class,
				()-> cursoService.atualizar(cursoEsperado));
	}
	
	@Test
	public void testAtualizar() {
		Curso curso = new Curso();
		curso.setId(1);
		curso.setNome("ads");
		curso.setPeriodo("Noturno");
		curso.setValor(500.00);
		Curso cursoEsperado = new Curso ();
		cursoEsperado.setId(1);
		cursoEsperado.setNome("ADS");
		cursoEsperado.setPeriodo("Noturno");
		cursoEsperado.setValor(500.00);
		Mockito.when(cursoRepository.save(curso)).thenReturn(cursoEsperado);
		
		Curso cursoRetornado = new Curso();
		cursoRetornado = cursoService.atualizar(curso);
		assertEquals(cursoRetornado, cursoEsperado);
	}
	@Test
	public void testFindByIdComValorNulo() {
		//Cenário
		Integer id = null;
		Mockito.when(cursoRepository.findById(id)).thenReturn(Optional.ofNullable(null));
		//simular o comportamento do objeto
		assertThrows(CursoException.class,
				()-> cursoService.findById(id));
	}
	@Test
	public void testFindByIdComValorVazio() {
		//Cenário
		Integer id = 1;
		Mockito.when(cursoRepository.findById(id)).thenReturn(Optional.empty());
		//simular o comportamento do objeto
		assertThrows(CursoException.class,
				()-> cursoService.findById(id));
	}
	
	@Test
	public void testFindById() {
		//Cenário
	    Integer id = 1;

	    Curso cursoEsperado = new Curso();
	    cursoEsperado.setId(id);
	    
	    Mockito.when(cursoRepository.findById(id)).thenReturn(Optional.of(cursoEsperado));
	    
	    //Executa o método a ser testado
	    Curso cursoRetornado = cursoService.findById(id);

	    //Verifica se o aluno retornado é igual ao aluno esperado
	    assertEquals(cursoEsperado, cursoRetornado);
	}
	
	@Test
	public void testFindByNomeNull() {
		Curso cursoEsperada = new Curso();
		//cursoEsperada.setNome("ads");
		cursoEsperada.setPeriodo("Noturno");
	    
	    List<Curso> listaEsperada = new ArrayList<>();
	    listaEsperada.add(cursoEsperada);

	    Mockito.when(cursoRepository.findByNomeLike(cursoEsperada.getNome())).thenReturn(listaEsperada);

	    // Executa o método a ser testado
	    List<Curso> listaRetornada = cursoRepository.findByNomeLike(cursoEsperada.getNome());

	    // Verifica se a lista retornada é igual à lista esperada
	    assertEquals(listaEsperada, listaRetornada);
	}
	

	@Test
	public void testFindByNomeVazio() {
		Curso cursoEsperado = new Curso();
		cursoEsperado.setId(1);
		cursoEsperado.setNome(" ");
		cursoEsperado.setPeriodo("Noturno");

		List<Curso> listaEsperada = new ArrayList<>();
	    listaEsperada.add(cursoEsperado);

	    assertThrows(CursoException.class,
	    		()-> cursoService.findByNomeLike(cursoEsperado.getNome()));
	}
	@Test
	public void testFindByNome() {
		Curso cursoEsperada = new Curso();
		cursoEsperada.setNome("Spring");
		cursoEsperada.setPeriodo("Noturno");

		List<Curso> listaEsperada = new ArrayList<>();
	    listaEsperada.add(cursoEsperada);

	    Mockito.when(cursoRepository.findByNomeLike(cursoEsperada.getNome())).thenReturn(listaEsperada);

	    // Executa o método a ser testado
	    List<Curso> listaRetornada = cursoService.findByNomeLike(cursoEsperada.getNome());

	    // Verifica se a lista retornada é igual à lista esperada
	    assertEquals(listaEsperada, listaRetornada);
	}
	
	@Test
	public void testDeletarComCursoIdNull() {
		Integer id = null;
		assertThrows(CursoException.class, 
				() -> cursoService.deletar(id));
	}
	
	@Test
	public void testDeletar() throws ProfessorException {
		Integer id = 5; //montar o cenario
	    cursoService.deletar(id);; //executar!
	    //validação, verificar se a execução é o que se espera. caminho correto ou Exception.
	    ArgumentCaptor<Integer> capturarId = ArgumentCaptor.forClass(Integer.class);
	    Mockito.verify(cursoRepository).deleteById(capturarId.capture()); //comparar se o que foi passado em do tipo Long
	    assertEquals(id,capturarId.getValue()); //comparação dos valores.
	}
	
	@Test
	public void testFindAllComListaVazia() {
		List<Curso> listaCurso = new ArrayList<>();
		assertThrows(CursoException.class,
				()-> cursoService.findAll());
	}
	
	@Test
	public void testFindAll() {
		Curso c = new Curso();
		c.setId(1);
		c.setNome("Spring");
		c.setPeriodo("Noturno");
		List<Curso> listaCurso = new ArrayList<>();
		listaCurso.add(c);
		Mockito.when(cursoRepository.findAll()).thenReturn(listaCurso);
		
		List<Curso> listaCursoRetornada = new ArrayList<>();
		listaCursoRetornada = cursoService.findAll();
		
		assertEquals(listaCurso, listaCursoRetornada);
	}
}
