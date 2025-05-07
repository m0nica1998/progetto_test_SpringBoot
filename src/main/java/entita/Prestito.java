package entita;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//marcatore
@Entity
@Table(name = "prestiti")
public class Prestito {
	
		//Marcatore
		@Id 
		//da un valore autoincrementante in automatico all'id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
        private int id;
		@ManyToOne
		@JoinColumn(name="book_id", nullable = false)
		private Book book;
		
		//getter e setter
		public Book getBook() {
			return book;
		}
		public void setBook(Book book) {
			this.book = book;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
}
