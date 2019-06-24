package fr.cci.ProjetJava.SimVille.Projet.controller;
import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import fr.cci.ProjetJava.SimVille.Projet.model.TuileCarte;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TuileCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fr.cci.ProjetJava.SimVille.Projet.model.Ville;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.VilleRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    @ResponseBody
    public void addNewVille(
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
            @RequestParam float rtbPMax,
            HttpServletResponse httpServletResponse) {
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
        n.setRtbDMax(rtbDMax);
        n.setRtbPMax(rtbPMax);
        n.setForDMax(forDMax);
        n.setForPMax(forPMax);
        villeRepository.save(n);
        setCarte(n);
        HttpHeaders headers = new HttpHeaders();
            String redirection= "/ville/all";
        httpServletResponse.setHeader("Location", redirection);
        httpServletResponse.setStatus(302);
    }

    @GetMapping(path = "/all")
    public String getAllUsers(  Model model) {
        model.addAttribute("listville",villeRepository.findAll() );
        return "afficheV";

    }
    @PostMapping("/affiche")
    public String AfficheCarte(@RequestParam int villeId, Model model) { // model est un paramettre envoyé lors de l'appel de la fonction. Il permet de transférer des informations vers la vue (équivalent de la requette dans servlet?)

        Ville tempo= villeRepository.findById(villeId);
        if (tempo==null)
        {
            String code="400";
            String message="Bad request : L'id de l'objet Ville n'existe pas";
            model.addAttribute("code", code);
            model.addAttribute("message", message);
            return "error";
        }
        Iterable<TuileCarte> TuileCarte = tuileCarteRepository.findByVilleOrderByTuileCarteposition(tempo);

        int taille= (tempo.getVilleLarg()*100);
        String tailletoString= Integer.toString(taille);
        tailletoString+="px";
        model.addAttribute("taille",tailletoString );
        model.addAttribute("produits", TuileCarte);
        return "Carte";  // on utilise thymeleaf -> retourne al page Accueil.html du dossier ressources
    }
    @GetMapping("/degrade/{id}")
    public String AfficheCarteIddegrader(@PathVariable int id, Model model) { // model est un paramettre envoyé lors de l'appel de la fonction. Il permet de transférer des informations vers la vue (équivalent de la requette dans servlet?)

        Ville tempo= villeRepository.findById(id);
        if (tempo==null)
        {
            String code="400";
            String message="Bad request : L'id de l'objet Ville n'existe pas";
            model.addAttribute("code", code);
            model.addAttribute("id", id);
            model.addAttribute("message", message);
            return "error";
        }
        else {

            Iterable<TuileCarte> TuileCarte = tuileCarteRepository.findByVilleOrderByTuileCarteposition(tempo);
            List<TuileCarte> TuileCarte3 = tuileCarteRepository.findByVilleOrderByTuileCarteposition(tempo);
            TuileCarte tuileCarte2= TuileCarte3.get(0);
            List<String>ListeUrl=tuileCarte2.createAllUrl(TuileCarte3);
            int taille = (tempo.getVilleLarg() * 100);
            String tailletoString = Integer.toString(taille);
            tailletoString += "px";
            model.addAttribute("taille", tailletoString);
            model.addAttribute("Listeurl", ListeUrl);
            return "cartedegrade";  // on utilise thymeleaf -> retourne al page Accueil.html du dossier ressources

        }
    }

    @GetMapping("/affiche/{id}")
    public String AfficheCarteId(@PathVariable int id, Model model) { // model est un paramettre envoyé lors de l'appel de la fonction. Il permet de transférer des informations vers la vue (équivalent de la requette dans servlet?)

        Ville tempo= villeRepository.findById(id);
        if (tempo==null)
        {
            String code="400";
            String message="Bad request : L'id de l'objet Ville n'existe pas";
            model.addAttribute("code", code);
            model.addAttribute("message", message);
            return "error";
        }
        else {
            Iterable<TuileCarte> TuileCarte = tuileCarteRepository.findByVilleOrderByTuileCarteposition(tempo);

            int taille = (tempo.getVilleLarg() * 100);
            String tailletoString = Integer.toString(taille);
            tailletoString += "px";
            model.addAttribute("taille", tailletoString);
            model.addAttribute("produits", TuileCarte);
            return "Carte";  // on utilise thymeleaf -> retourne al page Accueil.html du dossier ressources
        }
    }
    public void setCarte(Ville Ville) {
        System.out.println(terrainRepository.findById(1));
        Terrain terrainForet= (Terrain)terrainRepository.findById(1);

        for (int i = 0; i < Ville.getVilleLong() * Ville.getVilleLarg(); i=i+1000) {
            int max=1000;
            if (max> Ville.getVilleLong() * Ville.getVilleLarg()-i)
            {
                max=Ville.getVilleLong() * Ville.getVilleLarg()-i;
            }
            List<TuileCarte> listTuile = new ArrayList<TuileCarte>();
            for(int j=0; j<max ;j++)
            {            TuileCarte temp =new TuileCarte(i, terrainForet, Ville);
                listTuile.add(temp);
            }
            System.out.println(listTuile.size());
            tuileCarteRepository.saveAll(listTuile);
            listTuile.clear();
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
