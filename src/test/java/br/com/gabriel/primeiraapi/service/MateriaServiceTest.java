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

import br.com.gabriel.primeiraapi.entity.Aluno;
import br.com.gabriel.primeiraapi.entity.Curso;
import br.com.gabriel.primeiraapi.entity.Materia;
import br.com.gabriel.primeiraapi.entity.Professor;
import br.com.gabriel.primeiraapi.entity.StatusEnum;
import br.com.gabriel.primeiraapi.entity.Turma;
import br.com.gabriel.primeiraapi.exception.AlunoException;
import br.com.gabriel.primeiraapi.exception.MateriaException;
import br.com.gabriel.primeiraapi.exception.ProfessorException;
import br.com.gabriel.primeiraapi.repository.MateriaRepository;
import br.com.gabriel.primeiraapi.util.Data;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {
	
	@Mock //tenho um obj simulado de alunoRepository com os métodos
	private MateriaRepository materiaRepository;
	
	@InjectMocks
	private MateriaService materiaService;
	
	@Test
	public void testCriarMateriaNull() {
		Materia materia = null;
		assertThrows(MateriaException.class, //valida
				() -> materiaService.criar(materia)); //executa
	}
	
	@Test
	public void testCriarMateriaVazia() {
		Materia materia = new Materia();
		assertThrows(MateriaException.class, //valida
				() -> materiaService.criar(materia)); //executa
	}
	
	@Test
	public void testCriarMateriaComNomeNull() {
		//crio o cenário
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		p.setId(2l);
		//materiaEsperada.setNome(null);
		materiaEsperada.setProfessor(p);
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);
		assertThrows(MateriaException.class, 
				() -> materiaService.criar(materiaEsperada)); 
	}
	
	@Test
	public void testCriarMateriaComNomeVazio() {
		//crio o cenário
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		p.setId(2l);
		materiaEsperada.setNome("");
		materiaEsperada.setProfessor(p);
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);
		assertThrows(MateriaException.class, //valida exception
			() -> materiaService.criar(materiaEsperada)); //executa o service
	}
	
	@Test
	public void testCriarMateriaComProfessorNull() {
		Materia materia = new Materia();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		materia.setCurso(c);
		materia.setNome("TDD");
		materia.setTurma(t);
		//materia.setProfessor(null);
		assertThrows(MateriaException.class,
				() -> materiaService.criar(materia));
	}
	@Test
	public void testCriarMateriaComProfessorVazio() {
		Materia materia = new Materia();
		Professor p = new Professor();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		materia.setCurso(c);
		materia.setNome("TDD");
		materia.setProfessor(p); //vazio
		materia.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.criar(materia));
	}
	@Test
	public void testCriarMateriaComCursoNull() {
		Materia materia = new Materia();
		Professor p = new Professor();
		Curso c = null;
		Turma t = new Turma();
		t.setId(1);
		p.setId(2l);
		materia.setNome("TDD");
		materia.setCurso(c);
		materia.setProfessor(p);
		materia.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.criar(materia));
	}
	@Test
	public void testCriarMateriaComCursoVazio() {
		Materia materia = new Materia();
		Professor p = new Professor();
		Curso c = new Curso();
		Turma t = new Turma();
		t.setId(1);
		p.setId(2l);
		materia.setNome("TDD");
		materia.setCurso(c); //vazio
		materia.setProfessor(p);
		materia.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.criar(materia));
	}
	@Test
	public void testCriarMateriaTurmaNull() {
		Materia materia = new Materia();
		Professor p = new Professor();
		Curso c = new Curso();
		Turma t = null;
		c.setId(1);
		p.setId(1L);
		materia.setCurso(c);
		materia.setNome("Spring");
		materia.setProfessor(p);
		materia.setTurma(t);
		assertThrows(MateriaException.class, 
				() -> materiaService.criar(materia));
		
	}
	@Test
	public void testCriarMateriaComIdNull() {
		Materia materia = new Materia();
		Professor p = new Professor();
		Curso c = new Curso();
		Turma t = new Turma();
		t.setId(null);
		c.setId(1);
		materia.setCurso(c);
		materia.setNome("Spring");
		materia.setProfessor(p);
		materia.setTurma(t);
		assertThrows(MateriaException.class, 
				() -> materiaService.criar(materia));
	}
	@Test
	public void testCriar() {
		//cenario
		Materia materia = new Materia();
		Professor p = new Professor();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		p.setId(2L);
		materia.setCurso(c);
		materia.setNome("Spring");
		materia.setProfessor(p);
		materia.setTurma(t);
		Materia materiaEsperada = new Materia();
		Professor professor = new Professor();
		Curso curso = new Curso();
		Turma turma = new Turma();
		curso.setId(1);
		turma.setId(1);
		professor.setId(2L);
		materiaEsperada.setCurso(curso);
		materiaEsperada.setNome("Spring");
		materiaEsperada.setProfessor(professor);
		materiaEsperada.setTurma(turma);
		
		Mockito.when(materiaService.criar(materia)).thenReturn(materiaEsperada);
		
		Materia materiaRetornada = new Materia();
		materiaRetornada = materiaService.criar(materia);
		assertEquals(materiaRetornada, materiaEsperada);
	}
	
	@Test
	public void testAtualizarMateriaNull() {
		Materia materiaEsperada = null;
		assertThrows(MateriaException.class, 
				() -> materiaService.atualizar(materiaEsperada));
	}
	
	@Test
	public void testAtualizarMateriaVazia() {
		Materia materiaEsperada = new Materia();
		assertThrows(MateriaException.class, 
				() -> materiaService.atualizar(materiaEsperada));
	}
	
	@Test
	public void testAtualizaMateriaCursoNull() {
		Materia materia = new Materia();
		Professor p = new Professor();
		Curso c = null;
		Turma t = new Turma();
		materia.setId(1);
		t.setId(1);
		p.setId(2l);
		materia.setNome("TDD");
		materia.setCurso(c);
		materia.setProfessor(p);
		materia.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.criar(materia));
	}
	
	@Test
	public void testAtualizaCursoVazio() {
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		t.setId(1);
		p.setId(2l);
		materiaEsperada.setNome("TDD");
		materiaEsperada.setProfessor(p);
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.atualizar(materiaEsperada));
	}
	
	@Test
	public void testAtualizarNomeVazio() {
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		c.setId(1);
		t.setId(1);
		p.setId(2l);
		materiaEsperada.setNome("	");
		materiaEsperada.setProfessor(p);
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.atualizar(materiaEsperada));
	}
	
	@Test
	public void testAtualizaNomeNull() {
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		c.setId(1);
		t.setId(1);
		p.setId(2l);
		materiaEsperada.setNome(null);
		materiaEsperada.setProfessor(p);
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.atualizar(materiaEsperada));
	}
	
	@Test
	public void testAtualizaNomeVazio() {
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		c.setId(1);
		t.setId(1);
		p.setId(2l);
		materiaEsperada.setNome(" ");
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);
		materiaEsperada.setProfessor(p);
		assertThrows(MateriaException.class,
				() -> materiaService.atualizar(materiaEsperada));
	}
	
	@Test
	public void testAtualizaProfessorNull() {
		Materia materiaEsperada = new Materia();
		Turma t = new Turma();
		Curso c = new Curso();
		Professor p = null;
		c.setId(1);
		t.setId(1);
		materiaEsperada.setNome(" ");
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);
		materiaEsperada.setProfessor(p);
		assertThrows(MateriaException.class,
				() -> materiaService.atualizar(materiaEsperada));
	}
	@Test
	public void testAtualizaProfessorVazio() {
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		c.setId(1);
		t.setId(1);
		materiaEsperada.setNome(" ");
		materiaEsperada.setCurso(c);
		materiaEsperada.setProfessor(p);
		materiaEsperada.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.atualizar(materiaEsperada));
	}
	@Test
	public void testAtualizarTurmaVazia() {
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		c.setId(1);
		p.setId(1L);
		materiaEsperada.setNome(" ");
		materiaEsperada.setCurso(c);
		materiaEsperada.setProfessor(p);
		materiaEsperada.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.atualizar(materiaEsperada));
	}
	@Test
	public void testAtualizarTurmaNull() {
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Turma t = null;
		Curso c = new Curso();
		c.setId(1);
		p.setId(1L);
		materiaEsperada.setNome(" ");
		materiaEsperada.setCurso(c);
		materiaEsperada.setProfessor(p);
		materiaEsperada.setTurma(t);
		assertThrows(MateriaException.class,
				() -> materiaService.atualizar(materiaEsperada));
	}
	
	@Test
	public void testAtualizar() {
		Materia materia = new Materia();
		Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		materia.setId(1);
		c.setId(1);
		t.setId(2);
		p.setId(2l);
		materia.setNome("A");
		materia.setCurso(c);
		materia.setProfessor(p);
		materia.setTurma(t);
		Materia materiaEsperada = new Materia();
		Professor professor = new Professor();
		Turma turma = new Turma();
		Curso curso = new Curso();
		materiaEsperada.setId(1);
		curso.setId(1);
		turma.setId(2);
		professor.setId(2l);
		materiaEsperada.setNome("A");
		materiaEsperada.setNome("Spring");
		materiaEsperada.setProfessor(professor);
		materiaEsperada.setCurso(curso);
		materiaEsperada.setTurma(turma);
		
		Mockito.when(materiaService.atualizar(materia)).thenReturn(materiaEsperada);
		
		Materia materiaRetornada = new Materia();
		materiaRetornada = materiaService.atualizar(materia);
		assertEquals(materiaRetornada, materiaEsperada);
	}
	
	@Test
	public void testFiadAllListaVazia()  {
		List<Materia> listaEsperada = new ArrayList<>(); //crio uma lista vazia
		//Mockito.when(materiaRepository.findAll()).thenReturn(listaEsperada);   //mock metodo fiadAll do repository
		assertThrows(MateriaException.class, /*valida*/
				() -> materiaService.findAll()); /*executa*/
	}
	@Test
	public void testFiandAll() {
		Materia materiaEsperada = new Materia();
		Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		c.setId(1);
		t.setId(1);
		materiaEsperada.setNome("Spring");
		materiaEsperada.setCurso(c);
		materiaEsperada.setProfessor(p);
		materiaEsperada.setTurma(t);
	    List<Materia> listaEsperada = new ArrayList<>();
	    listaEsperada.add(materiaEsperada);
		
		Mockito.when(materiaRepository.findAll()).thenReturn(listaEsperada);
		
		List<Materia> listaRetornada = new ArrayList<>();
		
		listaRetornada = materiaService.findAll();// Executa o método a ser testado
		assertEquals(listaEsperada,listaRetornada);   // Verifica se a lista retornada é igual à lista esperada
	}
	
	@Test
	public void testFindByIdComIdNaoExistente() {
		// Cenário
	    Integer id = 0;
	    //simular o comportamento do objeto
	    Mockito.when(materiaRepository.findById(id)).thenReturn(Optional.empty());
	    
	    // Executa o método a ser testado e espera uma exceção
	    assertThrows(MateriaException.class,
	    		()-> materiaService.findById(id));
	}
	
	@Test
	public void testFindByIdComValorNulo() {
		//Cenário
		Integer id = null;
		assertThrows(MateriaException.class, 
				()-> materiaService.findById(id));
	}
	
	@Test
	public void testFindById() {
		//Cenário
	    Integer id = 1;

	    Materia materiaEsperado = new Materia();
	    materiaEsperado.setId(id);
	    
	    Mockito.when(materiaRepository.findById(id)).thenReturn(Optional.of(materiaEsperado));
	    
	    //Executa o método a ser testado
	    Materia materiaRetornado = materiaService.findById(id);

	    //Verifica se o aluno retornado é igual ao aluno esperado
	    assertEquals(materiaEsperado, materiaRetornado);
	}
	
	@Test
	public void testFindByNomeNull() {
		 Materia materiaEsperada = new Materia();
	    Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		t.setId(1);
		c.setId(1);
		p.setId(2l);
		materiaEsperada.setNome(null);
		materiaEsperada.setProfessor(p);
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);
	    
	    List<Materia> listaEsperada = new ArrayList<>();
	    listaEsperada.add(materiaEsperada);
	    
	    assertThrows(MateriaException.class,
	    		()-> materiaService.findByNomeLike(materiaEsperada.getNome()));
	}
	

	@Test
	public void testFindByNomeVazio() {
		Materia materiaEsperada = new Materia();
	    Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		t.setId(1);
		c.setId(1);
		p.setId(2l);
		materiaEsperada.setNome("	");
		materiaEsperada.setProfessor(p);
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);

	    List<Materia> listaEsperada = new ArrayList<>();
	    listaEsperada.add(materiaEsperada);
	    
	    assertThrows(MateriaException.class,
	    		()-> materiaService.findByNomeLike(materiaEsperada.getNome()));
	}
	
	@Test
	public void testFindByNome() {
		Materia materiaEsperada = new Materia();
	    Professor p = new Professor();
		Turma t = new Turma();
		Curso c = new Curso();
		t.setId(1);
		c.setId(1);
		p.setId(2l);
		materiaEsperada.setNome("Spring");
		materiaEsperada.setProfessor(p);
		materiaEsperada.setCurso(c);
		materiaEsperada.setTurma(t);

	    List<Materia> listaEsperada = new ArrayList<>();
	    listaEsperada.add(materiaEsperada);

	    Mockito.when(materiaService.findByNomeLike(materiaEsperada.getNome())).thenReturn(listaEsperada);

	    // Executa o método a ser testado
	    List<Materia> listaRetornada = materiaService.findByNomeLike(materiaEsperada.getNome());

	    // Verifica se a lista retornada é igual à lista esperada
	    assertEquals(listaEsperada, listaRetornada);
	}
	
	@Test
	public void testDeletarComIdNull() {
		Integer id = null;
		assertThrows(MateriaException.class, 
				()-> materiaService.deletar(id));
	}
	
	@Test
	public void testDeletar() throws ProfessorException {
		Integer id = 5; //montar o cenario
	    materiaService.deletar(id);; //executar!
	    //validação, verificar se a execução é o que se espera. caminho correto ou Exception.
	    ArgumentCaptor<Integer> capturarId = ArgumentCaptor.forClass(Integer.class);
	    Mockito.verify(materiaRepository).deleteById(capturarId.capture()); //comparar se o que foi passado em do tipo Long
	    assertEquals(id,capturarId.getValue()); //comparação dos valores.
	}
}
