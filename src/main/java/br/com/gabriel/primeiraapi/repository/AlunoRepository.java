package br.com.gabriel.primeiraapi.repository;

import java.util.List;  

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gabriel.primeiraapi.entity.Aluno;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Integer>{
	
	/*@Query(value = "SELECT p FROM  p WHERE p.nome LIKE ?%")
	List<Aluno>findByNomeLike(String nome);*/
	
	List<Aluno> findByNomeLike(String aluno);
}
