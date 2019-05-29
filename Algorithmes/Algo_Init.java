
ALGORITHME - CALCUL VALEURS VILLE


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
_______________________________________________________________________


GET BOUNDS:
		-------------------------------------------------
		public int[] getBounds(int Xc, int Yc, int Dmax){
			if(Xc-Dmax<0){
				Xmib = 0;
			}
			else{
				Xmib = (Xc - Dmax)%1;
			}
			
			if(Xc+Dmax>this.getVille().getVilleLarg()){
				Xmab = this.getVille().getVilleLarg();
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
			
			if(Yc+Dmax>this.getVille().getVilleLong()){
				Ymab = this.getVille().getVilleLong();
			}
			else{
				Ymab = (Yc + Dmax)%1;
			}
			return new int[] {Xmib,Xmab,Ymib,Ymab};
		}
		-------------------------------------------------	


CHANGE VALUE (or not):
		-------------------------------------------------
		
		public float getValue(int Xmib, int Xmab, int Ymib, int Ymab
			float V = 0.0;
		
			for(int x = Xmib; x<Xmab; x++){
				for(int y = Ymib; y<Ymab; y++){
				
					float D = Math.sqrt((x-Xc)²+(y-Yc)²)
					if( this.getDmax() <= D ){
						V = V + this.getPmax() * ((this.getDmax()-D)/this.getDmax());
					}
				}
			}
			
			return V;
		}	
			
		-------------------------------------------------	

Create IMAGE URL:

		-------------------------------------------------
		
		public String createImageUrl(float value, String nomTerrain){
			String result = nomTerrain.toUpperCase();
			
			int gradiantValue = (Math.round(value) + 100)/25)+1;
			if(gradiantValue>8){
				gradiantValue=8;
			}
			else if(gradiantValue<1){
				gradiantValue=0;
			}
			
			
			result += "_"+gradiantValue+".png";
			return result;			
		}

		-------------------------------------------------
	 
FILL TAB:
		-------------------------------------------------
		
		public String[] createAllUrl(List<TuileCarte> tuileList){
			String[] valueVille = new String[tuileList.getVille().getLong()*tuileList.getVille().getLarg()];
			
			for(int i=0; i<valueVille.length; i++){
				valueVille[i] = createImageUrl(tuileList.get(i).getValue(),tuileList.get(i).getTerrain().getTerrainNomShort());			
			}
			
			return valueVille;			
		}
		
		-------------------------------------------------


















