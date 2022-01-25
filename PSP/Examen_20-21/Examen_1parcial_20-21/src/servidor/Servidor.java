/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pauda
 */
public class Servidor extends Thread {

    private static int PUERTO = 2000;
    public Socket socketCliente;
    String nombreFichero = "refranes.txt";
    String refranGuiones;
    int numLetrasRefran;
    public String refranes[] = {"A quien madruga Dios le ayuda",
        "Cría cuervos y te sacarán los ojos",
        "Quien a buen árbol se arrima buena sombra le cobija",
        "A caballo regalado no le mires el diente",
        "El casado casa quiere"};

    public Servidor() {

    }

    public Servidor(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    public static void main(String[] args) {
//        Servidor servidor = new Servidor();
//        servidor.generarRefranAleatorio();
        try {

            ServerSocket serverSocket = new ServerSocket(PUERTO);

            Socket socketCliente = serverSocket.accept();

            new Servidor(socketCliente).start();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        FicheroRefranes ficheroRefranes = new FicheroRefranes(this.nombreFichero);
//        String refranAleatorio = generarRefranAleatorio();
        String refranAleatorio = ficheroRefranes.refran;
        int cont = 0;
        boolean adivinado=false;
        System.out.println(refranAleatorio);
        this.numLetrasRefran = sustituirLetrasXGuiones(refranAleatorio);
        System.out.println("letras que contiene el refrán "+ numLetrasRefran);
        try {
            DataInputStream flujo_entrada = new DataInputStream(socketCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(socketCliente.getOutputStream());

            flujo_salida.writeUTF(refranGuiones);
            do {

                char letra = flujo_entrada.readChar();
                cont = comprobarLetra(letra, refranAleatorio, refranGuiones,cont);

                flujo_salida.writeUTF(refranGuiones);
                if (cont==numLetrasRefran) {
                    adivinado=true;
                }
                flujo_salida.writeBoolean(adivinado);
            } while (cont < numLetrasRefran);


        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public String generarRefranAleatorio() {
//
//        int numAleatorio = (int) Math.floor(Math.random() * refranes.length);
//
//        String refran = refranes[numAleatorio];
//        System.out.println(refran);
//
//        return refran;
//    }

    public int sustituirLetrasXGuiones(String refran) {
        int num = 0;

        for (int i = 0; i < refran.length(); i++) {
            if (refran.charAt(i) == ' ') {
                this.refranGuiones += " ";
            } else {
                this.refranGuiones += "-";
                num++;
            }
        }
        System.out.println(refranGuiones);
        return num;
    }

    private int comprobarLetra(char letra, String refranAleatorio, String refranGuiones, int cont) {
        StringBuilder cadenaAux = new StringBuilder(refranGuiones);

        System.out.println(letra);
        for (int i = 0; i < refranAleatorio.length(); i++) {
            if (refranAleatorio.charAt(i) == letra) {
                System.out.println("encontrada letra " + letra + " en posicion " + i);

                cadenaAux.setCharAt(i, letra);
                cont++;
                System.out.println("Cadena auxiliar: "+cadenaAux);
            }
        }
        System.out.println("letras acertadas "+cont);
        this.refranGuiones = cadenaAux.toString();
        System.out.println(this.refranGuiones);
        return cont;
    }

}
