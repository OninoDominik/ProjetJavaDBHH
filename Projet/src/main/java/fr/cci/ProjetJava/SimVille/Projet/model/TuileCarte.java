package fr.cci.ProjetJava.SimVille.Projet.model;

import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TuileCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TuileCarte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private Integer tuileCarteposition;
    @ManyToOne
    private Terrain terrain;
    @ManyToOne
    private Ville ville;

    public TuileCarte(Integer tuileCarteposition, Terrain terrain, Ville ville) {
        super();
        this.tuileCarteposition = tuileCarteposition;
        this.terrain = terrain;
        this.ville = ville;
    }

    public TuileCarte(Integer tuileCarteId, Integer tuileCarteposition, Terrain terrain, Ville ville) {
        super();
        this.Id = tuileCarteId;
        this.tuileCarteposition = tuileCarteposition;
        this.terrain = terrain;
        this.ville = ville;
    }

    public TuileCarte() {
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer tuileCarteId) {
        Id = tuileCarteId;
    }

    public Integer getTuileCarteposition() {
        return tuileCarteposition;
    }

    public void setTuileCarteposition(Integer tuileCarteposition) {
        this.tuileCarteposition = tuileCarteposition;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }


//ALGORITHME ET METHODES ASSOCIEES:

    /*TO-DO-LIST/
     * Rajouter isNearRoad()
     * Implémenter methode getBusStopImpact();
     * Décommenter les cases "Arret de bus" dans getPMax() et getDMax()
     * Décommenter le cas "Arret de bus" dans getImpact()
     */

    //GETTERS SUPPLEMENTAIRES:
    public int getX() {
        int Xabs = this.getTuileCarteposition();
        int X = Xabs % (this.ville.getVilleLarg());

        return X;
    }

    public int getY() {
        int Xabs = this.getTuileCarteposition();
        int Y = (Xabs - this.getX()) / this.ville.getVilleLarg();

        return Y;
    }

    public float getDMax() {
        switch (this.terrain.getTerrainType()) {
            case 1:
                return this.getVille().getForDMax();
            case 2:
                return this.getVille().getRivDMax();
            case 3:
                return this.getVille().getComDMax();
            case 4:
                return this.getVille().getEclDMax();
            case 5:
                return this.getVille().getEglDMax();
            case 6:
                return this.getVille().getPolDMax();
            //case 8:
            //    return this.getVille().getRtbDMax();
            default:
                return 1.0f;
        }
    }

    public float getPMax() {
        switch (this.terrain.getTerrainType()) {
            case 1:
                return this.getVille().getForPMax();
            case 2:
                return this.getVille().getRivPMax();
            case 3:
                return this.getVille().getComPMax();
            case 4:
                return this.getVille().getEclPMax();
            case 5:
                return this.getVille().getEglPMax();
            case 6:
                return this.getVille().getPolPMax();
            //case 8:
            //    return this.getVille().getRtbPMax();
            default:
                return 0.0f;
        }
    }

    public float getDMaxMax() {
        float Dmax = this.getVille().getRivDMax();
        if (Dmax < this.getVille().getForDMax()) {
            Dmax = this.getVille().getForDMax();
        }
        if (Dmax < this.getVille().getForDMax()) {
            Dmax = this.getVille().getForDMax();
        }
        if (Dmax < this.getVille().getEclDMax()) {
            Dmax = this.getVille().getEclDMax();
        }
        if (Dmax < this.getVille().getEglDMax()) {
            Dmax = this.getVille().getEglDMax();
        }
        if (Dmax < this.getVille().getComDMax()) {
            Dmax = this.getVille().getComDMax();
        }
        if (Dmax < this.getVille().getPolDMax()) {
            Dmax = this.getVille().getPolDMax();
        }

        return Dmax;
    }


    public int[] getBounds(int Xc, int Yc) {
        int Xmin, Xmax;
        int Ymin, Ymax;

        float Dmax = this.getDMaxMax();

        if (Xc - Dmax < 0) {
            Xmin = 0;
        } else {
            Xmin = Math.round((Xc - Dmax));
        }

        if (Xc + Dmax > this.getVille().getVilleLarg()) {
            Xmax = this.getVille().getVilleLarg();
        } else {
            Xmax = Math.round((Xc + Dmax));
        }

        if (Yc - Dmax < 0) {
            Ymin = 0;
        } else {
            Ymin = Math.round((Yc - Dmax));
        }

        if (Yc + Dmax > this.getVille().getVilleLong()) {
            Ymax = this.getVille().getVilleLong();
        } else {
            Ymax = Math.round((Yc + Dmax));
        }

        int[] tab = new int[]{Xmin, Xmax, Ymin, Ymax};
        return tab;
    }

    public float getImpact(int Xc, int Yc) {
        float D = (float) Math.sqrt(Math.pow((this.getX() - Xc), 2) + Math.pow((this.getY() - Yc), 2));
        float Dmax = this.getDMax();

        float P = 0.0f;

        if (Dmax >= D) {
            //if(this.getTerrainType()== 8 && this.isNearRoad()){
            //    P = this.getBusStopImpact(Xc,Yc);
            //}
            //else{
            P = this.getPMax() * ((Dmax - D) / Dmax);
            //}
        }

        return P;
    }


    public float getValue(List<TuileCarte> tuileList) {
        int terrainType = this.getTerrain().getTerrainType();
        float Vmin = this.ville.getVilleValeurImmoMin();
        float Vmax = this.ville.getVilleValeurImmoMax();
        float tuileValue = (Vmin + Vmax) / 2;

        if (terrainType == 1 || terrainType == 3 || terrainType == 7) {
            //On récupère les éléments dont on a besoin pour le calcul:
            int Xc = this.getX();
            int Yc = this.getY();
            float P = 0.0f;

            int Xmin = this.getBounds(Xc, Yc)[0];
            int Xmax = this.getBounds(Xc, Yc)[1];
            int Ymin = this.getBounds(Xc, Yc)[2];
            int Ymax = this.getBounds(Xc, Yc)[3];

            //On calcule l'influence totale s'appliquant sur la tuile:

            for (int x = Xmin; x < Xmax; x++) {
                for (int y = Ymin; y < Ymax; y++) {
                    P += (tuileList.get(x + y * this.ville.getVilleLarg())).getImpact(Xc, Yc);
                }
            }
            P -= tuileList.get(Xc + Yc * this.ville.getVilleLarg()).getImpact(Xc, Yc);

            //On vérifie que l'influence totale est comprise entre -100 et +100:
            if (P < -100) {
                P = -100;
            } else if (P > 100) {
                P = 100;
            }

            //On calcule la valeur immobilière de la tuile:
            tuileValue = tuileValue + P * (Vmax - tuileValue) / 100;
        } else {
            tuileValue = 0.0f;
        }
        return tuileValue;
    }

    //METHODES NECESSAIRES POUR AFFICHER LE GRADIANT DE VALEURS IMMOBILIERES:
    public float getInfluenceTot(List<TuileCarte> tuileList) {
        int terrainType = this.getTerrain().getTerrainType();
        float Vmin = this.ville.getVilleValeurImmoMin();
        float Vmax = this.ville.getVilleValeurImmoMax();
        float tuileValue = (Vmin + Vmax) / 2;
        float P = 0.0f;

        if (terrainType == 1 || terrainType == 3 || terrainType == 7) {
            //On récupère les éléments dont on a besoin pour le calcul:
            int Xc = this.getX();
            int Yc = this.getY();

            int Xmin = this.getBounds(Xc, Yc)[0];
            int Xmax = this.getBounds(Xc, Yc)[1];
            int Ymin = this.getBounds(Xc, Yc)[2];
            int Ymax = this.getBounds(Xc, Yc)[3];

            //On calcule l'influence totale s'appliquant sur la tuile:
            for (int x = Xmin; x < Xmax; x++) {
                for (int y = Ymin; y < Ymax; y++) {
                    P += (tuileList.get(x + y * this.ville.getVilleLarg())).getImpact(Xc, Yc);
                }
            }
            P -= tuileList.get(Xc + Yc * this.ville.getVilleLarg()).getImpact(Xc, Yc);

            //On vérifie que l'influence totale est comprise entre -100 et +100:
            if (P < -100) {
                P = -100;
            } else if (P > 100) {
                P = 100;
            }
        } else {
            P = -1000.0f;
        }
        return P;
    }

    public String createImageUrl(float value, String nomTerrain) {
        String result = "/media/tiles/";
        result += nomTerrain.toUpperCase();

        int gradiantValue = ((Math.round(value) + 100) / 25) + 1;
        if (gradiantValue > 8) {
            gradiantValue = 8;
        } else if (gradiantValue < 1) {
            gradiantValue = 0;
        }
        result += "_" + gradiantValue + ".png";
        return result;
    }

    public List<String> createAllUrl(List<TuileCarte> tuileList) {
        List<String> valueVille = new ArrayList<>();
        int length = this.getVille().getVilleLong() * this.getVille().getVilleLarg();
        for (int i = 0; i < length; i++) {
            valueVille.add(createImageUrl(tuileList.get(i).getInfluenceTot(tuileList), tuileList.get(i).getTerrain().getTerrainNomShort()));
        }
        return valueVille;
    }


}


