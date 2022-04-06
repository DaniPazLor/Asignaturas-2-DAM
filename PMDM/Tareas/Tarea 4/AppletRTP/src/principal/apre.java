/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.applet.Applet;
import javax.swing.JLabel;

/**
 *
 * @author DANILOR
 */
public class apre extends Applet{

    @Override
    public void init() {
        JLabel etiqueta = new JLabel("Mensaje de bienvenida del applet");
        add(etiqueta);
    }
    
    
    
}
