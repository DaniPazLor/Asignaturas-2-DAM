package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jjalv
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Cliente nuevo = new Cliente();
            Socket socket = new Socket("localhost", 1500);

//        DataInputStream input= new DataInputStream(socket.getInputStream());
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);
            BufferedReader in
                    = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            BufferedReader entradaStandar
                    = new BufferedReader(
                            new InputStreamReader(System.in));

            System.out.println("ingrese un nº de referencia "
                    + "o un nombre de producto: ");
            String numeroReferencia = entradaStandar.readLine();
            out.write(numeroReferencia);

            String c;

            out.close();
            in.close();
            socket.close();

//        String entrada = input.readLine();
//        System.out.println(entrada);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
