/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_1;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author DANILOR
 */
public class Menu_Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int op;
        boolean correcta=true;
        Scanner teclado = new Scanner(System.in);
        Controlador_BBDD controlBBBDD = new Controlador_BBDD();
        
        BDJefeHjio bdJefeHijo = new BDJefeHjio();
        bdJefeHijo.crearBBDD(); 
        /*Borramos los que había antes insertados*/
        File fichero = new File("BDJefeHijo");
        fichero.delete();
                  
        do{
        try{
        System.out.println("MENÚ DE OPCIONES EJERCICIO 1");
        System.out.println("----------------------------");
        System.out.println("1.- Visualizar los jefes con más de 55 años");
        System.out.println("2.- Incrementar la edad de Miguel incrementando su edad un año más");
        System.out.println("3.- Borrar los jefes que llevan más de 6 años en la empresa");
        System.out.println("4.- Visualizar todos los jefes que no hayan sido borrados");
        System.out.println("5.- Salir");
        System.out.println("Introduzca la opción deseada: ");
        op = teclado.nextInt();
               
        switch (op) {
            case 1:
                controlBBBDD.consultarJefes55(bdJefeHijo.baseDatos);
        
                break;
            case 2:
                controlBBBDD.actualizarEdadMiguel(bdJefeHijo.baseDatos);
                break;
            case 3:
                controlBBBDD.borrarMasAnios(bdJefeHijo.baseDatos);
                break;
            case 4:
                controlBBBDD.consultarTodosJefes(bdJefeHijo.baseDatos);
                break;
            case 5:
                bdJefeHijo.baseDatos.close();
                correcta=false;
                break;
            default:
                System.out.println("Por favor introduzca número del 1-5");
        }
        
        }catch(Exception e){
            System.out.println("La opción introducida no es correcta");
            
        }
        }while(correcta);
    }
}


