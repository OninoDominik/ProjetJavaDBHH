package fr.cci.ProjetJava.SimVille.Projet.model;

import fr.cci.ProjetJava.SimVille.Projet.model.repository.TerrainRepository;
import fr.cci.ProjetJava.SimVille.Projet.model.repository.TuileCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

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

    public float getPoliceImpact(int Xc, int Yc, float D, float Dmax){
        float P = 0.0f;
        if(D <= 0.3*Dmax){
            P = this.getPMax() * ((Dmax - D) / Dmax);
        }
        else{
            P = -this.getPMax() * ((Dmax - D) / Dmax);
        }
        return P;
    }

    public Maze createRoadMap(int Xc, int Yc, List<TuileCarte> tuileList){
        int Xmin = this.getBounds(Xc, Yc)[0];
        int Xmax = this.getBounds(Xc, Yc)[1];
        int Ymin = this.getBounds(Xc, Yc)[2];
        int Ymax = this.getBounds(Xc, Yc)[3];

        //CHECK BOUNDS!!!!
        int[][] map = new int[Xmax+1][Ymax+1];

        for (int x = Xmin; x < Xmax; x++) {
            for (int y = Ymin; y < Ymax; y++) {
                // SI "sortie" = 3
                if (x == Xc && y == Yc){
                    map[x][y] = 3;
                }
                // SI "entrée" = 2
                else if (x == this.getX() && y == this.getY()){
                    map[x][y] = 2;
                }
                // SI Arret = 0
                else if((tuileList.get(x + y * this.ville.getVilleLarg())).getTerrain().getTerrainType()== 8){
                    map[x][y] = 0;
                }
                // SI Route = 0
                else if((tuileList.get(x + y * this.ville.getVilleLarg())).getTerrain().getTerrainType()== 9 ){
                    map[x][y] = 0;
                }
                // SINON = 1
                else{
                    map[x][y] = 1;
                }
            }
        }

        Maze roadMap = new Maze(map, new Coordinate(Xc,Yc) ,new Coordinate(this.getX(),this.getY()));

        return roadMap;
    }

    public boolean isNearRoad(int Xc, int Yc, List<TuileCarte> tuileList) {
        int X = this.getX(), Y = this.getY();

        /*
        System.out.println(this.getTuileCarteposition());
        System.out.println("X: "+this.getX());
        System.out.println("Y: "+this.getY());
        System.out.println("VilleLarg: "+this.getVille().getVilleLarg());
        System.out.println("VilleLong: "+this.getVille().getVilleLong());
        */

        if ((X - 1) >= 0 && tuileList.get((X-1)+ Y * this.ville.getVilleLarg()).getTerrain().getTerrainType() == 9) {
            return true;
        }

        if ((X + 1) <= (this.getVille().getVilleLarg()-1) && tuileList.get((X+1) + Y * this.ville.getVilleLarg()).getTerrain().getTerrainType() == 9) {
            return true;
        }

        if ((Y - 1) >= 0 && tuileList.get(X + (Y-1) * this.ville.getVilleLarg()-1).getTerrain().getTerrainType() == 9) {
            return true;
        }

        if ((Y + 1) <= (this.getVille().getVilleLong()-1) && tuileList.get(X + (Y+1) * this.ville.getVilleLarg()-1).getTerrain().getTerrainType() == 9){
            return true;
        }

        return false;
    }


    public float getBusStopImpact(int Xc, int Yc, Maze roadMap){
        int X = this.getX();
        int Y = this.getY();
        float D = (float) Math.sqrt(Math.pow((X - Xc), 2) + Math.pow((Y - Yc), 2));
        float Dmax = this.getDMax();

        float P = 0.0f;

        if (D <= Dmax) {
            BFSMazeSolver bfs = new BFSMazeSolver();
            List<Coordinate> path = bfs.solve(roadMap);
            float distance = path.size();
            if(distance <= Dmax){
                P = this.getPMax() * ((Dmax - distance) / Dmax);
            }
        }

        return P;
    }


    public float getImpact(int Xc, int Yc, List<TuileCarte> tuileList) {
        float D = (float) Math.sqrt(Math.pow((this.getX() - Xc), 2) + Math.pow((this.getY() - Yc), 2));
        float Dmax = this.getDMax();

        float P = 0.0f;

        if (Dmax >= D) {
            //SI arret de bus = 0 car fait à part
            if (this.getTerrain().getTerrainType() == 8 ){
                P = 0.0f;
            } else if (this.getTerrain().getTerrainType() == 6 ) {
                P = this.getPoliceImpact(Xc, Yc, D, Dmax);
            } else {
                P = this.getPMax() * ((Dmax - D) / Dmax);
            }
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

            //On calcule l'influence des arrêts de bus, si la tuile est prêt d'une route
            if(this.isNearRoad(Xc, Yc, tuileList)){
                Maze roadMap = this.createRoadMap(Xc, Yc, tuileList);
                for (int x = Xmin; x < Xmax; x++) {
                    for (int y = Ymin; y < Ymax; y++) {
                        if(tuileList.get(x + y * this.ville.getVilleLarg()).getTerrain().getTerrainType()==8
                        && tuileList.get(x + y * this.ville.getVilleLarg()).isNearRoad(Xc,Yc,tuileList)) {
                            P += (tuileList.get(x + y * this.ville.getVilleLarg())).getBusStopImpact(Xc, Yc, roadMap);
                        }
                    }
                }
            }

            //On calcule l'influence totale s'appliquant sur la tuile:
            for (int x = Xmin; x < Xmax; x++) {
                for (int y = Ymin; y < Ymax; y++) {
                    P += (tuileList.get(x + y * this.ville.getVilleLarg())).getImpact(Xc, Yc, tuileList);
                }
            }
            P -= tuileList.get(Xc + Yc * this.ville.getVilleLarg()).getImpact(Xc, Yc, tuileList);

            //On vérifie que l'influence totale est comprise entre -100 et +100:
            if (P < -100) {
                P = -100;
            } else if (P > 100) {
                P = 100;
            }

            //On calcule la valeur immobilière de la tuile:

            tuileValue = tuileValue + P * (Vmax - tuileValue) / 100;
            if (tuileValue < Vmin) {
                tuileValue = Vmin;
            }
            if (tuileValue > Vmax) {
                tuileValue = Vmax;
            }
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

            Maze roadMap = this.createRoadMap(Xc, Yc, tuileList);

            


            //On calcule l'influence totale s'appliquant sur la tuile:
            for (int x = Xmin; x < Xmax; x++) {
                for (int y = Ymin; y < Ymax; y++) {
                    P += (tuileList.get(x + y * this.ville.getVilleLarg())).getImpact(Xc, Yc, tuileList);
                }
            }
            P -= tuileList.get(Xc + Yc * this.ville.getVilleLarg()).getImpact(Xc, Yc, tuileList);

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



    //CHEMIN LE PLUS COURT:

    // Details algo de chemin le plus court:

    //Définition d'un objet Coordinate:
    public class Coordinate {
        int x;
        int y;
        Coordinate parent;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
            this.parent = null;
        }

        public Coordinate(int x, int y, Coordinate parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        Coordinate getParent() {
            return parent;
        }
    }

    //Définition de l'objet Maze:
    public class Maze {
        private static final int ROAD = 0;
        private static final int WALL = 1;
        private static final int START = 2;
        private static final int EXIT = 3;
        private static final int PATH = 4;

        private int[][] maze;
        private boolean[][] visited;
        private Coordinate start;
        private Coordinate end;

        public Maze(int[][] map, Coordinate start, Coordinate end){
            this.maze = map;
            this.visited = new boolean[this.getHeight()][this.getWidth()];
            this.start = start;
            this.end = end;
        }

        public int getHeight() {
            return maze.length;
        }

        public int getWidth() {
            return maze[0].length;
        }

        public Coordinate getEntry() {
            return start;
        }

        public Coordinate getExit() {
            return end;
        }

        public boolean isExit(int x, int y) {
            return x == end.getX() && y == end.getY();
        }

        public boolean isStart(int x, int y) {
            return x == start.getX() && y == start.getY();
        }

        public boolean isExplored(int row, int col) {
            return visited[row][col];
        }

        public boolean isWall(int row, int col) {
            return maze[row][col] == WALL;
        }

        public void setVisited(int row, int col, boolean value) {
            visited[row][col] = value;
        }

        public boolean isValidLocation(int row, int col) {
            if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
                return false;
            }
            return true;
        }

        public void reset() {
            for (int i = 0; i < visited.length; i++)
                Arrays.fill(visited[i], false);
        }
    }

    //Definition de l'objet MazeSolver:
    public class BFSMazeSolver {
        private final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        public List<Coordinate> solve(Maze maze) {
            LinkedList<Coordinate> nextToVisit = new LinkedList<>();
            Coordinate start = maze.getEntry();
            nextToVisit.add(start);

            while (!nextToVisit.isEmpty()) {
                Coordinate cur = nextToVisit.remove();

                if (!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) {
                    continue;
                }

                if (maze.isWall(cur.getX(), cur.getY())) {
                    maze.setVisited(cur.getX(), cur.getY(), true);
                    continue;
                }

                if (maze.isExit(cur.getX(), cur.getY())) {
                    return backtrackPath(cur);
                }

                for (int[] direction : DIRECTIONS) {
                    Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                    nextToVisit.add(coordinate);
                    maze.setVisited(cur.getX(), cur.getY(), true);
                }
            }
            return Collections.emptyList();
        }

        private List<Coordinate> backtrackPath(Coordinate cur) {
            List<Coordinate> path = new ArrayList<>();
            Coordinate iter = cur;

            while (iter != null) {
                path.add(iter);
                iter = iter.parent;
            }

            return path;
        }
    }









}


