package com.biblioteca.services;

import java.util.List;

import com.biblioteca.model.Leitor;
public interface LeitorService {

	List<Leitor> getAllLeitor();
    void save(Leitor leitor);
    Leitor getById(Long id);
    void deleteViaId(long id);
}
