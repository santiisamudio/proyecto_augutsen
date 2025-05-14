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
	private MediaPlayer reproductorSegundoMonitor;
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

                    String link = "/videos/01_cucarachonCuentaSuPlanMalvado.mp4";
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
	
	
	//nuevo agregado mio

	
	
	
	
	
	
	
	
	
	
	
	
	
	public void  iniciarSorpresa() {
    	Platform.runLater(() -> {
    		if (this._primerMonitor == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(1);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    this._primerMonitor = new Stage();
                    this._primerMonitor.setTitle("Video");

                    String link = "/videos/02e7_Hmm.mp4";
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
	public void  iniciarAlegria() {
    	Platform.runLater(() -> {
    		if (this._primerMonitor == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(1);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    this._primerMonitor = new Stage();
                    this._primerMonitor.setTitle("Video");

                    String link = "/videos/02e6_alegria.mp4";
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

	public void  iniciarTristeza() {
    	Platform.runLater(() -> {
    		if (this._primerMonitor == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(1);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    this._primerMonitor = new Stage();
                    this._primerMonitor.setTitle("Video");

                    String link = "/videos/02e3_tristeza.mp4";
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
	public void  iniciarIra() {
    	Platform.runLater(() -> {
    		if (this._primerMonitor == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(1);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    this._primerMonitor = new Stage();
                    this._primerMonitor.setTitle("Video");

                    String link = "/videos/02e1_ira.mp4";
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

	public void  iniciarDesagrado() {
    	Platform.runLater(() -> {
    		if (this._primerMonitor == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(1);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    this._primerMonitor = new Stage();
                    this._primerMonitor.setTitle("Video");

                    String link = "/videos/02e4_asco.mp4";
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
	public void  iniciarMiedo() {
    	Platform.runLater(() -> {
    		if (this._primerMonitor == null) {
                var monitores = Screen.getScreens();
                if (monitores.size() > 1) {
                    Screen segundoMonitor = monitores.get(1);
                    Rectangle2D dimensiones = segundoMonitor.getBounds();

                    this._primerMonitor = new Stage();
                    this._primerMonitor.setTitle("Video");

                    String link = "/videos/02e2_miedo.mp4";
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

	//probandoooo
	 private void iniciarv(String rutaVideo, boolean esSegundoMonitor) {
	        Platform.runLater(() -> {
	            var monitores = Screen.getScreens();
	            if (monitores.size() > 1 || !esSegundoMonitor) {
	                Screen monitor = esSegundoMonitor ? monitores.get(1) : monitores.get(0);
	                Rectangle2D dimensiones = monitor.getBounds();

	                Stage monitorActual = esSegundoMonitor ? this._segundoMonitor : this._primerMonitor;
	                if (monitorActual == null) {
	                    monitorActual = new Stage();
	                    monitorActual.setTitle("Video");

	                    URL videoURL = getClass().getResource(rutaVideo);
	                    if (videoURL == null) {
	                        System.out.println("Error: Video no encontrado");
	                        return;
	                    }

	                    Media media = new Media(videoURL.toExternalForm());
	                    reproductor = new MediaPlayer(media);
	                    MediaView mediaView = new MediaView(reproductor);

	                    StackPane videoRoot = new StackPane(mediaView);
	                    Scene videoScene = new Scene(videoRoot);
	                    monitorActual.setScene(videoScene);

	                    monitorActual.setX(dimensiones.getMinX());
	                    monitorActual.setY(dimensiones.getMinY());
	                    monitorActual.setWidth(dimensiones.getWidth());
	                    monitorActual.setHeight(dimensiones.getHeight());

	                    reproductor.setAutoPlay(true);
	                    monitorActual.show();

	                 

	                    if (esSegundoMonitor) {
	                        this._segundoMonitor = monitorActual;
	                    } else {
	                        this._primerMonitor = monitorActual;
	                    }
	                } else {
	                    reproductor.play(); 
	                }
	            } else {
	                System.out.println("Solo hay un monitor disponible");
	            }
	        });
	    }
	 
	 public void iniciarVideoInterferencia() {
		    Platform.runLater(() -> {
		        if (this._primerMonitor == null) {
		            Screen pantallaNotebook = Screen.getPrimary(); // Monitor principal = notebook
		            Rectangle2D dimensiones = pantallaNotebook.getBounds();

		            this._primerMonitor = new Stage();
		            this._primerMonitor.setTitle("Video Interferencia");

		            String link = "/videos/video interferencia.mp4";
		            URL videoURL = getClass().getResource(link);
		            if (videoURL == null) {
		                System.out.println("No se encontró el archivo de video: " + link);
		                return;
		            }

		            Media media = new Media(videoURL.toExternalForm());
		            reproductorSegundoMonitor = new MediaPlayer(media);
		            MediaView mediaView = new MediaView(reproductorSegundoMonitor);

		            StackPane videoRoot = new StackPane(mediaView);
		            Scene videoScene = new Scene(videoRoot);
		            this._primerMonitor.setScene(videoScene);

		            this._primerMonitor.setX(dimensiones.getMinX());
		            this._primerMonitor.setY(dimensiones.getMinY());
		            this._primerMonitor.setWidth(dimensiones.getWidth());
		            this._primerMonitor.setHeight(dimensiones.getHeight());

		            reproductorSegundoMonitor.setAutoPlay(true);
		            this._primerMonitor.show();

		            reproductorSegundoMonitor.setOnEndOfMedia(() -> {
		                this._primerMonitor.close();
		                System.out.println("Video finalizado. Ventana cerrada.");
		            });

		        } else {
		            reproductorSegundoMonitor.play(); 
		        }
		    });
		}


public void carpicnho() {
    Platform.runLater(() -> {
        if (this._primerMonitor == null) {
            Screen pantallaNotebook = Screen.getPrimary(); // Monitor principal = notebook
            Rectangle2D dimensiones = pantallaNotebook.getBounds();

            this._primerMonitor = new Stage();
            this._primerMonitor.setTitle("Video carpincho");
            String link = "/videos/video interferencia.mp4";
           // String link = "/videos/carpinchoFinal.mp4";
            URL videoURL = getClass().getResource(link);
            if (videoURL == null) {
                System.out.println("No se encontró el archivo de video: " + link);
                return;
            }

            Media media = new Media(videoURL.toExternalForm());
            reproductorSegundoMonitor = new MediaPlayer(media);
            MediaView mediaView = new MediaView(reproductorSegundoMonitor);

            StackPane videoRoot = new StackPane(mediaView);
            Scene videoScene = new Scene(videoRoot);
            this._primerMonitor.setScene(videoScene);

            this._primerMonitor.setX(dimensiones.getMinX());
            this._primerMonitor.setY(dimensiones.getMinY());
            this._primerMonitor.setWidth(dimensiones.getWidth());
            this._primerMonitor.setHeight(dimensiones.getHeight());

            reproductorSegundoMonitor.setAutoPlay(true);
            this._primerMonitor.show();

            reproductorSegundoMonitor.setOnEndOfMedia(() -> {
                this._primerMonitor.close();
                System.out.println("Video finalizado. Ventana cerrada.");
            });

        } else {
            reproductorSegundoMonitor.play(); 
        }
    });
}
}