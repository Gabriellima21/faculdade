package br.com.gabriel.primeiraapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
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
import org.springframework.beans.factory.annotation.Autowired;
import br.com.gabriel.primeiraapi.entity.Aluno;
import br.com.gabriel.primeiraapi.entity.Curso;
import br.com.gabriel.primeiraapi.entity.StatusEnum;
import br.com.gabriel.primeiraapi.entity.TesteAluno;
import br.com.gabriel.primeiraapi.entity.Turma;
import br.com.gabriel.primeiraapi.exception.AlunoException;
import br.com.gabriel.primeiraapi.exception.ProfessorException;
import br.com.gabriel.primeiraapi.kafka.producer.AlunoProducer;
import br.com.gabriel.primeiraapi.repository.AlunoRepository;
import br.com.gabriel.primeiraapi.util.DataUtil;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {
	
	@Mock //cria um obj simulado de alunoRepository com os métodos
	private AlunoRepository alunoRepository;

	@InjectMocks //o objeto simulado criado em @Mock é injetado em alunoService pelo @InjectMocks, atendendo as regras ele pode ser simulado.
	private AlunoService alunoService;
	@Mock
	private AlunoProducer alunoProducer;
	@Mock
	private DataUtil data;
	
	
	@Test
	public void testFiadAllListaVazia()  {
		List<Aluno> listaEsperada = new ArrayList<>(); //crio uma lista vazia
		Mockito.when(alunoRepository.findAll()).thenReturn(listaEsperada);   //mock metodo fiadAll do repository
		assertThrows(AlunoException.class, /*valida*/
				() -> alunoService.findAll()); /*executa*/
	}
	
	@Test
	public void testFiandAll() {
		Aluno a2 = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		c.setId(1);
		t.setId(1);
	    a2.setCurso(c);
	    a2.setId(2);
	    a2.setNome("Gabriel"); 
	    a2.setSexo(StatusEnum.M);
	    a2.setStatus(StatusEnum.A);
	    a2.setTurma(t);
	    a2.setData(d);
	    List<Aluno> listaEsperada = new ArrayList<>();
	    listaEsperada.add(a2);
		
		Mockito.when(alunoRepository.findAll()).thenReturn(listaEsperada);
		
		List<Aluno> listaRetornada = new ArrayList<>();
		
		listaRetornada = alunoService.findAll();// Executa o método a ser testado
		assertEquals(listaEsperada,listaRetornada);   // Verifica se a lista retornada é igual à lista esperada
	}
	
	@Test
	public void testFindByIdComValorNulo() {
		//Cenário
		Integer id = null;
		//simular o comportamento do objeto
		//Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.ofNullable(null)); //retorno vazio
		assertThrows(AlunoException.class, /*valida*/
				() -> alunoService.findById(id)); /*executa*/
	}
	
	@Test
	public void testFindByIdComAlunoVazio() {
		Integer id =1;
		Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(AlunoException.class,
			() -> alunoService.findById(id));
	}
	
	@Test
	public void testFindById() {
		// Cenário
	    Integer id = 1;

	    Aluno alunoEsperado = new Aluno();
	    alunoEsperado.setId(id);
	    
	    Mockito.when(alunoRepository.findById(id)).thenReturn(Optional.of(alunoEsperado));
	    
	    // Executa o método a ser testado
	    Aluno alunoRetornado = alunoService.findById(id);

	    // Verifica se o aluno retornado é igual ao aluno esperado
	    assertEquals(alunoEsperado, alunoRetornado);
	}
	
	@Test
	public void testFindByNomeNull() {
	    // Cenário
	    Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		c.setId(1);
		t.setId(1);
	    a.setCurso(c);
	    a.setId(1);
	    a.setNome(null); //Nome nulo para o teste
	    a.setSexo(StatusEnum.M);
	    a.setStatus(StatusEnum.A);
	    a.setTurma(t);
	    a.setData(d);
		a.setNome(null); //Nome nulo para o teste
	    List<Aluno> listaEsperada = new ArrayList<>();
	    listaEsperada.add(a);

	    Mockito.when(alunoRepository.findByNomeLike(a.getNome())).thenReturn(listaEsperada);

	    // Executa o método a ser testado
	    List<Aluno> listaRetornada = alunoRepository.findByNomeLike(a.getNome());

	    // Verifica se a lista retornada é igual à lista esperada
	    assertEquals(listaEsperada, listaRetornada);
	}
	
	@Test
	public void testFindByNomeVazio() {
	    Aluno a2 = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		c.setId(1);
		t.setId(1);
	    a2.setCurso(c);
	    a2.setId(2);
	    a2.setNome(""); //Nome vazio
	    a2.setSexo(StatusEnum.F);
	    a2.setStatus(StatusEnum.A);
	    a2.setTurma(t);
	    a2.setData(d);
	    List<Aluno> listaEsperada = new ArrayList<>();
	    listaEsperada.add(a2);
	    
	    assertThrows(AlunoException.class, 
	    		()-> alunoService.findByNomeLike(a2.getNome()));

	    /*Mockito.when(alunoRepository.findByNomeLike(a2.getNome())).thenReturn(listaEsperada);

	    //Executa o método a ser testado
	    List<Aluno> listaRetornada = alunoRepository.findByNomeLike(a2.getNome());

	    //Verifica se a lista retornada é igual à lista esperada
	    assertEquals(listaEsperada, listaRetornada);*/
	}
	@Test
	public void testFindByNome() {
	    Aluno a2 = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
	    a2.setCurso(c);
	    a2.setId(2);
	    a2.setNome("Gabriel"); 
	    a2.setSexo(StatusEnum.F);
	    a2.setStatus(StatusEnum.A);
	    a2.setTurma(t);
	    a2.setData(d);
	    List<Aluno> listaEsperada = new ArrayList<>();
	    listaEsperada.add(a2);

	    Mockito.when(alunoService.findByNomeLike(a2.getNome())).thenReturn(listaEsperada);

	    // Executa o método a ser testado
	    List<Aluno> listaRetornada = alunoService.findByNomeLike(a2.getNome());

	    // Verifica se a lista retornada é igual à lista esperada
	    assertEquals(listaEsperada, listaRetornada);
	}
	
	@Test
	public void testCriarAlunoComIdNaoExistente() {
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		a.setId(50);
		a.setData(d);
		assertThrows(AlunoException.class, 
				() -> alunoService.criar(a)); 
	}
	
	@Test
	public void testCriarAlunoNull() {
		//criar cenário
		Aluno a = null;
		assertThrows(AlunoException.class, //validação
				() -> alunoService.criar(a)); //execução
	}
	
	@Test
	public void testCriarAlunoComNomeVazio() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		c.setId(1);
		t.setId(1);
		a.setNome("");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		a.setData(d);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.criar(a)); //execução
	}
	
	@Test
	public void testCriarAlunoComNomeNull() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		t.setId(1);
		c.setId(1);
		a.setNome(null);
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		a.setData(d);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.criar(a)); //execução
	}
	@Test
	public void testCriarAlunoComCursoNaoExistente() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		t.setId(1);
		a.setNome("Gabriel");
		//a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		a.setData(d);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.criar(a)); //execução
	}
	
	@Test
	public void testCriarAlunoComCursoNull() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = null;
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		a.setData(d);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.criar(a)); //execução
	}
	
	@Test
	public void testCriarAlunoComTurmaIdNull() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		c.setId(1);
		t.setId(null);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		a.setData(d);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.criar(a)); //execução
	}
	

	@Test
	public void testCriarAlunoComTurmaNull() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = null;
		LocalDate d = data.dataAtual();
		c.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		a.setData(d);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.criar(a)); //execução
	}
	
	
	@Test
	public void testCriarAlunoComStatusNull() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		c.setId(1);
		t.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(null);
		a.setSexo(StatusEnum.M);
		a.setData(d);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.criar(a)); //execução
	}
	
	@Test
	public void testCriar() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		LocalDate d = data.dataAtual();
		c.setId(1);
		t.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		a.setData(d);
		Aluno alunoEsperado = new Aluno();
		Curso c2= new Curso();
		Turma t2 = new Turma();
		LocalDate d2 = data.dataAtual();
		c2.setId(1);
		t2.setId(1);
		alunoEsperado.setNome("Gabriel");
		alunoEsperado.setCurso(c2);
		alunoEsperado.setTurma(t2);
		alunoEsperado.setStatus(StatusEnum.A);
		alunoEsperado.setSexo(StatusEnum.M);
		//alunoEsperado.setData(data2);
		alunoEsperado.setData(d2);
		
		Mockito.when(alunoRepository.save(a)).thenReturn(alunoEsperado);
		
		Aluno alunoRetornado = alunoService.criar(a); //guardo o que retorna no alunoRepository.save(a)

		assertEquals(alunoEsperado,alunoRetornado); //comparo os 2.
		
	    Mockito.verify(alunoProducer).enviar(alunoRetornado);
	}
	
	@Test
	public void testAtualizaAlunoComIdNull() {
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		a.setId(null);
		c.setId(1);
		t.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		assertThrows(AlunoException.class, 
				() -> alunoService.criar(a)); 
	}
	
	@Test
	public void testAtualizarAlunoNull() {
		//criar cenário
		Aluno a = null;
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}
	
	@Test
	public void testAtualizarAlunoComNomeVazio() {
		//criar cenário
		Aluno a = new Aluno();
	    Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		a.setNome("	");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}
	
	@Test
	public void testAtualizarAlunoComNomeNull() {
		//criar cenário
		Aluno a = new Aluno();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		a.setNome(null);
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}
	@Test
	public void testAtualizarAlunoComCursoNaoExistente() {
		//criar cenário
		Aluno a = new Aluno();
		Curso c = new Curso();
		Turma t = new Turma();
		t.setId(1);
		c.setId(null);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}
	
	@Test
	public void testAtualizarAlunoComCursoNull() {
		//criar cenário
		Aluno a = new Aluno();
		Curso c = null;
		Turma t = new Turma();
		t.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}
	
	@Test
	public void testAtualizarAlunoComTurmaNaoExistente() {
		//criar cenário
		Aluno a = new Aluno();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(null);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}
	
	@Test
	public void testAtualizarAlunoComTurmaNull() {
		//criar cenário
		Aluno a = new Aluno();
		Curso c = new Curso();
		Turma t = null;
		c.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(StatusEnum.M);
		
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}
	
	@Test
	public void testAtualizarAlunoComStatusNull() {
		//criar cenário
		Aluno a = new Aluno();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(null);
		a.setSexo(StatusEnum.M);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}

	
	@Test
	public void testAtualizarAlunoComSexoNull() {
		//criar cenário
		Aluno a = new Aluno();
		Curso c = new Curso();
		Turma t = new Turma();
		c.setId(1);
		t.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.A);
		a.setSexo(null);
		assertThrows(AlunoException.class, //validação
				() -> alunoService.atualizar(a)); //execução
	}
	
	@Test
	public void testAtualizar() {
		Aluno a = new Aluno();
		Curso c = new Curso();
		Turma t = new Turma();
		a.setId(1);
		c.setId(1);
		t.setId(1);
		a.setNome("Gabriel");
		a.setCurso(c);
		a.setTurma(t);
		a.setStatus(StatusEnum.I);
		a.setSexo(StatusEnum.M);
		Aluno alunoEsperado = new Aluno();
		Curso c2 = new Curso();
		Turma t2 = new Turma();
		a.setId(1);
		c2.setId(1);
		t2.setId(1);
		alunoEsperado.setNome("Gabriel");
		alunoEsperado.setCurso(c);
		alunoEsperado.setTurma(t);
		alunoEsperado.setStatus(StatusEnum.I);
		alunoEsperado.setSexo(StatusEnum.M);
		
		
		Mockito.when(alunoRepository.save(a)).thenReturn(alunoEsperado);
		
		Aluno alunoRetornado = alunoService.atualizar(a);
		assertEquals(alunoEsperado,alunoRetornado);
	}
	
	@Test
	public void testDeletarComAlunoIdNull() {
		Integer id = null;
		assertThrows(AlunoException.class, 
				() -> alunoService.delete(id));
	}
	@Test
	public void testDeletar() throws ProfessorException {
		Integer id = 5; //montar o cenario
	    alunoService.delete(id);; //executar!
	    //validação, verificar se a execução é o que se espera. caminho correto ou Exception.
	    ArgumentCaptor<Integer> capturarId = ArgumentCaptor.forClass(Integer.class);
	    Mockito.verify(alunoRepository).deleteById(capturarId.capture()); //comparar se o que foi passado em do tipo Long
	    assertEquals(id,capturarId.getValue()); //comparação dos valores.
	}
}
