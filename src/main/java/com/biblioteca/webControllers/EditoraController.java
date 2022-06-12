package com.biblioteca.webControllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.Editora;
import com.biblioteca.services.EditoraService;

@Controller
public class EditoraController {

	private final EditoraService editoraService;

	public EditoraController(EditoraService editoraService) {
		this.editoraService = editoraService;
	}

	@RequestMapping("/publishers")
	public String findAllPublishers(Model model) {
		final List<Editora> editoras = editoraService.findAllPublishers();

		model.addAttribute("editoras", editoras);
		return "list-publishers";
	}

	@RequestMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable("id") Long id, Model model) {
		final Editora editora = editoraService.findPublisherById(id);

		model.addAttribute("editora", editora);
		return "list-publisher";
	}

	@GetMapping("/addPublisher")
	public String showCreateForm(Editora editora) {
		return "add-publisher";
	}

	@RequestMapping("/add-publisher")
	public String createPublisher(Editora editora, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}

		editoraService.createPublisher(editora);
		model.addAttribute("publisher", editoraService.findAllPublishers());
		return "redirect:/publishers";
	}

	@GetMapping("/updatePublisher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Editora editora = editoraService.findPublisherById(id);

		model.addAttribute("editora", editora);
		return "update-publisher";
	}

	@RequestMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable("id") Long id, Editora editora, BindingResult result, Model model) {
		if (result.hasErrors()) {
			editora.setId(id);
			return "update-publishers";
		}

		editoraService.updatePublisher(editora);
		model.addAttribute("editora", editoraService.findAllPublishers());
		return "redirect:/publishers";
	}

	@RequestMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable("id") Long id, Model model) {
		editoraService.deletePublisher(id);

		model.addAttribute("publisher", editoraService.findAllPublishers());
		return "redirect:/publishers";
	}
	
}
