package com.biblioteca.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.exception.NotFoundException;
import com.biblioteca.model.CategoriaLivro;
import com.biblioteca.repository.CategoriaLivroRepository;

import com.biblioteca.services.CategoriaLivroService;

@Service
public class CategoriaLivroImpl implements CategoriaLivroService {

	private final CategoriaLivroRepository categoriaRepository;

	public CategoriaLivroImpl(CategoriaLivroRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<CategoriaLivro> findAllCategories() {
		return categoriaRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public CategoriaLivro findCategoryById(Long id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));
	}

	@Override
	public void createCategory(CategoriaLivro categoria) {
		categoriaRepository.save(categoria);
	}

	@Override
	public void updateCategory(CategoriaLivro categoria) {
		categoriaRepository.save(categoria);
	}

	@Override
	public void deleteCategory(Long id) {
		final CategoriaLivro categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));

		categoriaRepository.deleteById(categoria.getId());
	}

}
