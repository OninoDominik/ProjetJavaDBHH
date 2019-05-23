CREATE Database projetjavadomben;
use projetjavadomben;

CREATE TABLE Ville (
        villeId            Int  Auto_increment  NOT NULL ,
        villeNom           Varchar (200) NOT NULL ,
        villeLong          Int NOT NULL ,
        villeLarg          Int NOT NULL ,
        villeValeurImmoMin Float NOT NULL ,
        villeValeurImmoMax Float NOT NULL ,
        rivDMax            Float NOT NULL ,
        rivPMax            Float NOT NULL ,
        forDMax            Float NOT NULL ,
        forPMax            Float NOT NULL ,
        eclDMax            Float NOT NULL ,
        eclPMax            Float NOT NULL ,
        eglDMax            Float NOT NULL ,
        eglPMax            Float NOT NULL ,
        comDMax            Float NOT NULL ,
        comPMax            Float NOT NULL ,
        polDMax            Float NOT NULL ,
        polPMax            Float NOT NULL ,
        rtbDMax            Float NOT NULL ,
        rtbPMax            Float NOT NULL
	,CONSTRAINT Ville_PK PRIMARY KEY (villeId)
);


#------------------------------------------------------------
# Table: Terrain
#------------------------------------------------------------

CREATE TABLE Terrain(
        terrainId         Int  Auto_increment  NOT NULL ,
        terrainValeurInit Float NOT NULL ,
        terrainNom        Varchar (200) NOT NULL ,
        terrainNomShort   Varchar (50) NOT NULL ,
        terrainCouleur    Varchar (50) NOT NULL
	,CONSTRAINT Terrain_PK PRIMARY KEY (terrainId)
);


CREATE TABLE Tuiles(
        tuilesId    Int  Auto_increment  NOT NULL ,
        tuilesPostion   Int NOT NULL ,
        terrainId Int ,
        villeId   Int
	,CONSTRAINT Case_PK PRIMARY KEY (tuilesId)

	,CONSTRAINT Case_Terrain_FK FOREIGN KEY (terrainId) REFERENCES Terrain(terrainId)
	,CONSTRAINT Case_Ville0_FK FOREIGN KEY (villeId) REFERENCES Ville(villeId)
);