/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pauda
 */
public class Cliente {
    
    static final String HOST = "localhost";
    static final int PUERTO = 2000;
    
    public Cliente(){
        Scanner teclado = new Scanner(System.in);
        String refranIncognita;
        boolean adivinado;
        
         try {
            Socket socketCliente = new Socket(HOST, PUERTO);
            
             DataInputStream flujo_entrada = new DataInputStream (socketCliente.getInputStream());
             DataOutputStream flujo_salida = new DataOutputStream (socketCliente.getOutputStream());
             
             System.out.println("Este es el refrán que tienes que adivinar");
             refranIncognita = flujo_entrada.readUTF();
             System.out.println(refranIncognita);
             
             do{
             System.out.println("Dime una letra: ");
             char caracter = teclado.next().charAt(0);
             flujo_salida.writeChar(caracter);
             
             System.out.println("El refrán quedaría así");
             refranIncognita = flujo_entrada.readUTF();
             System.out.println(refranIncognita);
             adivinado = flujo_entrada.readBoolean();
             }while(!adivinado);
             System.out.println("ENHORABUENA!!! HAS ADIVINADO EL REFRÁN");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args) {
        
         new Cliente();
        
       
    }
}
