package com.biblioteca.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.exception.NotFoundException;
import com.biblioteca.model.*;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.services.EmprestimoService;

@Service
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

	@Override
	public Emprestimo findLendById(Long id) {
		Optional<Emprestimo> optional = emprestimoRepository.findById(id);
        Emprestimo emprestimo = null;
        if (optional.isPresent())
            emprestimo = optional.get();
        else
            throw new RuntimeException(
                "Emprestimo nao encontrado com o codigo : " + id);
        return emprestimo;
	}


	@Override
	public void deleteLend(Long id) {
		
		emprestimoRepository.deleteById(id);
		
	}

	@Override
	public void save(Emprestimo emprestimo) {
		emprestimoRepository.save(emprestimo);
	}
	
	@Override
	public void updateLend(Emprestimo emprestimo) {
		emprestimoRepository.save(emprestimo);
	}
}
