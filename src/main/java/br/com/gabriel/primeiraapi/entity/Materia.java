package br.com.gabriel.primeiraapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "materias")
public class Materia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materia")
	private Integer id;
	@ManyToOne
	@JoinColumn(nullable = false, name = "curso")
	private Curso curso;
	@Column(nullable = false, unique = false, length = 45)
	private String nome;
	@ManyToOne//(fetch = FetchType.) //onetomany com lista e conjuto, eager traz o objeto (padrão pro ManyToOne), lazy não.
	@JoinColumn(nullable = false,name = "professor") //para @@ManyToOne 
	private Professor professor;
	@ManyToOne
	@JoinColumn(nullable = false, name ="turma")
	private Turma turma;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
}
