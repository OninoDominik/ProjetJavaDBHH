package fr.cci.ProjetJava.SimVille.Projet.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Ville {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    private String villeNom;
    private Integer villeLong;
    private Integer villeLarg;
    private float villeValeurImmoMin;
    private float villeValeurImmoMax;
    private float rivDMax;
    private float rivPMax;
    private float forDMax;
    private float forPMax ;
    private float eclDMax;
    private float eclPMax;
    private float eglDMax;
    private float eglPMax;
    private float comDMax;
    private float comPMax;
    private float polDMax;
    private float polPMax;
    private float rtbDMax;
    private float rtbPMax;

    public Ville()
    {
        this.villeNom = "default";
        this.villeLong = 8;
        this.villeLarg = 6;
        this.villeValeurImmoMin = 1000;
        this.villeValeurImmoMax = 3000;
        this.rivDMax = 3;
        this.rivPMax = 25;
        this.forDMax = 2;
        this.forPMax = 30;
        this.eclDMax = 5;
        this.eclPMax = 15;
        this.eglDMax = 3;
        this.eglPMax = 35;
        this.comDMax = 4;
        this.comPMax = 15;
        this.polDMax = 4;
        this.polPMax = 15;
        this.rtbDMax = 4;
        this.rtbPMax = 35;

    }

    public Ville(Integer villeId, String villeNom, Integer villeLong, Integer villeLarg, float villeValeurImmoMin, float villeValeurImmoMax, float rivDMax, float rivPMax, float forDMax, float forPMax, float eclDMax, float eclPMax, float eglDMax, float eglPMax, float comDMax, float comPMax, float polDMax, float polPMax, float rtbDMax, float rtbPMax) {
        this.Id = villeId;
        this.villeNom = villeNom;
        this.villeLong = villeLong;
        this.villeLarg = villeLarg;
        this.villeValeurImmoMin = villeValeurImmoMin;
        this.villeValeurImmoMax = villeValeurImmoMax;
        this.rtbDMax = rtbDMax;
        this.rtbPMax = rtbPMax;
        this.rivDMax = rivDMax;
        this.rivPMax = rivPMax;
        this.forDMax = forDMax;
        this.forPMax = forPMax;
        this.eclDMax = eclDMax;
        this.eclPMax = eclPMax;
        this.eglDMax = eglDMax;
        this.eglPMax = eglPMax;
        this.comDMax = comDMax;
        this.comPMax = comPMax;
        this.polDMax = polDMax;
        this.polPMax = polPMax;

    }

    public Ville(String villeNom, Integer villeLong, Integer villeLarg, float villeValeurImmoMin, float villeValeurImmoMax, float rivDMax, float rivPMax, float forDMax, float forPMax, float eclDMax, float eclPMax, float eglDMax, float eglPMax, float comDMax, float comPMax, float polDMax, float polPMax, float rtbDMax, float rtbPMax) {
        this.villeNom = villeNom;
        this.villeLong = villeLong;
        this.villeLarg = villeLarg;
        this.villeValeurImmoMin = villeValeurImmoMin;
        this.villeValeurImmoMax = villeValeurImmoMax;
        this.rivDMax = rivDMax;
        this.rivPMax = rivPMax;
        this.forDMax = forDMax;
        this.forPMax = forPMax;
        this.eclDMax = eclDMax;
        this.eclPMax = eclPMax;
        this.eglDMax = eglDMax;
        this.eglPMax = eglPMax;
        this.comDMax = comDMax;
        this.comPMax = comPMax;
        this.polDMax = polDMax;
        this.polPMax = polPMax;
        this.rtbDMax = rtbDMax;
        this.rtbPMax = rtbPMax;

    }







    public String getVilleNom() {
        return villeNom;
    }

    public void setVilleNom(String villeNom) {
        this.villeNom = villeNom;
    }

    public Integer getVilleLong() {
        return villeLong;
    }

    public void setVilleLong(Integer villeLong) {
        this.villeLong = villeLong;
    }

    public Integer getVilleLarg() {
        return villeLarg;
    }

    public void setVilleLarg(Integer villeLarg) {
        this.villeLarg = villeLarg;
    }

    public float getVilleValeurImmoMin() {
        return villeValeurImmoMin;
    }

    public void setVilleValeurImmoMin(float villeValeurImmoMin) {
        this.villeValeurImmoMin = villeValeurImmoMin;
    }

    public float getVilleValeurImmoMax() {
        return villeValeurImmoMax;
    }

    public void setVilleValeurImmoMax(float villeValeurImmoMax) {
        this.villeValeurImmoMax = villeValeurImmoMax;
    }

    public float getRivDMax() {
        return rivDMax;
    }

    public void setRivDMax(float rivDMax) {
        this.rivDMax = rivDMax;
    }

    public float getRivPMax() {
        return rivPMax;
    }

    public void setRivPMax(float rivPMax) {
        this.rivPMax = rivPMax;
    }

    public float getForDMax() {
        return forDMax;
    }

    public void setForDMax(float forDMax) {
        this.forDMax = forDMax;
    }

    public float getForPMax() {
        return forPMax;
    }

    public void setForPMax(float forPMax) {
        this.forPMax = forPMax;
    }

    public float getEclDMax() {
        return eclDMax;
    }

    public void setEclDMax(float eclDMax) {
        this.eclDMax = eclDMax;
    }

    public float getEclPMax() {
        return eclPMax;
    }

    public void setEclPMax(float eclPMax) {
        this.eclPMax = eclPMax;
    }

    public float getEglDMax() {
        return eglDMax;
    }

    public void setEglDMax(float eglDMax) {
        this.eglDMax = eglDMax;
    }

    public float getEglPMax() {
        return eglPMax;
    }

    public void setEglPMax(float eglPMax) {
        this.eglPMax = eglPMax;
    }

    public float getComDMax() {
        return comDMax;
    }

    public void setComDMax(float comDMax) {
        this.comDMax = comDMax;
    }

    public float getComPMax() {
        return comPMax;
    }

    public void setComPMax(float comPMax) {
        this.comPMax = comPMax;
    }

    public float getPolDMax() {
        return polDMax;
    }

    public void setPolDMax(float polDMax) {
        this.polDMax = polDMax;
    }

    public float getPolPMax() {
        return polPMax;
    }

    public void setPolPMax(float polPMax) {
        this.polPMax = polPMax;
    }

    public float getRtbDMax() {
        return rtbDMax;
    }

    public void setRtbDMax(float rtbDMax) {
        this.rtbDMax = rtbDMax;
    }

    public float getRtbPMax() {
        return rtbPMax;
    }

    public void setRtbPMax(float rtbPMax) {
        this.rtbPMax = rtbPMax;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer villeId) {
        this.Id = villeId;
    }


}
