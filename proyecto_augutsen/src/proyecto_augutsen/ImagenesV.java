package proyecto_augutsen;
import java.util.HashMap;
import javafx.stage.Screen;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.media.AudioClip;

public class ImagenesV {
	private HashMap<Long, ImageView> _imagenes = new HashMap<>();
	private Pane _contenedor;
	private ImageView _imagenPrincipal;
	private String imagenEmocionActual;
	private Gifs gifView;
	private Emociones emociones;
    private boolean rotacionHabilitada = true;
	private VideoMonitor _videos;

	public ImagenesV(Pane contenedor) {
	    this._contenedor = contenedor;
	    this._imagenPrincipal = new ImageView();
	    this._videos = new VideoMonitor();
	    this.emociones = new Emociones(this._videos); // si así se construye
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
    
    public void EliminarImagenPorSesion(long idSesion) {
        ImageView img = _imagenes.get(idSesion);
        if (img != null) {
            _contenedor.getChildren().remove(img); // Quitás del contenedor
            _imagenes.remove(idSesion);            // Quitás del mapa
        }
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
    



    public void setImagenEmocionActual(String emocion) {
        this.imagenEmocionActual = emocion;
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
    
    
    public void Detector() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-29.png").toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setId("detector"); // 
        imageView.setFitWidth(280);
        imageView.setFitHeight(280);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(100);
        imageView.setLayoutY(450);
        imageView.setVisible(true);
        this._contenedor.getChildren().add(imageView);
        imageView.setId("detector");
    }

    public void EmocionesGenerar() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-28.png").toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setId("emocion");
        imageView.setFitWidth(280);
        imageView.setFitHeight(280);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1120);
        imageView.setLayoutY(450);
        imageView.setVisible(true);
        this._contenedor.getChildren().add(imageView);
        imageView.setId("emocion");
    }

    public void Estado() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-09.png").toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setId("estado"); 
        imageView.setFitWidth(280);
        imageView.setFitHeight(280);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1120);
        imageView.setLayoutY(100);
        imageView.setVisible(true);
        this._contenedor.getChildren().add(imageView);
        imageView.setId("estado");
        
    }

    public void Barra(String rutaImagen) {
        Image img = new Image(getClass().getResource(rutaImagen).toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(750);
        imageView.setFitHeight(100);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(400);
        imageView.setLayoutY(130);
        imageView.setId("barra"); 
        imageView.setVisible(true);
        
        this._contenedor.getChildren().add(imageView);
    }
    public void EmocionPanel(String rutaImagen) {
        Image img = new Image(getClass().getResource(rutaImagen).toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setLayoutX(130);
        imageView.setLayoutY(150);
        imageView.setScaleX(1.3);
        imageView.setId("emocionPanel"); 
        imageView.setVisible(true);
        this._contenedor.getChildren().add(imageView);
    }


    public void IconoEnojoBien() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-27.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1380);
        imageView.setLayoutY(200);
        imageView.setVisible(true);
        imageView.setId("emocion7");
        this._contenedor.getChildren().add(imageView);
    }
    public void IconoEnojoMal() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-20.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1300);
        imageView.setLayoutY(200);
        imageView.setVisible(true);
        imageView.setId("emocion7");
        this._contenedor.getChildren().add(imageView);
    }
    
    
    public void IconoDesagradoeBien() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-26.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1200);
        imageView.setLayoutY(200);
        imageView.setVisible(true);
        imageView.setId("emocion10");
        this._contenedor.getChildren().add(imageView);
    }
    
    public void IconoDesagradoeMal() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-21.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1100);
        imageView.setLayoutY(200);
        imageView.setVisible(true);
        imageView.setId("emocion10");
        this._contenedor.getChildren().add(imageView);
    }
    
    public void mostrarIconoEmocion(int id, boolean bien) {
        double x = 0, y = 0;

        switch(id) {
            case 6: x = 1680; y = 400; break;
            case 7: x = 1520; y = 400; break;
            case 8: x = 1680; y = 300; break;
            case 9: x = 1600; y = 400; break;
            case 10: x = 1600; y = 300; break;
            case 11: x = 1520; y = 300; break;
            default:
                System.out.println("ID no reconocido: " + id);
                return;
        }

        // Eliminar imagen previa en esa posición
        final double fx = x, fy = y; // Necesario porque x, y son usadas dentro de una lambda
        _contenedor.getChildren().removeIf(node -> {
            if (node instanceof ImageView) {
                double nx = ((ImageView) node).getLayoutX();
                double ny = ((ImageView) node).getLayoutY();
                return Math.abs(nx - fx) < 1 && Math.abs(ny - fy) < 1;
            }
            return false;
        });

        // Elegir ruta según id y emoción
        String rutaImagen = switch(id) {
            case 6-> bien ? "/imagenes/Sala de escape 2025-24.png" : "/imagenes/Sala de escape 2025-18.png";
            case 7 -> bien ? "/imagenes/Sala de escape 2025-27.png" : "/imagenes/Sala de escape 2025-20.png";
            case 8 -> bien ? "/imagenes/Sala de escape 2025-22.png" : "/imagenes/Sala de escape 2025-16.png";
            case 9 -> bien ? "/imagenes/Sala de escape 2025-25.png" : "/imagenes/Sala de escape 2025-19.png";
            case 10 -> bien ? "/imagenes/Sala de escape 2025-26.png" : "/imagenes/Sala de escape 2025-21.png";
            case 11 -> bien ? "/imagenes/Sala de escape 2025-23.png" : "/imagenes/Sala de escape 2025-17.png";
            default -> null;
        };

        if (rutaImagen == null) return;

        Image img = new Image(getClass().getResource(rutaImagen).toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setVisible(true);

        _contenedor.getChildren().add(imageView);
    }


    
    public void IconoSorpresaeMal() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-17.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1200);
        imageView.setLayoutY(200);
        imageView.setVisible(true);
        imageView.setId("emocion11");
        this._contenedor.getChildren().add(imageView);
    }
    public void IconoTristeMal() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-19.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1300);
        imageView.setLayoutY(300);
        imageView.setVisible(true);
        imageView.setId("emocion9");
        this._contenedor.getChildren().add(imageView);
    }
    
    public void IconoFelizeMal() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-18.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1200);
        imageView.setLayoutY(300);
        imageView.setVisible(true);
        imageView.setId("emocion6");
        this._contenedor.getChildren().add(imageView);
    }   
    public void IconoMiedoMal() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-16.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(1100);
        imageView.setLayoutY(300);
        imageView.setVisible(true);
        imageView.setId("emocion8"); 
        this._contenedor.getChildren().add(imageView);
    }
    public void EmocionesGanadas() {
        Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-30.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(280);
        imageView.setFitHeight(280);
        imageView.setScaleX(1.3);
        imageView.setLayoutX(100);
        imageView.setLayoutY(100);
        imageView.setVisible(true);

        imageView.setId("imagenGanada"); // 

        this._contenedor.getChildren().add(imageView);
    }
    public void EliminarEmocionGanada() {
        _contenedor.getChildren().removeIf(node -> {
            if (!(node instanceof ImageView)) return false;

            String id = node.getId();
            return "imagenGanada".equals(id)
                || "estado".equals(id)
                || "emocion".equals(id)
                || "detector".equals(id);
        });
    }

    
    public void AsignarImagenAugutsen() {
    	Image img = new Image(getClass().getResource("/imagenes/Sala de escape 2025-02.png").toExternalForm());
    	
    	System.out.print("habilitado");
    	this._imagenPrincipal.setImage(img);
    	this.Detector();
    	this.EmocionesGenerar();
    	this.Estado();
    	
    	this.EmocionesGanadas();
    
    	// this.IconoEnojoBien();
    	this.IconoEnojoMal();
    	// this.IconoTristeBien();
      this.IconoTristeMal();
    	 this.IconoFelizeMal();
    	 //this.IconoFelizeBien();
    	// this.IconoDesagradoeBien();
    	this.IconoDesagradoeMal();
    	// this.IconoSorpresaeBien();
    	 this.IconoSorpresaeMal();
    	this.IconoMiedoMal(); 
    }
    public void permitirRotacion(boolean habilitar) {
        this.rotacionHabilitada = habilitar;
    }

    //LO SAQUE PORQUE REMPLACE LAS IMAGENES CON LOS VIDEOS DE AUGUTSEN 
    public void AsignarImagenRotacion(float angulo, int id) {
        if (!rotacionHabilitada) return;
        if (id != 3) return; // Solo permitir rotación para el ID 3

        // 1. Eliminar imágenes anteriores del panel
        _contenedor.getChildren().removeIf(node -> {
            if (!(node instanceof ImageView)) return false;
            String idNode = node.getId();
            return "emocionPanel".equals(idNode) || "barra".equals(idNode);
        });
         if (id == 3) {
        // 2. Determinar emoción actual según ángulo
        if (angulo >= 0 && angulo < 60) {
            this.imagenEmocionActual = "alegria";
            this.EmocionPanel("/imagenes/Sala de escape 2025-33.png");
            this.Barra("/imagenes/Sala de escape 2025-07.png");
            this._videos.reproducirVideoEmocion("alegria");
        } else if (angulo >= 60 && angulo < 120) {
            this.imagenEmocionActual = "enojo";
            this.Barra("/imagenes/Sala de escape 2025-05.png");
            this.EmocionPanel("/imagenes/Sala de escape 2025-35.png");
        } else if (angulo >= 120 && angulo < 180) {
            this.imagenEmocionActual = "miedo";
            this.EmocionPanel("/imagenes/Sala de escape 2025-31.png");
            this.Barra("/imagenes/Sala de escape 2025-03.png");
        } else if (angulo >= 180 && angulo < 240) {
            this.imagenEmocionActual = "tristeza";
            this.EmocionPanel("/imagenes/Sala de escape 2025-36.png");
            this.Barra("/imagenes/Sala de escape 2025-06.png");
        } else if (angulo >= 240 && angulo < 300) {
            this.imagenEmocionActual = "asco";
            this.EmocionPanel("/imagenes/Sala de escape 2025-34.png");
            this.Barra("/imagenes/Sala de escape 2025-04.png");
        } else if (angulo >= 300 && angulo < 360) {
            this.imagenEmocionActual = "sorpresa";
            this.EmocionPanel("/imagenes/Sala de escape 2025-32.png");
            this.Barra("/imagenes/Sala de escape 2025-08.png");
        } else {
            return; // ángulo fuera de rango
        }
        }
        
    }
    public void reproducirSonido(String ruta) {
        try {
            AudioClip sonido = new AudioClip(getClass().getResource(ruta).toExternalForm());
            sonido.play();
        } catch (Exception e) {
            System.out.println("Error al reproducir sonido: " + e.getMessage());
        }
    }
    
    public void validarSeleccionUsuario(int idSeleccionado) {
        Map<Integer, String> mapa = Map.of(
            6, "alegria",
            7, "enojo",
            8, "miedo",
            9, "tristeza",
            10, "asco",
            11, "sorpresa"
        );

        String emocionEsperada = imagenEmocionActual;
        String emocionSeleccionada = mapa.get(idSeleccionado);

        System.out.println("Esperada: " + emocionEsperada + ", seleccionada: " + emocionSeleccionada);

        if (emocionEsperada != null && emocionEsperada.equalsIgnoreCase(emocionSeleccionada)) {
            System.out.println("¡Correcto!");
            emociones.marcarEmocionSeleccionada(idSeleccionado, this);  // método para marcar y mostrar ícono
            reproducirSonido("/sonidos/correcto.mp3");
        } else {
            System.out.println("Incorrecto");
            reproducirSonido("/sonidos/error.mp3");

        }
    }
    
   public void AsignarImagenAugutsenNormal() {
   	Image img = new Image(getClass().getResource("/imagenes/augutsen normal.jpeg").toExternalForm());
   	this._imagenPrincipal.setImage(img);
   } 
   
    public String getImagenEmocionActual() {
    	return this.imagenEmocionActual;
    }
 

    public void mostrarImagenEnSegundoMonitor() {
        Platform.runLater(() -> {
            var monitores = Screen.getScreens();
            if (monitores.size() < 2) {
                System.out.println("Se necesita al menos 2 monitores.");
                return;
            }

            Screen segundoMonitor = monitores.get(1);  // Primer monitor adicional
            Rectangle2D bounds = segundoMonitor.getBounds();

            // Cargar imagen (desde recursos)
            URL url = getClass().getResource("/imagenes/augutsen normal.jpeg");
            if (url == null) {
                System.err.println("No se encontró la imagen.");
                return;
            }
            Image imagen = new Image(url.toExternalForm());

            // Configurar vista
            ImageView imageView = new ImageView(imagen);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(bounds.getWidth());
            imageView.setFitHeight(bounds.getHeight());

            // Crear escena y stage
            StackPane root = new StackPane(imageView);
            root.setStyle("-fx-background-color: black;");
            Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());

            Stage imagenStage = new Stage();
            imagenStage.setTitle("Imagen en Segundo Monitor");
            imagenStage.setScene(scene);
            imagenStage.setX(bounds.getMinX());
            imagenStage.setY(bounds.getMinY());
            imagenStage.setWidth(bounds.getWidth());
            imagenStage.setHeight(bounds.getHeight());
            imagenStage.setFullScreen(true);
            imagenStage.show();
        });
    } }