package com.biblioteca.services.impl;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;
import com.biblioteca.exception.NotFoundException;
import com.biblioteca.model.Autor;
import com.biblioteca.repository.AutorRepository;
import com.biblioteca.services.AutorService;

@Service
public class AutorServiceImpl implements AutorService{

	private final AutorRepository autorRepository;

	public AutorServiceImpl(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Autor> findAllAuthors() {
		return autorRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Autor findAuthorById(Long id) {
		return autorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Autor com o ID %d nao encontrado", id)));
	}

	@Override
	public void createAuthor(Autor autor) {
		autorRepository.save(autor);
	}

	@Override
	public void updateAuthor(Autor autor) {
		autorRepository.save(autor);
	}

	@Override
	public void deleteAuthor(Long id) {
		final Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Autor com o ID %d nao encontrado", id)));

		autorRepository.deleteById(autor.getId());
	}

	
	
}
