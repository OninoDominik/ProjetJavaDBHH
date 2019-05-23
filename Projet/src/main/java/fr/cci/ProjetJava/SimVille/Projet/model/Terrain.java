package fr.cci.ProjetJava.SimVille.Projet.model;

import javax.persistence.*;

@Entity
public class Terrain {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer terrainId;
    private Integer terrainType;
    private String terrainNom;
    private String terrainNomShort;
    private String terrainImgUrl;

    public Terrain(){
         super();
        this.terrainId = 0;
        this.terrainType = 0;
        this.terrainNom="default";
        this.terrainNomShort = "default";
        this.terrainImgUrl = "default";
}

    public Terrain(Integer terrainId,Integer terrainType,String terrainNom, String terrainNomShort, String terrainImgUrl) {
        super();
        this.terrainId = terrainId;
        this.terrainType = terrainType;
        this.terrainNom=terrainNom;
        this.terrainNomShort = terrainNomShort;
        this.terrainImgUrl = terrainImgUrl;
    }

    public Integer getTerrainId() {
        return this.terrainId;
    }

    public void setTerrainId(Integer terrainId) {
        this.terrainId = terrainId;
    }

    public Integer getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(Integer terrainType) {
        this.terrainType = terrainType;
    }

    public String getTerrainNom() {
        return terrainNom;
    }

    public void setTerrainNom(String terrainNom) {
        this.terrainNom = terrainNom;
    }

    public String getTerrainNomShort() {
        return terrainNomShort;
    }

    public void setTerrainNomShort(String terrainNomShort) {
        this.terrainNomShort = terrainNomShort;
    }

    public String getTerrainImgUrl() {
        return terrainImgUrl;
    }

    public void setTerrainImgUrl(String terrainImgUrl) {
        this.terrainImgUrl = terrainImgUrl;
    }
}
