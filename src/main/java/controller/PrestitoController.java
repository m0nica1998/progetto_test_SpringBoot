package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import entita.Book;
import entita.Prestito;
import jakarta.validation.Valid;
import repository.BookRepository;
import repository.PrestitiRepository;



//marcatore
@Controller
@RequestMapping("/prestiti")
public class PrestitoController {
	@Autowired
	private PrestitiRepository repository;
	@Autowired
	private BookRepository repositoryBook;
	@GetMapping
 public String index(Model model) {
		model.addAttribute("prestiti", repository.findAll());
	 return "prestiti/index";
 }
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("prestito", new Prestito());
		model.addAttribute("type",0);
		
		model.addAttribute("books", repositoryBook.findAll());
		return "prestiti/create_edit";
		
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("prestito") Prestito formPrestito,
			BindingResult bindingResult,
			Model model) {
		System.out.println("entro in store");
		if(bindingResult.hasErrors()){
			
			return "prestiti/create_edit";
			} 
		
		repository.save(formPrestito);
		return "redirect:/prestiti";
	}
	
	@GetMapping("/edit/{id}")
		public String edit(@PathVariable("id")int id, Model model) {
			model.addAttribute("prestito", repository.findById(id).get());
			model.addAttribute("books", repositoryBook.findAll());
			model.addAttribute("type", 1);
			
			return "prestiti/create_edit";
			
			
		}
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("prestito") Prestito formPrestito,
			BindingResult bindingResult,
			Model model){
		if(bindingResult.hasErrors()){
			return "prestiti/create_edit";
			} 
		
				repository.save(formPrestito);
			
		return "redirect:/prestiti";
	}
	//metodo per eliminare un elemento
		@PostMapping("delete/{id}")
		public String delete(@PathVariable("id")int id) {
			repository.deleteById(id);
			return "redirect:/prestiti";
		}
		
}
