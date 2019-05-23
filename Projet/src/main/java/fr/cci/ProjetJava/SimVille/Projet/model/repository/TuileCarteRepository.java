package fr.cci.ProjetJava.SimVille.Projet.model.repository;

import fr.cci.ProjetJava.SimVille.Projet.model.TuileCarte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public interface TuileCarteRepository extends CrudRepository<TuileCarte, Integer> {

}
