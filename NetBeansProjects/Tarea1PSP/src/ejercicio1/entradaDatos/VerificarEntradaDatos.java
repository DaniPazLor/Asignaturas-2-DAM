/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1.entradaDatos;

/**
 *
 * @author DANILOR
 */
public class VerificarEntradaDatos {
    
    //Comprueba si es entero
    public boolean comprobarSiEntero(String entradaTeclado){
        boolean esNumero=true;
            for (int j = 0; j < entradaTeclado.length(); j++) {
                    if (!Character.isDigit(entradaTeclado.charAt(j))) {
                        esNumero = false;
                    }
                }
        return esNumero;
    }
}
