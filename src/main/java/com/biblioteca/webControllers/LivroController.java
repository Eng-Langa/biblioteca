package com.biblioteca.webControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.Livro;
import com.biblioteca.services.*;
@Controller
public class LivroController {

	private final LivroService livroService;
	private final AutorService autorService;
	private final CategoriaLivroService categoriaService;
	private final EditoraService editoraService;

	@Autowired
	public LivroController(LivroService livroService, AutorService autorService, CategoriaLivroService categoriaService,
			EditoraService editoraService) {
		this.livroService = livroService;
		this.autorService = autorService;
		this.categoriaService = categoriaService;
		this.editoraService = editoraService;
	}

	@RequestMapping("/books")
	public String findAllBooks(Model model) {
		final List<Livro> livros = livroService.findAllBooks();

		model.addAttribute("livros", livros);
		return "list-books";
	}

	

	@RequestMapping("/book/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model) {
		final Livro livro = livroService.findBookById(id);

		model.addAttribute("livro", livro);
		return "list-books";
	}

	@GetMapping("/add")
	public String showCreateForm(Livro book, Model model) {
		model.addAttribute("categorias", categoriaService.findAllCategories());
		model.addAttribute("autores", autorService.findAllAuthors());
		model.addAttribute("editoras", editoraService.findAllPublishers());
		return "add-book";
	}

	@RequestMapping("/add-book")
	public String createBook(Livro livro, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-book";
		}

		livroService.createBook(livro);
		model.addAttribute("livro", livroService.findAllBooks());
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Livro livro = livroService.findBookById(id);

		model.addAttribute("livro", livro);
		return "update-book";
	}

	@RequestMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, Livro livro, BindingResult result, Model model) {
		if (result.hasErrors()) {
			livro.setId(id);
			return "update-book";
		}

		livroService.updateBook(livro);
		model.addAttribute("livro", livroService.findAllBooks());
		return "redirect:/books";
	}

	@RequestMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		livroService.deleteBook(id);

		model.addAttribute("livro", livroService.findAllBooks());
		return "redirect:/books";
	}
	
}
