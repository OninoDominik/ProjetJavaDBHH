package fr.cci.ProjetJava.SimVille.Projet.controller;

import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(path="/simville")
public class AcceuilControler {

    @GetMapping(path = "/accueil")
    public String afficheAcceuil(Model model) {

        return "accueil";
    }
}
