/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadesLibroPsp;

/**
 *
 * @author DANILOR
 */
public class unSaludo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("SE NECESITA UN SALUDO...");
            System.exit(1);
        }
        for(int i=0; i<5; i++){
            System.out.println(i+1 +". "+args[0]);
        }
            
    }
    
}
