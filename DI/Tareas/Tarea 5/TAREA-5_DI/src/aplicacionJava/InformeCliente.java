/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionJava;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;

/**
 *
 * @author DANILOR
 */
public class InformeCliente {

    public static Connection conexion = null;
    String baseDatos = "jdbc:mysql://localhost/fabrica";
    String usuario = "Dani";
    String clave = "5678";

    public InformeCliente() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(baseDatos, usuario, clave);
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Fallo al cargar JDBC");
            System.exit(1);
        } catch (SQLException sqle) {
            System.err.println("No se pudo conectar a BD");
            System.exit(1);
        } catch (java.lang.InstantiationException sqlex) {
            System.err.println("Imposible Conectar");
            System.exit(1);
        } catch (Exception ex) {
            System.err.println("Imposible Conectar");
            System.exit(1);
        }
    }
    //El método ejecutar recibe el parametro del informe

    public void ejecutar(int cliente) {
        //Ruta del informe respecto del proyecto NetBeans
        String archivojasper = "informe_cliente.jasper";
        try {
            //Cargamos los parametros del informe en una tabla Hash
            Map parametros = new HashMap();
            parametros.put("CLIENTE", cliente);
            //Generamos el informe en memoria
            JasperPrint print = JasperFillManager.fillReport(archivojasper, parametros, conexion);
            // Exporta el informe a PDF 
            JasperExportManager.exportReportToPdfFile(print, "informe.pdf");
            //Abre el archivo PDF generado
            File path = new File("informe.pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
