/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Cliente {
    static final String HOST = "localhost";
    static final int PUERTO = 2000;

    public Cliente() {
        try {
            // Iniciamos la instancia de nuestro cliente
            Socket jugador = new Socket(HOST, PUERTO);
            
            // Una vez aceptados procedemos a ejecutar el juego
            jugar(jugador);
            
            // Finalizado el juego cerramos la conexion
            jugador.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void jugar(Socket jugador) {
        InputStream instream = null;
        OutputStream outstream = null;
        Scanner input = new Scanner(System.in);
        
        Boolean terminar = false;
        Boolean valido = false;
        String respuesta = "";
        String primeraRespuesta = "";
        
        try {
            instream = jugador.getInputStream();
            outstream = jugador.getOutputStream();
            
            DataInputStream respuestaServer = new DataInputStream(instream);
            DataOutputStream mensajeJugador = new DataOutputStream(outstream);
            
            // Indicamos en pantalla que el juego ha comenzado
            System.out.println(respuestaServer.readUTF());
            System.out.println(respuestaServer.readUTF());
            System.out.println(respuestaServer.readUTF());
            
            do {
                // Imprimimos el menú
                System.out.println(respuestaServer.readUTF());
                System.out.println(respuestaServer.readUTF());
                System.out.println(respuestaServer.readUTF());
                
                // Si nuestro input es un integer implica que estamos enviando comandos al juego
                do {
                    if (input.hasNextInt()) {                    
                        //Enviamos nuestro comando al servidor y finalizamos la lectura de la línea (si no se hace el salto de línea acarrea problemas a la hora de finalizar el juego)
                        mensajeJugador.writeInt(input.nextInt());
                        input.nextLine();
                        valido = true;
                    } else {
                        System.out.println("Debes introducir un numero para indicar el comando");
                        input.nextLine();
                    }
                } while(!valido);
                
                
                System.out.println(respuestaServer.readUTF());
                
                // Si introducimos un string se considera una respuesta al juego
                respuesta = input.nextLine();
                mensajeJugador.writeUTF(respuesta);
                
                primeraRespuesta = respuestaServer.readUTF();
                System.out.println(primeraRespuesta);
                System.out.println(respuestaServer.readUTF());
                
                if (primeraRespuesta.equals("Has acertado!")) {
                    terminar = true;
                }
            } while(!terminar);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                instream.close();
                outstream.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
