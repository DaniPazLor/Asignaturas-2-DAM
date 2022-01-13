package ejercicio1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que nos va a servir como buffer de 6 caracteres compartido entre el
 * hilo productor y el hilo consumidor por medio de los métodos incrementar y
 * decrementar
 *
 * @author Daniel Paz Lorenzo
 */
public class BufferCaracteres {

    char[] bufferCaracteres = new char[6];//Vector de 6 caracteres que hace de buffer
    public int indice;

    /**
     * Constructor de la clase que inicializa el valor de indice a 1
     */
    public BufferCaracteres() {
        indice = 0;
    }

    /**
     * Método monitor protegido para la concurrencia de datos compartidos entre
     * hilos que deposita un carácter aleatorio en el buffer de izquierda a
     * derecha mientras no esté lleno
     */
    public synchronized void incrementaBuffer() {

        Random numAleatorio = new Random();
        while (indice == 6) {//Cuando está lleno espera que se vacie el buffer 
            try {
                System.out.println("El buffer está lleno, esperando a que hilo CONSUMIDOR recoja caracteres");
                wait();//Bloqueamos el hilo hasta que se deje de cumplir la condición del bucle
            } catch (InterruptedException ex) {
                Logger.getLogger(BufferCaracteres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bufferCaracteres[indice] = (char) numAleatorio.nextInt(65, 90);
        System.out.println("DEPOSITADO carácter " + bufferCaracteres[indice] + " de la posición " + (indice) + " del buffer");  
        indice++;
        notify();
    }

    /**
     * Método monitor protegido para la concurrencia de datos compartidos entre
     * hilos que recoge un carácter aleatorio del buffer de derecha a izquierda
     * mientras no esté vacío
     */
    public synchronized void decrementaBuffer() {

        while (indice == 0) {//Espera a que tenga algún caracter   
            try {
                System.out.println("El buffer está vacío, esperando a que hilo PRODUCTOR rellene con caracteres");
                wait();//Bloqueamos el hilo hasta que se deje de cumplir la condición del bucle
            } catch (InterruptedException ex) {
                Logger.getLogger(BufferCaracteres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        indice--;
        System.out.println("RECOGIDO carácter " + bufferCaracteres[indice] + " de la posición " + (indice) + " del buffer");
        bufferCaracteres[indice] = '\u0000';//Una vez recogido el carácter le asignamos valor 0 para volver a rellenar
        notify();//Desbloqueamos el acceso compartido para que puedan entrar otros hilos
    }

}
