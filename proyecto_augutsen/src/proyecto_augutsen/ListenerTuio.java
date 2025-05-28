package proyecto_augutsen;

import TUIO.TuioBlob;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class ListenerTuio implements TuioListener{
    private Pane _contenedor;//contenedor
    private int _nivel;
    
    private ImagenesV imagenV;
    private PeriodicoInicial periodico;
    private Emociones _emociones;
    private VideoMonitor _videos;
    //mica
    private Gifs gifView;
    private boolean esperandoVaciarPeriodico;

    private boolean[] _emocion;// 
    public ListenerTuio(Pane contenedor) {    	
    	this.periodico = new PeriodicoInicial();
    	this._nivel= 0;
    	
        this._contenedor = contenedor;
        this._emociones = new Emociones();
        double ancho = Screen.getPrimary().getVisualBounds().getWidth();
        double alto = Screen.getPrimary().getVisualBounds().getHeight();    
        
        this.imagenV = new ImagenesV(this._contenedor);       
        this.imagenV.ConfigurarImagenPrincipal(ancho,alto);
        this.imagenV.AsignarImagenPrincipal();
        //mica
        this.gifView = new Gifs(this._contenedor); 
        this.gifView.AsignarGif_nivel0(); 
        
        this._videos = new VideoMonitor();
        this.esperandoVaciarPeriodico = false;

    //    Platform.runLater(() -> this.iniciarCuca());
        this._emocion = new boolean[6];
        for(int i=0;i<5;i++) {
    		this._emocion[i]= false;
    	}
       
        }
       
    
  /*  private void agregarIcono(double x, double y,String link) {
    	Image img = new Image(getClass().getResource(link).toExternalForm());
        ImageView imageV = new ImageView(img); 
        imageV.setId("icono");
        imageV.setX(x * this._contenedor.getWidth());  // Posición X de la imagen
        imageV.setY(y * this._contenedor.getHeight());  // Posición Y de la imagen
        imageV.setVisible(true);
        imageV.setFitWidth(60);  // Ajusta el tamaño de la imagen
        imageV.setFitHeight(60);  // Ajusta el tamaño de la imagen
        imageV.setPreserveRatio(true);  // Mantiene la relación de aspecto
        
        this._contenedor.getChildren().add(imageV);  // Agregamos la imagen al contenedor
    } */
    
    @Override
    public void addTuioObject(TuioObject to) {
        System.out.println("Object added: " + to.getSymbolID());
        Platform.runLater(() -> {/*javaFX solo permite actualizar la UI desde si propio thread para evitar errores, esta linea hace que el codigo se ejecute en el thread principal,
                                    TUIO ejecuta las funciones desde un thread secundario*/
            int id_simbolo= to.getSymbolID();
            double x = to.getX() * this._contenedor.getWidth();/*TUIO usa coordenadas de 0.0(arriba izquierda) a 1.1(abajo derecha) el contenedor de javafx tiene como coordenadas
                                                                    pixeles, para convertirlo se multiplican, si x=0.5 , 0.5 * 800 pixeles = 400*/
            double y = to.getY() * this._contenedor.getHeight();
            this.imagenV.CrearImagen(id_simbolo, x, y, to.getSessionID());
        	ImageView imageV = this.imagenV.getImagen(to.getSessionID());   
        
                if((to.getSymbolID()!=125)&&(this._nivel==0)&&(esCuadranteCentroMapa(to.getX(),to.getY()))){//se pasa la ubicacion del objeto TUIO para ver si se encuentra abajo a la derecha                    
                
                	if (imageV != null) {//verifica que el objeto exista 
                		imageV.setVisible(true);
                        imageV.setX(to.getX() * this._contenedor.getWidth());  // Posición X de la imagen
                        imageV.setY(to.getY() * this._contenedor.getHeight());  // Posición Y de la imagen
                        this.periodico.agregarMapa(to.getSymbolID(),to.getX(),to.getY(),to.getAngleDegrees());    
                        //a
                        System.out.println(periodico.toString());
                    
                        if (this.periodico.estaCompleto() && !esperandoVaciarPeriodico) {
                            this.gifView.Gif_SacaPiezas(); 
                          //  this._videos.iniciarSecuenciaVideos();
                            esperandoVaciarPeriodico = true;
                            System.out.print("esperando vaciar periodico true");
                        }

                        // Esto se verifica siempre que ya estuvo completo antes
                        System.out.print("antes del if");
                        if (esperandoVaciarPeriodico && this.periodico.estaVacio()) {
                        	System.out.print("sube nivel");
                            this.subirNivel();
                            esperandoVaciarPeriodico = false;
                        }

                	
                        
                }  	
                   
                }else if((to.getSymbolID()==3)&&(this._nivel==1)&&(esCuadranteInferiorIzquierdo(to.getX(),to.getY()))){
                	
                	
                	if(imageV != null){//verifica que el objeto exista 
                		imageV.setVisible(true);
                		imageV.setX(to.getX() * this._contenedor.getWidth());//se actualiza x
                		imageV.setY(to.getY() * this._contenedor.getHeight());//se actualiza y
                		
                	// QUEDAMOS EN SACARLO PORQUE AHORA REMPLAZAMOS LAS IMAGENES CON LOS VIDEOS
                	this.imagenV.AsignarImagenRotacion(to.getAngleDegrees());
                	}
                
                }      
        });
        
}
    
 public void sacarPiezas() {
	 
 }
    
    private void subirNivel() {
    	this._nivel++;
		this.imagenV.AsignarImagenAugutsen();
    //	Platform.runLater(() ->this.iniciarVideo());
		this._contenedor.getChildren().removeIf(node -> node instanceof ImageView && !node.getId().equals("background"));//elimina las partes del rompecabezas del contenedor
		this.imagenV.LimpiarContenedor();//limpia la imagen
		//AGREGO MAS GIFS
		this.gifView.AsignarGif_nivel1();
	
    
    }
    
    
    /*public void iniciarCuca() {
    	if(this._nivel==-1) {
    		//inicia en la tele la presentacion de la cucaracha y enla mesa interferencia
			this._videos.iniciarVideoProyector();
			this._videos.iniciarVideoInterferencia();
			this._nivel++;
			 
    	}} */
    
    private boolean esCuadranteCentroMapa(double x, double y) {
    	return ((x>0.1674)&&(x<0.8330)&&(y>0.1241)&&(y<0.8878));
    }
    
    private boolean esCuadranteInferiorIzquierdo(double x, double y){
        return ((x>0.0195)&&(x<0.210)&&(y>0.6570)&&(y<0.930));
    }
  

    @Override   
 
    
    public void updateTuioObject(TuioObject obj) { 
        double x = obj.getX();
        double y = obj.getY();
        double angle = obj.getAngleDegrees();  // Guardamos el ángulo para comparar cambios

        Platform.runLater(() -> {  
            
            ImageView imageV = this.imagenV.getImagen(obj.getSessionID());
            if (imageV != null) {
                if (obj.getSymbolID() != 125) {
                    if (this._nivel == 0) {	
                        boolean dentroDelCuadrante = esCuadranteCentroMapa(x, y);
                        	
                        // Solo actualizar si hay cambios para evitar repaints innecesarios
                        if (dentroDelCuadrante) {
                            
                            imageV.setVisible(true);
                            
                            imageV.setX(x * this._contenedor.getWidth());
                            imageV.setY(y * this._contenedor.getHeight());
                            
                            this.periodico.agregarMapa(obj.getSymbolID(), x, y, obj.getAngleDegrees());
                    	
                        } else {
                            imageV.setVisible(false);
                            this.periodico.eliminarMapa(obj.getSymbolID());
                            }
                    		}else{
                    
                    			 if(this.esCuadranteCentroMapa(x, y)) {
                    				System.out.print("entra al if");
                    			
                    				this._emociones.detectarObjetoEmocion(obj.getSymbolID(), this.imagenV);
                    			 }
                    }
                } else { // Si el símbolo es 3
                    if (this._nivel == 1) {
                        boolean dentroDelCuadrante = esCuadranteInferiorIzquierdo(x, y);

                        if (dentroDelCuadrante) {
                            imageV.setVisible(true);
                            imageV.setX(x * this._contenedor.getWidth());
                            imageV.setY(y * this._contenedor.getHeight());

                            // Evitar actualizaciones constantes de rotación si el cambio es mínimo
                            double anguloActual = imageV.getRotate();
                            if (Math.abs(anguloActual - angle) > 60) { 
                                imageV.setRotate(angle);
                            	//eliminar gifs del nivel 1
                				this.gifView.EliminarGifs_nivel1();	
                                this.imagenV.AsignarImagenRotacion(obj.getAngleDegrees());
                                
                                
                            }
                            
                        }
                        else {
                        	imageV.setVisible(false);
                        }
                    }
                }
            }
        });
    }
    
    
    


    
    @Override
    public void removeTuioObject(TuioObject obj) {
        long idSesion= obj.getSessionID();
        Platform.runLater(() -> {
            if(obj.getSymbolID()!=3){
                ImageView imageV = this.imagenV.EliminarImagen(idSesion);
                if (imageV != null) {               	
                    this._contenedor.getChildren().remove(imageV);                
                    this.periodico.eliminarMapa(obj.getSymbolID());  
                 // Verificar si ahora el periódico está vacío y estamos esperando vaciado
                    if (esperandoVaciarPeriodico && this.periodico.estaVacio()) {
                    	this._videos.iniciarSecuenciaVideos(this.imagenV);
                    	System.out.print("sube nivel");
                        this.subirNivel();
                        esperandoVaciarPeriodico = false;
                    }

                }
            } else {
                ImageView imageV = this.imagenV.EliminarImagen(idSesion);
                if (imageV != null) {
                    this._contenedor.getChildren().remove(imageV);
                }
            }

          //  if (this.periodico.estaVacio()) {
           //     System.out.println("Se vació el periódico, reproduciendo video");
          //      this._videos.iniciarVideoInterferencia();
         //   }
        });
    }

    @Override
    public void addTuioCursor(TuioCursor tc) {
    System.out.println("Cursor added: " + tc.getCursorID());
            }

    @Override
    public void updateTuioCursor(TuioCursor tc) {
    System.out.println("Cursor updated: " + tc.getCursorID() + " at " + tc.getX() + ", " + tc.getY());
    }

    @Override
    public void removeTuioCursor(TuioCursor tc) {}

    @Override
    public void addTuioBlob(TuioBlob tb) {}

    @Override
    public void updateTuioBlob(TuioBlob tb) {}

    @Override
    public void removeTuioBlob(TuioBlob tb) {}

    @Override
    public void refresh(TuioTime tt) {}
    
}