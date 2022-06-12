package com.biblioteca.webControllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.biblioteca.model.Autor;
import com.biblioteca.services.AutorService;
@Controller

public class AutorController {

	private final AutorService autorService;

	public AutorController(AutorService autorService) {
		this.autorService = autorService;
	}

	@RequestMapping("/authors")
	public String findAllAuthors(Model model) {
		final List<Autor> autores = autorService.findAllAuthors();

		model.addAttribute("autores", autores);
		return "list-authors";
	}

	@RequestMapping("/author/{id}")
	public String findAuthorById(@PathVariable("id") Long id, Model model) {
		final Autor autor = autorService.findAuthorById(id);

		model.addAttribute("autor", autor);
		return "list-authors";
	}

	@GetMapping("/addAuthor")
	public String showCreateForm(Autor author) {
		return "add-author";
	}

	@RequestMapping("/add-author")
	public String createAuthor(Autor author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-author";
		}

		autorService.createAuthor(author);
		model.addAttribute("autor", autorService.findAllAuthors());
		return "redirect:/authors";
	}

	@GetMapping("/updateAuthor/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Autor autor = autorService.findAuthorById(id);

		model.addAttribute("autor", autor);
		return "update-author";
	}

	@RequestMapping("/update-author/{id}")
	public String updateAuthor(@PathVariable("id") Long id, Autor autor, BindingResult result, Model model) {
		if (result.hasErrors()) {
			autor.setId(id);
			return "update-author";
		}

		autorService.updateAuthor(autor);
		model.addAttribute("autor", autorService.findAllAuthors());
		return "redirect:/authors";
	}

	@RequestMapping("/remove-author/{id}")
	public String deleteAuthor(@PathVariable("id") Long id, Model model) {
		autorService.deleteAuthor(id);

		model.addAttribute("autor", autorService.findAllAuthors());
		return "redirect:/authors";
	}
	
}
