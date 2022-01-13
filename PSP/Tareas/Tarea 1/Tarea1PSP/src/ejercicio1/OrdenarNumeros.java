package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aplicación que ordena un cadena indeterminada de números recibidos por la
 * entrada estandar
 *
 * @author Daniel Paz Lorenzo
 */
public class OrdenarNumeros {

    /**
     * @param args cadena de entrada de números
     */
    public static void main(String[] args) {
        //Declaración de array de enteros
        int numer = 0;
        ArrayList<Integer> cadenaNumAleatorios = new ArrayList();
        BufferedReader bufferEntradaEstandar = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nEstos son los números aleatorios generados por la salida estandar de la aplicación Aleatorios");
        System.out.println("-----------------------------------------------------------------------------------------------");
        try {
            while (numer != -1) {
                numer = bufferEntradaEstandar.read();
                if (numer != -1) {
                    cadenaNumAleatorios.add(numer);//Añade el número recibido a el array                    
                    System.out.print(numer + " ");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrdenarNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Este bucle nos permite probar la entrada por argumentos de la aplicación
        /*for (int i = 0; i < args.length; i++) {
            cadenaNumAleatorios.add(Integer.parseInt(args[i]));
        }*/
        
        //Ordenar la cadena de números ascendente mediante el método sort
        Collections.sort(cadenaNumAleatorios);

        //Mostrar la cadena de números ordenada
        System.out.println("\n\nCadena ordenada de los números aleatorios recibidos por la entrada estandar de la aplicación OrdenarNúmeros");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for (int num : cadenaNumAleatorios) {
            System.out.print(num + " ");
        }
    }
}
