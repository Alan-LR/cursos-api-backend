package com.cursos.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cursos.models.Curso;
import com.cursos.repository.CursosRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursosRepository cursosRepository;
	
	public Curso salvarCurso(Curso curso) {
		return cursosRepository.save(curso);
	}

	public Optional<Curso> findById(Integer id) {
		return cursosRepository.findById(id);
	}

	public Page<Curso> findAll(PageRequest page) {
		 return cursosRepository.findAll(page);
	}

	public List<Curso> pegarTodos() {
		return cursosRepository.findAll();
	}

	public void delete(Integer id) {
		cursosRepository.deleteById(id);
	}
	
}
