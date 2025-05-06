package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entita.Book;

import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {

}
