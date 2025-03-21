package proyecto_augutsen;

import TUIO.TuioBlob;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

/**
 *
 * @author santi
 */
public class ListenerTuio implements TuioListener{
    private Pane _contenedor;//contenedor
    private ImageView _imagen;//sirve para mostrar imagenes
    private HashMap<Long, Circle> _circulos = new HashMap<>();
    private HashMap<Long, Arc> _semicirculos= new HashMap<>();
    private int _nivel;
    private boolean _primerParte=false;
    private boolean _segundaParte=false;
    private boolean _tercerParte=false;
    private boolean _cuartaParte=false;
    
    
    public ListenerTuio(Pane contenedor) {
    	this._nivel= 0;
        this._contenedor = contenedor;
        this._imagen = new ImageView();
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
           // this.CrearCirculoNormal(id_simbolo,x,y,to.getSessionID());
            //Circle circulo = this._circulos.get(to.getSessionID());
            //circulo.setVisible(false);
            this.CrearCirculoNormal(id_simbolo,x,y,to.getSessionID());
            Circle circulo = this._circulos.get(to.getSessionID());  	
        	
        	
        	this.CrearSemicirculoValores(to.getSymbolID(), x, y, to.getSessionID());
        	Arc semicirculo = this._semicirculos.get(to.getSessionID());
        	
        	
                if((to.getSymbolID()!=3)&&(this._nivel==0)&&(esCuadranteCentroMapa(to.getX(),to.getY()))){//se pasa la ubicacion del objeto TUIO para ver si se encuentra abajo a la derecha
                    
                	if (circulo != null) {//verifica que el objeto exista 
                    	circulo.setVisible(true);
                        circulo.setCenterX(to.getX() * this._contenedor.getWidth());// se actualiza x
                        circulo.setCenterY(to.getY() * this._contenedor.getHeight());//se actualiza y
                     // version santi   this.agregarMapa(to.getSymbolID());
                        this.agregarMapa(to.getSymbolID(),to.getX(),to.getY(),to.getAngleDegrees());
                        this.controlMapa();
                	}
                   
                }else if((to.getSymbolID()==3)&&(this._nivel==1)&&(esCuadranteInferiorIzquierdo(to.getX(),to.getY()))){
                	
                	
                	if(semicirculo != null){//verifica que el objeto exista 
                		semicirculo.setVisible(true);
                		semicirculo.setCenterX(to.getX() * this._contenedor.getWidth());//se actualiza x
                		semicirculo.setCenterY(to.getY() * this._contenedor.getHeight());//se actualiza y
                		this.reaccionarConRotacion(to.getAngleDegrees(),semicirculo);//actualiza rotacion
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
    
    
    private void CrearSemicirculoValores(int id,double x, double y, long idSesion){
        if(id == 3){
            Arc semiCirculo = new Arc();
                semiCirculo.setCenterX(x);//se ubica el semicirculo en la ubicacion del simbolo fiducial
                semiCirculo.setCenterY(y);
                semiCirculo.setRadiusX(20);//se define el tamaño
                semiCirculo.setRadiusY(20);
                semiCirculo.setStartAngle(0);//se define como 0 el angulo en donde comienza el semicirculo
                semiCirculo.setLength(180);//cuanto se extiende el semicirculo en grados
                semiCirculo.setType(ArcType.ROUND);//el arco se cierra con borde redondeado
                semiCirculo.setFill(Color.BLUE);//color por defecto
                semiCirculo.setVisible(false);//visible
                
                
                this._semicirculos.put(idSesion, semiCirculo);//se asigna al semicirculo el sessionid del objeto que se esta mostrando
                this._contenedor.getChildren().add(semiCirculo);//se agrega al contenedor pane el semicirculo
        }
    }
    private void CrearCirculoNormal(int id,double x, double y, long idSesion){
        Circle circulo = new Circle(x, y, 50, Color.TRANSPARENT);//se crea el circulo
                circulo.setStroke(Color.RED);//color del borde
                circulo.setStrokeWidth(3);//tamaño del borde
                circulo.setVisible(false);//visible

                this._circulos.put(idSesion, circulo);//asigno el circulo al objeto añadido
                this._contenedor.getChildren().add(circulo);//agrego el circulo al pane
                
    }
    
    private void AsignarImagenInicial() {
    	Image img = new Image(getClass().getResource("/imagenes/1_inicialRompecabezas.png").toExternalForm());
    	this._imagen.setImage(img);
    	
    }
    

    
    private void habilitarImagenAugutsen() {
    	Image img = new Image(getClass().getResource("/imagenes/2_panel_sinEmocion.png").toExternalForm());
    	this._imagen.setImage(img);
    }
    
    private void controlMapa() {
    	if((this._primerParte==true)&&(this._segundaParte==true)&&(this._tercerParte==true)&&(this._cuartaParte==true)) {
    		this._nivel++;
    		habilitarImagenAugutsen();
    		this._contenedor.getChildren().removeIf(node -> node instanceof Circle);
    		this._circulos.clear();
    	}
    }
    

    @Override
    public void updateTuioObject(TuioObject obj) {//esta funcion actualiza automaticamente el objeto cuando se detecta movimiento en el mismo. la maneja TUIO internamente
        double x = obj.getX();
        double y = obj.getY();
        
        Platform.runLater(() -> {// al actualizar la UI de javafx se debe pasar al thread principal
            if((obj.getSymbolID()!=3)&&(this._nivel==0)&&(esCuadranteCentroMapa(x,y))){//solo se actualiza dentro del cuadrante abajo derecha, sino no se muestra el simbolo
                
            	Circle circulo = this._circulos.get(obj.getSessionID());//se busca el circulo correspondiente al objeto
                if (circulo != null) {//verifica que el objeto exista 
                	circulo.setVisible(true);
                    circulo.setCenterX(obj.getX() * this._contenedor.getWidth());// se actualiza x
                    circulo.setCenterY(obj.getY() * this._contenedor.getHeight());//se actualiza y
                   // VERSION SANTI this.agregarMapa(obj.getSymbolID());
                    this.agregarMapa(obj.getSymbolID(),obj.getX(),obj.getY(),obj.getAngleDegrees());
                    System.out.println(obj.getSymbolID());
                    this.controlMapa();
                    //this.reaccionarSimboloSimple(obj.getSymbolID());
                }else if((this._nivel==0)&&(!esCuadranteCentroMapa(x,y))) {
                	circulo.setVisible(false);
                }
            
            }else if((this._nivel==1)&&(esCuadranteInferiorIzquierdo(x,y))){
            	Arc semicirculo = this._semicirculos.get(obj.getSessionID());
                	if(semicirculo != null){//verifica que el objeto exista 
                		semicirculo.setVisible(true);
                		semicirculo.setCenterX(obj.getX() * this._contenedor.getWidth());//se actualiza x
                		semicirculo.setCenterY(obj.getY() * this._contenedor.getHeight());//se actualiza y
                		this.reaccionarConRotacion(obj.getAngleDegrees(),semicirculo);//actualiza rotacion
                	}else if((this._nivel==1)&&(!esCuadranteCentroMapa(x,y))) {
                    	semicirculo.setVisible(false);
                    }
            }
        });
    
    }
    
    private void reaccionarConRotacion(float angulo, Arc semicirculo){
        
        //dependiendo el angulo cambia el color
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
                Circle circulo = this._circulos.remove(idSesion);//elimina el circulo del hashmap
                if (circulo != null) {
                    this._contenedor.getChildren().remove(circulo);//elimina al circulo del contenedor pane
                    eliminarMapa(obj.getSymbolID());
                }
            }else{
                Arc semicirculo = this._semicirculos.remove(idSesion);//elimina el semicirculo del hashmap
                if (semicirculo != null) {
                    this._contenedor.getChildren().remove(semicirculo);//elimina al circulo del contenedor pane
                }
            }   
        }
         );
    
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
    	System.out.println("agregado");
    }
    
    private void seEncuentraSegundaParte() {
    	this._segundaParte= true;
    	System.out.println("agregado");
    }
    
    private void seEncuentraTercerParte() {
    	this._tercerParte=true;
    	System.out.println("agregado");
    }
    
    private void seEncuentraCuartaParte() {
    	this._cuartaParte=true;
    	System.out.println("agregado");
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
                	System.out.println("case 1");
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
                	System.out.println("case 4");
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
    		System.out.println("eliminado");
    		break;
    	case 1: seEliminaSegundaParte();
    		System.out.println("eliminado");
			break;
    	case 2: seEliminaTercerParte();
    		System.out.println("eliminado");
			break;
    	case 4: seEliminaCuartaParte();
    		System.out.println("eliminado");
			break;
    	
    	}
    }
    
}