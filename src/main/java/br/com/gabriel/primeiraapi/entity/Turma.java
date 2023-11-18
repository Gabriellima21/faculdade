package br.com.gabriel.primeiraapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "turmas")
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private Integer id;
	@Column(name = "turma")
	private String nome;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	@JoinColumn(name = "curso")
	@ManyToOne
	private Curso curso;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
}
