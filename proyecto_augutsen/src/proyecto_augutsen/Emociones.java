package proyecto_augutsen;

public class Emociones {
	 private boolean[] _emociones;// 1alegria,2miedo,3enojo,4tristeza,5asco,6sorpresa  
	 private String _emocionActual;
	 private VideoMonitor _videos;
	 
	 public Emociones(VideoMonitor video) {
		 this._videos = video;
		 this._emociones = new boolean[6];
	    	for(int i=0;i<5;i++) {
	    		this._emociones[i]= false;
	    	}
	    	
	 }
	 
	 
	 public void detectarObjetoEmocion(int idObjeto, ImagenesV imagenV) {
	    	String emocion = imagenV.getImagenEmocionActual();
	    	switch (idObjeto) {
	        case 6:
	            if (emocion.equals("alegria")){
	            	this._emociones[0]=true;
	        
	            	imagenV.CrearIcono(0.865, 0.190, "/iconos/alegria_carita.png");
	            	this._videos.reproducirVideoEmocion("alegria");
	            	System.out.print("agregado");
	            
	        
	            }
	            break;
	        case 7:
	        	if (emocion.equals("enojo")){
	        		
	            	this._emociones[1]=true;
	            	imagenV.CrearIcono(0.930, 0.220, "/iconos/ira_carita.png");
	            	this._videos.reproducirVideoEmocion("ira");
	            	System.out.print("agregado");
	           
	            }
	            break;
	        case 8:
	        	if (emocion.equals("miedo")){
	        		
	            	this._emociones[2]=true;
	            	imagenV.CrearIcono(0.800, 0.220, "/iconos/miedo_carita.png");
	            	this._videos.reproducirVideoEmocion("miedo");
	            	System.out.print("agregado");
	            
	            }
	            break;
	        case 9:
	        	if (emocion.equals("tristeza")){
	            	this._emociones[3]=true;
	            	imagenV.CrearIcono(0.865, 0.400, "/iconos/tristeza_carita.png");
	            	this._videos.reproducirVideoEmocion("tristeza");
	            	System.out.print("agregado");
	            	
	            }
	            break;
	        case 10:
	        	if (emocion.equals("asco")){
	            	this._emociones[4]=true;
	            	imagenV.CrearIcono(0.930, 0.350, "/iconos/asco_carita_.png");
	            	this._videos.reproducirVideoEmocion("desagrado");
	            	System.out.print("agregado");
	            	
	            }
	            break;
	        case 11:
	        	if (emocion.equals("sorpresa")){
	            	this._emociones[5]=true;
	            	imagenV.CrearIcono(0.800, 0.350, "/iconos/sorpresa_carita.png");
	            	this._videos.reproducirVideoEmocion("sorpresa");
	            	System.out.print("agregado");
	            
	        	}
	            break;
	        default:
	            
	    }
	    	
	    	
	    }
	 
	 
	 
	 public String getEmocionActual() {
		 return this._emocionActual;
	 }
}
