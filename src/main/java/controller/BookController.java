package controller;

import java.util.List;

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
import jakarta.validation.Valid;
import repository.BookRepository;

//marcatore
@Controller
@RequestMapping("/")

public class BookController {
	
	//marcatore per poter usare la repo dentro il controller
	@Autowired 
	private BookRepository repository;
	
	//metodi CRUD
	
	@GetMapping
	//metodo che ritorna tutti i libri del DB e li passa alla pagina index.html che si trova in books nella cartella resources
	
	public String index(Model model) {
		//crea la lista e la popola con tutti gli elementi "Book" del BD
		List <Book> listaLibri = repository.findAll();
		//inserisce nella mappa model gli elementi trovati (la lista)
		model.addAttribute("libri", listaLibri);
		return "books/index";
	}
	
	@GetMapping("/{id}")
	//metodo per mostrare un singolo elemento
	public String show(@PathVariable("id")int id, Model model) {
		model.addAttribute("libro", repository.findById(id).get());

		return "books/show";
	}
	
	//il metodo GET passa le info al form e crea l'oggetto vuoto, il form riempie l'oggetto e lo manda al metodo POST di cretate nel controller, il POST controlla le regole e in base al risultato decide o meno di salvarlo nel DB
	//metodo per creare un elemento (tipo GET)
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("libro",new Book());
		model.addAttribute("type", 0);
		System.out.println("ok");
		return "books/create";
	}
	//metodo per creare un elemento (tipo POST)
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("libro") Book formLibro,
			BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors()){
			return "books/create";
			} 
				repository.save(formLibro);
			
		return "redirect:/";
	}
	//metodo per modificare le entità
	//il GET ci permette di recuperare l'id e passarlo al form, il form pre-popola i campi, salviamo e andiamo nel metodo POST dell'edit che fa i controlli
	
	//metodo get
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id")int id, Model model) {
		model.addAttribute("libro", repository.findById(id).get());
		System.out.println("entro nel metodo");
		model.addAttribute("type", 1);
		
		return "books/create";
		
		
	}
	
	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute("libro") Book formLibro,
			BindingResult bindingResult,
			Model model){
		if(bindingResult.hasErrors()){
			return "books/create";
			} 
		System.out.println(formLibro.getAutore());
		System.out.println(formLibro.getId());
		System.out.println(formLibro.getTitolo());
		System.out.println(formLibro.getIsbn());
				repository.save(formLibro);
			
		return "redirect:/";
	}
	
	//metodo per eliminare un elemento
	@PostMapping("delete/{id}")
	public String delete(@PathVariable("id")int id) {
		repository.deleteById(id);
		return "redirect:/";
	}
	
	
	
	
}   
   
