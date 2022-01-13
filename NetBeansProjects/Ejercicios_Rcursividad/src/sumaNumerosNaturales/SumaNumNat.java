package sumaNumerosNaturales;

import java.util.Scanner;

/**
 * Suma numeros naturales hasta un N dado
 *
 * @author DANILOR
 */
public class SumaNumNat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int num;

        System.out.println("SUMA DE N NÚMEROS");
        System.out.println("---------------------");
        System.out.println("Introduzca un número");
        num = teclado.nextInt();
        System.out.println(sumaNumeros(num));
    }

    public static int sumaNumeros(int numero) {
        int res;

        if (numero == 1) {
            return 1;
        } else {
            return res = numero + sumaNumeros(numero - 1);
        }
    }
}
