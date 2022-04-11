/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaMatriculaBean;


/**
 *
 * @author DANILOR
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        AccedeBD gestion = new AccedeBD();

        gestion.listado();
//        gestion.anade();
        gestion.listarPorDNI("96385274f");
    }
    
}
