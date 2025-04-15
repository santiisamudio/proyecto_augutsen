package proyecto_augutsen;

import TUIO.*;
import javafx.scene.layout.Pane;

    public class ClienteTuio {
    private TuioClient _cliente;
    private Pane _contenedor;
    
    private AplicacionMain _main;

    public ClienteTuio(int puerto,Pane contenedor,AplicacionMain main) {
        this._cliente = new TuioClient(puerto);
        this._contenedor = contenedor;
        this._main=main;
        ;
        
    }

    public void start(){
        
    	
        ListenerTuio listener = new ListenerTuio(_contenedor);//se crea el listener que manejara los eventos de los simbolos fiduciales y se le pasa el contenedor pane
        this._cliente.addTuioListener(listener);//se agrega al cliente el tuiolistener       
        this._cliente.connect();//se conecta al servidor 
        System.out.println("conectado ");
        
    }
    
    public void stop(){
        this._cliente.disconnect();//se desconecta del servidor
    }
    //

    
}
