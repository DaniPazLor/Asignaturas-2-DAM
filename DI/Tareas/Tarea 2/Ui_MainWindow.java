/********************************************************************************
 ** Form generated from reading ui file 'MainWindow.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_MainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QFrame frame;
    public QPushButton btonAdd;
    public QPushButton b_BorrrarT;
    public QPushButton botonBorrarS;
    public QPushButton botonImprimir;
    public QTableWidget tablaProducto;
    public QMenuBar menubar;
    public QStatusBar statusbar;

    public Ui_MainWindow() { super(); }

    public void setupUi(QMainWindow MainWindow)
    {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(800, 600).expandedTo(MainWindow.minimumSizeHint()));
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        frame = new QFrame(centralwidget);
        frame.setObjectName("frame");
        frame.setGeometry(new QRect(20, 10, 751, 531));
        frame.setFrameShape(QFrame::StyledPanel);
        frame.setFrameShadow(QFrame::Raised);
        btonAdd = new QPushButton(frame);
        btonAdd.setObjectName("btonAdd");
        btonAdd.setGeometry(new QRect(600, 50, 93, 28));
        b_BorrrarT = new QPushButton(frame);
        b_BorrrarT.setObjectName("b_BorrrarT");
        b_BorrrarT.setGeometry(new QRect(600, 150, 93, 28));
        botonBorrarS = new QPushButton(frame);
        botonBorrarS.setObjectName("botonBorrarS");
        botonBorrarS.setGeometry(new QRect(582, 230, 121, 28));
        botonImprimir = new QPushButton(frame);
        botonImprimir.setObjectName("botonImprimir");
        botonImprimir.setGeometry(new QRect(600, 360, 93, 28));
        tablaProducto = new QTableWidget(frame);
        tablaProducto.setObjectName("tablaProducto");
        tablaProducto.setGeometry(new QRect(20, 10, 521, 491));
        tablaProducto.setColumnCount(5);
        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 800, 26));
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);
        retranslateUi(MainWindow);

        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "MainWindow", null));
        btonAdd.setToolTip(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "<html><head/><body><p>a\u00f1adir producto</p></body></html>", null));
        btonAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "&A\u00f1adir", null));
        b_BorrrarT.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "&Borrar todo", null));
        botonBorrarS.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "&Borrar Seleccion", null));
        botonImprimir.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "&Imprimir", null));
        tablaProducto.clear();
        tablaProducto.setColumnCount(5);
        tablaProducto.setRowCount(0);
    } // retranslateUi

}

