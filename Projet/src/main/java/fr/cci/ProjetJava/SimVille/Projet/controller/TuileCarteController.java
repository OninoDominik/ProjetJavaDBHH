package fr.cci.ProjetJava.SimVille.Projet.controller;

import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import fr.cci.ProjetJava.SimVille.Projet.model.TuileCarte;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TuileCarteRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

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
        Terrain terrain2 = terrainRepository.findById(terrain);
        Ville ville2 = villeRepository.findById(ville);
        t.setVille(ville2);
        t.setTerrain(terrain2);
        if (ville2 == null || terrain2 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mauvaise id ville ou terrain");
        } else {

            tuileCarteRepository.save(t);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }
    }

    @GetMapping(path = "/detail/{id}")
    public String afficheCarte(@PathVariable int id, Model model) {
        TuileCarte tuileCarte = tuileCarteRepository.findById(id);
        if (tuileCarte != null) {
            List<TuileCarte> listTuileParVille = tuileCarteRepository.findByVilleOrderByTuileCarteposition(tuileCarte.getVille());
            float valeurImmo = tuileCarte.getValue(listTuileParVille);
            String valeurImmoString = new BigDecimal(valeurImmo).setScale(2,BigDecimal.ROUND_HALF_DOWN).toPlainString();
            model.addAttribute("valeurImmo", valeurImmoString);
            model.addAttribute("tuileCarte", tuileCarte);
            return "modifT";
        } else {
            String code = "400";
            String message = "L'id de l'objet CarteTuile n'existe pas";
            model.addAttribute("status", code);
            model.addAttribute("message", message);
            return "error";
        }
    }
    @GetMapping(path = "/villeidetposition/{villeId}/{position}")
    public String afficheCarteEnFonctionDePosition(@PathVariable int villeId,@PathVariable int position, Model model) {
        Ville ville= villeRepository.findById(villeId);
        TuileCarte tuileCarte = tuileCarteRepository.findByVilleAndTuileCarteposition(ville,position);
        if (tuileCarte != null) {
            List<TuileCarte> listTuileParVille = tuileCarteRepository.findByVilleOrderByTuileCarteposition(tuileCarte.getVille());
            float valeurImmo = tuileCarte.getValue(listTuileParVille);
            String valeurImmoString = new BigDecimal(valeurImmo).setScale(2,BigDecimal.ROUND_HALF_DOWN).toPlainString();
            model.addAttribute("valeurImmo",  valeurImmoString);
            model.addAttribute("tuileCarte", tuileCarte);
            return "modifT";
        } else {
            String code = "400";
            String message = "L'id de l'objet CarteTuile n'existe pas";
            model.addAttribute("status", code);
            model.addAttribute("message", message);
            return "error";
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<TuileCarte> getAllUsers() {
        return tuileCarteRepository.findAll();
    }

    @PostMapping(path = "/updateterrain")
    @ResponseBody
    public void updateTuileCarte(@RequestParam int IdTerrain,
                                 @RequestParam int terrainNom1, HttpServletResponse httpServletResponse) {
        TuileCarte tuileCarte = tuileCarteRepository.findById(terrainNom1);
        Terrain terrain = terrainRepository.findById(IdTerrain);
        if (terrain == null) {
            HttpHeaders headers = new HttpHeaders();
            String redirection = "/ville/affiche/" + tuileCarte.getVille().getId();
            headers.add("Location", redirection);
            httpServletResponse.setHeader("Location", redirection);
            httpServletResponse.setStatus(400);
        } else {
            tuileCarte.setTerrain(terrain);
            tuileCarteRepository.save(tuileCarte);
            HttpHeaders headers = new HttpHeaders();
            int id = tuileCarte.getVille().getId();
            String redirection = "/ville/affiche/" + tuileCarte.getVille().getId();
            httpServletResponse.setHeader("Location", redirection);
            httpServletResponse.setStatus(302);
        }
    }

    @GetMapping(path = "/error")
    public String afficheError(HttpServletRequest request, HttpServletResponse response, Model model) {
        int code = response.getStatus();
        String message = HttpStatus.valueOf(code).getReasonPhrase();
        model.addAttribute("status", code);
        model.addAttribute("message", message);
        return "error";
    }

}