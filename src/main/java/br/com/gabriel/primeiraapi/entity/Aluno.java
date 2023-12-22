package br.com.gabriel.primeiraapi.entity;

import java.time.LocalDate;

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
@Table(name = "alunos")
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_matricula")
	private Integer id;
	@Column(nullable = false, unique = false, length = 45)
	private String nome;
	@ManyToOne
	@JoinColumn(nullable = false, name = "curso")
	private Curso curso;
	@ManyToOne
	@JoinColumn(nullable = false, name = "turma")
	private Turma turma;
	@Column(name = "data")
	private LocalDate data;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	@Enumerated(EnumType.STRING)
	private StatusEnum sexo;
	@Column
	private Integer codigo;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
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
	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public StatusEnum getSexo() {
		return sexo;
	}
	public void setSexo(StatusEnum sexo) {
		this.sexo = sexo;
	}
}
