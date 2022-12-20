package com.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursos.models.Curso;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Integer>{

}
