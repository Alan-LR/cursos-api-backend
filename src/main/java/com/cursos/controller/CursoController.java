package com.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.models.Curso;
import com.cursos.service.CursoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	// Criar um curso
	@PostMapping(value = "/")
	public ResponseEntity<Curso> salvarCurso(@RequestBody Curso curso) {
		return new ResponseEntity<Curso>(cursoService.salvarCurso(curso), HttpStatus.CREATED);
	}

	// Alterando curso pegando o seu ID
	@PutMapping("/{id}")
	public ResponseEntity<Curso> alterarCurso(@PathVariable Integer id, @RequestBody Curso curso) {
		Optional<Curso> cursoOp = cursoService.findById(id);
		if (!cursoOp.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			curso.setId(cursoOp.get().getId());
			return new ResponseEntity<Curso>(cursoService.salvarCurso(curso), HttpStatus.OK);
		}

	}

	// Alterando curso (Recebendo id no corpo json)
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Curso> atualizar(@RequestBody Curso curso) throws Exception {
		return new ResponseEntity<Curso>(cursoService.salvarCurso(curso), HttpStatus.OK);
	}

	// Pegar um curso por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Curso>> pegarCursoId(@PathVariable Integer id) {
		Optional<Curso> result = cursoService.findById(id);
		if (!result.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<Curso>>(result, HttpStatus.OK);
		}
	}

	// Retornando com page por página
	@GetMapping(value = "/page/{pagina}", produces = "application/json")
	public ResponseEntity<Page<Curso>> cursos(@PathVariable int pagina) throws InterruptedException {
		// Implementando paginacao
		PageRequest page = PageRequest.of((pagina - 1), 5, Sort.by("id"));
		Page<Curso> listPage = (Page<Curso>) cursoService.findAll(page);

		// List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		// List<UsuarioDTO> listDTO = listPage.stream().map(x -> new
		// UsuarioDTO(x)).collect(Collectors.toList());
		return new ResponseEntity<Page<Curso>>(listPage, HttpStatus.OK);
	}

	// Retornando todos os cursos sem paginação
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Curso>> todosCursos() {
		return new ResponseEntity<List<Curso>>(cursoService.pegarTodos(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	// ? - quer dizer que podemos retornar qualquer coisa, algo generico
	public ResponseEntity<?> deleteCurso(@PathVariable(value = "id") Integer id) {
		Optional<Curso> cursoOp = cursoService.findById(id);
		if (!cursoOp.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			cursoService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}