package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import entita.Book;
import repository.BookRepository;

//marcatore
@Controller
@RequestMapping("/books")

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
		return "/books/index";
	}
}
