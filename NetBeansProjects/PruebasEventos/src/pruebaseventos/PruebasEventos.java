/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaseventos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author DANILOR
 */
public class PruebasEventos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Marco miMarco = new Marco();
    }
    
    
}

class Marco extends JFrame{
    
    public Marco(){
    setVisible(true);
    setBounds(400,400,600,400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Lamina miLamina= new Lamina();
    add(miLamina);
    }
}

class Lamina extends JPanel implements ActionListener{
    JButton boton=new JButton("Azul");
    
    public Lamina(){
        
        add(boton);
        boton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.BLUE);
    }
}
