package com.cursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cursos.models.Aula;


@Repository
public interface AulasRepository extends JpaRepository<Aula, Integer>{

	@Query(nativeQuery = true, value = "select * from aulas where curso_id = ?1")
	List<Aula> aulasCurso(Integer id);
}
