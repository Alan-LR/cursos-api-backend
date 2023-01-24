package com.cursos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.models.Curso;
import com.cursos.service.CursoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	// Nova logica com post
	@PostMapping(value = "/novoAdd", produces = "application/json")
	public ResponseEntity<Curso> salvarNovoAdd(@Valid @RequestBody Curso curso) throws Exception {
		for (int pos = 0; pos < curso.getAulas().size(); pos++) {
			curso.getAulas().get(pos).setCurso(curso);
		}
		return new ResponseEntity<Curso>(cursoService.salvarCurso(curso), HttpStatus.CREATED);
	}

	// Criar um curso
	@PostMapping(value = "/")
	public ResponseEntity<Curso> salvarCurso(@RequestBody Curso curso) {
		return new ResponseEntity<Curso>(cursoService.salvarCurso(curso), HttpStatus.CREATED);
	}

	// Exemplo utilizando o @ResponseStatus
	@PostMapping(value = "/postCursoTeste")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Curso postCursoTeste(@RequestBody Curso curso) {
		return cursoService.salvarCurso(curso);
	}

	// Alterando curso pegando o seu ID
	@PutMapping("/{id}")
	public ResponseEntity<Curso> alterarCurso(@PathVariable Integer id, @RequestBody Curso curso) {
		System.out.println("Alterando corretamente");

		for (int pos = 0; pos < curso.getAulas().size(); pos++) {
			curso.getAulas().get(pos).setCurso(curso);
		}

		Optional<Curso> cursoOp = cursoService.findById(id);
		if (!cursoOp.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			curso.setCurso_id(cursoOp.get().getCurso_id());
			return new ResponseEntity<Curso>(cursoService.salvarCurso(curso), HttpStatus.OK);
		}
	}

	// Alterando curso (Recebendo id no corpo json)
	@PutMapping(value = "alterando/", produces = "application/json")
	public ResponseEntity<Curso> atualizar(@RequestBody Curso curso) throws Exception {
		for (int pos = 0; pos < curso.getAulas().size(); pos++) {
			curso.getAulas().get(pos).setCurso(curso);
		}
		return new ResponseEntity<Curso>(cursoService.salvarCurso(curso), HttpStatus.OK);
	}

	// Pegar um curso por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Curso>> pegarCursoId(@PathVariable Integer id) {
		Optional<Curso> result = cursoService.findById(id);
		if (!result.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Entrou");
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

	@GetMapping("/pageable")
	public ResponseEntity<Page<Curso>> getAllLives(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Curso> livePage = cursoService.findAllPage(pageable);
		if (livePage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Curso>>(livePage, HttpStatus.OK);
		}
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
