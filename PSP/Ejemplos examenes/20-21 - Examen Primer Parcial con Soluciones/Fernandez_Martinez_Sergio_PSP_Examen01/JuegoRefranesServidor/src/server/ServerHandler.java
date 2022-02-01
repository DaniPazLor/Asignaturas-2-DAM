/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import juego.Juego;

/**
 *
 * @author sergio
 */
public class ServerHandler {
    static final int PUERTO = 2000;
    
    public ServerHandler() {
        try {
            // Iniciamos la instancia de nuestro servidor
            ServerSocket socket = new ServerSocket(PUERTO);
            
            //Iniciamos la instancia del juego común a todos los jugadores
            Juego juego = new Juego();
            
            // Creamos un bucle infinito para aceptar clientes de forma indefinida
            while(true) {
                // Aceptamos la conexion de uno de los clientes
                Socket jugador = socket.accept();

                // Iniciamos la instancia de juego con nuestro cliente conectado
                new Server(jugador, juego).start();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
