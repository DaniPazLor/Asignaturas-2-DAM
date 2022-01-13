/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.Random;

/**
 *
 * @author DANILOR
 */
public class Productor extends Thread{

    @Override
    public void run() {
        char caracterAleatorio;
        Random numAleatorio = new Random();
        for (int i = 0; i < 40; i++) {
            caracterAleatorio=(char) numAleatorio.nextInt(65,90);
            System.out.println(caracterAleatorio);
        }
        
        
    }
    
    
}
