package com.cursos.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Aulas")
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank
	@Column(nullable = false)
	private String nomeAula;
	@NotBlank
	private String aulaLink;

	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	// nullable quer dizer que esse campo não pode ser nulo
	private Curso curso;

	public Aula() {
	}

	public Aula(Integer id, @NotBlank String nomeAula, @NotBlank String aulaLink, Curso curso) {
		super();
		this.id = id;
		this.nomeAula = nomeAula;
		this.aulaLink = aulaLink;
		this.curso = curso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAula() {
		return nomeAula;
	}

	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}

	public String getAulaLink() {
		return aulaLink;
	}

	public void setAulaLink(String aulaLink) {
		this.aulaLink = aulaLink;
	}

	@JsonIgnore
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
