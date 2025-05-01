package entita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	

}
