/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pauda
 */
public class Servidor extends Thread {

    private static int PUERTO = 2000;
    public Socket socketCliente;
    public String refranes[] = {"A quien madruga Dios le ayuda",
         "Cría cuervos y te sacarán los ojos",
         "Quien a buen árbol se arrima buena sombra le cobija",
         "A caballo regalado no le mires el diente",
         "El casado casa quiere"};

    public Servidor(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    public static void main(String[] args) {
        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(PUERTO);

                Socket socketCliente = serverSocket.accept();

                new Servidor(socketCliente).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        mostrarRefranAleatorio();
        

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.socketCliente.getOutputStream()));

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mostrarRefranAleatorio() {
        
        sustituirLetrasXGuiones();
    }

    public void sustituirLetrasXGuiones() {

    }

}
