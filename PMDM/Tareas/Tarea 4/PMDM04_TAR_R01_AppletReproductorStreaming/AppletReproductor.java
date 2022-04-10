

import java.applet.Applet;
import java.awt.*;
import java.net.MalformedURLException;
import java.io.IOException;
import javax.media.*;

/**
  * Applet de java para probar el Streaming
  * 
  * <!-- Incrustar en el codigo HTML
  *<applet code="AppletReproductor" width="320" height="300">
  *	<param name="rtp" value="rtp://<host>:<puerto>/audio" />	
  *</applet> 
  * -->
  */


public class AppletReproductor extends Applet implements ControllerListener {
    
    
    // Elementos graficos
    // ...

    // Elementos multimedia
    // ...
    
    public void init() {
        
        // Obtencion del parametro rtp del applet para crear el reproductor.
        // ...

        // Creacion del reproductor 
        // ...
        
        // Aspectos esteticos del applet
        // ...
    }
    

    /**
     * Funcionamiento del applet
     */

    public void start() {
        // Para iniciar el reproductor.
    }

    public void stop() {
	//Para detener la reproduccion y liberar los recursos antes de dejar la pagina
    }

    public void destroy() {
	// Antes de destruir el applet cerramos el reproductor
    }


    /**
     * Metodo controllerUpdate
     * Sera llamado cada vez que se produzca un evento multimedia
     */
    public synchronized void controllerUpdate(ControllerEvent event) {
	

        // El reproductor ya se encuentra en estado Realized:
        //   - Obtenemos los componentes visuales.
        //   - Se los añadimos al applet
        if (event instanceof RealizeCompleteEvent) {
            
            
            
        } else if (event instanceof EndOfMediaEvent) {
	    // Hemos llegado al final de la reproduccion.
	    // Rebobinamos y comenzamos de nuevo.

        
        } else if (event instanceof ControllerErrorEvent) {
	    // Se ha producido un error

        
        } else if (event instanceof ControllerClosedEvent) {

        
        
        }
    }


}
