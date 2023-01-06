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

import com.cursos.models.Aula;
import com.cursos.service.AulaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/aulas")
public class AulaController {

	@Autowired
	private AulaService aulaService;

	// Criar uma aula
	@PostMapping("/")
	public ResponseEntity<Aula> salvarCurso(@RequestBody Aula aula) {
		return new ResponseEntity<Aula>(aulaService.salvarAula(aula), HttpStatus.CREATED);
	}

	// Alterando aula pegando o seu ID
	@PutMapping("/{id}")
	public ResponseEntity<Aula> alterarCurso(@PathVariable Integer id, @RequestBody Aula aula) {
		Optional<Aula> aulaOp = aulaService.findById(id);
		if (!aulaOp.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			aula.setId(aulaOp.get().getId());
			return new ResponseEntity<Aula>(aulaService.salvarAula(aula), HttpStatus.OK);
		}

	}

	// Alterando aula (Recebendo id no corpo json)
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Aula> atualizar(@RequestBody Aula aula) throws Exception {
		return new ResponseEntity<Aula>(aulaService.salvarAula(aula), HttpStatus.OK);
	}

	// Pegar uma aula por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Aula>> pegarAulaId(@PathVariable Integer id) {
		Optional<Aula> result = aulaService.findById(id);
		if (!result.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<Aula>>(result, HttpStatus.OK);
		}
	}

	// Retornando com page por página
	@GetMapping(value = "/page/{pagina}", produces = "application/json")
	public ResponseEntity<Page<Aula>> aulas(@PathVariable int pagina) throws InterruptedException {
		// Implementando paginacao
		PageRequest page = PageRequest.of((pagina - 1), 5, Sort.by("id"));
		Page<Aula> listPage = (Page<Aula>) aulaService.findAll(page);

		// List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		// List<UsuarioDTO> listDTO = listPage.stream().map(x -> new
		// UsuarioDTO(x)).collect(Collectors.toList());
		return new ResponseEntity<Page<Aula>>(listPage, HttpStatus.OK);
	}

	// Retornando todas as aulas sem paginação
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Aula>> todosCursos() {
		return new ResponseEntity<List<Aula>>(aulaService.pegarTodos(), HttpStatus.OK);
	}
	
	//Metodo para retornar todas as aulas de um curso
	@GetMapping(value = "/curso/{id}")
	public ResponseEntity<List<Aula>> aulasCurso(@PathVariable Integer id){
		return new ResponseEntity<List<Aula>>(aulaService.aulasCurso(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	// ? - quer dizer que podemos retornar qualquer coisa, algo generico
	public ResponseEntity<?> deleteAula(@PathVariable(value = "id") Integer id) {
		Optional<Aula> aulaOp = aulaService.findById(id);
		if (!aulaOp.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			aulaService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
