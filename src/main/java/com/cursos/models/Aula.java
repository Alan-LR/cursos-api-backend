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


@Entity
@Table(name = "Aulas")
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String nomeAula;
	private String aulaLink;

	
	@ManyToOne
	@JoinColumn(name = "curso_id", nullable = false)
	//nullable quer dizer que esse campo não pode ser nulo
	private Curso curso;

	public Aula() {
	}

	public Aula(Integer id, String nomeAula, String aulaLink, Curso curso) {
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Aula [id=" + id + ", nomeAula=" + nomeAula + ", aulaLink=" + aulaLink + ", curso=" + curso + "]";
	}

}
