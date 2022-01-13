package actividadesLibroPsp;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANILOR
 */
public class actividad1_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Runtime r=Runtime.getRuntime();
        String comando="CMD /C DIR";
        Process p;
        
        try {
            p=r.exec(comando);
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader (new InputStreamReader (is));
            String linea;
            while((linea = br.readLine())!=null){
                System.out.println(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(actividad1_4.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
