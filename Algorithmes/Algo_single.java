/*
ALGORITHME - CALCUL CASE INDIVIDUELLE:


NOTATIONS:

	Xc,Yc 	- coordonnées de la case "en cours"
	x,y 	- coordonnées de la case "considérée"	

	Pmax	- influence max
	Dmax	- distance max
	D		- distance tile-to-tile

	Xmib 	- x min in bounds
	Xmab 	- x max in bounds
	Ymib 	- y min in bounds
	Ymab 	- y max in bounds
	
	
RESTE A DEFINIR:
*	methode busStopImpact(int Xc, int Yc), qui calcule l'influence de la		A Faire
	case "Arret de Bus" considérée sur la case aux coordonnées Xc, Yc.
* 	methode isNearRoad()														A Faire
* 	redéfinir GetBounds pour le cas "individuel" 								Fait
* 	définir "Tuile(x,y)" ainsi que le modèle de tableau utilisée pour le 		Fait
	calcul
* 	Remplacer les appels d'attributs BARBARES par leur GETTERS associés			A Faire
*/
	
//_______________________________________________________________________

//GET DMAX VILLE:
		//-------------------------------------------------
		public float DmaxMax(){
			float Dmax=this.rivDmax;
			if(Dmax<this.forDmax){
				Dmax=this.forDmax;
			}
			if(Dmax<this.forDmax){
				Dmax=this.forDmax;
			}
			if(Dmax<this.eclmax){
				Dmax=this.eclDmax;
			}
			if(Dmax<this.eglDmax){
				Dmax=this.eglDmax;
			}
			if(Dmax<this.comDmax){
				Dmax=this.comDmax;
			}
			if(Dmax<this.polDmax){
				Dmax=this.polDmax;
			}
			if(Dmax<this.polDmax){
				Dmax=this.polDmax;
			}
			if(Dmax<this.polDmax){
				Dmax=this.polDmax;
			}
			if(Dmax<this.rtbDmax){
				Dmax=this.rtbDmax;
			}
			
			return Dmax;		
		}


//GET BOUNDS: 
		//-------------------------------------------------
		
		public int[] getBounds(int Xc, int Yc){
			
			float Dmax = this.getDmaxMax();
		
			if(Xc-Dmax<0){
				Xmib = 0;
			}
			else{
				Xmib = (Xc - Dmax)%1;
			}
			
			if(Xc+Dmax>Ville.getXmax()){
				Xmab = Ville.getXmax();
			}
			else{
				Xmab = (Xc + Dmax)%1;
			}
			
			if(Yc-Dmax<0){
				Ymib = 0;
			}
			else{
				Ymib = (Yc - Dmax)%1;
			}
			
			if(Yc+Dmax>Ville.getYmax()){
				Ymab = Ville.getYmax();
			}
			else{
				Ymab = (Yc + Dmax)%1;
			}
			
			int[] tab = {Xmib;Xmab;Ymib;Ymab};
			return tab;
		}
			
		//-------------------------------------------------	

//METHODES POUR RECUPERER LES ATTRIBUTS PMAX ET DMAX D'UNE TUILE:  

	public float getDMax(){
		switch (this.terrain.getTerrainType()){
			case 1:
				return this.ville.getForDMax();
			case 2:
				return this.ville.getRivDMax(); 
			case 3:
				return this.ville.getComDMax();
			case 4:
				return this.ville.getEclDMax();
			case 5:
				return this.ville.getEglDMax();
			case 6:
				return this.ville.getPolDMax();
			case 7:
				return this.ville.getResDMax();
			case 8:
				return this.ville.getRtbDMax();
			default:
				return 0.0;
		}
	}
	
	
	public float getPMax(){
		switch (this.terrain.getTerrainType()){
			case 1:
				return this.ville.getForPMax();
			case 2:
				return this.ville.getRivPMax(); 
			case 3:
				return this.ville.getComPMax();
			case 4:
				return this.ville.getEclPMax();
			case 5:
				return this.ville.getEglPMax();
			case 6:
				return this.ville.getPolPMax();
			case 7:
				return this.ville.getResPMax();
			case 8:
				return this.ville.getRtbPMax();
			default:
				return 0.0;
		}		
	}		
		
		
//METHODE PERMETTANT DE TESTER SI UNE TUILE EST EN CONTACT AVEC UNE ROUTE:

	public boolean isNearRoad(int Xc, int Yc){
		TuileCarteRepository tuileRep;
		
		if((this.getTuileCartePosition()-1>=0) 
			&& tuileRep.findByTuileCarteposition(this.getTuileCartePosition()-1).getTerrain().getTerrainType()== 8
			|| tuileRep.findByTuileCarteposition(this.getTuileCartePosition()-1).getTerrain().getTerrainType()== 9){
				return true;
		}
		
		if((this.getTuileCartePosition()+1<this.getVille().getVilleLong()*this.getVille().getVilleLarg()) 
			&& tuileRep.findByTuileCarteposition(this.getTuileCartePosition()+1).getTerrain().getTerrainType()== 8
			|| tuileRep.findByTuileCarteposition(this.getTuileCartePosition()+1).getTerrain().getTerrainType()== 9){
				return true;
		}
		
		if((this.getTuileCartePosition()-this.getVille().getVilleLarg()>=0) 
			&& tuileRep.findByTuileCarteposition(this.getTuileCartePosition()-this.getVille().getVilleLarg()).getTerrain().getTerrainType()== 8
			|| tuileRep.findByTuileCarteposition(this.getTuileCartePosition()-this.getVille().getVilleLarg()).getTerrain().getTerrainType()== 9){
				return true;
		}
		
		if((this.getTuileCartePosition()+this.getVille().getVilleLarg()<this.getVille().getVilleLong()*this.getVille().getVilleLarg()) 
			&& tuileRep.findByTuileCarteposition(this.getTuileCartePosition()+this.getVille().getVilleLarg()).getTerrain().getTerrainType()== 8
			|| tuileRep.findByTuileCarteposition(this.getTuileCartePosition()+this.getVille().getVilleLarg()).getTerrain().getTerrainType()== 9){
				return true;
		}


		return false;
	}
		

//METHODE POUR VOIR SI UNE CASE INFLUENCE LA VALEUR DE LA CASE A EVALUER:

	boolean testRoad = tuileC.isNearRoad();

	public float getImpact(int Xc, int Yc, boolean testRoad){
		float D = Math.sqrt((this.getX()-Xc)²+(this.getY()-Yc)²);
		float Dmax = this.getDMax();
		
		float P = 0.0;
		
		if(this.getTerrainType()== 8 && testRoad){
			P = this.busStopImpact(Xc,Yc);
		}
		else{				
			P = this.getPMax() * ((Dmax-D)/Dmax);
		}				
		return P;		
	}	
		

//CALCULATE VALUE:
		//-------------------------------------------------
			
		public float getValue(int Xc, int Yc){
			//On récupère les éléments dont on a besoin pour le calcul:
			TuileCarteRepository tuileRep;		
			float P = 0.0;
			float Vmin = this.ville.getVilleValeurImmoMin();
			float Vmax = this.ville.getVilleValeurImmoMax();
			float tuileValue = (Vmin+Vmax)/2;
		
			//On calcule l'influence totale s'appliquant sur la tuile:
			for(int x = Xmib; x<Xmab; x++){
				for(int y = Ymib; y<Ymab; y++){
					P += tuileRep.findByTuileCarteposition(x+y*this.ville.getVilleLarg()).getImpact(Xc,Yc);
				}
			}
			
			//On vérifie que l'influence totale est comprise entre -100 et +100:
			if(P < -100){
				P = -100;
			}
			else if(P > 100){
				P = 100;
			}
			
			//On calcule la valeur immobilière de la tuile: 
			tuileValue = tuileValue + P*(Vmax-tuileValue)/100;
			return tuileValue;			
		}
			
		//-------------------------------------------------	 
	 

//CALCUL POSITION tuile(x) -> tuile(x,y):


	public int getX(){
		int Xabs = this.getTuileCartePosition();
		int X = Xabs % (this.ville.getVilleLarg());
		
		return X;
	}
	
	public int getY(){
		int Xabs = this.getTuileCartePosition();
		int Y = (Xabs - this.getX())/this.ville.getVilleLarg();
		
		return Y;
	}
	

	 
	 
	 
	 
	 
	 
	 





