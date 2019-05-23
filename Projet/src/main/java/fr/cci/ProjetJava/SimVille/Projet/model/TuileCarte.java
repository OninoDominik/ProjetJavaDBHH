package fr.cci.ProjetJava.SimVille.Projet.model;

import javax.persistence.*;

@Entity
public class TuileCarte {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer tuileCarteId;
    private Integer tuileCarteposition;

    public TuileCarte(Integer tuileCarteposition, Terrain terrain, Ville ville) {
        this.tuileCarteposition = tuileCarteposition;
        this.terrain = terrain;
        this.ville = ville;
    }

    public TuileCarte(Integer tuileCarteId, Integer tuileCarteposition, Terrain terrain, Ville ville) {
        this.tuileCarteId = tuileCarteId;
        this.tuileCarteposition = tuileCarteposition;
        this.terrain = terrain;
        this.ville = ville;
    }

    public TuileCarte() {
    }

    @ManyToOne
    private Terrain terrain;

    @ManyToOne
    private Ville ville;

    public Integer getTuileCarteId() {
        return tuileCarteId;
    }

    public void setTuileCarteId(Integer tuileCarteId) {
        tuileCarteId = tuileCarteId;
    }

    public Integer getTuileCarteposition() {
        return tuileCarteposition;
    }

    public void setTuileCarteposition(Integer tuileCarteposition) {
        tuileCarteposition = tuileCarteposition;
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
}
