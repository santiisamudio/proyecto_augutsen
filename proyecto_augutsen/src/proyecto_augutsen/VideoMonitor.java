package proyecto_augutsen;

import java.net.URL;
import javafx.util.Duration;
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
	private Stage _primerMonitor = null;
	private Stage _segundoMonitor = null;
	private MediaPlayer reproductorPrimerMonitor= null;
	private MediaPlayer reproductor= null;
	private MediaView mediaViewPrimerMonitor= null;
	private MediaView mediaMiewSegundoMonitor= null;
	public VideoMonitor() {
		
	}
	public void reproducirVideoEmocion(String emocion) {
		switch(emocion) {
		case "miedo": 
			this.iniciarVideoEnSegundoMonitor("/videos/02e2_miedo.mp4");
		
		break;
		case "sorpresa":
			this.iniciarVideoEnSegundoMonitor("/videos/02e7_Hmm.mp4");
		break;
		
		case "alegria":
			this.iniciarVideoEnSegundoMonitor("/videos/02e6_alegria.mp4");
		break;
		
		case "tristeza":
			this.iniciarVideoEnSegundoMonitor("/videos/02e3_tristeza.mp4");
		break;
		
		case "ira":
			this.iniciarVideoEnSegundoMonitor("/videos/02e1_ira.mp4");
		break;
		
		case "desagrado":
			this.iniciarVideoEnSegundoMonitor("/videos/02e4_asco.mp4");
		break;
		}
	}
	/*
	public void iniciarVideoEnSegundoMonitor(String urlVideo) {
	    Platform.runLater(() -> {
	        var monitores = Screen.getScreens();
	        if (monitores.size() ==3 ) {
	            System.out.println("Se necesitan al menos 2 monitores");
	            return;
	        }

	        Screen segundoMonitor = monitores.get(1);
	        Rectangle2D dimensiones = segundoMonitor.getBounds();

	        if (_segundoMonitor == null) {
	        	_segundoMonitor = new Stage();
	        	_segundoMonitor.setTitle("Segundo Monitor");
	        	_segundoMonitor.setX(dimensiones.getMinX());
	        	_segundoMonitor.setY(dimensiones.getMinY());
	        	_segundoMonitor.setWidth(dimensiones.getWidth());
	        	_segundoMonitor.setHeight(dimensiones.getHeight());
	        }

	        // Si ya hay un reproductor, detener y liberar recursos
	        if (reproductor != null) {
	        	reproductor.stop();
	            reproductor.dispose();
	        }

	        // Crear nuevo MediaPlayer para el video
	        URL videoURL = getClass().getResource(urlVideo);
	        if (videoURL == null) {
	            System.out.println("Video no encontrado: " + urlVideo);
	            return;
	        }
	        Media media = new Media(videoURL.toExternalForm());
	        reproductor = new MediaPlayer(media);

	        if (mediaMiewSegundoMonitor == null) {
	        	mediaMiewSegundoMonitor = new MediaView(reproductor);
	            StackPane root = new StackPane(mediaMiewSegundoMonitor);
	            Scene scene = new Scene(root);
	            _segundoMonitor.setScene(scene);
	        } else {
	            // Actualizar mediaView con el nuevo reproductor
	        	mediaMiewSegundoMonitor.setMediaPlayer(reproductor);
	        }

	        _segundoMonitor.show();

	        reproductor.setOnError(() -> {
	            System.out.println("Error al reproducir video: " + reproductor.getError());
	        });

	        reproductor.setOnEndOfMedia(() -> {
	            System.out.println("Video terminado en segundo monitor.");
	            
	        });

	        reproductor.play();
	    });
	}
*/
	
	public void iniciarSecuenciaVideos(ImagenesV imagen) {
	    Platform.runLater(() -> {
	    	var monitores = Screen.getScreens();
	    	if (monitores.size() == 1) {
	            System.out.println("Se necesitan al menos 2 monitores");
	            return;
	        }
	    	System.out.println("entra el metodo iniciarsecuenciavideo");
	        

	        Screen segundoMonitor = monitores.get(0); // carpincho
	        Screen tercerMonitor = monitores.get(1);  // interferencia + augutsen

	        // --------- Video 1: Cucarachón ----------
	        URL url1 = getClass().getResource("/videos/AutgutsenPresentacion.mp4");
	        if (url1 == null) {
	            System.err.println("Error: video 1 no encontrado.");
	            return;
	        }

	        Media media1 = new Media(url1.toExternalForm());
	        media1.setOnError(() -> System.err.println("Error en media1: " + media1.getError()));
	        MediaPlayer player1 = new MediaPlayer(media1);
	        player1.setOnError(() -> System.err.println("Error en player1: " + player1.getError()));
	        player1.statusProperty().addListener((obs, oldStatus, newStatus) -> 
	            System.out.println("Estado player1: " + newStatus));

	        MediaView view1 = new MediaView(player1);
	        Stage stage1 = new Stage();
	        stage1.setTitle("Cucarachón");
	        stage1.setScene(new Scene(new StackPane(view1)));
	        Rectangle2D dim1 = tercerMonitor.getBounds();
	        stage1.setX(dim1.getMinX());
	        stage1.setY(dim1.getMinY());
	        stage1.setWidth(dim1.getWidth());
	        stage1.setHeight(dim1.getHeight());
	        stage1.setFullScreen(true);
	        
	        // --------- Video 2: Interferencia ----------
	        URL url2 = getClass().getResource("/videos/interferencia.mp4");
	        if (url2 == null) {
	            System.err.println("Error: video 2 no encontrado.");
	            return;
	        }
	        
	        
	        Media media2 = new Media(url2.toExternalForm());
	        media2.setOnError(() -> {
	            System.err.println("Error en media2: " + media2.getError());
	            if (media2.getError() != null) {
	                media2.getError().printStackTrace();
	            }
	        });

	        MediaPlayer player2 = new MediaPlayer(media2);
	        player2.setOnError(() -> {
	            System.err.println("Error en player2: " + player2.getError());
	            if (player2.getError() != null) {
	                player2.getError().printStackTrace();
	            }
	        });
	        
	        
	        

	        MediaView view2 = new MediaView(player2);
	        view2.setFitWidth(1280); // Por ejemplo, Full HD width
	        view2.setFitHeight(720); // Por ejemplo, HD height
	        view2.setPreserveRatio(true); // Mantiene proporción original
	        Stage stage2 = new Stage();
	        stage2.setTitle("Interferencia");
	        stage2.setScene(new Scene(new StackPane(view2)));
	        Rectangle2D dim2 = segundoMonitor.getBounds();
	        stage2.setX(dim2.getMinX());
	        stage2.setY(dim2.getMinY());
	        stage2.setWidth(dim2.getWidth());
	        stage2.setHeight(dim2.getHeight());
	        stage2.setFullScreen(true);

	        // --------- Video 3: Carpincho ----------
	        URL url3 = getClass().getResource("/videos/carpinchoFInal.mp4");
	        if (url3 == null) {
	            System.err.println("Error: video 3 no encontrado.");
	            return;
	        }

	        Media media3 = new Media(url3.toExternalForm());
	        media3.setOnError(() -> System.err.println("Error en media3: " + media3.getError()));
	        MediaPlayer player3 = new MediaPlayer(media3);
	        player3.setOnError(() -> System.err.println("Error en player3: " + player3.getError()));
	        player3.statusProperty().addListener((obs, oldStatus, newStatus) -> 
	            System.out.println("Estado player3: " + newStatus));

	        MediaView view3 = new MediaView(player3);
	        Stage stage3 = new Stage();
	        stage3.setTitle("Carpincho");
	        stage3.setScene(new Scene(new StackPane(view3)));
	        Rectangle2D dim3 = segundoMonitor.getBounds();
	        stage3.setX(dim3.getMinX());
	        stage3.setY(dim3.getMinY());
	        stage3.setWidth(dim3.getWidth());
	        stage3.setHeight(dim3.getHeight());
	        stage3.setFullScreen(true);
	        // --------- Encadenamiento ---------

	        player1.setOnEndOfMedia(() -> {
	            //stage1.close();
	          //  imagen.mostrarImagenEnSegundoMonitor();
	        	player1.dispose();
	        	stage1.close();
	            stage2.show();
	            System.out.println("termino video 1");
	            System.out.println("estado player 2" + player2.getStatus());
	            
	            
	            if (player2.getStatus() == MediaPlayer.Status.READY) {
	                System.out.println("Video 2 ya estaba listo, reproduciendo.");
	                player2.play();
	            } else {
	                player2.setOnReady(() -> {
	                    System.out.println("Video 2 listo, reproduciendo.");
	                    player2.play();
	                });
	            }
	        });

	        player2.setOnEndOfMedia(() -> {
	        	
	            stage2.close();
	            
	            player2.stop();
	            player2.dispose();
	            
	            stage3.show();
	            
	            System.out.println("termino video 2");
	            System.out.println("estado player 3" + player3.getStatus());

	            if (player3.getStatus() == MediaPlayer.Status.READY) {
	                System.out.println("Video 3 ya estaba listo, reproduciendo.");
	                player3.play();
	            } else {
	                player3.setOnReady(() -> {
	                    System.out.println("Video 3 listo, reproduciendo.");
	                    player3.play();
	                });
	            }
	        });

	        player3.setOnEndOfMedia(() -> {
	            stage3.close();
	            System.out.println("Secuencia finalizada.");
	        });

	        // --------- Iniciar la secuencia ---------
	        stage1.show();
	        player1.play();
	    });
	}



public void iniciarVideoEnSegundoMonitor(String urlVideo) {
	    Platform.runLater(() -> {
	        var monitores = Screen.getScreens();
	        if (monitores.size() == 1) {
	            System.out.println("Se necesitan al menos 2 monitores");
	            return;
	        }
	        
	        Screen segundoMonitor = monitores.get(1);
	        Rectangle2D dimensiones = segundoMonitor.getBounds();

	        if (_segundoMonitor == null) {
	        	_segundoMonitor = new Stage();
	        	_segundoMonitor.setTitle("Segundo Monitor");
	        	_segundoMonitor.setX(dimensiones.getMinX());
	        	_segundoMonitor.setY(dimensiones.getMinY());
	        	_segundoMonitor.setWidth(dimensiones.getWidth());
	        	_segundoMonitor.setHeight(dimensiones.getHeight());
	        	_segundoMonitor.setFullScreen(true);
	        }

	        // Si ya hay un reproductor, detener y liberar recursos
	        if (reproductor != null) {
	        	reproductor.stop();
	            reproductor.dispose();
	        }

	        // Crear nuevo MediaPlayer para el video que queremos reproducir
	        URL videoURL = getClass().getResource(urlVideo);
	        if (videoURL == null) {
	            System.out.println("Video no encontrado: " + urlVideo);
	            return;
	        }
	        Media media = new Media(videoURL.toExternalForm());
	        reproductor = new MediaPlayer(media);

	        if (mediaMiewSegundoMonitor == null) {
	        	mediaMiewSegundoMonitor = new MediaView(reproductor);
	            StackPane root = new StackPane(mediaMiewSegundoMonitor);
	            Scene scene = new Scene(root);
	            _segundoMonitor.setScene(scene);
	        } else {
	            // Actualizar mediaView con el nuevo reproductor
	        	mediaMiewSegundoMonitor.setMediaPlayer(reproductor);
	        }

	       // _segundoMonitor.show();

	        reproductor.setOnError(() -> {
	            System.out.println("Error al reproducir video: " + reproductor.getError());
	        });

	        reproductor.setOnEndOfMedia(() -> {
	            System.out.println("Video terminado en segundo monitor.");
	            
	        });

	        reproductor.setOnReady(() -> {
	        	_segundoMonitor.show();
	            reproductor.play();
	        });
	    });
	}
	
	/*

public void iniciarSecuenciaVideos(ImagenesV imagenv) {
    Platform.runLater(() -> {
        var monitores = Screen.getScreens();
        if (monitores.size() == 3) {
            System.out.println("Se necesitan 3 monitores");
            return;
        }

        Screen segundoMonitor = monitores.get(0); // carpincho
        Screen tercerMonitor = monitores.get(1);  // interferencia + augutsen

        // --------- Video 1: Cucarachón ----------
        URL url1 = getClass().getResource("/videos/01_cucarachonCuentaSuPlanMalvado.mp4");
        MediaPlayer player1 = new MediaPlayer(new Media(url1.toExternalForm()));
        MediaView view1 = new MediaView(player1);

        Stage stage1 = new Stage();
        stage1.setTitle("Cucarachón");
        stage1.setScene(new Scene(new StackPane(view1)));
        Rectangle2D dim1 = tercerMonitor.getBounds();
        stage1.setX(dim1.getMinX());
        stage1.setY(dim1.getMinY());
        stage1.setWidth(dim1.getWidth());
        stage1.setHeight(dim1.getHeight());
        

        // --------- Video 2: Interferencia ----------
        URL url2 = getClass().getResource("/videos/interferencia.mp4");
        MediaPlayer player2 = new MediaPlayer(new Media(url2.toExternalForm()));
        MediaView view2 = new MediaView(player2);
        
        view2.setFitWidth(1280); // Por ejemplo, Full HD width
        view2.setFitHeight(720); // Por ejemplo, HD height
        view2.setPreserveRatio(true); // Mantiene proporción original
        Stage stage2 = new Stage();
        stage2.setTitle("Interferencia");
        stage2.setScene(new Scene(new StackPane(view2)));
        Rectangle2D dim2 = segundoMonitor.getBounds();
        stage2.setX(dim2.getMinX());
        stage2.setY(dim2.getMinY());
        stage2.setWidth(dim2.getWidth());
        stage2.setHeight(dim2.getHeight());

        // --------- Video 3: Carpincho ----------
        URL url3 = getClass().getResource("/videos/carpinchoFInal.mp4");
        MediaPlayer player3 = new MediaPlayer(new Media(url3.toExternalForm()));
        MediaView view3 = new MediaView(player3);

        Stage stage3 = new Stage();
        stage3.setTitle("Carpincho");
        
        Rectangle2D dim3 = segundoMonitor.getBounds();
        stage3.setScene(new Scene(new StackPane(view3)));
        stage3.setX(dim3.getMinX());  
        stage3.setY(dim3.getMinY());
        stage3.setWidth(dim3.getWidth());
        stage3.setHeight(dim3.getHeight());

        // ENCADENAR VIDEOS
     
        player2.setOnReady(() -> {
            
        });

        
        player3.setOnReady(() -> {
            
        });

        player1.setOnEndOfMedia(() -> {
            stage1.close();
            imagenv.AsignarImagenAugutsenNormal();
            stage2.show();
            player2.play();
        });

        player2.setOnEndOfMedia(() -> {
            stage2.close();
            if (player2 != null) {
            	player2.stop();
            	player2.dispose();
	        }
            stage3.show();
            player3.play();
        });

        player3.setOnEndOfMedia(() -> {
            stage3.close();
            System.out.println("Secuencia finalizada.");
        });

        // comenzar la secuencia
        stage1.show();
        player1.play();
        
    });
    
    
}
 
	
	*/


}