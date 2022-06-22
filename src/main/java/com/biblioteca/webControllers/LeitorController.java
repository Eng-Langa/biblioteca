package com.biblioteca.webControllers;

import org.springframework.stereotype.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.Leitor;
import com.biblioteca.services.LeitorService;


@Controller

public class LeitorController {

		
	//	private LeitorServiceImpl leitorServiceImpl;
		
		private LeitorService leitorService;

		public LeitorController(LeitorService leitorService) {
			this.leitorService = leitorService;
		}
		
		
	
		@GetMapping("/leitores")
		public String viewHomePage(Model model) {
			model.addAttribute("allleitorlist", leitorService.getAllLeitor());
			return "list-leitor";
		}

		@GetMapping("/addnew")
		public String addNew(Model model) {
			Leitor leitor = new Leitor();
			model.addAttribute("leitor", leitor);
			return "novo-leitor";
		}

		@PostMapping("/save")
		public String save(@ModelAttribute("leitor") Leitor leitor) {
			leitorService.save(leitor);
			return "redirect:/leitores";
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
			Leitor leitor = leitorService.getById(id);
			model.addAttribute("leitor", leitor);
			return "update-leitor";
		}

		@GetMapping("/delete/{id}")
		public String deleteThroughId(@PathVariable(value = "id") long id) {
			leitorService.deleteViaId(id);
			return "redirect:/";

		}
	}

	

