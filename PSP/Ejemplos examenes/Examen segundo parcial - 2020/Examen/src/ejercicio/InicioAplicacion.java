package ejercicio;



import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 * Clase que contiene el método main para iniciar la aplicación. Solicita
 * usuario y contraseña y muestra formulario
 *
 * @author Daniel Paz Lorenzo
 */
public class InicioAplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //Solicita usuario y contraseña y los recoge a través de JOptionPane
        String usuario = JOptionPane.showInputDialog(null, "Introduzca nombre de usuario", "USUARIO", JOptionPane.INFORMATION_MESSAGE);
        String password = JOptionPane.showInputDialog(null, "Introduzca password", "PASSWORD", JOptionPane.WARNING_MESSAGE);
        //Crea instancia de la clase FormularioAplicacion
        FormularioAplicacion formApli = new FormularioAplicacion(usuario, password);
        formApli.setVisible(true);
    }

}
