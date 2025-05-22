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
	/* private void iniciarv(String rutaVideo, boolean esSegundoMonitor) {
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
	 */
	 public void iniciarVideoInterferencia() {
		 
			Platform.runLater(() -> {
	    		if (this._primerMonitor == null) {
	                var monitores = Screen.getScreens();
	                if (monitores.size() > 1) {
	                    Screen segundoMonitor = monitores.get(2);
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



		public void carpincho() {
	    	Platform.runLater(() -> {
	    		if (this._primerMonitor == null) {
	                var monitores = Screen.getScreens();
	                if (monitores.size() > 1) {
	                    Screen segundoMonitor = monitores.get(2);
	                    Rectangle2D dimensiones = segundoMonitor.getBounds();

	                    this._primerMonitor = new Stage();
	                    this._primerMonitor.setTitle("Video");

	                    String link = "/videos/carpinchoFInal.mp4";
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

public void iniciarSecuenciaVideos() {
    Platform.runLater(() -> {
        var monitores = Screen.getScreens();
        if (monitores.size() < 3) {
            System.out.println("Se necesitan al menos 3 monitores");
            return;
        }

        Screen segundoMonitor = monitores.get(1); // cucarachon
        Screen tercerMonitor = monitores.get(2);  // interferencia + carpincho

        // --------- Video 1: Cucarachón ----------
        URL url1 = getClass().getResource("/videos/01_cucarachonCuentaSuPlanMalvado.mp4");
        MediaPlayer player1 = new MediaPlayer(new Media(url1.toExternalForm()));
        MediaView view1 = new MediaView(player1);

        Stage stage1 = new Stage();
        stage1.setTitle("Cucarachón");
        stage1.setScene(new Scene(new StackPane(view1)));
        Rectangle2D dim1 = segundoMonitor.getBounds();
        stage1.setX(dim1.getMinX());
        stage1.setY(dim1.getMinY());
        stage1.setWidth(dim1.getWidth());
        stage1.setHeight(dim1.getHeight());
        stage1.show();

        // --------- Video 2: Interferencia ----------
        URL url2 = getClass().getResource("/videos/video interferencia.mp4");
        MediaPlayer player2 = new MediaPlayer(new Media(url2.toExternalForm()));
        MediaView view2 = new MediaView(player2);

        Stage stage2 = new Stage();
        stage2.setTitle("Interferencia");
        stage2.setScene(new Scene(new StackPane(view2)));
        Rectangle2D dim2 = tercerMonitor.getBounds();
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
        stage3.setScene(new Scene(new StackPane(view3)));
        stage3.setX(dim2.getMinX());  // mismo que el de interferencia
        stage3.setY(dim2.getMinY());
        stage3.setWidth(dim2.getWidth());
        stage3.setHeight(dim2.getHeight());

        // ENCADENAR VIDEOS
        player1.setOnEndOfMedia(() -> {
            stage1.close();
            stage2.show();
            player2.play();
        });

        player2.setOnEndOfMedia(() -> {
            stage2.close();
            stage3.show();
            player3.play();
        });

        player3.setOnEndOfMedia(() -> {
            stage3.close();
            System.out.println("Secuencia finalizada.");
        });

        // Comenzar la secuencia
        player1.play();
    });
}
}