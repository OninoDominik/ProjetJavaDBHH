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

    @GetMapping(path = "/error")
    public String afficheError(HttpServletRequest request, HttpServletResponse response, Model model) {
        int code = response.getStatus();
        String message = HttpStatus.valueOf(code).getReasonPhrase();
        model.addAttribute("code", code);
        model.addAttribute("message", message);
        return "error";
    }
}
