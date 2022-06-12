package com.biblioteca.services;

import java.util.List;
import com.biblioteca.model.Autor;

public interface AutorService {

	public List<Autor> findAllAuthors();

	public Autor findAuthorById(Long id);

	public void createAuthor(Autor author);

	public void updateAuthor(Autor author);

	public void deleteAuthor(Long id);
}
