package proyecto_augutsen;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Gifs {
    private HashMap<Long, ImageView> _imagenes = new HashMap<>();
    private Pane _contenedor;
    private ImageView _gifPrincipal;
    private Gifs gifView;
    public Gifs(Pane contenedor) {
        this._contenedor = contenedor;
        this._gifPrincipal = new ImageView();
    }

    public void CrearGif(int id, double x, double y, long idSesion, String rutaGif) {
    	//chequear que onda este if
    	 if (_imagenes.containsKey(idSesion)) return;
    	 Image gifImage = new Image(getClass().getResource(rutaGif).toExternalForm());  
         ImageView gifView = new ImageView(gifImage); 

        gifView.setId("pieza");
        gifView.setLayoutX(x);  // Posición X correcta para un Pane
        gifView.setLayoutY(y);  // Posición Y correcta para un Pane
        gifView.setVisible(false);
        gifView.setFitWidth(250);  // Ajustar tamaño
        gifView.setFitHeight(250);
        gifView.setPreserveRatio(true);

        this._imagenes.put(idSesion, gifView);  // Guardamos el GIF en el HashMap
        this._contenedor.getChildren().add(gifView);  // Agregamos el GIF al contenedor
    }

    public ImageView getGif(long sesionId) {
        return this._imagenes.get(sesionId);
    }

    public void LimpiarContenedor() {
        this._imagenes.clear();
        this._contenedor.getChildren().clear(); // Limpiar visualmente también
    }

    public ImageView EliminarGif(long sesionId) {
        ImageView gif = this._imagenes.remove(sesionId);
        if (gif != null) {
            this._contenedor.getChildren().remove(gif);
        }
        return gif;
    }

    public void ConfigurarGifPrincipal(double ancho, double alto) {
        this._gifPrincipal.setPreserveRatio(false);
        this._gifPrincipal.setFitWidth(ancho);
        this._gifPrincipal.setFitHeight(alto);
        this._gifPrincipal.setId("background");
        this._contenedor.getChildren().add(this._gifPrincipal);
    }
    public void AsignarGif_nivel0() {
    	 this.gifView = new Gifs(this._contenedor); 
         double gifX = 0.062 * this._contenedor.getWidth();  // centro X del cuadrante sup izq
         double gifY = 0.6 * this._contenedor.getHeight();   // centro Y del cuadrante sup izq
         this.gifView.CrearGif(999, gifX, gifY, 999, "/gifs/4RNk.gif");
         ImageView gif = this.gifView.getGif(999);
         if (gif != null) {
             gif.setVisible(true);
             gif.setLayoutX(5);  // CORREGIDO
             gif.setLayoutY(200);  // CORREGIDO
         }

         double gifX2 = 0.5 * this._contenedor.getWidth();
         double gifY2 = 0.5 * this._contenedor.getHeight();
         this.gifView.CrearGif(888, gifX2, gifY2, 888, "/gifs/Z23b.gif");
         ImageView gif2 = this.gifView.getGif(888);
         if (gif2 != null) {
             gif2.setVisible(true);
             gif2.setLayoutX(10);
             gif2.setLayoutY(550); }
         //gif3
         double gifX3 = 0.2 * this._contenedor.getWidth();
         double gifY3 = 0.2 * this._contenedor.getHeight();
         this.gifView.CrearGif(777, gifX3, gifY3,777, "/gifs/VJl.gif");
         ImageView gif3 = this.gifView.getGif(777);
         if (gif3 != null) {
             gif3.setVisible(true);
             gif3.setLayoutX(1300);
             gif3.setLayoutY(450); }
    }
    public void AsignarGif_nivel1() {
    	/*double gifX_4 = 0.25 * this._contenedor.getWidth();
		  double gifY_4 = 0.75 * this._contenedor.getHeight();
		  this.gifView.CrearGif(1111, gifX_4, gifY_4, 1111, "/gifs/VJE4.gif");
		  ImageView gif_4 = this.gifView.getGif(1111);
		  gif_4.setScaleX(1.3);
		    if (gif_4 != null) {
		        gif_4.setVisible(true);
		        gif_4.setLayoutX(100);
		        gif_4.setLayoutY(700);
		    }
		    
			*/
		    double gifX_5 = 0.55 * this._contenedor.getWidth();
		    double gifY_5 = 0.78 * this._contenedor.getHeight();
		    this.gifView.CrearGif(1112, gifX_5, gifY_5, 1112, "/gifs/7SUp.gif");
		    ImageView gif_5 = this.gifView.getGif(1112);
		    gif_5.setScaleX(1.2); // Aumenta el tamaño al 150% horizontalmente
		    gif_5.setScaleY(1.2); // Aumenta el tamaño al 150% verticalmente

		    if (gif_5 != null) {
		        gif_5.setVisible(true);
		        gif_5.setLayoutX(1590);
		        gif_5.setLayoutY(730);
		    }
		    
		    
    }
    
    
    public void Gif_SacaPiezas() {
    	double gifX_11 = 0.5 * this._contenedor.getWidth();
		  double gifY_11= 0.5 * this._contenedor.getHeight();
		  this.gifView.CrearGif(555, gifX_11, gifY_11, 555, "/gifs/saca_las_piezas.gif");
		  
		  ImageView gif_11 = this.gifView.getGif(555);
		  gif_11.setFitHeight(500);
		  gif_11.setFitWidth(500);
		  gif_11.setScaleX(1.3);
		    if (gif_11 != null) {
		        gif_11.setVisible(true);
		        gif_11.setLayoutX(600);
		        gif_11.setLayoutY(100);
		    }

    }
    
    
  
    
    
    public void eliminar_sacarPiezas() {
    	ImageView gif_11 = this.gifView.getGif(555);
    	if (gif_11 != null) {
    	    this._contenedor.getChildren().remove(gif_11);
    	}
    }
    	
    public void EliminarGifs_nivel1() {
    	ImageView gif_5 = this.gifView.getGif(1112);
    	if (gif_5 != null) {
    	    this._contenedor.getChildren().remove(gif_5);
    	}
    
    
    }
    
    
    
}