package com.biblioteca.services;
import java.util.List;

import com.biblioteca.model.Editora;

public interface EditoraService {

	public List<Editora> findAllPublishers();

	public Editora findPublisherById(Long id);

	public void createPublisher(Editora editora);

	public void updatePublisher(Editora editora);

	public void deletePublisher(Long id);

	
}
