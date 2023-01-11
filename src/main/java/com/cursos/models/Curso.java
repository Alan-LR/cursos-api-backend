package com.cursos.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Cursos")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @JsonProperty("_id")// - informa como esse dado chegará no front-end
	private Integer curso_id;
	@NotBlank(message = "Por favor, digite um nome") // Não permite salvar nulo ou vázio
	@Column(length = 50, nullable = false, unique = true) // Unique = não deixa repetir o nome salvo
	private String nome;
	@NotBlank
	@Column(length = 15, nullable = false)
	private String categoria;

	// orphanRemoval, para remover as aulas - cascade = CascadeType.REMOVE, remover
	// as aulas só quando curso for removido
	@OneToMany(mappedBy = "curso", orphanRemoval = true, cascade = CascadeType.PERSIST.REFRESH.REMOVE, fetch = FetchType.LAZY)
	private List<Aula> aulas = new ArrayList<Aula>();

	public Curso() {
	}

	public Curso(Integer curso_id, @NotBlank(message = "Por favor, digite um nome") String nome,
			@NotBlank String categoria, List<Aula> aulas) {
		super();
		this.curso_id = curso_id;
		this.nome = nome;
		this.categoria = categoria;
		this.aulas = aulas;
	}

	public Integer getCurso_id() {
		return curso_id;
	}

	public void setCurso_id(Integer curso_id) {
		this.curso_id = curso_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

}
