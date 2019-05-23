package fr.cci.ProjetJava.SimVille.Projet.controller;

import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import fr.cci.ProjetJava.SimVille.Projet.model.TuileCarte;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TuileCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/tuilecarte")
public class TuileCarteController {
    @Autowired
    private TuileCarteRepository tuileCarteRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewTuileCarte(@RequestParam Integer tuileCarteposition,
                            @RequestParam Ville ville, @RequestParam Terrain terrain)
    {
        TuileCarte t = new TuileCarte();
        t.setTuileCarteposition(tuileCarteposition);
        t.setVille(ville);
        t.setTerrain(terrain);
        tuileCarteRepository.save(t);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<TuileCarte> getAllUsers() {
        return  tuileCarteRepository.findAll();
    }
}
