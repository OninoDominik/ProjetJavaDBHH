package fr.cci.ProjetJava.SimVille.Projet.controller;

import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/terrain")
public class TerrainController {
    @Autowired

    private TerrainRepository terrainRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewTerrain(@RequestParam String terrainImgUrl, @RequestParam Integer terrainType,
                         @RequestParam String terrainNomShort, @RequestParam String terrainNom)
    {
        Terrain t = new Terrain();
        t.setTerrainImgUrl(terrainImgUrl);
        t.setTerrainType(terrainType);
        t.setTerrainNomShort(terrainNomShort);
        t.setTerrainNom(terrainNom);
        terrainRepository.save(t);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Terrain> getAllTerrain() {
        return terrainRepository.findAll();
    }

    @RequestMapping("/affiche")
    public String accueil(Model model) { // model est un paramettre envoyé lors de l'appel de la fonction. Il permet de transférer des informations vers la vue (équivalent de la requette dans servlet?)

        Iterable<Terrain> terrains = terrainRepository.findAll();

        model.addAttribute("produits", terrains);
        System.out.println(System.getProperty("java.class.path"));
        return "Accueil";  // on utilise thymeleaf -> retourne al page Accueil.html du dossier ressources
    }
}
