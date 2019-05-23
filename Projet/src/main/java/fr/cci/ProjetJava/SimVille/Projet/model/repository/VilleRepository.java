package fr.cci.ProjetJava.SimVille.Projet.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;

public interface VilleRepository extends CrudRepository<Ville, Integer> {


}
