package ejercicio1;

import java.util.*;

/**
 *
 * @author PAUDA
 */
public class Aleatorios {

    public static void main(String[] args) {
            Random numAleatorio = new Random();
        ArrayList<Integer> cadenaNumAleatorios = new ArrayList();
        
        for (int i = 0; i < 40; i++) {
            int num = numAleatorio.nextInt(100);
            cadenaNumAleatorios.add(num);
            //System.out.println(cadenaNumAleatorios.get(i) +" cont "+ (i+1));
            
        }
        System.out.println(cadenaNumAleatorios);
    }
}
