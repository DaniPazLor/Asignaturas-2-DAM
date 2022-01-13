package ejercicio1;

/**
 * Clase que contiene el método principal o main()
 *
 * @author Daniel Paz Lorenzo
 */
public class PrincipalProductorConsumidor {

    /**
     * En el método principal creamos una instancia de la clase BufferCaracteres
     * que luego pasaremos como parámetro tanto al hilo productor como al hilo
     * consumidor que vamos a instanciar a continuación. Ejecutamos los hilos
     * mediante el método star()
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        BufferCaracteres bc = new BufferCaracteres();

        HiloProductor productor = new HiloProductor(bc);
        productor.start();

        HiloConsumidor consumidor = new HiloConsumidor(bc);
        consumidor.start();

    }

}
