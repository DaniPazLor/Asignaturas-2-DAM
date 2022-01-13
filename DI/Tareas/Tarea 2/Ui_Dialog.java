/********************************************************************************
 ** Form generated from reading ui file 'AddDialog.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_Dialog implements com.trolltech.qt.QUiForm<QDialog>
{
    public QLabel label;
    public QLabel label_2;
    public QLabel label_3;
    public QLineEdit lineEdit;
    public QLineEdit lineEdit_2;
    public QComboBox comboBox;
    public QGroupBox groupBox;
    public QRadioButton radioButton;
    public QRadioButton radioButton_2;

    public Ui_Dialog() { super(); }

    public void setupUi(QDialog Dialog)
    {
        Dialog.setObjectName("Dialog");
        Dialog.resize(new QSize(459, 376).expandedTo(Dialog.minimumSizeHint()));
        label = new QLabel(Dialog);
        label.setObjectName("label");
        label.setGeometry(new QRect(50, 50, 53, 16));
        label_2 = new QLabel(Dialog);
        label_2.setObjectName("label_2");
        label_2.setGeometry(new QRect(50, 110, 53, 16));
        label_3 = new QLabel(Dialog);
        label_3.setObjectName("label_3");
        label_3.setGeometry(new QRect(50, 180, 53, 16));
        lineEdit = new QLineEdit(Dialog);
        lineEdit.setObjectName("lineEdit");
        lineEdit.setGeometry(new QRect(180, 50, 113, 22));
        lineEdit_2 = new QLineEdit(Dialog);
        lineEdit_2.setObjectName("lineEdit_2");
        lineEdit_2.setGeometry(new QRect(180, 120, 113, 22));
        comboBox = new QComboBox(Dialog);
        comboBox.setObjectName("comboBox");
        comboBox.setGeometry(new QRect(180, 180, 121, 22));
        groupBox = new QGroupBox(Dialog);
        groupBox.setObjectName("groupBox");
        groupBox.setGeometry(new QRect(80, 240, 231, 51));
        radioButton = new QRadioButton(groupBox);
        radioButton.setObjectName("radioButton");
        radioButton.setGeometry(new QRect(10, 20, 95, 20));
        radioButton_2 = new QRadioButton(groupBox);
        radioButton_2.setObjectName("radioButton_2");
        radioButton_2.setGeometry(new QRect(120, 20, 95, 20));
        retranslateUi(Dialog);
        label.linkActivated.connect(lineEdit, "setText(QString)");
        label_2.linkActivated.connect(lineEdit_2, "setText(QString)");
        label_3.linkActivated.connect(comboBox, "setEditText(QString)");

        Dialog.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog Dialog)
    {
        Dialog.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Dialog", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "&Cantidad", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "&Nombre", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "&Seccion", null));
        comboBox.clear();
        comboBox.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Panader\u00eda", null));
        comboBox.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Pescader\u00eda", null));
        comboBox.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Fruter\u00eda", null));
        comboBox.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Carnicer\u00eda", null));
        comboBox.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Charcuter\u00eda", null));
        comboBox.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Conservas", null));
        comboBox.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Perfumer\u00eda", null));
        comboBox.addItem(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "General", null));
        groupBox.setTitle(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "&Urgente", null));
        radioButton.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "Si", null));
        radioButton_2.setText(com.trolltech.qt.core.QCoreApplication.translate("Dialog", "No", null));
    } // retranslateUi

}

