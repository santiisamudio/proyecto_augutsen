package proyecto_augutsen;
import javafx.scene.media.AudioClip;
import java.util.Map;
import java.util.HashMap;

public class Emociones {
    private boolean[] _emociones; // 0=alegria,1=enojo,2=miedo,3=tristeza,4=asco,5=sorpresa  
    private VideoMonitor _videos;

    public Emociones(VideoMonitor video) {
        this._videos = video;
        this._emociones = new boolean[6];
        for (int i = 0; i < 6; i++) {
            this._emociones[i] = false;
        }
    }
    public void marcarEmocionSeleccionada(int id, ImagenesV imagenV) {
        int indice = id - 6;
        if (!_emociones[indice]) {
            _emociones[indice] = true;
            imagenV.mostrarIconoEmocion(id, true);
            reproducirSonido("/sonidos/correcto.mp3");
            System.out.println("Emoción marcada como correcta: " + id);
        }
    }
    private void reproducirSonido(String ruta) {
        AudioClip sonido = new AudioClip(getClass().getResource(ruta).toExternalForm());
        sonido.play();
    }
    

}