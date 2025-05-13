package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entita.Prestito;

@Repository
public interface PrestitiRepository extends JpaRepository <Prestito, Integer>{

}
