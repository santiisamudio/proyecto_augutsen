package proyecto_augutsen;

import java.net.URL;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VideoMonitor {
	private Stage _primerMonitor;
	private Stage _segundoMonitor;
	private MediaPlayer reproductor;
	
	public VideoMonitor() {
		
	}
	
	
	
	
	public void iniciarVideoProyector() {
    	Platform.runLater(() -> {
            if (this._segundoMonitor == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(1);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    this._segundoMonitor = new Stage();
                    this._segundoMonitor.setTitle("Video");

                    String link = "/videos/lirili.mp4";
                    URL videoURL = getClass().getResource(link);
                    Media media = new Media(videoURL.toExternalForm());
                    reproductor = new MediaPlayer(media);
                    MediaView mediaView = new MediaView(reproductor);

                    StackPane videoRoot = new StackPane(mediaView);
                    Scene videoScene = new Scene(videoRoot);
                    this._segundoMonitor.setScene(videoScene);

                    this._segundoMonitor.setX(dimensiones.getMinX());
                    this._segundoMonitor.setY(dimensiones.getMinY());
                    this._segundoMonitor.setWidth(dimensiones.getWidth());
                    this._segundoMonitor.setHeight(dimensiones.getHeight());

                    reproductor.setAutoPlay(true);
                    this._segundoMonitor.show();
                } else {
                    System.out.println("Solo hay un monitor disponible");
                    
                }
            } else {
            	reproductor.play(); 
            }
        });
    }
	
	public void iniciarVideoInterferencia() {
    	Platform.runLater(() -> {
            if (this._primerMonitor == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(0);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    this._primerMonitor = new Stage();
                    this._primerMonitor.setTitle("Video");

                    String link = "/videos/video interferencia.mp4";
                    URL videoURL = getClass().getResource(link);
                    Media media = new Media(videoURL.toExternalForm());
                    reproductor = new MediaPlayer(media);
                    MediaView mediaView = new MediaView(reproductor);

                    StackPane videoRoot = new StackPane(mediaView);
                    Scene videoScene = new Scene(videoRoot);
                    this._primerMonitor.setScene(videoScene);

                    this._primerMonitor.setX(dimensiones.getMinX());
                    this._primerMonitor.setY(dimensiones.getMinY());
                    this._primerMonitor.setWidth(dimensiones.getWidth());
                    this._primerMonitor.setHeight(dimensiones.getHeight());

                    reproductor.setAutoPlay(true);
                    this._primerMonitor.show();
                    
                    reproductor.setOnEndOfMedia(() -> {
                        this._primerMonitor.close();
                        System.out.println("Video finalizado. Ventana cerrada.");
                        
                    });
                } else {
                    System.out.println("Solo hay un monitor disponible");
                    
                }
            } else {
            	reproductor.play(); 
            }
        });
    }
}
