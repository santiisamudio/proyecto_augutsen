package proyecto_augutsen;


public class PeriodicoInicial {
	private boolean[] partes;
	
	
	public PeriodicoInicial() {
		partes = new boolean[4];
		
		for(int i= 0; i< 4; i++) {
			this.partes[i]= false;
		}
	}
	
	
	
	 public boolean estaCompleto() {
	    	return(this.partes[0] && this.partes[1] && this.partes[2] && this.partes[3]);
	 }
	 
	
	 public boolean estaVacio() {
		    return (!partes[0] && !partes[1] && !partes[2] && !partes[3]);
		}

	 
	 public void seEncuentraParte1() {
		 partes[0] = true;
	 }
	 public void seEncuentraParte2() {
		 partes[1]= true; 
	 }
	 public void seEncuentraParte3() {
		 partes[2]= true;
	 }	
	 public void seEncuentraParte4() {
		 partes[3]= true;
	 }
	 
	 
	 public void seEliminaParte1() {
		 partes[0]= false;
	 }
	 public void seEliminaParte2() {
		 partes[1]= false;
	 }
	 public void seEliminaParte3() {
		 partes[2]= false;
	 }
	 public void seEliminaParte4() {
		 partes[3]= false;
	 }
	 
	 public void eliminarMapa(int id) {
	    	switch(id) {
	    	case 0: this.seEliminaParte1();
	    		
	    		break;
	    	case 1: this.seEliminaParte2();
	    		
				break;
	    	case 2: this.seEliminaParte3();
	    		
				break;
	    	case 4: this.seEliminaParte4();
	    		
				break;
	    	
	    	}
	    }
	 
	 public void agregarMapa(int id, double x, double y, float angulo) { 
	        switch(id) {
	            case 0: 
	                if ((x > 0.1674) && (x < 0.5) && (y > 0.1241) && (y < 0.5)) {
	                    if (angulo >= 90 && angulo < 180) {
	                    	System.out.println(angulo);
	                    	
	                    	this.seEncuentraParte1();
	                    }
	                }
	                break;
	            case 1: 
	            	
	                if ((x > 0.5) && (x < 0.8330) && (y > 0.1241) && (y < 0.5)) {
	                	
	                    if (angulo >= 180 && angulo < 270) {
	                    	System.out.println(angulo);
	                    	this.seEncuentraParte2();
	                    }
	                }
	                break;
	            case 2: 
	           
	                if ((x > 0.1674) && (x < 0.5) && (y > 0.5) && (y < 0.8878)) {
	                    if (angulo >= 0 && angulo < 90) {
	                    	System.out.println(angulo);
	                    	this.seEncuentraParte3();
	                    }
	                }
	                break;
	            case 4: 
	          
	                if ((x > 0.5) && (x < 0.8330) && (y > 0.5) && (y < 0.8878)) {
	                	
	                    if ((angulo >= 270) && (angulo < 360)) {
	                    	
	                    	System.out.println(angulo);
	                    	this.seEncuentraParte4();
	                    }
	                }
	                break;
	            default:
	                System.out.println("ID no válido");
	                break;
	        }
	    }
}
