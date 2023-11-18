package br.com.gabriel.primeiraapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gabriel.primeiraapi.entity.Materia;
@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer>{
	List<Materia> findByNomeLike(String materia);
}
