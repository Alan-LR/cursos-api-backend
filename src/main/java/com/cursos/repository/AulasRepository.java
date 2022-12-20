package com.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursos.models.Aula;

@Repository
public interface AulasRepository extends JpaRepository<Aula, Integer>{

}
