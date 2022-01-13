package ejercicio2;

import java.util.concurrent.Semaphore;

/**
 * Clase principal que contiene el método main()
 *
 * @author Daniel Paz Lorenzo
 */
public class Main {

    /**
     * En esta clase vamos a instanciar y ejecutar los 5 hilos, uno por cada
     * filósofo, de la aplicación. A cada constructor le pasaremos los
     * parámetros correspondientes
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int miIndice;
        //Creamos los 5 semáforos, uno por cada palillo, dando permiso de único hilo
        Semaphore[] semaforoPalillo = new Semaphore[]{new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1)};
        //Definimos en una matriz los palillos que le corresponden a cada filósofo
        int[][] palillosFilosofo = new int[][]{{0, 4}, {1, 0}, {2, 1}, {3, 2}, {4, 3}};

        //Creamos y ejecutamos los hilos correspondientes a los 5 filósofos
        miIndice = 0;
        Filosofo filosofo_1 = new Filosofo(miIndice, semaforoPalillo, palillosFilosofo);
        filosofo_1.start();

        miIndice = 1;
        Filosofo filosofo_2 = new Filosofo(miIndice, semaforoPalillo, palillosFilosofo);
        filosofo_2.start();

        miIndice = 2;
        Filosofo filosofo_3 = new Filosofo(miIndice, semaforoPalillo, palillosFilosofo);
        filosofo_3.start();

        miIndice = 3;
        Filosofo filosofo_4 = new Filosofo(miIndice, semaforoPalillo, palillosFilosofo);
        filosofo_4.start();

        miIndice = 4;
        Filosofo filosofo_5 = new Filosofo(miIndice, semaforoPalillo, palillosFilosofo);
        filosofo_5.start();
    }

}
