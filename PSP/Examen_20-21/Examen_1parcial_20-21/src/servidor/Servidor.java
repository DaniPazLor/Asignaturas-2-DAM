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
    String refranGuiones="";
    String refranAleatorio="";
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
        this.refranAleatorio = ficheroRefranes.refran;
        int cont = 0;
        int adivinado=0;
        int numIntentos=0;
        int num=0;
        
        System.out.println(refranAleatorio);
        this.numLetrasRefran = sustituirLetrasXGuiones(refranAleatorio);
        System.out.println("letras que contiene el refrán "+ numLetrasRefran);
        try {
            DataInputStream flujo_entrada = new DataInputStream(socketCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(socketCliente.getOutputStream());
   
            flujo_salida.writeUTF(refranGuiones);
            do {
                num++;
                numIntentos = (numLetrasRefran+5) - num;
                 flujo_salida.writeInt(numIntentos);
                char letra = flujo_entrada.readChar();
                cont = comprobarLetra(letra, refranAleatorio, refranGuiones,cont);

                flujo_salida.writeUTF(refranGuiones);
                if (cont==numLetrasRefran) {
                    adivinado=1;
                }
                if (num==(numLetrasRefran*2)) {
                    adivinado=2;
                }
                
                flujo_salida.writeInt(adivinado);
               
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
        StringBuilder cadenaAux2 = new StringBuilder(refranAleatorio);

        System.out.println(letra);
        for (int i = 0; i < refranAleatorio.length(); i++) {
            if (refranAleatorio.charAt(i) == letra) {
                System.out.println("encontrada letra " + letra + " en posicion " + i);
                cadenaAux2.setCharAt(i, '-');
                cadenaAux.setCharAt(i, letra);
                cont++;
                System.out.println("Cadena auxiliar: "+cadenaAux);
            }
        }
        System.out.println("letras acertadas "+cont);
        this.refranAleatorio = cadenaAux2.toString();

        this.refranGuiones = cadenaAux.toString();
        System.out.println(this.refranGuiones);
        System.out.println("Refran descartes "+ refranAleatorio);
        return cont;
    }

}
