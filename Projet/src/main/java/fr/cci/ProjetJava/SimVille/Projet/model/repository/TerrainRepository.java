package fr.cci.ProjetJava.SimVille.Projet.model.repository;

import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Integer> {
    Terrain findById(int id);

}
