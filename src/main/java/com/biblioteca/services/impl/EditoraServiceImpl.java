package com.biblioteca.services.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.exception.NotFoundException;
import com.biblioteca.model.Editora;
import com.biblioteca.repository.EditoraRepository;
import com.biblioteca.services.EditoraService;

@Service
public class EditoraServiceImpl implements EditoraService {

	private final EditoraRepository editoraRepository;

	public EditoraServiceImpl(EditoraRepository editoraRepository) {
		this.editoraRepository = editoraRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Editora> findAllPublishers() {
		return editoraRepository.findAll();
	}

	@Override
	public Editora findPublisherById(Long id) {
		return editoraRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));
	}

	@Override
	public void createPublisher(Editora editora) {
		editoraRepository.save(editora);
	}

	@Override
	public void updatePublisher(Editora editora) {
		editoraRepository.save(editora);
	}

	@Override
	public void deletePublisher(Long id) {
		final Editora editora = editoraRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));

		editoraRepository.deleteById(editora.getId());
	}
	
}
