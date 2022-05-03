
package examen;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;


/**
 * Clase principal del ejercicio de examen de la segunda evaluación de PSP 21/22    
 * 
 * @author Daniel Paz Lorenzo
 */
public class Principal {

    private SecretKey claveSimetrica;
    private SecretKey nuevaClaveSimetricaDescifrada;
    private KeyPair clavesAsimetricas;
    private PublicKey clavePublica;
    private PrivateKey clavePrivada;

    private File clavePublicaCifrada;
    private File clavePrivadaCifrada;
    private File claveCifrada;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal principal = new Principal();
        Scanner teclado = new Scanner(System.in);

        //1.Generar la clave simétrica
        principal.generarClaveSimétrica();
        //2.Generar par de claves asimétricas
        principal.generarClavesAsimetricas();
        //3.Solicitar a usuario fichero de clave pública
        System.out.println("Introduzca el nombre del fichero con la clave pública: ");
        String nomFichClavePublica = teclado.next();
        teclado.nextLine();
        //4.Cifrar clave simetrica con pública
        principal.cifrarClaveSimetrica(nomFichClavePublica);
        //5.Recoger mensaje de usuario
        System.out.println("Introduzca un mensaje a cifrar: ");
        String mensaje = teclado.nextLine();
        //6.Cifrar mensaje con clave simétrica y guardar en "mensajecifrado"
        principal.cifrarMensaje(mensaje);
        //7.Descifrar clave simétrica con clave privada del destinatario
        //Solicita a usuario fichero de clave privada
        System.out.println("Introduzca el nombre del fichero con la clave privada: ");
        String nomFichClavePrivada = teclado.next();
        teclado.nextLine();
        principal.descifrarClaveSimetrica(nomFichClavePrivada);
        //8.Descifrar el mensaje cifrado anteriormente y mostrarlo por pantalla
        principal.descifrarFichero();

    }

    /**
     * Método para generar la clave simétrica con el algoritmo AES
     */
    public void generarClaveSimétrica() {
        try {

            //1. Crear e inicializar clave
            System.out.println("1.- Genera clave simétrica con AES");
            //crea un objeto para generar la clave usando algoritmo DES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); //se indica el tamaño de la clave
            claveSimetrica = keyGen.generateKey(); //genera la clave privada
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para generar las claves asimétricas y poder comprobar el 
     * funcionamiento de la aplicación
     */
    private void generarClavesAsimetricas() {

        try {
            //Creamos los flujos de salida de ficheros 
            clavePublicaCifrada = new File("clavePublica.cifrada");
            FileOutputStream fosPublica = new FileOutputStream(clavePublicaCifrada);
            clavePrivadaCifrada = new File("clavePrivada.cifrada");
            FileOutputStream fosPrivada = new FileOutputStream(clavePrivadaCifrada);
            //Generamos el par de claves RSA (publica y privada)
            System.out.println("2.- Generando par de claves RSA...");
            KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA");
            generadorRSA.initialize(1024);
            clavesAsimetricas = generadorRSA.genKeyPair();
            System.out.println("Generada la clave asimétrica.");
            clavePublica = clavesAsimetricas.getPublic();
            clavePrivada = clavesAsimetricas.getPrivate();
            //Convertimos a flujo de bytes
            byte[] bytesClavePublica = clavePublica.getEncoded();
            byte[] bytesClavePrivada = clavePrivada.getEncoded();
            //Guardamos en respectivos ficheros
            fosPublica.write(bytesClavePublica);
            fosPrivada.write(bytesClavePrivada);
            System.out.println("Se han guardado las 2 claves correctamente en " + clavePublicaCifrada.getName()
                    + " y " + clavePrivadaCifrada.getName());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para cifrar la clave simétrica con la clave pública
     * @param nomFicClavePublica 
     */
    private void cifrarClaveSimetrica(String nomFicClavePublica) {
        try {
            //Crear e inicializar el cifrador RSA que se va a encargar de encriptar
            //la clave AES con la parte pública del par RSA
            Cipher cifradorRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cifradorRSA.init(Cipher.ENCRYPT_MODE, clavesAsimetricas.getPublic());

            //Una vez tenemos este cifrador cogemos los byte de la clave AES y los encriptamos
            byte[] bytesClaveAES = claveSimetrica.getEncoded();
            byte[] claveAESCifrada = cifradorRSA.doFinal(bytesClaveAES);

            //Guardamos la clave simétrica cifrada con RSA en fichero clavecifrada
            claveCifrada = new File("clavecifrada");
            FileOutputStream fosClaveCifrada = new FileOutputStream(claveCifrada);
            fosClaveCifrada.write(claveAESCifrada);
            System.out.println("3.- La clave simétrica se ha cifrado y guardado en fichero \"clavecifrada\"");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para cifrar el mensaje de usuario con clave simétrica
     * @param mensaje 
     */
    private void cifrarMensaje(String mensaje) {

        try {
            //Creación de objeto Cipher para cifrar, utilizando el algoritmo AES
            Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //Inicialización del cifrador en modo cifrado
            cifrador.init(Cipher.ENCRYPT_MODE, claveSimetrica);
            //Realizamos el cifrado de la información con el método doFinal
            byte textoPlano[] = mensaje.getBytes();
            byte textoCifrado[] = cifrador.doFinal(textoPlano);
            System.out.println("Encriptado: " + new String(textoCifrado));
            //Guardamos en fichero mensajecifrado
            FileOutputStream fsc = new FileOutputStream("mensajecifrado"); //flujo de salida
            fsc.write(textoCifrado);
            fsc.close();
            System.out.println("4.- Se ha cifrado el mensaje y guardado en fichero \"mensajecifrado\"");
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para descifrar la clave simétrica con la clave privada del 
     * receptor del mensaje
     * @param nomFichClavePrivada 
     */
    private void descifrarClaveSimetrica(String nomFichClavePrivada) {

        try {
            //Leer fichero clavecifrada y pasar a array de bytes
            Path p = FileSystems.getDefault().getPath("", "clavecifrada");
            byte[] bytesNuevaClaveCifrada = Files.readAllBytes(p);

            //Leer fichero clave privada y pasar a array de bytes
            Path p1 = FileSystems.getDefault().getPath("", nomFichClavePrivada);
            byte[] bytesClavePrivada = Files.readAllBytes(p1);

            //crear e inicializar el cifrador RSA que se va a encargar de desencriptar
            //la clave AES con la parte privada del par RSA
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytesClavePrivada);
            KeyFactory factoria = KeyFactory.getInstance("RSA");
            PrivateKey clavePrivada = factoria.generatePrivate(spec);

            Cipher cifradorRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cifradorRSA.init(Cipher.DECRYPT_MODE, clavePrivada);
            byte[] bytesnuevaClaveDescifrada = cifradorRSA.doFinal(bytesNuevaClaveCifrada);

            nuevaClaveSimetricaDescifrada = new SecretKeySpec(bytesnuevaClaveDescifrada, "AES");
            System.out.println("5.- Clave simétrica descifrada con éxito");
            
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para desencriptar mensajecifrado si la clave simétrica es correcta
     */
    private void descifrarFichero() {
        if (nuevaClaveSimetricaDescifrada.equals(claveSimetrica)) {
            try {
                System.out.println("clave privada correcta");//comprueba el correcto funcionamiento de codificación y decodificación

                //Creamos los ficheros de cifrado y descifrado
                File ficheroCifrado = new File("mensajecifrado");

                int bytesLeidos;
                String cadena = "";
                //Crear objeto Cipher con algoritmo AES
                Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
                //Poner cifrador en modo DESCIFRADO o DESENCRIPTACIÓN
                cifrador.init(Cipher.DECRYPT_MODE, claveSimetrica);
                System.out.println("6.- Descifrar con AES el " + ficheroCifrado
                        + ", y mostrar por pantalla  el mensaje: \n");

                //declaración  de objetos
                FileInputStream fe = new FileInputStream(ficheroCifrado);

                byte[] bufferClaro;
                byte[] buffer = new byte[1000]; //array de bytes

                //lee el fichero de 1k en 1k y pasa los fragmentos leidos al cifrador
                bytesLeidos = fe.read(buffer, 0, 1000);
                while (bytesLeidos != -1) {//mientras no se llegue al final del fichero
                    //pasa texto cifrado al cifrador y lo descifra, asignándolo a bufferClaro
                    bufferClaro = cifrador.update(buffer, 0, bytesLeidos);
                    bytesLeidos = fe.read(buffer, 0, 1000);
                    cadena = new String(bufferClaro, "UTF-8");
                    System.out.print(cadena);
                }
                bufferClaro = cifrador.doFinal(); //Completa el descifrado
                cadena = new String(bufferClaro, "UTF-8");
                System.out.println(cadena);
                //Cerrar flujos
                fe.close();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Clave privada incorrecta, no se ha podido desencriptar el mensaje");
        }

    }

}
