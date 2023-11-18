package br.com.gabriel.primeiraapi.dto;

import javax.validation.constraints.NotBlank; 
import javax.validation.constraints.Size;
public class ProfessorDto {
	
	@NotBlank //verificar se o campo não é nulo
	private String nome;
	@NotBlank
	@Size(max = 45) // limita o n° de caracteres
	@NotBlank
	private String status;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
