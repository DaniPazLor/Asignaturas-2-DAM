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
        //MÓDULO DE SUMA
        //Valores límites
        System.out.println("Módulo suma valores límites: " + operacion.add(Math.pow(2, 64), Math.pow(-2, 64)));
        System.out.println("Módulo suma valores límites: " + operacion.add(Math.pow(-2, 64), Math.pow(2, 64)));
        //Valores equivalentes
        System.out.println("Módulo suma valores equivalentes: " + operacion.add(-1100.56, -3223.34543));
        System.out.println("Módulo suma valores equivalentes: " + operacion.add(43535.546546, 234324.3423432));
        //Valores aleatorios 
        System.out.println("Módulo suma valores aleatorios: " + operacion.add(Math.random()*Math.pow(2, 64), Math.random()*Math.pow(2, 64)));
        System.out.println("Módulo suma valores aleatorios: " + operacion.add((Math.random()*Math.pow(2, 64))*-1, (Math.random()*Math.pow(2, 64))*-1));     
        //MÓDULO DE RESTA
        //Valores límites
        System.out.println("Módulo resta valores límites: " + operacion.subtract(Math.pow(2, 64), Math.pow(-2, 64)));
        System.out.println("Módulo resta valores límites: " + operacion.subtract(Math.pow(-2, 64), Math.pow(2, 64)));
        //Valores equivalentes
        System.out.println("Módulo resta valores equivalentes: " + operacion.subtract(-1100.56, -3223.34543));
        System.out.println("Módulo resta valores equivalentes: " + operacion.subtract(43535.546546, 234324.3423432));
        //Valores aleatorios
        System.out.println("Módulo resta valores aleatorios: " + operacion.subtract(Math.random()*Math.pow(2, 64), Math.random()*Math.pow(2, 64)));
        System.out.println("Módulo resta valores aleatorios: " + operacion.subtract((Math.random()*Math.pow(2, 64))*-1, (Math.random()*Math.pow(2, 64))*-1));
        //MÓDULO DE MULTIPLICACIÓN
        //Valores límites
        System.out.println("Módulo multiplicación valores límites: " + operacion.multiply(Math.pow(2, 64), Math.pow(-2, 64)));
        System.out.println("Módulo multiplicación valores límites: " + operacion.multiply(Math.pow(-2, 64), Math.pow(2, 64)));
        //Valores equivalentes
        System.out.println("Módulo multiplicación valores equivalentes: " + operacion.multiply(-1100.56, -3223.34543));
        System.out.println("Módulo multiplicación valores equivalentes: " + operacion.multiply(43535.546546, 234324.3423432));
        //Valores aleatorios 
        System.out.println("Módulo multiplicación valores aleatorios: " + operacion.multiply(Math.random()*Math.pow(2, 64), Math.random()*Math.pow(2, 64)));
        System.out.println("Módulo multiplicación valores aleatorios: " + operacion.multiply((Math.random()*Math.pow(2, 64))*-1, (Math.random()*Math.pow(2, 64))*-1));
        //MÓDULO DE DIVISIÓN
        //Valores límites
        System.out.println("Módulo división valores límites: " + operacion.divide(Math.pow(2, 64), Math.pow(-2, 64)));
        System.out.println("Módulo división valores límites: " + operacion.divide(Math.pow(-2, 64), Math.pow(2, 64)));
        //Valores equivalentes
        System.out.println("Módulo división valores equivalentes: " + operacion.divide(-1100.56, -3223.34543));
        System.out.println("Módulo división valores equivalentes: " + operacion.divide(43535.546546, 234324.3423432));
        //Valores aleatorios 
        System.out.println("Módulo división valores aleatorios: " + operacion.divide(Math.random()*Math.pow(2, 64), Math.random()*Math.pow(2, 64)));
        System.out.println("Módulo división valores aleatorios: " + operacion.divide((Math.random()*Math.pow(2, 64))*-1, (Math.random()*Math.pow(2, 64))*-1));

    }

}
