/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.Random;

/**
 *
 * @author sergio
 */
public class Juego {
    private int vidas = 10;
    private String[] refranes = new String[5];
    private String refranElegido;
    private String pista;

    public Juego() {
        this.refranes = cargarRefranes();
        this.refranElegido = elegirRefran(this.refranes);
        this.pista = generarPista(this.refranElegido);
    }

    public int getVidas() {
        return vidas;
    }

    public String getRefranElegido() {
        return refranElegido;
    }

    public String getPista() {
        return pista;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    // editamos el setter de modo que valida la última letra indicada en lugar del string completo y utiliza el mismo sistema del metodo generar pista
    public void setPista(char letra) {
        int longitud = refranElegido.length();
        String pista = "";
        char caracter;
        
        for (int i = 0; i < longitud; i++) {
            caracter = this.refranElegido.charAt(i);
            if (Character.compare(caracter, letra) == 0) {
                pista += letra;
            } else {
                pista += this.pista.charAt(i);
            }
        }
        
        this.pista = pista;
    }
    
    // función que valida la letra indicada utilizando el metodo de la clase Character
    public int comprobarLetra(char letra) {
        int aciertos = 0;
        char caracter;
        String frase = this.refranElegido;
        
        for (int i = 0; i < this.refranElegido.length(); i++) {
            caracter = frase.charAt(i);
            
            if (Character.compare(caracter, letra) == 0) {
                aciertos++;
            }
        }
        
        return aciertos;
    }
    
    // función que valida el refran indicado
    public boolean adivinarRefran(String refran) {        
        return refran.equalsIgnoreCase(this.refranElegido);
    }

    private String[] cargarRefranes() {
        String[] refranes = new String[5];
        
        refranes[0] = "al que madruga dios le ayuda";
        refranes[1] = "en abril aguas mil";
        refranes[2] = "marzo ventoso y abril lluvioso sacan a mayo florido y hermoso";
        refranes[3] = "no por mucho madrugar amanece mas temprano";
        refranes[4] = "en boca cerrada no entran moscas";
        
        return refranes;
    }

    // funcion que asigna de forma aleatoria uno de los refranes del array como el elegido
    private String elegirRefran(String[] refranes) {
        Random rng = new Random();
        
        return refranes[rng.nextInt(5)];
    }

    // funcion que genera el numero de guiones segun la longitud de nuestro refran elegido
    private String generarPista(String refranElegido) {
        int longitud = refranElegido.length();
        String pista = "";
        
        for (int i = 0; i < longitud; i++) {
            pista += "-";
        }
        
        return pista;
    }
    
    
}
