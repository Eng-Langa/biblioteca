package com.biblioteca.model;

import java.util.HashSet;
import java.util.Set;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "livros")

public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "isbn", length = 50, nullable = false, unique = true)
	private String isbn;

	@Column(name = "titulo", length = 100, nullable = false)
	private String titulo;

	@Column(name = "serialName", length = 50, nullable = false)
	private String serialName;

	@Column(name = "descricao", length = 250, nullable = false)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "livros_autores", joinColumns = { @JoinColumn(name = "livro_id") }, inverseJoinColumns = {
			@JoinColumn(name = "autor_id") })
	private Set<Autor> autores = new HashSet<Autor>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "livro_categoria", joinColumns = { @JoinColumn(name = "livro_id") }, inverseJoinColumns = {
			@JoinColumn(name = "categoria_id") })
	private Set<CategoriaLivro> categoriaLivro = new HashSet<CategoriaLivro>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "livro_editora", joinColumns = { @JoinColumn(name = "livro_id") }, inverseJoinColumns = {
			@JoinColumn(name = "editora_id") })
	private Set<Editora> editoras = new HashSet<Editora>();

	public Livro(String isbn, String titulo, String serialName, String descricao) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.serialName = serialName;
		this.descricao = descricao;
	}

	public void addAuthors(Autor autor) {
		this.autores.add(autor);
		autor.getLivros().add(this);
	}

	public void removeAuthors(Autor autor) {
		this.autores.remove(autor);
		autor.getLivros().remove(this);
	}

	public void addCategories(CategoriaLivro categoria) {
		this.categoriaLivro.add(categoria);
		categoria.getLivros().add(this);
	}

	public void removeCategories(CategoriaLivro categoria) {
		this.categoriaLivro.remove(categoria);
		categoria.getLivros().remove(this);
	}

	public void addPublishers(Editora editora) {
		this.editoras.add(editora);
		editora.getLivros().add(this);
	}

	public void removePublishers(Editora editora) {
		this.editoras.remove(editora);
		editora.getLivros().remove(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSerialName() {
		return serialName;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	public Set<CategoriaLivro> getCategorias() {
		return categoriaLivro;
	}

	public void setCategorias(Set<CategoriaLivro> categoriaLivro) {
		this.categoriaLivro = categoriaLivro;
	}

	public Set<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(Set<Editora> editoras) {
		this.editoras = editoras;
	}
	
public Livro() {
		
	}
	
}
