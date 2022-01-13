package listaCompra;

import com.trolltech.qt.gui.*;

/**
 *Clase que contiene el main con la que se iniciará la aplicación
 * @author Daniel Paz Lorenzo
 */
public class Inicio {

    /**
     * Método principal donde abrimos una ventana instanciando una QMainWindow 
     * de la clases pantallaPrincipal
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Inizializamos QT
        QApplication.initialize(args);
        //Instanciamos QMainWindow de la pantallaPrincipal
        QMainWindow mw = new QMainWindow();      
        PantallaPrincipal pantallaPrincipal =new PantallaPrincipal();
        pantallaPrincipal.setupUi(mw);        
        mw.show();     
        
        QApplication.execStatic();
        QApplication.shutdown();
    }
    
}
