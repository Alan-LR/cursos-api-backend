package com.cursos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.cursos.models.Aula;


@Repository
public interface AulasRepository extends JpaRepository<Aula, Integer>{

	@Query(nativeQuery = true, value = "select * from aulas where curso_curso_id = ?1")
	Page<Aula> aulasCurso(Integer id, Pageable pageable);
}
