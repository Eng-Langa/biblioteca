package com.biblioteca.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.exception.NotFoundException;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.services.EmprestimoService;

public class EmprestimoServiceImpl implements EmprestimoService {

	private final EmprestimoRepository emprestimoRepository;

	public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepository) {
		this.emprestimoRepository = emprestimoRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Emprestimo> findAllLends() {
		return emprestimoRepository.findAll();
	}

/*	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override*/
	/*public List<Livro> searchBooks(String keyword) {
		if (keyword != null) {
			return livroRepository.search(keyword);
		}
		return livroRepository.findAll();
	}*/

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Emprestimo findLendById(Long id) {
		return emprestimoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Emprestimo com o ID %d nao encontrado", id)));
	}



	@Override
	public void createLend(Emprestimo livro) {
		emprestimoRepository.save(livro);
	}

	@Override
	public void updateLend(Emprestimo livro) {
		emprestimoRepository.save(livro);
	}

	@Override
	public void deleteLend(Long id) {
		final Emprestimo emprestimo = emprestimoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Emprestimo com o ID %d nao encontrado", id)));

		emprestimoRepository.deleteById(emprestimo.getId());
	}
	
}
