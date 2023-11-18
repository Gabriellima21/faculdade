package br.com.gabriel.primeiraapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import br.com.gabriel.primeiraapi.entity.Professor;
import br.com.gabriel.primeiraapi.entity.StatusEnum;
import br.com.gabriel.primeiraapi.exception.ProfessorException;
import br.com.gabriel.primeiraapi.repository.ProfessorRepository;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

	@InjectMocks //o objeto simulado criado em @Mock é injetado em ProfessorService pelo @InjectMocks, atendendo as regras ele pode ser simulado.
	private ProfessorService professorService;
	
	@Mock //cria um obj simulado de ProfessorRepository com os métodos
	private ProfessorRepository professorRepository;
	
	@Test
	public void testCriarProfessorNull() {
		Professor p = null;
		assertThrows(ProfessorException.class, 
				() -> professorService.criar(p)); 
	}
	@Test
	public void testCriarProfessorComNomeNull() {
		Professor p = new Professor();
		assertThrows(ProfessorException.class, 
				() -> professorService.criar(p)); 
	}
	@Test
	public void testCriarProfessorComNomeVazio() {
		//preparo cenario
		Professor p = new Professor();
		p.setNome("  ");
		assertThrows(ProfessorException.class, //validação
				() -> professorService.criar(p)); //execução
	}
	@Test
	public void testCriarProfessorComStatusNull() {
		Professor p = new Professor();
		p.setNome("Gabriel");
		assertThrows(ProfessorException.class, 
				() -> professorService.criar(p)); 
	}
	@Test
	public void testCriarProfessorComIdNaoNull() {
		//pe
		Professor p = new Professor();
		p.setNome("Gabriel");
		p.setStatus(StatusEnum.A);
		p.setId(2l);
		assertThrows(ProfessorException.class, 
				() -> professorService.criar(p)); 
	}
	@Test
	public void testCriar() throws ProfessorException {
		// preparo o cenario
		Professor p = new Professor();
		p.setNome("Gabriel");
		p.setStatus(StatusEnum.A);
		Professor professorEsperado = new Professor();
		professorEsperado.setNome("Lucas");
		professorEsperado.setStatus(StatusEnum.I);
		
		Mockito.when(professorRepository.save(p)).thenReturn(professorEsperado);
		
		Professor professorRetornado = professorService.criar(p);
		assertEquals(professorEsperado,professorRetornado);
	}
	@Test
	public void testAtualizarComProfessorNull() {
		Professor p = null;
		assertThrows(ProfessorException.class, 
				() -> professorService.atualizar(p)); 
	}
	@Test
	public void testAtualizarComProfessorNomeNull() {
		Professor p = new Professor();
		assertThrows(ProfessorException.class, 
				() -> professorService.atualizar(p));
	}
	@Test
	public void testAtualizarComProfessorNomeVazio() {
		Professor p = new Professor();
		p.setNome(" ");
		assertThrows(ProfessorException.class, 
				() -> professorService.atualizar(p));
	}
	@Test
	public void testAtualizarComProfessorStatusNull() {
		Professor p = new Professor();
		p.setNome("Lucas");
		assertThrows(ProfessorException.class, 
				() -> professorService.atualizar(p));
	}
	@Test
	public void testAtualizarComProfessorIdNull() {
		Professor p = new Professor();
		p.setNome("Lucas");
		p.setStatus(StatusEnum.A);
		assertThrows(ProfessorException.class, 
				() -> professorService.atualizar(p));
	}
	
	@Test
	public void testAtualizar() throws ProfessorException {
		Professor p = new Professor();
		p.setNome("Lucas");
		p.setStatus(StatusEnum.A);
		p.setId(26l);
		Professor professorEsperado = new Professor();
		professorEsperado.setNome("Lucas");
		professorEsperado.setStatus(StatusEnum.A);
		professorEsperado.setId(26l);
		Mockito.when(professorService.atualizar(p)).thenReturn(professorEsperado);
		
		Professor professorRetornado = professorService.atualizar(p);
		assertEquals(professorEsperado,professorRetornado);
	}
	@Test
	public void testDeletarComProfessorIdNull() {
		Long p = null;
		assertThrows(RuntimeException.class, 
				() -> professorService.deletar(p));
	}
	@Test
	public void testDeletar() throws ProfessorException {
		Long p = 24l; //montar o cenario, instaciar o obj e setar os valores do obj, td que precisa para a execução do teste.
	    professorService.deletar(p); //executar!
	    //validação, verificar se a execução é o que se espera. caminho correto ou Exception.
	    ArgumentCaptor<Long> capturarId = ArgumentCaptor.forClass(Long.class);
	    Mockito.verify(professorRepository).deleteById(capturarId.capture()); //comparar se o que foi passado em do tipo Long
	    assertEquals(p,capturarId.getValue()); //comparação dos valores.
	}
	
	@Test
	public void testConsultarComProfessorNull() throws ProfessorException {
		List<Professor> listaEsperada = new ArrayList<Professor>(); //crio uma lista vazia
		Mockito.when(professorRepository.findAll()).thenReturn(listaEsperada);   //mock metodo consultar do repository
		assertThrows(ProfessorException.class, /*valida*/
				() -> professorService.consultar()); /*executa*/
	}
	
	@Test
	public void testConsultarComListaVazia() throws ProfessorException {
		List<Professor> p = new ArrayList<>();
		Mockito.when(professorRepository.findAll()).thenReturn(p);   //mock metodo consultar do repository
		assertThrows(ProfessorException.class, 
				() -> professorService.consultar());
	}
	
	@Test
	public void testConsultar(){
		Professor p = new Professor();;
		p.setId(1L);
		p.setNome("Gabriel");
		p.setStatus(StatusEnum.A);
		List<Professor> listaEsperada = new ArrayList<>(); //crio uma lista vazia
		listaEsperada.add(p);
		Mockito.when(professorRepository.findAll()).thenReturn(listaEsperada); 
		List<Professor> listaRetornada = professorService.consultar();
		assertEquals(listaEsperada,listaRetornada);
	}
}