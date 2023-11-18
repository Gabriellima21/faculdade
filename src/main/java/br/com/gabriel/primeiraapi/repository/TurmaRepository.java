package br.com.gabriel.primeiraapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.gabriel.primeiraapi.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer>{
	
	List<Turma> findByNomeLike(String turma);
}
