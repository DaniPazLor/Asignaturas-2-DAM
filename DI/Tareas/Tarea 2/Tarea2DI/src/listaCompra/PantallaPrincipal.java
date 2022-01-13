package listaCompra;

/**
 * ******************************************************************************
 ** Form generated from reading ui file 'PantallaPrincipal.jui' * * Created by:
 * Qt User Interface Compiler version 4.8.6 * * WARNING! All changes made in
 * this file will be lost when recompiling ui file!
 *******************************************************************************
 */
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

/**
 * Clase en la que se define toda la interfaz del menú principal del programa.
 * Contiene los signal/slots de cada botón junto a su método correspondiente. 
 * Hereda de la clase QMainWindow
 *
 * @author Daniel Paz Lorenzo
 */
public class PantallaPrincipal extends QMainWindow {

    //Declaración de atributos de clase
    public QWidget centralwidget;
    public QFrame frame;
    public QWidget layoutWidget;
    public QGridLayout gridLayout;
    public QPushButton botonAdd;
    public QTableWidget tablaProducto;
    public QPushButton botonBorrrarTodo;
    public QPushButton botonBorrarSel;
    public QPushButton botonImprimir;
    public QMenuBar menubar;
    public QStatusBar statusbar;
    public QCheckBox checkBoxProducto;
    public QTableWidgetItem chechItem;
    public QPalette palette;

    public int rowCount;

    public PantallaPrincipal() {
        super();
    }

    /**
     * Método que define como será la estructura de la clase cuando va a ser
     * instanciada
     *
     * @param MainWindow
     */
    public void setupUi(QMainWindow MainWindow) {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(820, 566).expandedTo(MainWindow.minimumSizeHint()));

        //Definición de los elementos
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        frame = new QFrame(centralwidget);
        frame.setObjectName("frame");
        frame.setGeometry(new QRect(30, 10, 771, 501));
        //Definición de la paleta de colores
        palette = new QPalette();
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
        this.palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Button, new QColor(0, 100, 100));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Light, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Midlight, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Dark, new QColor(127, 127, 127));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Mid, new QColor(170, 170, 170));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Text, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Base, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Window, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.AlternateBase, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Button, new QColor(0, 100, 100));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Light, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Midlight, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Dark, new QColor(127, 127, 127));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Mid, new QColor(170, 170, 170));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Text, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Base, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Window, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.AlternateBase, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.WindowText, new QColor(127, 127, 127));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Button, new QColor(0, 100, 100));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Light, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Midlight, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Dark, new QColor(127, 127, 127));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Mid, new QColor(170, 170, 170));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Text, new QColor(127, 127, 127));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.ButtonText, new QColor(127, 127, 127));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Base, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Window, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.AlternateBase, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
        frame.setPalette(palette);

        frame.setFrameShape(QFrame.Shape.NoFrame);
        frame.setFrameShadow(QFrame.Shadow.Plain);
        //Definición de layout
        layoutWidget = new QWidget(frame);
        layoutWidget.setObjectName("layoutWidget");
        layoutWidget.setGeometry(new QRect(0, 0, 771, 501));
        gridLayout = new QGridLayout(layoutWidget);
        gridLayout.setObjectName("gridLayout");
        gridLayout.setSizeConstraint(QLayout.SizeConstraint.SetNoConstraint);
        gridLayout.setHorizontalSpacing(40);
        gridLayout.setVerticalSpacing(100);
        gridLayout.setContentsMargins(-1, -1, 50, -1);
        botonAdd = new QPushButton(layoutWidget);
        botonAdd.setObjectName("botonAdd");

        gridLayout.addWidget(botonAdd, 0, 1, 1, 1);

        tablaProducto = new QTableWidget(layoutWidget);
        tablaProducto.setObjectName("tablaProducto");
        tablaProducto.setColumnCount(5);

        gridLayout.addWidget(tablaProducto, 0, 0, 4, 1);

        botonBorrrarTodo = new QPushButton(layoutWidget);
        botonBorrrarTodo.setObjectName("botonBorrrarTodo");

        gridLayout.addWidget(botonBorrrarTodo, 1, 1, 1, 1);

        botonBorrarSel = new QPushButton(layoutWidget);
        botonBorrarSel.setObjectName("botonBorrarSel");

        gridLayout.addWidget(botonBorrarSel, 2, 1, 1, 1);

        botonImprimir = new QPushButton(layoutWidget);
        botonImprimir.setObjectName("botonImprimir");

        gridLayout.addWidget(botonImprimir, 3, 1, 1, 1);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 820, 26));
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);
        QWidget.setTabOrder(tablaProducto, botonAdd);
        QWidget.setTabOrder(botonAdd, botonBorrrarTodo);
        QWidget.setTabOrder(botonBorrrarTodo, botonBorrarSel);
        QWidget.setTabOrder(botonBorrarSel, botonImprimir);
        retranslateUi(MainWindow);

        //Añadimos y definimos las acciones de los 4 botones del menú
        botonAdd.clicked.connect(this, "addProduct()");
        botonBorrrarTodo.clicked.connect(this, "borrarTodaTabla()");
        botonBorrarSel.clicked.connect(this, "borrarProductosSel()");
        botonImprimir.clicked.connect(this, "imprimirLista()");

        MainWindow.connectSlotsByName();
    } // setupUi

    /**
     * Método para definir el diálogo de los elementos para multidioma
     *
     * @param Dialog
     */
    void retranslateUi(QMainWindow MainWindow) {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Menú Lista de la compra", null));
        botonAdd.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "<html><head/><body><p>a\u00f1adir producto</p></body></html>", null));
        botonAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "&A\u00f1adir", null));
        tablaProducto.clear();
        tablaProducto.setColumnCount(5);

        QTableWidgetItem __colItem = new QTableWidgetItem();
        __colItem.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Seleccion", null));
        tablaProducto.setHorizontalHeaderItem(0, __colItem);

        QTableWidgetItem __colItem1 = new QTableWidgetItem();
        __colItem1.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Cantidad", null));
        tablaProducto.setHorizontalHeaderItem(1, __colItem1);

        QTableWidgetItem __colItem2 = new QTableWidgetItem();
        __colItem2.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Nombre", null));
        tablaProducto.setHorizontalHeaderItem(2, __colItem2);

        QTableWidgetItem __colItem3 = new QTableWidgetItem();
        tablaProducto.setColumnWidth(3, 150);
        __colItem3.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Secci\u00f3n Super", null));
        tablaProducto.setHorizontalHeaderItem(3, __colItem3);

        QTableWidgetItem __colItem4 = new QTableWidgetItem();
        __colItem4.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Urgente", null));
        tablaProducto.setHorizontalHeaderItem(4, __colItem4);
        tablaProducto.setRowCount(0);
        tablaProducto.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "<html><head/><body><p>lista de la compra</p></body></html>", null));
        botonBorrrarTodo.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "<html><head/><body><p>borrar todo el contenido tabla</p></body></html>", null));
        botonBorrrarTodo.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "&Borrar todo", null));
        botonBorrarSel.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "<html><head/><body><p>borrar productos seleccionados</p></body></html>", null));
        botonBorrarSel.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Borrar &Seleccion", null));
        botonImprimir.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "<html><head/><body><p>imprimir lista de la compra</p></body></html>", null));
        botonImprimir.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "&Imprimir", null));
    } // retranslateUi

    /**
     * Método para abrir la ventana diálogo y añadir a la tabla los datos del
     * producto
     */
    public void addProduct() {
        //Instanciamos la clase addProduct
        AddProduct addProduct = new AddProduct(this);
        //Si recibimos la aceptación de la ventan dialog añadimos producto a la lista
        if (addProduct.exec() == QDialog.DialogCode.Accepted.value()) {

            rowCount = tablaProducto.rowCount();
            tablaProducto.insertRow(rowCount);
            chechItem = new QTableWidgetItem();
            chechItem.setCheckState(Qt.CheckState.Unchecked);
            tablaProducto.setItem(rowCount, 0, chechItem);
            tablaProducto.setItem(rowCount, 1, new QTableWidgetItem(addProduct.getCantidad()));
            tablaProducto.setItem(rowCount, 2, new QTableWidgetItem(addProduct.getNombre()));
            tablaProducto.setItem(rowCount, 3, new QTableWidgetItem(addProduct.getSeccion()));
            tablaProducto.setItem(rowCount, 4, new QTableWidgetItem(addProduct.getUrgente()));
        }
    }

    /**
     * Método para borrar todos los productos de la tabla
     */
    public void borrarTodaTabla() {
        //Se posiciona en la primera fila
        tablaProducto.setRowCount(0);
        //Recorre con un for todas las filas de la tabla y las borra
        for (int i = 0; i < tablaProducto.rowCount(); i++) {
            tablaProducto.removeRow(i);
        }
    }

    /**
     * Método para borrar los productos seleccionados mediante check de la
     * primera columna de la tabla
     */
    public void borrarProductosSel() {

        QTableWidgetItem item;
        for (int i = 0; i < tablaProducto.rowCount(); i++) {
            //Recogemmos en item la primera columna de todas las filas
            item = tablaProducto.item(i, 0);
            //Comprobamos si está checkada y si es así la borra
            if (item.checkState() == Qt.CheckState.Checked) {
                tablaProducto.removeRow(i);
                i--;//Retrocedemos una poosición porque la anterior fué borrada
            }
        }
    }

    /**
     * Método que abrirá un cuadro de diálogo donde se mostrará la lista de la
     * compra
     */
    public void imprimirLista() {
        ListaCompra listaCompra = new ListaCompra();
        QDialog dialogList = new QDialog();
        listaCompra.setupUi(dialogList, this);
        dialogList.show();
    }
}
