/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.io.*;
import java.util.*;

/**
 *
 * @author DANILOR
 */
public class OrdenarNumeros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Declaración de variables y objetos
        System.getProperty("java.classpath");
    ArrayList<Integer> cadenaNumAleatorios = new ArrayList();
        BufferedReader  stdin = new BufferedReader(new InputStreamReader(System.in));
        
        stdin.read();
        
        System.out.println("Programa para introducir un número indeterminado de números y ordenarlos");
         //Ordenar la cadena de números ascendente
        Collections.sort(cadenaNumAleatorios);

        //Mostrar la cadena de números ordenada
        System.out.println("Cadena ordenada");
        for (int num:cadenaNumAleatorios) {
            System.out.println(num);
        }

    }

}
