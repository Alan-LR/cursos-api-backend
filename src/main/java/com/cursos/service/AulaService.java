package com.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cursos.models.Aula;
import com.cursos.repository.AulasRepository;

@Service
public class AulaService {
	
	@Autowired
	private AulasRepository aulasRepository;

	public Aula salvarAula(Aula aula) {
		return aulasRepository.save(aula);
	}
	

	public Optional<Aula> findById(Integer id) {
		return aulasRepository.findById(id);
	}

	public Page<Aula> findAll(PageRequest page) {
		 return aulasRepository.findAll(page);
	}

	public List<Aula> pegarTodos() {
		return aulasRepository.findAll();
	}

	public void delete(Integer id) {
		aulasRepository.deleteById(id);	
	}

	public Page<Aula> aulasCurso(Integer id, Pageable pageable) {
		return aulasRepository.aulasCurso(id, pageable); 
	}
	

}
