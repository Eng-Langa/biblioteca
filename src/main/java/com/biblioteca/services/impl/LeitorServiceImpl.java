package com.biblioteca.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.Leitor;
import com.biblioteca.repository.LeitorRepository;
import com.biblioteca.services.LeitorService;

@Service
public class LeitorServiceImpl implements LeitorService {

	@Autowired 
	private LeitorRepository leitorRepo;
	 
	@Override
	public List<Leitor> getAllLeitor() {
	
	        return leitorRepo.findAll();
	    
	}

	@Override
	public void save(Leitor leitor) {
		leitorRepo.save(leitor);
		
	}

	@Override
	public Leitor getById(Long id) {
		Optional<Leitor> optional = leitorRepo.findById(id);
        Leitor leitor = null;
        if (optional.isPresent())
            leitor = optional.get();
        else
            throw new RuntimeException(
                "Leitor nao encontrado com o codigo : " + id);
        return leitor;
	}

	@Override
	public void deleteViaId(long id) {
		
		leitorRepo.deleteById(id);
		
	}

}
