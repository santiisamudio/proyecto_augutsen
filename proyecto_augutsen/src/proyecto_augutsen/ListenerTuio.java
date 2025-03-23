package proyecto_augutsen;

import TUIO.TuioBlob;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;


/**
 *
 * @author santi
 */
public class ListenerTuio implements TuioListener{
    private Pane _contenedor;//contenedor
    private ImageView _imagen;//sirve para mostrar imagenes
    private HashMap<Long, ImageView> _imagenes = new HashMap<>();
    private int _nivel;
    private boolean _primerParte=false;
    private boolean _segundaParte=false;
    private boolean _tercerParte=false;
    private boolean _cuartaParte=false;
    private AplicacionMain _main;
    
    
    public ListenerTuio(Pane contenedor,AplicacionMain main) {
    	this._nivel= 0;
    	this._main= main;
        this._contenedor = contenedor;
        double ancho = Screen.getPrimary().getVisualBounds().getWidth();
        double alto = Screen.getPrimary().getVisualBounds().getHeight();
        this._imagen = new ImageView();
        this._imagen.setPreserveRatio(false);
        this._imagen.setFitWidth(ancho);
        this._imagen.setFitHeight(alto);
        this._imagen.setId("background");
        this._contenedor.getChildren().add(this._imagen);//se agrega la imagen al contenedor
        this.AsignarImagenInicial();
        
        
    }
    
    @Override
    public void addTuioObject(TuioObject to) {
        System.out.println("Object added: " + to.getSymbolID());
        Platform.runLater(() -> {/*javaFX solo permite actualizar la UI desde si propio thread para evitar errores, esta linea hace que el codigo se ejecute en el thread principal,
                                    TUIO ejecuta las funciones desde un thread secundario*/
            int id_simbolo= to.getSymbolID();
            double x = to.getX() * this._contenedor.getWidth();/*TUIO usa coordenadas de 0.0(arriba izquierda) a 1.1(abajo derecha) el contenedor de javafx tiene como coordenadas
                                                                    pixeles, para convertirlo se multiplican, si x=0.5 , 0.5 * 800 pixeles = 400*/
            double y = to.getY() * this._contenedor.getHeight();

        	
        	this.CrearImagen(id_simbolo, x, y, to.getSessionID());
        	ImageView imageV = this._imagenes.get(to.getSessionID()); 
        	
                if((to.getSymbolID()!=3)&&(this._nivel==0)&&(esCuadranteCentroMapa(to.getX(),to.getY()))){//se pasa la ubicacion del objeto TUIO para ver si se encuentra abajo a la derecha
                    
                	if (imageV != null) {//verifica que el objeto exista 
                
                		imageV.setVisible(true);
                        imageV.setX(to.getX() * this._contenedor.getWidth());  // Posición X de la imagen
                        imageV.setY(to.getY() * this._contenedor.getHeight());  // Posición Y de la imagen
                		System.out.println("Número de nodos en el contenedor: " + _contenedor.getChildren().size());
                        
                        
                        
                        
                        this.agregarMapa(to.getSymbolID(),to.getX(),to.getY(),to.getAngleDegrees());
                        this.controlMapa();
                	}
                   
                }else if((to.getSymbolID()==3)&&(this._nivel==1)&&(esCuadranteInferiorIzquierdo(to.getX(),to.getY()))){
                	
                	
                	if(imageV != null){//verifica que el objeto exista 
                		imageV.setVisible(true);
                		imageV.setX(to.getX() * this._contenedor.getWidth());//se actualiza x
                		imageV.setY(to.getY() * this._contenedor.getHeight());//se actualiza y
                		this.reaccionarConRotacion(to.getAngleDegrees());//actualiza rotacion
            }
                }
                //
         //   }
            

        });
    }
    ;
    private boolean esCuadranteCentroMapa(double x, double y) {
    	return ((x>0.1674)&&(x<0.8330)&&(y>0.1241)&&(y<0.8878));
    }
    
    private boolean esCuadranteInferiorIzquierdo(double x, double y){
        return ((x>0.0195)&&(x<0.210)&&(y>0.6570)&&(y<0.930));
    }
    
    private void CrearImagen(int id,double x, double y, long idSesion) {
    	Image img = new Image(getClass().getResource("/imagenes/rompecabezas.png").toExternalForm());
        ImageView imageV = new ImageView(img); 
        imageV.setId("pieza");
        imageV.setX(x);  // Posición X de la imagen
        imageV.setY(y);  // Posición Y de la imagen
        imageV.setVisible(false);
        imageV.setFitWidth(100);  // Ajusta el tamaño de la imagen
        imageV.setFitHeight(100);  // Ajusta el tamaño de la imagen
        imageV.setPreserveRatio(true);  // Mantiene la relación de aspecto
        
        this._imagenes.put(idSesion, imageV);  // Guardamos el ImageView en el mapa
        this._contenedor.getChildren().add(imageV);  // Agregamos la imagen al contenedor
        
    }
    
    private void AsignarImagenInicial() {
    	Image img = new Image(getClass().getResource("/imagenes/1_inicialRompecabezas.png").toExternalForm());
    	this._imagen.setImage(img);
    	
    } 

    
    private void habilitarImagenAugutsen() {
    	Image img = new Image(getClass().getResource("/imagenes/2_panel_sinEmocion.png").toExternalForm());
    	
    	System.out.print("habilitado");
    	this._imagen.setImage(img);
    	Platform.runLater(() ->this._main.iniciarVideo());
    	
    	
    }
    
    private void controlMapa() {
    	if((this._primerParte==true)&&(this._segundaParte==true)&&(this._tercerParte==true)&&(this._cuartaParte==true)) {
    		this._nivel++;
    		habilitarImagenAugutsen();
    		this._contenedor.getChildren().removeIf(node -> node instanceof ImageView && !node.getId().equals("background"));
    		this._imagenes.clear();
    	}
    }
    
   
    
    
    
    @Override   
    public void updateTuioObject(TuioObject obj) { 
        double x = obj.getX();
        double y = obj.getY();
        double angle = obj.getAngleDegrees();  // Guardamos el ángulo para comparar cambios

        Platform.runLater(() -> {  
            ImageView imageV = this._imagenes.get(obj.getSessionID());

            if (imageV != null) {
                if (obj.getSymbolID() != 3) {
                    if (this._nivel == 0) {
                        boolean dentroDelCuadrante = esCuadranteCentroMapa(x, y);

                        // Solo actualizar si hay cambios para evitar repaints innecesarios
                        if (dentroDelCuadrante) {
                            
                            imageV.setVisible(true);
                            
                            imageV.setX(x * this._contenedor.getWidth());
                            imageV.setY(y * this._contenedor.getHeight());

                            this.agregarMapa(obj.getSymbolID(), x, y, obj.getAngleDegrees());
                            this.controlMapa();
                        } else {
                            imageV.setVisible(false);
                            this.eliminarMapa(obj.getSymbolID());
                            
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
                            if (Math.abs(anguloActual - angle) > 1) { 
                               // imageV.setRotate(angle);
                                this.reaccionarConRotacion(obj.getAngleDegrees());
                            }
                        } else {
                              imageV.setVisible(false);
                            
                        }
                    }
                }
            }
        });
    }
    
    
    private void reaccionarConRotacion(float angulo){
        
        		if (angulo >= 0 && angulo < 60) {
                    
                    Image img = new Image(getClass().getResource("/imagenes/3_panel_seleccionAlegria.png").toExternalForm());
                	this._imagen.setImage(img);
                } else if (angulo >= 60 && angulo < 120) {
                    
                    Image img = new Image(getClass().getResource("/imagenes/seleccion_enojo.png").toExternalForm());
                	this._imagen.setImage(img);
                } else if (angulo >= 120 && angulo < 180) {
                	Image img = new Image(getClass().getResource("/imagenes/seleccion_miedo.png").toExternalForm());
                	this._imagen.setImage(img);
                }  else if (angulo >= 180 && angulo < 240) {
                	Image img = new Image(getClass().getResource("/imagenes/seleccion_triste.png").toExternalForm());
                	this._imagen.setImage(img);
                } else if (angulo >= 240 && angulo < 300) {
                	Image img = new Image(getClass().getResource("/imagenes/seleccion_emocion5.png").toExternalForm());
                	this._imagen.setImage(img);
                } else if (angulo >= 300 && angulo < 360) {
                	Image img = new Image(getClass().getResource("/imagenes/seleccion_emocion6.png").toExternalForm());
                	this._imagen.setImage(img);
                }
    }

    @Override
    public void removeTuioObject(TuioObject obj) {// un objeto se elimina automaticamente cuando desaparece del campo visual
       
        long idSesion= obj.getSessionID();
         Platform.runLater(() -> {
            if(obj.getSymbolID()!=3){
            	ImageView imageV = this._imagenes.remove(idSesion);
               
                if (imageV != null) {
                	
                    this._contenedor.getChildren().remove(imageV);//elimina al circulo del contenedor pane
                    
                    
                    eliminarMapa(obj.getSymbolID());	
                    
                }
            }else{
                ImageView imageV = this._imagenes.remove(idSesion);//elimina el semicirculo del hashmap
                if (imageV != null) {
                    this._contenedor.getChildren().remove(imageV);//elimina al circulo del contenedor pane
                }
            }   
        }
         )
;
    
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
    
    private void seEncuentraPrimerParte() {
    	this._primerParte= true;
    	
    }
    
    private void seEncuentraSegundaParte() {
    	this._segundaParte= true;
    	
    }
    
    private void seEncuentraTercerParte() {
    	this._tercerParte=true;
    	
    }
    
    private void seEncuentraCuartaParte() {
    	this._cuartaParte=true;
    	
    }
    
    private void seEliminaPrimerParte() {
    	this._primerParte=false;
    }
    
    private void seEliminaSegundaParte() {
    	this._segundaParte=false;
    }
    
    private void seEliminaTercerParte() {
    	this._tercerParte=false;
    }
    
    private void seEliminaCuartaParte() {
    	this._cuartaParte=false;
    }
   
    
    private void agregarMapa(int id, double x, double y, float angulo) { 
        switch(id) {
            case 0: 
                if ((x > 0.1674) && (x < 0.5) && (y > 0.1241) && (y < 0.5)) {
                    if (angulo >= 90 && angulo < 180) {
                    	System.out.println(angulo);
                    	seEncuentraPrimerParte();
                    }
                }
                break;
            case 1: 
            	
                if ((x > 0.5) && (x < 0.8330) && (y > 0.1241) && (y < 0.5)) {
                	
                    if (angulo >= 180 && angulo < 270) {
                    	System.out.println(angulo);
                    	seEncuentraSegundaParte();
                    }
                }
                break;
            case 2: 
           
                if ((x > 0.1674) && (x < 0.5) && (y > 0.5) && (y < 0.8878)) {
                    if (angulo >= 0 && angulo < 90) {
                    	System.out.println(angulo);
                    	seEncuentraTercerParte();
                    }
                }
                break;
            case 4: 
          
                if ((x > 0.5) && (x < 0.8330) && (y > 0.5) && (y < 0.8878)) {
                	
                    if ((angulo >= 270) && (angulo < 360)) {
                    	
                    	System.out.println(angulo);
                      	seEncuentraCuartaParte();
                    }
                }
                break;
            default:
                System.out.println("ID no válido");
                break;
        }
    }

    
    private void eliminarMapa(int id) {
    	switch(id) {
    	case 0: seEliminaPrimerParte();
    		
    		break;
    	case 1: seEliminaSegundaParte();
    		
			break;
    	case 2: seEliminaTercerParte();
    		
			break;
    	case 4: seEliminaCuartaParte();
    		
			break;
    	
    	}
    }
    
}