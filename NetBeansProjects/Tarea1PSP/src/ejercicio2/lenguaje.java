/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.io.*;
import java.util.Random;

/**
 *
 * @author PAUDA
 */
public class lenguaje {

     public static void main(String[] args) {
    Random letraAleatoria = new Random();
    try {

      String rutaArchivo = "ficheros/"+args[0];
      File archivoTexto = new File(rutaArchivo);
      FileWriter ficheroPrueba = new   FileWriter(archivoTexto);
      BufferedWriter bfw= new BufferedWriter (ficheroPrueba);

      int num = 0;

      for (int i = 0; i < 40; i++) {

        for (int j = 0; j < letraAleatoria.nextInt(30) + 1; j++) {
          num = letraAleatoria.nextInt(25) + 97;
          System.out.print((char) num);
          bfw.write(num);
        }
         
         bfw.newLine();
         
        System.out.println();
      }
      
      bfw.close();
      ficheroPrueba.close();
    } catch (Exception e) {
      System.out.println("error");
    }
  }
    
}
