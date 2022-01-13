/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 *
 * @author DANILOR
 */
public class TextosMenu extends JPanel {
    
    public TextosMenu(){
        
        JLabel etiqueta = new JLabel("Introduce un número");        
        add(etiqueta);
        
        JTextField campoTexto = new JTextField(5);
        
        Document miCuadroTexto =  campoTexto.getDocument();
        
        EscuchaTexto eventoEscucha = new EscuchaTexto();
        
        miCuadroTexto.addDocumentListener(eventoEscucha);        
        add(campoTexto);
        
        JButton boton = new JButton("Más Números");
        
        JButton boton1 = new JButton("Ordenar números");
        
        add(boton);
        add(boton1);
        
        
    }
        
    private class EscuchaTexto implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            System.out.println("Has insertado texto");
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            System.out.println("Has borrado texto");
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            System.out.println("Has modificado el texto");
        }
        
    }
}
