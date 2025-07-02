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

public class ListenerTuio implements TuioListener {
    private Pane _contenedor;
    private int _nivel;



    private ImagenesV imagenV;
    private PeriodicoInicial periodico;
    private Emociones _emociones;
    private VideoMonitor _videos;
    private Gifs gifView;
    private boolean esperandoVaciarPeriodico;

    private boolean[] _emocion;

   public ListenerTuio(Pane contenedor) {
        this.periodico = new PeriodicoInicial();
        this._nivel = 0;
        this._contenedor = contenedor;

        double ancho = Screen.getPrimary().getVisualBounds().getWidth();
        double alto = Screen.getPrimary().getVisualBounds().getHeight();

        this.imagenV = new ImagenesV(this._contenedor);
        this.imagenV.ConfigurarImagenPrincipal(ancho, alto);
        this.imagenV.AsignarImagenPrincipal();

        this.gifView = new Gifs(this._contenedor);
        this.gifView.AsignarGif_nivel0();

        this._videos = new VideoMonitor();
        this._emociones = new Emociones(this._videos);

        this.esperandoVaciarPeriodico = false;

        this._emocion = new boolean[6];
        for (int i = 0; i < 5; i++) {
            this._emocion[i] = false;
        }
    }


    @Override
    public void addTuioObject(TuioObject to) {
        System.out.println("Object added: " + to.getSymbolID());
        Platform.runLater(() -> {
            int id_simbolo = to.getSymbolID();
            double x = to.getX();
            double y = to.getY();
            double posX = x * this._contenedor.getWidth();
            double posY = y * this._contenedor.getHeight();

            this.imagenV.CrearImagen(id_simbolo, posX, posY, to.getSessionID());
            ImageView imageV = this.imagenV.getImagen(to.getSessionID());

            if (id_simbolo != 3 && this._nivel == 0 && esCuadranteCentroMapa(x, y)) {
                if (imageV != null) {
                    imageV.setVisible(true);
                    imageV.setX(posX);
                    imageV.setY(posY);
                    this.periodico.agregarMapa(id_simbolo, x, y, to.getAngleDegrees());
                    System.out.println(periodico.toString());

                    if (this.periodico.estaCompleto() && !esperandoVaciarPeriodico) {
                        this.gifView.Gif_SacaPiezas();
                        esperandoVaciarPeriodico = true;
                        System.out.print("esperando vaciar periodico true");
                    }

                    if (esperandoVaciarPeriodico && this.periodico.estaVacio()) {
                        System.out.println("sube nivel");
                        this.subirNivel();
                        esperandoVaciarPeriodico = false;
                    }
                }
            }
            // Nivel 1 para emociones IDs 6 a 11 en cuadrante centro
            else if (this._nivel == 1) {
                boolean adentroDelCuadrante = esCuadranteCentroMapaMasgrande(x, y);
                //System.out.println("→ Nivel 1, ID: " + id_simbolo + ", en centro: " + dentroDelCuadrante);
                //System.out.println("📍 Coordenadas objeto ID " + id_simbolo + ": x=" + x + ", y=" + y);
                if (adentroDelCuadrante && id_simbolo >= 6 && id_simbolo <= 11) {
                    System.out.println("✅ ID " + id_simbolo + " en cuadrante centro y válido (6–11), imageV: " + (imageV != null));
                   

                    if (imageV != null) {
                        imageV.setVisible(true);
                        imageV.setX(posX);
                        imageV.setY(posY);
                        imageV.setPickOnBounds(true);
                        imageV.setMouseTransparent(false);
                        System.out.println("holaaaa");
                      
                            System.out.println("👉 Click detectado sobre imagen ID: " + id_simbolo);
                            imagenV.validarSeleccionUsuario(id_simbolo);
                   
                    }
                }
                // ID 3 en cuadrante inferior izquierdo para rotación
                else if (id_simbolo == 3 && esCuadranteInferiorIzquierdo(x, y)) {
                    if (imageV != null) {
                        imageV.setVisible(true);
                        imageV.setX(posX);
                        imageV.setY(posY);

                        this.imagenV.permitirRotacion(true);
                        this.imagenV.AsignarImagenRotacion(to.getAngleDegrees(), id_simbolo);
                        this.imagenV.permitirRotacion(false);
                        this.imagenV.EliminarEmocionGanada();
                    }
                } else {
                    System.out.println("⚠️ ID " + id_simbolo + " no está en el centro ni es ID 3 en el inferior izquierdo.");
                }
            }
        });
    }

    private void subirNivel() {
        this._nivel++;
        this._contenedor.getChildren().removeIf(node -> {
            if (node instanceof ImageView) {
                String id = node.getId();
                return id == null ||
                       (!id.equals("background") &&
                        !id.equals("detector") &&
                        !id.equals("emocion") &&
                        !id.equals("estado"));
            }
            return false;
        });

        //  Limpiamos solo las piezas (rompecabezas)
        this.imagenV.LimpiarContenedor();

        // Luego agregamos las imágenes del nuevo nivel
        this.imagenV.AsignarImagenAugutsen();

        // this.gifView.AsignarGif_nivel1(); // si querés volver a activar los gifs
    }

    private boolean esCuadranteCentroMapa(double x, double y) {
        return ((x > 0.1674) && (x < 0.8330) && (y > 0.1241) && (y < 0.8878));
    }

    private boolean esCuadranteInferiorIzquierdo(double x, double y) {
        return ((x > 0.0195) && (x < 0.210) && (y > 0.6570) && (y < 0.930));
    }
    
    
    public boolean esCuadranteCentroMapaMasgrande(double x, double y) {
        return x >= 0.3 && x <= 0.7 && y >= 0.3 && y <= 0.7;
    }

    @Override
    
 
 
    public void updateTuioObject(TuioObject obj) {
        double x = obj.getX();
        double y = obj.getY();
        double angle = obj.getAngleDegrees();

        Platform.runLater(() -> {
            ImageView imageV = this.imagenV.getImagen(obj.getSessionID());
            if (imageV != null) {
                int id_simbolo = obj.getSymbolID();

                if (id_simbolo != 3) {
                    if (this._nivel == 0) {
                        boolean dentroDelCuadrante = esCuadranteCentroMapa(x, y);

                        if (dentroDelCuadrante) {
                            imageV.setVisible(true);
                            imageV.setX(x * this._contenedor.getWidth());
                            imageV.setY(y * this._contenedor.getHeight());
                            this.periodico.agregarMapa(obj.getSymbolID(), x, y, obj.getAngleDegrees());
                        } else {
                            imageV.setVisible(false);
                            this.periodico.eliminarMapa(id_simbolo);
                        }
                    } else if (this._nivel == 1) {
                        boolean enCentro = esCuadranteCentroMapaMasgrande(x, y);
                    //	System.out.println("📍 ID: " + id_simbolo + " | x: " + x + ", y: " + y);
                        if (id_simbolo >= 6 && id_simbolo <= 11 && enCentro) {


                            imageV.setVisible(true);
                            imageV.setX(x * this._contenedor.getWidth());
                            imageV.setY(y * this._contenedor.getHeight());
                      
                                System.out.println("👉 Click detectado sobre imagen ID: " + id_simbolo);
                                imagenV.validarSeleccionUsuario(id_simbolo);
                           
                        } else {
                            imageV.setVisible(false);
                        }
                    }
                } else {
                    if (this._nivel == 1) {
                        boolean enInferiorIzquierdo = esCuadranteInferiorIzquierdo(x, y);
                        if (enInferiorIzquierdo) {
                            imageV.setVisible(true);
                            imageV.setX(x * this._contenedor.getWidth());
                            imageV.setY(y * this._contenedor.getHeight());

                            double anguloActual = imageV.getRotate();
                           if (Math.abs(anguloActual - angle) > 60) {
                            	imageV.setRotate(angle);
                            	this.gifView.EliminarGifs_nivel1();
                            	this.imagenV.AsignarImagenRotacion(obj.getAngleDegrees(), obj.getSymbolID());

                             //   this._emociones.detectarObjetoEmocion(id_simbolo, this.imagenV);
                            }
                        } else {
                            imageV.setVisible(false);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void removeTuioObject(TuioObject obj) {
        long idSesion = obj.getSessionID();
        Platform.runLater(() -> {
            if (obj.getSymbolID() != 3) {
                ImageView imageV = this.imagenV.EliminarImagen(idSesion);
                if (imageV != null) {
                    this._contenedor.getChildren().remove(imageV);
                    this.periodico.eliminarMapa(obj.getSymbolID());

                    if (esperandoVaciarPeriodico && this.periodico.estaVacio()) {
                    	this._videos.iniciarSecuenciaVideos(imagenV);
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