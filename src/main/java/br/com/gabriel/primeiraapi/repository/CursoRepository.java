package br.com.gabriel.primeiraapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriel.primeiraapi.entity.Curso;
import br.com.gabriel.primeiraapi.entity.Materia;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
	List<Curso> findByNomeLike(String curso);
}
