package com.biblioteca.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "editoras")
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 100, nullable = false, unique = true)
	private String nome;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "editoras")
	private Set<Livro> livros = new HashSet<Livro>();

	public Editora(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public Editora() {}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}
	
}
