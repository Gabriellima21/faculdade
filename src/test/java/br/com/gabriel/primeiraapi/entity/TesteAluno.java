package br.com.gabriel.primeiraapi.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.gabriel.primeiraapi.util.DataUtil;

public class TesteAluno {
	
	@Autowired
	DataUtil data; 
	
	 @Test
	  public Aluno mockAluno() {
		 Aluno alunoMock = Mockito.mock(Aluno.class);
		 Curso c = new Curso();
		 c.setId(1);
		 Turma t = new Turma();
		 t.setId(1);
		 LocalDate d = data.dataAtual();
		// Define o valor do atributo `nome` do mock
	    Mockito.doReturn("Gabriel").when(alunoMock).getNome();
	    Mockito.doReturn(c).when(alunoMock).getCurso();
	    Mockito.doReturn("M").when(alunoMock).getSexo();
	    Mockito.doReturn("A").when(alunoMock).getStatus();
	    Mockito.doReturn(t).when(alunoMock).getTurma();
	    Mockito.doReturn(d).when(alunoMock).getData();
	    
        String nome = alunoMock.getNome(); // Chama o método `getNome()` do mock, nome recebe o valor retornado
        Curso curso = alunoMock.getCurso();
        StatusEnum sexo = alunoMock.getSexo();
        StatusEnum status = alunoMock.getStatus();
        Turma turma = alunoMock.getTurma();
        LocalDate d2 = alunoMock.getData();
        
        // Verifica o valor retornado com assertEquals
        assertEquals("Gabriel", nome);
        assertEquals(c, curso);
        assertEquals("M", sexo);
        assertEquals("A", status);
        assertEquals("t", turma);
        assertEquals(d, d2);
        
        Aluno alunoMockRetornado = alunoMock;
        
        return alunoMockRetornado;
	 }
}
