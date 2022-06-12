package com.biblioteca.webControllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.CategoriaLivro;
import com.biblioteca.services.CategoriaLivroService;

@Controller
public class CategoriaController {

	
	private final CategoriaLivroService categoriaService;

	public CategoriaController(CategoriaLivroService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@RequestMapping("/categories")
	public String findAllCategories(Model model) {
		final List<CategoriaLivro> categorias = categoriaService.findAllCategories();

		model.addAttribute("categorias", categorias);
		return "list-categories";
	}

	@RequestMapping("/category/{id}")
	public String findCategoryById(@PathVariable("id") Long id, Model model) {
		final CategoriaLivro categoria = categoriaService.findCategoryById(id);

		model.addAttribute("categoria", categoria);
		return "list-category";
	}

	@GetMapping("/addCategory")
	public String showCreateForm(CategoriaLivro categoria, Model model) {
		model.addAttribute("categoria", categoria);
		return "add-category";
	}

	@RequestMapping("/add-category")
	public String createCategory(CategoriaLivro categoria, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-category";
		}

		categoriaService.createCategory(categoria);
		model.addAttribute("categoria", categoriaService.findAllCategories());
		return "redirect:/categories";
	}

	@GetMapping("/updateCategory/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final CategoriaLivro categoria = categoriaService.findCategoryById(id);

		model.addAttribute("categoria", categoria);
		return "update-category";
	}

	@RequestMapping("/update-category/{id}")
	public String updateCategory(@PathVariable("id") Long id, CategoriaLivro categoria, BindingResult result, Model model) {
		if (result.hasErrors()) {
			categoria.setId(id);
			return "update-category";
		}

		categoriaService.updateCategory(categoria);
		model.addAttribute("category", categoriaService.findAllCategories());
		return "redirect:/categories";
	}

	@RequestMapping("/remove-category/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model) {
		categoriaService.deleteCategory(id);

		model.addAttribute("categoria", categoriaService.findAllCategories());
		return "redirect:/categories";
	}

	
}
