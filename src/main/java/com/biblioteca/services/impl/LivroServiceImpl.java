package com.biblioteca.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.exception.NotFoundException;
import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.services.LivroService;

@Service
public class LivroServiceImpl implements LivroService{

	private final LivroRepository livroRepository;

	public LivroServiceImpl(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Livro> findAllBooks() {
		return livroRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Livro> searchBooks(String keyword) {
		if (keyword != null) {
			return livroRepository.search(keyword);
		}
		return livroRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Livro findBookById(Long id) {
		return livroRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Livro com o ID %d nao encontrado", id)));
	}



	@Override
	public void createBook(Livro livro) {
		livroRepository.save(livro);
	}

	@Override
	public void updateBook(Livro livro) {
		livroRepository.save(livro);
	}

	@Override
	public void deleteBook(Long id) {
		final Livro livro = livroRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Livro com o ID %d nao encontrado", id)));

		livroRepository.deleteById(livro.getId());
	}
	
}
