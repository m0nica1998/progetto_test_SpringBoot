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
import entita.Categoria;
import entita.Prestito;
import jakarta.validation.Valid;
import repository.BookRepository;
import repository.CatRepository;

//marcatore
@Controller
@RequestMapping("/categorie")
public class CatController {
	@Autowired
	private CatRepository repository;
	@Autowired
	private BookRepository repositoryBook;
	
	@GetMapping
 public String index(Model model) {
		model.addAttribute("categorie", repository.findAll());
	 return "categorie/index";
 }
	
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("type",0);
		
		model.addAttribute("books", repositoryBook.findAll());
		return "categorie/create_edit";
		
	}
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("categoria") Categoria formCategoria,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()){
			
			return "categorie/create_edit";
			} 
		// Recupera gli ID dei libri selezionati
	    List<Integer> selectedBookIds = formCategoria.getBooks()
	                                              .stream()
	                                              .map(Book::getId)
	                                              .toList();
        System.out.println(formCategoria.getBooks().stream()
                .map(Book::getId)
                .toList());
        
	    // Recupera gli oggetti Book completi
	    List<Book> books = repositoryBook.findAllById(selectedBookIds);
        System.out.println(books);
	    // Imposta i libri completi nella categoria
	   // formCategoria.setBooks(books);
	    
	    System.out.println(formCategoria.toString());
       
	    // Salva la categoria con la relazione
	    repository.save(formCategoria);
	    // ciclo su tutti i libri della cat e aggiungo la nuova categoria e poi aggiorno i libri in db
	    for(Book book : books) {
       	 book.addCategorie(formCategoria);
       	 repositoryBook.save(book);
        }
		return "redirect:/categorie";
		
		
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id")int id, Model model) {
		model.addAttribute("categoria", repository.findById(id).get());
		model.addAttribute("books", repositoryBook.findAll());
		model.addAttribute("type", 1);
		
		return "categorie/create_edit";
		
		
	}
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("categoria") Categoria formCategoria,
	                     BindingResult bindingResult,
	                     Model model) {
	    if (bindingResult.hasErrors()) {
	        return "categorie/create_edit";
	    }

	    // Recupera gli oggetti Book completi dai loro ID
	    List<Book> selectedBooks = repositoryBook.findAllById(
	        formCategoria.getBooks().stream().map(Book::getId).toList()
	    );

	    // Recupera la categoria esistente dal DB
	    Categoria categoriaEsistente = repository.findById(formCategoria.getId()).orElseThrow();

	    // 1. Rimuovi la categoria da tutti i libri che la contenevano
	    for (Book book : repositoryBook.findAll()) {
	    	
	    	if (book.getCategorie().removeIf(c -> c.getId() == categoriaEsistente.getId())) {
	    	    repositoryBook.save(book);
	    	}

	    }

	    // 2. Aggiungi la categoria ai nuovi libri selezionati
	    for (Book book : selectedBooks) {
	        book.getCategorie().add(categoriaEsistente); // evita duplicati se equals/hashCode sono ok
	        repositoryBook.save(book);
	    }

	    // 3. Aggiorna la categoria stessa con i nuovi libri
	    categoriaEsistente.setNome_cat(formCategoria.getNome_cat());
	    categoriaEsistente.setBooks(selectedBooks);
	    repository.save(categoriaEsistente);

	    return "redirect:/categorie";
	}
	
	//metodo per eliminare un elemento
			@PostMapping("delete/{id}")
			public String delete(@PathVariable("id")int id) {
				repository.deleteById(id);
				return "redirect:/categorie";
			}
			

}
