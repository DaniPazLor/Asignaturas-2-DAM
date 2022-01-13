/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author DANILOR
 */
public class MenuEjercicio1 extends JFrame{
    
    public MenuEjercicio1(){
        Toolkit miPantalla= Toolkit.getDefaultToolkit();
        Dimension tamanoMiPantalla= miPantalla.getScreenSize();
        setBounds(300,300,tamanoMiPantalla.width/4,tamanoMiPantalla.height/4);
        //setBounds(300,300,300,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplicación para ordenar números");
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        TextosMenu tMenu= new TextosMenu();
        
        add(tMenu);        
        
    }   
}
