package ejercicio2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase hereda de Thread para convertirse en un hilo mediante la
 * definición de la interfaz run().Implementa las variables locales, el
 * constructor de la misma y los métodos comer() y pensar()
 *
 * @author Daniel Paz Lorenzo
 */
public class Filosofo extends Thread {

    //Definimos las variables de la clase
    int miIndice;
    Semaphore[] semaforoPalillo;
    int[][] palillosFilosofo;

    /**
     * Constructor de la clase que asigna a las variables locales los parámetros
     * que recibe
     *
     * @param miIndice Identifica el hilo, en este caso se corresponde a cada
     * uno de los 5 filósofos
     * @param semaforoPalillo Vector de 5 Semaforos uno para cada palillo
     * @param palillosFilosofo Matriz que define el par de palillos para cada
     * filósofo
     */
    public Filosofo(int miIndice, Semaphore[] semaforoPalillo, int[][] palillosFilosofo) {

        this.miIndice = miIndice;
        this.semaforoPalillo = semaforoPalillo;
        this.palillosFilosofo = palillosFilosofo;
    }

    /**
     * Método en el que se define como tiene que actuar cada filósofo para
     * adquirir los palillos para comer. Como estos son un recurso compartido
     * vamos a sincronizar su acceso mediante la clase Semaphore
     */
    public void comer() {

        System.out.println("Filósofo " + (miIndice + 1) + " está HAMBRIENTO");
        try {
            //Trata de conseguir los palillos que necesita
            semaforoPalillo[palillosFilosofo[miIndice][0]].acquire();
            semaforoPalillo[palillosFilosofo[miIndice][1]].acquire();

            System.out.println("Filósofo " + (miIndice + 1) + " está COMIENDO con los palillos " + (palillosFilosofo[miIndice][0] + 1) + " y " + (palillosFilosofo[miIndice][1] + 1));

            sleep(1000);//Cuando consigue los palillos come y duerme hilo 1 segundo

        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Filósofo " + (miIndice + 1) + " ha TERMINADO DE COMER y soltado los palillos " + (palillosFilosofo[miIndice][0] + 1) + " y " + (palillosFilosofo[miIndice][1] + 1));
        //El filósofo libera los palillos después de haber comido
        semaforoPalillo[palillosFilosofo[miIndice][0]].release();
        semaforoPalillo[palillosFilosofo[miIndice][1]].release();

    }

    /**
     * Método para mostrar por pantalla que el filósofo está pensando y al que
     * le hemos añadido una parada de 1 segundo para que sea más visual
     */
    public void pensar() {

        System.out.println("Filosofo " + (miIndice + 1) + " está PENSANDO");
        try {
            sleep(1000);//Duerme el hilo durante 1 segundo
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método sobreescrito de la clase Thread que ejecuta los métodos pensar() y
     * comer() en un bucle infinito
     */
    @Override
    public void run() {
        
        while (true) {
            pensar();
            comer();
        }
    }
}
