package videojava;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
/**
 * @web http://jc-mouse.blogspot.com/
 * @author mouse
 */
public class videopanel extends javax.swing.JPanel {
private Player mediaPlayer;
private Component controls;        
    
    public videopanel(URL mediaURL, Dimension d){
        Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
         try
            {
         // crea un reproductor especificando la direccion del archivo
         mediaPlayer = Manager.createRealizedPlayer( mediaURL );         
         // optiene los componentes del video
         Component video2 = mediaPlayer.getVisualComponent();
         video2.setSize(d.width, d.height);         
         video2.setVisible(true);
         //optiene los componentes de control del reproductor
         controls = mediaPlayer.getControlPanelComponent();
         controls.setSize(d.width, 24);
         
         if ( video2 != null )            
            add( video2 ); //agrega el componente del reproductor al panel 
         
         mediaPlayer.start(); // comienza la reproduccion
      } // fin try
      catch ( NoPlayerException noPlayerException )
      {
         System.err.println( "No se encontro medio disponible, osea no se reproduce archivo" );
      } // fin catch
      catch ( CannotRealizeException cannotRealizeException )
      {
         System.err.println( "no se pudo realizar el reproductor" );
      } // fin catch
      catch ( IOException iOException )
      {
         System.err.println( "Error de lectura del archivo" );
      } // fin catch
    }
    
    public Component controles (){     
        return controls;        
    }
    
    public Component controles(Dimension d){
        controls.setSize(d);
        return controls;
    }
    
  
}
