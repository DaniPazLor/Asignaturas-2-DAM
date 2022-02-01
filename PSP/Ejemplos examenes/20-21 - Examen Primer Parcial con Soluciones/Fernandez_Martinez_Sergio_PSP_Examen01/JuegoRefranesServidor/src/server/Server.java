/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import juego.Juego;

/**
 *
 * @author sergio
 */
public class Server extends Thread {
    private Socket socket;
    private Juego juego;

    public Server(Socket socket, Juego juego) {
        this.socket = socket;
        this.juego = juego;
    }
    
    @Override
    public void run() {
        InputStream instream = null;
        OutputStream outstream = null;
        
        Boolean terminar = false;
        int comando = 0;
        
        try {
            outstream = socket.getOutputStream();
            instream = socket.getInputStream();
            
            DataOutputStream mensajeServer = new DataOutputStream(outstream);
            DataInputStream respuestaCliente = new DataInputStream(instream);
            
            // Enviamos un mensaje indicando la entrada en el juego
            mensajeServer.writeUTF("Ya he decidido mi refrán.");
            mensajeServer.writeUTF("Vidas restantes: " + this.juego.getVidas());
            mensajeServer.writeUTF(this.juego.getPista());
            System.out.println(this.juego.getRefranElegido());
            
            do {     
                // Imprimimos el menú de comandos
                imprimirComandos(mensajeServer);
                
                // Recibimos la respuesta del cliente que esta jugando
                comando = respuestaCliente.readInt();
                
                // Ejecutamos la lógica en caso de querer probar una letra
                if (comando == 1) {
                    probarLetra(mensajeServer, respuestaCliente);
                }
                
                // Ejecutamos la lógica si el usuario desea acertar el juego
                if (comando == 2) {
                    terminar = adivinarRefran(mensajeServer, respuestaCliente);
                }
                
                // Comprobamos si el usuario se ha quedado sin vidas sin acertar
                if (this.juego.getVidas() <= 0 && !terminar) {
                    mensajeServer.writeUTF("No te quedan más vidas");
                    
                    terminar = true;
                }
            } while (!terminar);
            
            
            // Cerramos la conexion con el cliente
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                instream.close();
                outstream.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void imprimirComandos(DataOutputStream mensajeServer) {
        try {
            mensajeServer.writeUTF("Indica lo que deseas realizar:");
            mensajeServer.writeUTF("    1 - Probar una letra");
            mensajeServer.writeUTF("    2 - Adivinar el refrán");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // función en caso de que se elija el comando de probar una letra
    private void probarLetra(DataOutputStream mensajeServer, DataInputStream respuestaCliente) {
        String letra;
        
        try {    
            mensajeServer.writeUTF("Indica la letra con la que quieres probar:");
            letra = respuestaCliente.readUTF();
            if (this.juego.comprobarLetra(letra.charAt(0)) > 0) {
                mensajeServer.writeUTF("Letra encontrada.");
                
                this.juego.setPista(letra.charAt(0));
                
                mensajeServer.writeUTF(this.juego.getPista());
            } else {
                mensajeServer.writeUTF("Letra no encontrada.");
                this.juego.setVidas(this.juego.getVidas() - 1);
                mensajeServer.writeUTF("Vidas restantes: " + this.juego.getVidas());
            }
            
            System.out.println(this.juego.comprobarLetra(letra.charAt(0)));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // función en caso de que se quiera adivinar el refran
    private Boolean adivinarRefran(DataOutputStream mensajeServer, DataInputStream respuestaCliente) {
        String refran;
        
        try {    
            mensajeServer.writeUTF("Indica el refrán con el que quieres probar:");
            refran = respuestaCliente.readUTF();
            
            if (this.juego.adivinarRefran(refran)) {
                mensajeServer.writeUTF("Has acertado!");
                this.juego.setVidas(this.juego.getVidas() - 1);
                mensajeServer.writeUTF("Vidas restantes: " + this.juego.getVidas());
                
                return true;
            } else {
                mensajeServer.writeUTF("Has fallado.");
                this.juego.setVidas(this.juego.getVidas() - 1);
                mensajeServer.writeUTF("Vidas restantes: " + this.juego.getVidas());
                
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
