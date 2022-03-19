/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculadora;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculando operacion = new Calculando();
        System.out.println(operacion.add(3, 4));
        System.out.println(operacion.subtract(3, 4));
        System.out.println(operacion.multiply(3, 4));
        System.out.println(operacion.divide(3, 4));
    }
    
    

}
