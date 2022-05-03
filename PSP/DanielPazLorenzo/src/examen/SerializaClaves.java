/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examen;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author Jero
 */
public class SerializaClaves {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
   try{    
       System.out.println("Crear clave pública y privada");
    //Crea e inicializa el generador de claves RSA
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(512);//tamaño de la clave
    KeyPair clavesRSA = keyGen.generateKeyPair();
    PrivateKey clavePrivada = clavesRSA.getPrivate();//obtiene clave privada
    PublicKey clavePublica = clavesRSA.getPublic();//obtiene clave pública
    
    System.out.println("Crear clave simetrica");
    KeyGenerator keyGenSim = KeyGenerator.getInstance("DES");
    keyGenSim.init(56); //se indica el tamaño de la clave
    SecretKey claveSimetrica = keyGenSim.generateKey(); //genera la clave simetrica

   
    
    //Codifica claves
    byte[] publica = clavePublica.getEncoded();
    byte[] privada = clavePrivada.getEncoded();
    byte[] simetrica = claveSimetrica.getEncoded();
    
    //decodifica clave publica
    KeyFactory factoria = KeyFactory.getInstance("RSA");
    X509EncodedKeySpec spec = new X509EncodedKeySpec(publica);
    PublicKey nuevaClavePublica = factoria.generatePublic(spec);
    
    if(nuevaClavePublica.equals(clavePublica))
        System.out.println("bieen");//comprueba el correcto funcionamiento de codificación y decodificación
    
    //decodifica clave privada
    PKCS8EncodedKeySpec specpr = new PKCS8EncodedKeySpec(privada);
    PrivateKey nuevaClavePrivada =  factoria.generatePrivate(specpr);

    if(nuevaClavePrivada.equals(clavePrivada))
        System.out.println("super bieen");//comprueba el correcto funcionamiento de codificación y decodificación

    //decodifica clave simetrica
   SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
   DESKeySpec kspec = new DESKeySpec(simetrica);
   SecretKey nuevaClaveSimetrica = skf.generateSecret(kspec);
   
   if(nuevaClaveSimetrica.equals(claveSimetrica))
        System.out.println("requete bieen");//comprueba el correcto funcionamiento de codificación y decodificación
    
    }
   catch (Exception e){
       System.out.println(e.getMessage());
   }
  }
    
}
