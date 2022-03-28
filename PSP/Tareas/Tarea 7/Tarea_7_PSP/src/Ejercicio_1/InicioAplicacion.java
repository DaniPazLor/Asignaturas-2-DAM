/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_1;

import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author Pauda
 */
public class InicioAplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String usuario = JOptionPane.showInputDialog(null, "Introduzca nombre de usuario", "USUARIO", JOptionPane.INFORMATION_MESSAGE);
        String password = JOptionPane.showInputDialog(null, "Introduzca password", "PASSWORD", JOptionPane.WARNING_MESSAGE);
        
        FormularioAplicacion formApli = new FormularioAplicacion(usuario,password);
        formApli.setVisible(true);
    }
    
}
