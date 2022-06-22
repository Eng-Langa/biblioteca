package com.biblioteca.webControllers;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.services.EmprestimoService;

public class EmprestimoController {


	private EmprestimoService emprestimoService;

	public EmprestimoController(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}
	
	/*@RequestMapping("/leitores")
	public String viewHomePage(Model model) {
		final List<Leitor> leitor = leitorService.getAllLeitor();

		model.addAttribute("allleitorlist", leitor);
		return "list-leitor";
	}*/

	@GetMapping("/emprestimo")
	public String viewHomePage(Model model) {
		model.addAttribute("emprestimos", emprestimoService.findAllLends());
		return "list-emprestimo";
	}

	/* @GetMapping("/emprestimo")
	public String viewPage(Model model) {
		final List<Emprestimo> emprestimo = emprestimoService.findAllLends();

		model.addAttribute("emprestimo", emprestimo);
		return "list-emprestimo";
	} */

	@GetMapping("/addnew")
	public String addNew(Model model) {
		Emprestimo emprestimo = new Emprestimo();
		model.addAttribute("emprestimo", emprestimo);
		return "novo-emprestimo";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("emprestimo") Emprestimo emprestimo) {
		emprestimoService.save(emprestimo);
		return "redirect:/list-emprestimo";
	}
	
/*	@PostMapping("/save")
	public ResponseEntity<String> saveInvoice(@RequestBody Leitor leitor){
		ResponseEntity<String> resp = null;
		try{
			Long id = leitorService.save(leitor);
			resp= new ResponseEntity<String>(
					"Leitor '"+id+"' criado",HttpStatus.CREATED); //201-created
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"Impossivel adicionar", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500-Internal Server Error
		}
		return resp;
	}*/

	@GetMapping("/showFormForUpdate/{id}")
	public String updateForm(@PathVariable(value = "id") long id, Model model) {
		Emprestimo emprestimo = emprestimoService.findLendById(id);
		model.addAttribute("emprestimo", emprestimo);
		return "actualizar-emprestimo";
	}

	@GetMapping("/delete/{id}")
	public String deleteThroughId(@PathVariable(value = "id") long id) {
		emprestimoService.deleteLend(id);
		return "redirect:/emprestimo-list";

	}

	
}
