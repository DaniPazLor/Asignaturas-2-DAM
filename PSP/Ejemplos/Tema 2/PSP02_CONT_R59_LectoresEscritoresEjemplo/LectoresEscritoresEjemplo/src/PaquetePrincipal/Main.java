package PaquetePrincipal;

/******************************************************************************
 * pone 10 lectores a leer y 3 escritores a escribir sobre los mismo datos.
 * Utiliza un objeto de la clase Semáforo para que todas esas acciones se
 * realicen coherentemente
 *
 * @author IMCG
 */
public class Main {

    /**************************************************************************
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Semaforo smfro = new Semaforo();
        //semáforo de control

        //pone 5 lectores a leer y 2 escritores a escribir, controlados por
        //el mismo semáforo
        for (int i = 1; i <= 5; i++) {
            new HiloLector("Lector" + i, smfro).start();
        }

        for (int i = 1; i <= 2; i++) {
            new HiloEscritor("Escritor" + i, smfro).start();
        }
    }

}
