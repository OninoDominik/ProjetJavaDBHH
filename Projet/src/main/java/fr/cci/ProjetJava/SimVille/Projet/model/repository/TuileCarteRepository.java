package fr.cci.ProjetJava.SimVille.Projet.model.repository;

import fr.cci.ProjetJava.SimVille.Projet.model.TuileCarte;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface TuileCarteRepository extends JpaRepository<TuileCarte, Integer> {

    List<TuileCarte> findByVilleOrderByTuileCarteposition(Ville ville);
    TuileCarte findById(int id);
}
