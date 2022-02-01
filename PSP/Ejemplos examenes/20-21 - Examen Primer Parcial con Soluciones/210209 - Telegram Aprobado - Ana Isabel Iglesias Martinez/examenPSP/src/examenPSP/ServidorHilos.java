package examenPSP;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author AnaIglesias
 */
public class ServidorHilos extends Thread{
    private int nCliente;
    private Socket socket;
    private DataInputStream flujo_entrada;
    private DataOutputStream flujo_salida;
    private Refranes refranes;
    
    public ServidorHilos(int nCliente, Socket socket,Refranes refranes) {
        this.nCliente = nCliente;
        this.socket = socket;
        this.refranes = refranes;
     
        try{
            //creamos los flujos de entrada y salida de datos
            flujo_entrada = new DataInputStream(socket.getInputStream());
            flujo_salida = new DataOutputStream(socket.getOutputStream());
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        
        boolean acertado = false;
        String refran, buscar, cadena = "";
        char miLetra;
        
        try {
            //enviamos al cliente el número de  Cliente
            flujo_salida.writeInt(nCliente);
            
            //elegimos el refran
            int ref = (int)(Math.random()*6);
            refran = refranes.getRefran(ref);
            System.out.println("Refran para el cliente "+nCliente+": "+refran);
           
            //enviamos los guiones que sustituyen a las letras
            for (int i=0;i<refran.length();i++){
                if (refran.charAt(i) == ' ')
                    cadena += ' ';
                else
                    cadena += '-'; 
            }
            
            flujo_salida.writeUTF(cadena);
            
            while (!acertado){
                //leemos lo que envía el cliente
                buscar = flujo_entrada.readUTF();
                System.out.println("BUSCAR: "+buscar+ " para el cliente "+nCliente);
               
                if (buscar.length()==1){
                    
                    for (int i=0;i<refran.length();i++){
                         
                        miLetra = buscar.charAt(0);
                        
                        if (refran.charAt(i) == miLetra || refran.charAt(i) == Character.toUpperCase(miLetra)){
                            //colocamos la letra en su lugar
                            cadena = cadena.substring(0, i)+miLetra+cadena.substring(i+1, refran.length());
                                
                        }
                            
                        if (cadena.equalsIgnoreCase(refran))
                            acertado = true;
                        
                    }
                    
                    //enviamos la cadena al cliente
                    flujo_salida.writeUTF(cadena);
                
                }else{
                        
                    if (buscar.equalsIgnoreCase(refran)){
                       
                        System.out.println("El cliente "+nCliente+" ha acertado");
                        
                        acertado = true;
                    }
                }
                   
                flujo_salida.writeBoolean(acertado);
               
            }
            
            //cerramos flujos, socket y servidor
            flujo_salida.close();
            flujo_entrada.close();
            socket.close();
            
            System.out.println("Cliente "+nCliente+" desconectado");
            
         } catch (IOException ex) {
            ex.printStackTrace();
        }
    }           
}
