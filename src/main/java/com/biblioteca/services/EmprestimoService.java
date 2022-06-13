package com.biblioteca.services;

import java.util.List;

import com.biblioteca.model.Emprestimo;

public interface EmprestimoService {

	public List<Emprestimo> findAllLends();

	public Emprestimo findLendById(Long id);

	public void createLend(Emprestimo emprestimo);

	public void updateLend(Emprestimo emprestimo);

	public void deleteLend(Long id); 
	
}
