package entita;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



//Marcatori
@Entity
@Table(name = "books")
public class Book {
	//Marcatore
	@Id 
	//da un valore autoincrementante in automatico all'id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(
		    nullable = false,
		    length = 100
		   
		    
		)
	private String titolo;
	@Column(
		    nullable = false,
		    length = 100
		   
		    
		)
	private String autore;
	@Column(
		    nullable = false,
		    unique = true,
		    length = 13
		   
		    
		)
	
	
	private String isbn;
	@ManyToMany()
	@JoinTable(
			name="book_categoria",
			joinColumns = @JoinColumn(name="book_id"),
			inverseJoinColumns=@JoinColumn(name="id_cat")
			)
	private List <Categoria> categorie;
	
	
	
public Book() {
		
	}
	public Book(String titolo, String autore, String isbn) {
		 setTitolo(titolo);
		 setAutore(autore);
		 setIsbn(isbn);
		
	}
	
	public void addCategorie(Categoria cat) {
		if(!getCategorie().contains(cat)) {
			getCategorie().add(cat);
		}
		
	}
	//getter e setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public List <Categoria> getCategorie() {
		return categorie;
	}
	public void setCategorie(List <Categoria> categorie) {
		this.categorie = categorie;
	}
	

}
