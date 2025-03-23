package proyecto_augutsen;

import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.input.KeyCombination;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
/**
 *
 * @author santi
 */
public class AplicacionMain extends Application{
	private MediaPlayer reproductor;
	private Stage video;

    public void start(Stage primaryStage) {
        Pane _contenedor = new Pane();//el contenedor se va a encargar de mostrar la UI 
        
        ClienteTuio cliente = new ClienteTuio(3333,_contenedor,this);//se configura el puerto 3333 al contenedor pane y se crea una instancia de cliente
        cliente.start();//se inicia conexion con el servidor TUIO
        double ancho = Screen.getPrimary().getBounds().getWidth();
        double alto = Screen.getPrimary().getBounds().getHeight();

        Scene escena = new Scene(_contenedor, ancho, alto);//se crea la ventana y establecen sus dimensiones
        primaryStage.setScene(escena);
        primaryStage.setTitle("TUIO");
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();//se muestra la ventana
        System.out.println("Resolución detectada: " + alto + "x" + ancho);
        
    }
    
    public void iniciarVideo() {
    	Platform.runLater(() -> {
            if (video == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(1);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    video = new Stage();
                    video.setTitle("Video");

                    String link = "/videos/lirili.mp4";
                    URL videoURL = getClass().getResource(link);
                    Media media = new Media(videoURL.toExternalForm());
                    reproductor = new MediaPlayer(media);
                    MediaView mediaView = new MediaView(reproductor);

                    StackPane videoRoot = new StackPane(mediaView);
                    Scene videoScene = new Scene(videoRoot);
                    video.setScene(videoScene);

                    video.setX(dimensiones.getMinX());
                    video.setY(dimensiones.getMinY());
                    video.setWidth(dimensiones.getWidth());
                    video.setHeight(dimensiones.getHeight());

                    reproductor.setAutoPlay(true);
                    video.show();
                } else {
                    System.out.println("Solo hay un monitor disponible");
                }
            } else {
            	reproductor.play(); 
            }
        });
    }
    
    
    public static void main(String[] args) {
        
        
        launch(args);//inicia el programa en launch y se llama al metodo start
    }
}
