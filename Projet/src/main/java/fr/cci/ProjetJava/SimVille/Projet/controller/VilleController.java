package fr.cci.ProjetJava.SimVille.Projet.controller;
import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import fr.cci.ProjetJava.SimVille.Projet.model.TuileCarte;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TuileCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.VilleRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="/ville")
public class VilleController {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private TerrainRepository terrainRepository;
    @Autowired
    private TuileCarteRepository tuileCarteRepository;


    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewVille(
            @RequestParam String villeNom,
            @RequestParam Integer villeLong,
            @RequestParam Integer villeLarg,
            @RequestParam float villeValeurImmoMin,
            @RequestParam float villeValeurImmoMax,
            @RequestParam float rivDMax,
            @RequestParam float rivPMax,
            @RequestParam float forDMax,
            @RequestParam float forPMax,
            @RequestParam float eclDMax,
            @RequestParam float eclPMax,
            @RequestParam float eglDMax,
            @RequestParam float eglPMax,
            @RequestParam float comDMax,
            @RequestParam float comPMax,
            @RequestParam float polDMax,
            @RequestParam float polPMax,
            @RequestParam float rtbDMax,
            @RequestParam float rtbPMax) {
        Ville n = new Ville();
        n.setVilleNom(villeNom);
        n.setVilleLong(villeLong);
        n.setVilleLarg(villeLarg);
        n.setVilleValeurImmoMin(villeValeurImmoMin);
        n.setVilleValeurImmoMax(villeValeurImmoMax);
        n.setRivDMax(rivDMax);
        n.setRivPMax(rivPMax);
        n.setEclDMax(eclDMax);
        n.setEclPMax(eclPMax);
        n.setEglDMax(eglDMax);
        n.setEglPMax(eglPMax);
        n.setComDMax(comDMax);
        n.setComPMax(comPMax);
        n.setPolDMax(polDMax);
        n.setPolPMax(polPMax);
        n.setRivDMax(rtbDMax);
        n.setRivPMax(rtbPMax);
        n.setForDMax(forDMax);
        n.setForPMax(forPMax);
        villeRepository.save(n);
        System.out.println("find by id va etre lancé");
        setCarte(n);
        return "Saved and map created";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Ville> getAllUsers() {
        return villeRepository.findAll();

    }
    @GetMapping("/affiche/{toto}")
    public String AfficheCarte(@PathVariable int toto, Model model) { // model est un paramettre envoyé lors de l'appel de la fonction. Il permet de transférer des informations vers la vue (équivalent de la requette dans servlet?)

        Ville tempo= villeRepository.findById(toto);
        Iterable<TuileCarte> TuileCarte = tuileCarteRepository.findByVilleOrderByTuileCarteposition(tempo);

        int taille= (tempo.getVilleLarg()*100);
        String tailletoString= Integer.toString(taille);
        tailletoString+="px";
        model.addAttribute("taille",tailletoString );
        model.addAttribute("produits", TuileCarte);
        return "Carte";  // on utilise thymeleaf -> retourne al page Accueil.html du dossier ressources
    }

    public void setCarte(Ville Ville) {
        System.out.println(terrainRepository.findById(1));
        Terrain terrainForet= (Terrain)terrainRepository.findById(1);
        for (int i = 0; i < Ville.getVilleLong() * Ville.getVilleLarg(); i++) {
            TuileCarte temp =new TuileCarte(i, terrainForet, Ville);
            tuileCarteRepository.save(temp);
        }
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
