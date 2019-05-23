package fr.cci.ProjetJava.SimVille.Projet.model;

import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;

import javax.persistence.*;

@Entity
public class TuileCarte {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;
    private Integer tuileCarteposition;

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

    @ManyToOne
    private Terrain terrain;

    @ManyToOne
    private Ville ville;

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
}
