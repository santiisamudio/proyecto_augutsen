package proyecto_augutsen;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImagenesV {
	private HashMap<Long, ImageView> _imagenes = new HashMap<>();
	private Pane _contenedor;
	private ImageView _imagenPrincipal;
	private String imagenEmocionActual;
	 //mio nuevo
	private VideoMonitor _videos;
	public ImagenesV(Pane contenedor) {
		this._contenedor= contenedor;
		this._imagenPrincipal = new ImageView();
		 this._videos = new VideoMonitor();
	}
	
    public void CrearImagen(int id,double x, double y, long idSesion) {
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
    
    public void CrearIcono(double x, double y,String link) {
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
    }
    
    
    
    public ImageView getImagen(long sesionId) {
    	return this._imagenes.get(sesionId);
    }
    
    public void LimpiarContenedor() {
    	this._imagenes.clear();
    }
    
    public ImageView EliminarImagen(long sesionId) {
    	return this._imagenes.remove(sesionId);
    }
    
    
    public void ConfigurarImagenPrincipal(double ancho, double alto) {
    	
    	this._imagenPrincipal.setPreserveRatio(false);
    	this._imagenPrincipal.setFitWidth(ancho);
    	this._imagenPrincipal.setFitHeight(alto);
    	this._imagenPrincipal.setId("background");
    	this._contenedor.getChildren().add(this._imagenPrincipal);
    }
    
    public void AsignarImagenPrincipal() {
    	Image img = new Image(getClass().getResource("/imagenes/1_inicialRompecabezas.png").toExternalForm());
    	this._imagenPrincipal.setImage(img);
    }
    
    
    public void AsignarImagenAugutsen() {
    	Image img = new Image(getClass().getResource("/imagenes/2_panel_sinEmocion.png").toExternalForm());
    	
    	System.out.print("habilitado");
    	this._imagenPrincipal.setImage(img);
    }
    //LO SAQUE PORQUE REMPLACE LAS IMAGENES CON LOS VIDEOS DE AUGUTSEN 
   public void AsignarImagenRotacion(float angulo) {
    	if (angulo >= 0 && angulo < 60) {
            this._videos.iniciarAlegria();
          //  Image img = new Image(getClass().getResource("/imagenes/3_panel_seleccionAlegria.png").toExternalForm());
            //this._imagenPrincipal.setImage(img);
        	this.imagenEmocionActual=  "alegria";
        } else if (angulo >= 60 && angulo < 120) {
            this._videos.iniciarIra();
            
       //    Image img = new Image(getClass().getResource("/imagenes/seleccion_enojo.png").toExternalForm());
        //this._imagenPrincipal.setImage(img);
         this.imagenEmocionActual= "enojo";
        } else if (angulo >= 120 && angulo < 180) {
            this._videos.iniciarMiedo();
        	//Image img = new Image(getClass().getResource("/imagenes/seleccion_miedo.png").toExternalForm());
        	//this._imagenPrincipal.setImage(img);
        	this.imagenEmocionActual= "miedo";
        }  else if (angulo >= 180 && angulo < 240) {
            this._videos.iniciarTristeza();
        //	Image img = new Image(getClass().getResource("/imagenes/seleccion_triste.png").toExternalForm());
        //	this._imagenPrincipal.setImage(img);
       	this.imagenEmocionActual= "tristeza";
        } else if (angulo >= 240 && angulo < 300) {
            this._videos.iniciarDesagrado();
        //	Image img = new Image(getClass().getResource("/imagenes/seleccion_emocion5.png").toExternalForm());
        	//this._imagenPrincipal.setImage(img);
           this.imagenEmocionActual= "asco";
        } else if (angulo >= 300 && angulo < 360) {
            this._videos.iniciarSorpresa ();
     //   	Image img = new Image(getClass().getResource("/imagenes/seleccion_emocion6.png").toExternalForm());
       // 	this._imagenPrincipal.setImage(img);
        this.imagenEmocionActual= "sorpresa";
        }
    	
    }
    
    
    public String getImagenEmocionActual() {
    	return this.imagenEmocionActual;
    }
}
