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
    /*public void determinarEmocionDesdeRotacion(int idDetectado, ImagenesV imagenV) {
        String emocionEsperada = imagenV.getImagenEmocionActual(); // Emoción "esperada" o que debería coincidir
        Map<Integer, String> idAEmocion = Map.of(
            6, "alegria",
            7, "enojo",
            8, "miedo",
            9, "tristeza",
            10, "asco",
            11, "sorpresa"
        );
        String emocionDetectada = idAEmocion.get(idDetectado);

        if (emocionDetectada != null && emocionEsperada.equals(emocionDetectada)) {
            int indice = idDetectado - 6;
            if (!_emociones[indice]) {
                _emociones[indice] = true;
                imagenV.mostrarIconoEmocion(idDetectado, true);
                _videos.reproducirVideoEmocion(emocionDetectada);
                reproducirSonido("/sonidos/correcto.mp3");
                System.out.println("Correcto por rotación: " + emocionDetectada);
            }
        } else {
            reproducirSonido("/sonidos/error.mp3");
            System.out.println("Incorrecto por rotación: seleccionaste " + emocionDetectada + ", esperaba " + emocionEsperada);
        }
    }


  
  /*  public void detectarObjetoEmocion(int idObjeto, ImagenesV imagenV) {
        String emocionActual = imagenV.getImagenEmocionActual();
        if (emocionActual == null) {
            System.out.println(" La emoción actual es null.");
            return;
        }

        Map<Integer, String> mapaEmociones = new HashMap<>();
        mapaEmociones.put(6, "alegria");
        mapaEmociones.put(7, "enojo");
        mapaEmociones.put(8, "miedo");
        mapaEmociones.put(9, "tristeza");
        mapaEmociones.put(10, "asco");
        mapaEmociones.put(12, "sorpresa");

        if (idObjeto == 3) {
            // ID 3 no hace nada acá, ya que cambia emoción por rotación
            return;
        }

        if (!mapaEmociones.containsKey(idObjeto)) {
            reproducirSonido("/sonidos/error.mp3");
            System.out.println("ID no reconocido: " + idObjeto);
            return;
        }

        String emocionEsperada = mapaEmociones.get(idObjeto);
        int indice = idObjeto - 6; // del 6 al 11 → índice 0 a 5

        if (emocionActual.equals(emocionEsperada)) {
            if (!this._emociones[indice]) {
                this._emociones[indice] = true;
                imagenV.mostrarIconoEmocion(idObjeto, true);
                this._videos.reproducirVideoEmocion(emocionEsperada);
                System.out.println("Correcto: " + emocionEsperada);
                reproducirSonido("/sonidos/correcto.mp3");
            }
        } else {
            System.out.println("Emoción actual: " + emocionActual + ", se esperaba: " + emocionEsperada);
            reproducirSonido("/sonidos/error.mp3");
        }
    }
*/
    private void reproducirSonido(String ruta) {
        AudioClip sonido = new AudioClip(getClass().getResource(ruta).toExternalForm());
        sonido.play();
    }
    

}