package pruebaMatriculaBean;

/**
 * Clase principal
 *
 * @author Daniel Paz Lorenzo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {

        AccedeBD gestion = new AccedeBD();

        //Llamada a métodos
        gestion.listado();
        gestion.anade();
        gestion.listarPorDNI("96385274f");

    }

}
