/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/**
 *
 * @author DANILOR
 */
public class ProductorConsumidorMain {

    char [] bufferCaracteres = new char[6];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Productor productor = new Productor();
        
        Thread hiloProductor = new Thread(productor);
        hiloProductor.start();
    }
    
}
