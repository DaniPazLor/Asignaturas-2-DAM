/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1.salidaDatos;
    

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author DANILOR
 */
public class SalidaDatos {
    
    /**
     * Ordena cadena de números y los muestra por pantalla
     * @param cadena 
     */
    public void ordenarArrayNumeros(ArrayList<Integer> cadena){
        
        //Ordenar la cadena de números ascendente
        Collections.sort(cadena);

        //Mostrar la cadena de números ordenada
        System.out.println("Cadena ordenada");
        for (int num:cadena) {
            System.out.println(num);
        }
    }
}
