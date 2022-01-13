/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import ejercicio1.entradaDatos.*;
import ejercicio1.salidaDatos.*;
import graficos.MenuEjercicio1;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 *
 * @author DANILOR
 */
public class OrdenaNumeros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Declaración de variables y objetos
        String pulsacionTeclado = null;
        int cont = 1, fin = 0;
        ArrayList<Integer> cadena = new ArrayList();
        Scanner teclado = new Scanner(System.in);
        VerificarEntradaDatos valEntrada=new VerificarEntradaDatos();
        SalidaDatos salDatos = new SalidaDatos();

        MenuEjercicio1 menu= new MenuEjercicio1();
        System.out.println("Programa para introducir un número indeterminado de números y ordenarlos");
        System.out.println("Introduzca una cadena de números");
        while (fin == 0) {
            boolean esNumero = true;
            
            System.out.println("Número " + cont + ": ");
            pulsacionTeclado = teclado.next();            
            esNumero=valEntrada.comprobarSiEntero(pulsacionTeclado);
            
            if (pulsacionTeclado.equals("F") || pulsacionTeclado.equals("f")) {
                fin = 1;
            }             
            if(esNumero==false){
                System.out.println("No ha introducido un número válido,\nPor favor vuelva a intentarlo");
            }
            if(fin !=1 && esNumero) {             
                cadena.add(parseInt(pulsacionTeclado));
                System.out.println("-------Para dejar de introducir números pulse \"F\"------");
                cont++;
            }
        }
        
        salDatos.ordenarArrayNumeros(cadena);

    }

}
