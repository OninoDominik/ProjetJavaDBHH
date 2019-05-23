package fr.cci.ProjetJava.SimVille.Projet.controller;

import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import fr.cci.ProjetJava.SimVille.Projet.model.TuileCarte;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TuileCarteRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/tuilecarte")
public class TuileCarteController {
    @Autowired
    private TuileCarteRepository tuileCarteRepository;
    @Autowired
    private TerrainRepository terrainRepository;
    @Autowired
    private VilleRepository villeRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity addNewTuileCarte(@RequestParam Integer tuileCarteposition,
                            @RequestParam int ville, @RequestParam int terrain) {
        TuileCarte t = new TuileCarte();
        t.setTuileCarteposition(tuileCarteposition);
        System.out.println("0\n");
        Terrain terrain2 = terrainRepository.findById(terrain);
        System.out.println("1\n");
        Ville ville2= villeRepository.findById(ville);
        System.out.println("2\n");
        t.setVille(ville2);
        System.out.println("3\n");
        t.setTerrain(terrain2);
        System.out.println("4\n");
        if (ville2 == null || terrain2==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mauvaise id ville ou terrain");
        } else {

            tuileCarteRepository.save(t);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }
    }

    @GetMapping(path = "/detail/{id}")
    public String afficheCarte(@PathVariable int id, Model model) {
        TuileCarte tuileCarte = tuileCarteRepository.findById(id);
        model.addAttribute("tuileCarte", tuileCarte);
        return "TuileCarteDetail";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<TuileCarte> getAllUsers() {
        return tuileCarteRepository.findAll();
    }

    @PostMapping(path = "/updateTerrain")
    public @ResponseBody
    ResponseEntity updateTuileCarte(@RequestParam int IdTerrain,
                                    @RequestParam int IdTuileCarte) {
        TuileCarte tuileCarte = tuileCarteRepository.findById(IdTuileCarte);
        Terrain terrain = terrainRepository.findById(IdTerrain);

        if (terrain == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            tuileCarte.setTerrain(terrain);
            tuileCarteRepository.save(tuileCarte);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }

    }
}