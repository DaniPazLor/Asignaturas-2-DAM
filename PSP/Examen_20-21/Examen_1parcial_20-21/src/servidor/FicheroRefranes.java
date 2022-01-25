/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANILOR
 */
public class FicheroRefranes {

    File ficheroRefranes;
    ArrayList<String> listaRefranes;
    String refran;

    public FicheroRefranes(String nombreFichero) {
        //Abrir fichero
        this.ficheroRefranes = abrirFichero(nombreFichero);
        //Generar lista de refranes
        this.listaRefranes = crearListaRefranes(this.ficheroRefranes);
        //Seleccionar refran al azar
        this.refran = generaRefranAzar(this.listaRefranes);
    }

    public File abrirFichero(String nombreFichero){
        File fichero = new File(nombreFichero);
        
        return fichero;
    }

    private ArrayList<String> crearListaRefranes(File ficheroRefranes) {
        ArrayList <String> lista = new ArrayList<>();
        String linea;
        try {
            FileReader leerFichero = new FileReader(ficheroRefranes);
            BufferedReader br = new BufferedReader(leerFichero);
            while ((linea = br.readLine())!=null) {
               lista.add(linea);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FicheroRefranes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FicheroRefranes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    private String generaRefranAzar(ArrayList<String> listaRefranes) {
       int numRandom = (int) Math.floor(Math.random()*listaRefranes.size());
        System.out.println("numAleatorio "+ numRandom);
        
       String refran = listaRefranes.get(numRandom);

       return refran; 
    }

}
