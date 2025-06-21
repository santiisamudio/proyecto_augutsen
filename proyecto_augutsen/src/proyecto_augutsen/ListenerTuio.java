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
            double x = to.getX() * this._contenedor.getWidth();
            double y = to.getY() * this._contenedor.getHeight();
            this.imagenV.CrearImagen(id_simbolo, x, y, to.getSessionID());
            ImageView imageV = this.imagenV.getImagen(to.getSessionID());

            if ((to.getSymbolID() != 5) && (this._nivel == 0) && (esCuadranteCentroMapa(to.getX(), to.getY()))) {
                if (imageV != null) {
                    imageV.setVisible(true);
                    imageV.setX(x);
                    imageV.setY(y);
                    this.periodico.agregarMapa(to.getSymbolID(), to.getX(), to.getY(), to.getAngleDegrees());
                    System.out.println(periodico.toString());

                    if (this.periodico.estaCompleto() && !esperandoVaciarPeriodico) {
                        this.gifView.Gif_SacaPiezas();
                        esperandoVaciarPeriodico = true;
                        System.out.print("esperando vaciar periodico true");
                    }

                    System.out.print("antes del if");
                    if (esperandoVaciarPeriodico && this.periodico.estaVacio()) {
                        System.out.print("sube nivel");
                        this.subirNivel();
                        esperandoVaciarPeriodico = false;
                    }
                }
            } else if ((to.getSymbolID() == 3) && (this._nivel == 1) && (esCuadranteInferiorIzquierdo(to.getX(), to.getY()))) {
                if (imageV != null) {
                    imageV.setVisible(true);
                    imageV.setX(x);
                    imageV.setY(y);
                    this.imagenV.AsignarImagenRotacion(to.getAngleDegrees());
                }
            }
        });
    }

    private void subirNivel() {
        this._nivel++;

        // ✅ Primero limpiamos el contenedor (pero conservando ciertas imágenes)
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

        // ✅ Limpiamos solo las piezas (rompecabezas)
        this.imagenV.LimpiarContenedor();

        // ✅ Luego agregamos las imágenes del nuevo nivel
        this.imagenV.AsignarImagenAugutsen();

        // this.gifView.AsignarGif_nivel1(); // si querés volver a activar los gifs
    }

    private boolean esCuadranteCentroMapa(double x, double y) {
        return ((x > 0.1674) && (x < 0.8330) && (y > 0.1241) && (y < 0.8878));
    }

    private boolean esCuadranteInferiorIzquierdo(double x, double y) {
        return ((x > 0.0195) && (x < 0.210) && (y > 0.6570) && (y < 0.930));
    }

    @Override
    public void updateTuioObject(TuioObject obj) {
        double x = obj.getX();
        double y = obj.getY();
        double angle = obj.getAngleDegrees();

        Platform.runLater(() -> {
            ImageView imageV = this.imagenV.getImagen(obj.getSessionID());
            if (imageV != null) {
                if (obj.getSymbolID() != 5) {
                    if (this._nivel == 0) {
                        boolean dentroDelCuadrante = esCuadranteCentroMapa(x, y);

                        if (dentroDelCuadrante) {
                            imageV.setVisible(true);
                            imageV.setX(x * this._contenedor.getWidth());
                            imageV.setY(y * this._contenedor.getHeight());
                            this.periodico.agregarMapa(obj.getSymbolID(), x, y, obj.getAngleDegrees());
                        } else {
                            imageV.setVisible(false);
                            this.periodico.eliminarMapa(obj.getSymbolID());
                        }
                    } else {
                        if (this.esCuadranteCentroMapa(x, y)) {
                            System.out.print("entra al if");
                            this._emociones.detectarObjetoEmocion(obj.getSymbolID(), this.imagenV);
                        }
                    }
                } else {
                    if (this._nivel == 1) {
                        boolean dentroDelCuadrante = esCuadranteInferiorIzquierdo(x, y);

                        if (dentroDelCuadrante) {
                            imageV.setVisible(true);
                            imageV.setX(x * this._contenedor.getWidth());
                            imageV.setY(y * this._contenedor.getHeight());

                            double anguloActual = imageV.getRotate();
                            if (Math.abs(anguloActual - angle) > 60) {
                                imageV.setRotate(angle);
                                this.gifView.EliminarGifs_nivel1();
                                this.imagenV.AsignarImagenRotacion(obj.getAngleDegrees());
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
            if (obj.getSymbolID() != 5) {
                ImageView imageV = this.imagenV.EliminarImagen(idSesion);
                if (imageV != null) {
                    this._contenedor.getChildren().remove(imageV);
                    this.periodico.eliminarMapa(obj.getSymbolID());

                    if (esperandoVaciarPeriodico && this.periodico.estaVacio()) {
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
