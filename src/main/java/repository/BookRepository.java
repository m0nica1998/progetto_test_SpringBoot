package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entita.Book;

public interface BookRepository extends JpaRepository <Book, Integer> {

}
