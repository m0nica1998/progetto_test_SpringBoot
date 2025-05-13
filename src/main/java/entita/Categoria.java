package entita;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//Marcatori
@Entity
@Table(name = "categorie")
public class Categoria {
	//Marcatore
		@Id 
		//da un valore autoincrementante in automatico all'id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Column(
			    nullable = false,
			    length = 100
			   
			    
			)
		private String nome_cat;
		@ManyToMany(mappedBy="categorie")
		
		private List <Book> books;
		
		//costruttore
		public Categoria() {
			
		}

		// getter e setter
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNome_cat() {
			return nome_cat;
		}

		public void setNome_cat(String nome_cat) {
			this.nome_cat = nome_cat;
		}

		public List <Book> getBooks() {
			return books;
		}

		public void setBooks(List <Book> books) {
			this.books = books;
		}
		
		@Override
		public String toString() {
			String result = "ID:" + getId() + "NOME_CAT:" + getNome_cat() + "BOOKS:";
			for(Book book: getBooks()) {
				result += book.getId() + " ";
			}
			return result;
			
		}
		
		@Override
		public boolean equals(Object o) {
		    if (this == o) return true;
		    if (!(o instanceof Categoria)) return false;
		    Categoria that = (Categoria) o;
		    Integer id2 = this.id;
		    return (id2 != null) && id2.equals(this.id);
		}

		@Override
		public int hashCode() {
		    return Objects.hash(id);
		}

}
