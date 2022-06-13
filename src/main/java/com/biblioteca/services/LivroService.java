package com.biblioteca.services;

import java.util.List;
import com.biblioteca.model.Livro;
public interface LivroService {

public List<Livro> findAllBooks();
	
	/*public List<Livro> searchBooks(String keyword);*/

	public Livro findBookById(Long id);

	public void createBook(Livro livro);

	public void updateBook(Livro livro);

	public void deleteBook(Long id);
}
