package listaCompra;

/**
 * ******************************************************************************
 ** Form generated from reading ui file 'listaCompra.jui' * * Created by: Qt
 * User Interface Compiler version 4.8.6 * * WARNING! All changes made in this
 * file will be lost when recompiling ui file!
 *******************************************************************************
 */
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que genera cuadro de diálogo con un QWidgetList para mostrar los
 * elementos contenidos en la tabla de lista de la compra
 *
 * @author Daniel Paz Lorenzo
 */
public class ListaCompra implements com.trolltech.qt.QUiForm<QDialog> {

    public QListWidget listWidget;

    public ListaCompra() {
        super();
    }

    /**
     * Método donde se configuran y definen los elementos y signal/slots de la
     * clase
     *
     * @param Dialog
     * @param pantallaPrincipal
     */
    public void setupUi(QDialog Dialog, PantallaPrincipal pantallaPrincipal) {
        Dialog.setObjectName("Dialog");
        Dialog.resize(new QSize(682, 526).expandedTo(Dialog.minimumSizeHint()));
        listWidget = new QListWidget(Dialog);
        listWidget.setObjectName("listWidget");
        listWidget.setGeometry(new QRect(10, 10, 661, 501));
        retranslateUi(Dialog);
        //Llamada a método que imprimirá los datos
        imprimirDatosTabla(pantallaPrincipal);

        Dialog.connectSlotsByName();
    } // setupUi

    /**
     * Método para definir el diálogo de los elementos para multidioma
     *
     * @param Dialog
     */
    void retranslateUi(QDialog Dialog) {
        Dialog.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Impresión lista de la compra", null));

    } // retranslateUi

    @Override
    public void setupUi(QDialog t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método que recoge los datos de la tabla contenida en la clase
     * pantallaPrincipal y los escribe en el QWidgetList
     * @param pantallaPrincipal 
     */
    private void imprimirDatosTabla(PantallaPrincipal pantallaPrincipal) {
        //Definimos una lista de items
         List<String> listaItem;
        QTableWidgetItem item = new QTableWidgetItem();

        //Recorremos la tabla para racoger los valores y a la vez los va escribiendo
        for (int i = 0; i < pantallaPrincipal.tablaProducto.rowCount(); i++) {
            listaItem = new ArrayList<>();
            for (int j = 1; j < pantallaPrincipal.tablaProducto.columnCount(); j++) {
                item = pantallaPrincipal.tablaProducto.item(i, j);
                listaItem.add(item.text());
            }

            listWidget.addItem("Producto " + (i + 1) + " - " + "Cantidad: " + listaItem.get(0) + "\tNombre: " + listaItem.get(1) + "\tSección: " + listaItem.get(2) + "\t" + listaItem.get(3));
        }
    }

}
