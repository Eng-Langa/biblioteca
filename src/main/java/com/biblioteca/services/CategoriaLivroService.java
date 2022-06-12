package com.biblioteca.services;
import java.util.List;
import com.biblioteca.model.CategoriaLivro;
public interface CategoriaLivroService {

	public List<CategoriaLivro> findAllCategories();

	public CategoriaLivro findCategoryById(Long id);

	public void createCategory(CategoriaLivro categoria);

	public void updateCategory(CategoriaLivro categoria);

	public void deleteCategory(Long id);
	
}
