package aplicacionJava;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANILOR
 */
public class Tarea5_DI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InformeCliente informe = new InformeCliente();
        int cliente = 1;//Valor que se va a pasar para que lo recoja el parámetro
        informe.ejecutar(cliente);
    }

}
