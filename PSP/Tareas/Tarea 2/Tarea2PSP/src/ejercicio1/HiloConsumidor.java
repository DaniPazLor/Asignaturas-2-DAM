package ejercicio1;

/**
 * Clase que hereda Thread para poder ejecutarla como hilo que va recoger 15
 * caracteres del buffer mediante la llamada a método decrementarBuffer
 *
 * @author Daniel Paz Lorenzo
 */
public class HiloConsumidor extends Thread {

    BufferCaracteres bc = new BufferCaracteres();

    /**
     * Constructor de la clase, transfiere el parámetro recibido a la variable
     * local
     *
     * @param bc instancia de la clase BufferCaracteres
     */
    public HiloConsumidor(BufferCaracteres bc) {
        this.bc = bc;
    }

    /**
     * Interfaz run() de la clase Thread en la que definimos la ejecución del
     * hilo. Llama 15 veces al método decrementarBuffer de la clase
     * BufferCaracteres
     */
    @Override
    public void run() {

        for (int i = 0; i < 15; i++) {
            bc.decrementaBuffer();
        }

    }

}
