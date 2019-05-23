package fr.cci.ProjetJava.SimVille.Projet.model.repository;
import fr.cci.ProjetJava.SimVille.Projet.model.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TerrainRepository extends CrudRepository<Terrain, Integer> {
  Terrain findById(int id);
}
