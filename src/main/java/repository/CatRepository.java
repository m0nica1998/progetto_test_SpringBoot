package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entita.Categoria;

public interface CatRepository extends JpaRepository <Categoria, Integer>{

}
