package examenPSP;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author AnaIglesias
 */
public class Servidor {
    
    private Refranes refranes;// recurso compartido
    public Servidor(){
        this.refranes = new Refranes();
    }

    public Refranes getRefranes() {
        return refranes;
    }
    
    public static void main(String[] args) {
        
        //String [] refranes = new String[6];
        
        int nCliente = 0;
        ServerSocket servidor;
        Socket socket;
        Servidor miServidor = new Servidor();
    
        /*//creamos los refranes
        refranes[0]= "Mas vale tarde que nunca";
        refranes[1]= "no por mucho madrugar amanece mas temprano";
        refranes[2]= "mas vale prevenir que lamentar";
        refranes[3]= "mas vale pajaro en mano que ciento volando";
        refranes[4]= "al que madruga dios le ayuda";
        refranes[5]= "se cree el ladron que todos son de su condicion";*/
        
       try {
            servidor = new ServerSocket(2000);
            System.out.println("Servidor iniciado...");
            
           
            while(true){
                //aceptamos un cliente
                socket = servidor.accept();
                
                //informar del número de cliente conectado
                nCliente++;
                System.out.println("Cliente "+nCliente+" conectado...");
                
                //atendemos los clientes
                ServidorHilos atenderCliente = new ServidorHilos(nCliente,socket,miServidor.getRefranes());
                atenderCliente.start();
                
            }
            
        } catch (IOException ex) {
            System.out.println("Error al iniciar el servidor");
        }
    }
  
}
