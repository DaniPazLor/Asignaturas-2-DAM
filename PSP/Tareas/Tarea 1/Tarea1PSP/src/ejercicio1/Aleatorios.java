package ejercicio1;

import java.util.*;

/**
 * Aplicación que genera una cantidad (pasada como argumento en línea de
 * comandos) de números aleatorios del 1 al 100 y los manda a la salida estandar
 *
 * @author Daniel Paz Lorenzo
 */
public class Aleatorios {

    /**
     * Método principal de la aplicación
     *
     * @param args
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //Creación de variables y objetos
        Random numAleatorio = new Random();
        int cantidadNumeros = Integer.parseInt(args[0]);//Recoge el primer argumento dado por el usuario

        //Bucle para generar los números aleatorios
        for (int i = 0; i < cantidadNumeros; i++) {
            int num = numAleatorio.nextInt(100);//Genera números aleatorios de 1 a 100
            System.out.println(num);//Se muestran por la salida estandar
        }

    }

}
