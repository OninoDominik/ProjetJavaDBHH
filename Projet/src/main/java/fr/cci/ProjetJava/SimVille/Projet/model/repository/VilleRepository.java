package fr.cci.ProjetJava.SimVille.Projet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {
    Ville findById(int id);
}
