package listaCompra;

/**
 * ******************************************************************************
 ** Form generated from reading ui file 'AnadirProducto.jui' * * Created by: Qt
 * User Interface Compiler version 4.8.6 * * WARNING! All changes made in this
 * file will be lost when recompiling ui file!
 * ******************************************************************************
 */
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

/**
 * Clase que hereda de QDialog y que implementa la interfaz del cuadro de
 * diálogo para añadir productos a la lista de la compra junto con los métodos
 * geter de los atributos de los campos
 *
 * @author Daniel Paz Lorenzo
 */
public class AddProduct extends QDialog {

    public QFrame frame;
    public QWidget widget;
    public QGridLayout gridLayout;
    public QLabel label_Cantidad;
    public QLabel label_Nombre;
    public QLineEdit lineEdit_2;
    public QLabel label_Seccion;
    public QComboBox comboBox_Seccion;
    public QLabel label_Urgente;
    public QRadioButton radioButtonSi;
    public QRadioButton radioButtonNo;
    public QLineEdit lineEdit;
    public QSpacerItem horizontalSpacer;
    public QPushButton boton_addProduct;

    /**
     * Constructor que recibe como argumento el QWidget de la pantalla principal
     *
     * @param parent
     */
    public AddProduct(QMainWindow parent) {

        super(parent);

        this.setObjectName("Dialog");
        this.resize(new QSize(344, 305).expandedTo(this.minimumSizeHint()));
        //Definición del frame
        frame = new QFrame(this);
        frame.setObjectName("frame");
        frame.setGeometry(new QRect(10, 0, 361, 291));

        QPalette palette = new QPalette();
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Button, new QColor(0, 100, 100));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Light, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Midlight, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Dark, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Mid, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Text, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Base, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Window, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Shadow, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.AlternateBase, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
        palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Button, new QColor(0, 100, 100));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Light, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Midlight, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Dark, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Mid, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Text, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Base, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Window, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Shadow, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.AlternateBase, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
        palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.WindowText, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Button, new QColor(0, 100, 100));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Light, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Midlight, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Dark, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Mid, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Text, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.ButtonText, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Base, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Window, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Shadow, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.AlternateBase, new QColor(255, 255, 255));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
        palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));

        frame.setPalette(palette);
        frame.setFrameShape(QFrame.Shape.StyledPanel);
        frame.setFrameShadow(QFrame.Shadow.Raised);

        widget = new QWidget(frame);
        widget.setObjectName("widget");
        widget.setGeometry(new QRect(10, 10, 304, 281));

        gridLayout = new QGridLayout(widget);
        gridLayout.setObjectName("gridLayout");
        gridLayout.setHorizontalSpacing(40);
        gridLayout.setVerticalSpacing(7);
        //Definición de elementos del cuadro de diálogo
        label_Cantidad = new QLabel(widget);
        label_Cantidad.setObjectName("label_Cantidad");

        gridLayout.addWidget(label_Cantidad, 0, 0, 1, 2);

        label_Nombre = new QLabel(widget);
        label_Nombre.setObjectName("label_Nombre");

        gridLayout.addWidget(label_Nombre, 1, 0, 1, 2);

        lineEdit_2 = new QLineEdit(widget);
        lineEdit_2.setObjectName("lineEdit_Nombre");

        gridLayout.addWidget(lineEdit_2, 1, 1, 1, 2);

        label_Seccion = new QLabel(widget);
        label_Seccion.setObjectName("label_Seccion");

        gridLayout.addWidget(label_Seccion, 2, 0, 1, 2);

        comboBox_Seccion = new QComboBox(widget);
        comboBox_Seccion.setObjectName("comboBox_Seccion");

        gridLayout.addWidget(comboBox_Seccion, 2, 1, 1, 2);

        label_Urgente = new QLabel(widget);
        label_Urgente.setObjectName("label_Urgente");

        gridLayout.addWidget(label_Urgente, 3, 0, 1, 1);

        radioButtonSi = new QRadioButton(widget);
        QButtonGroup buttonGroup = new QButtonGroup(this);
        buttonGroup.addButton(radioButtonSi);
        radioButtonSi.setObjectName("radioButtonSi");

        gridLayout.addWidget(radioButtonSi, 3, 1, 1, 1);

        radioButtonNo = new QRadioButton(widget);
        buttonGroup.addButton(radioButtonNo);
        radioButtonNo.setObjectName("radioButtonNo");

        gridLayout.addWidget(radioButtonNo, 3, 2, 1, 1);

        lineEdit = new QLineEdit(widget);
        lineEdit.setObjectName("lineEdit_Cantidad");

        gridLayout.addWidget(lineEdit, 0, 1, 1, 1);

        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout.addItem(horizontalSpacer, 0, 2, 1, 1);

        boton_addProduct = new QPushButton(widget);
        boton_addProduct.setObjectName("boton_addProduct");

        gridLayout.addWidget(boton_addProduct, 4, 2, 1, 3);
        //Asociación de signal y slots
        label_Cantidad.setBuddy(lineEdit);
        label_Nombre.setBuddy(lineEdit_2);
        label_Seccion.setBuddy(comboBox_Seccion);
        label_Urgente.setBuddy(radioButtonSi);
        //Colocación de elementos
        QWidget.setTabOrder(lineEdit, lineEdit_2);
        QWidget.setTabOrder(lineEdit_2, comboBox_Seccion);
        QWidget.setTabOrder(comboBox_Seccion, radioButtonSi);
        QWidget.setTabOrder(radioButtonSi, radioButtonNo);
        QWidget.setTabOrder(radioButtonNo, boton_addProduct);
        retranslateUi(this);
        //Añadimos y definimos el conector al botón de de addProduct
        boton_addProduct.clicked.connect(this, "accept()");

        this.connectSlotsByName();
    }

    /**
     * Método que devuelve el String contenido del recuadro cantidad
     *
     * @return
     */
    public String getCantidad() {
        return lineEdit.text();
    }

    /**
     * Método que devuelve el String contenido en el recuadro nombre
     *
     * @return
     */
    public String getNombre() {
        return lineEdit_2.text();
    }

    /**
     * Método que devuelve el String contenido en el recuadro sección
     *
     * @return
     */
    public String getSeccion() {
        return comboBox_Seccion.currentText();
    }

    /**
     * Método que devuelve como String si es urgente o no dependiendo si está
     * checkado el radioButton si. Por defecto lo ponemos como no urgente
     *
     * @return
     */
    public String getUrgente() {
        String urgente;

        if (radioButtonSi.isChecked()) {
            urgente = "urgente";
        } else {
            urgente = "no urgente";
        }

        return urgente;
    }

    /**
     * Método para definir el diálogo de los elementos para multidioma
     *
     * @param Dialog
     */
    void retranslateUi(QDialog Dialog) {
        Dialog.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Añadir productos", null));
        label_Cantidad.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "&Cantidad", null));
        label_Nombre.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "&Nombre", null));
        lineEdit_2.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "<html><head/><body><p>nombre del producto</p></body></html>", null));
        label_Seccion.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "&Seccion", null));
        comboBox_Seccion.clear();
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Panader\u00eda", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Pescader\u00eda", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Fruter\u00eda", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Carnicer\u00eda", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Charcuter\u00eda", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Conservas", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Perfumer\u00eda", null));
        comboBox_Seccion.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "General", null));
        comboBox_Seccion.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "<html><head/><body><p>a que seccion pertenece el articulo</p></body></html>", null));
        label_Urgente.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "&Urgente", null));
        radioButtonSi.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Si", null));
        radioButtonNo.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "No", null));
        lineEdit.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "<html><head/><body><p>cantidad de productos</p></body></html>", null));
        boton_addProduct.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "<html><head/><body><p>a\u00f1adir el producto a la lista de la compra</p></body></html>", null));
        boton_addProduct.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "A\u00f1adir Producto", null));
    } // retranslateUi

}
