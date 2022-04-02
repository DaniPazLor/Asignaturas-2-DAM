import java.io.InputStreamReader;
import java.io.BufferedReader;


import javax.media.Player;
import javax.media.Manager;
import java.net.URL;

public class ReproductorAudio {
    
    
    public static void main(String[] args) {
        String nombreArchivo=null;
        Player reproductorJMF= null;
                
        // Archivo de prueba incluido en el proyecto
        nombreArchivo= "src/Wonderwall.wav";
                
        // Creacion del reproductor
        System.out.println ("Cargando fuente de datos...");
        try {
            // Creaación de la URL a partir de un archivo
            URL url= new URL ("file", null, nombreArchivo);
            
            // Creación del reproductor (Player)
            reproductorJMF= Manager.createRealizedPlayer(url);
            
        } catch (Exception e) {
            System.out.println ("No se ha podido crear el Player: " + e);
            System.exit(1);
        }
        
        // Reproducción de la fuente con la que se ha alimentado el Player
        System.out.println ("Reproduccion del archivo de audio...");
        reproductorJMF.start();        
               
        // Espera a la pulsacion de una tecla
        System.out.println ("Pulse una tecla para terminar...");
        try {
            pulsacionTecla ();
        } catch (Exception e) {
            System.out.println (e.toString());
        }

        // Cerramos el reproductor
        System.out.println ("Cerrando reproductor...");
        reproductorJMF.stop ();
        reproductorJMF.close();
        System.out.println ("Fin...");
        
    }
    
    
    //---------------------------------------------------------------        
    // Metodo pulsacionTecla: Captura de un tecla de teclado
    //---------------------------------------------------------------      
    private static int pulsacionTecla () throws Exception {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            int c = reader.read();
            return c;
        }
        catch (Exception e) {
            throw e;
        }
    }       
    
	private static void salidaSinArgumento ()
	{
            System.out.println("Hay que pasar como argumento un archivo de audio.");
            System.exit(1);
	}    
    
}