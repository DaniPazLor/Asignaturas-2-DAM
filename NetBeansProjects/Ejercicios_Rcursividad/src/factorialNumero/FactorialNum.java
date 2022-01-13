package factorialNumero;

import java.util.Scanner;

/**
 * Obtener el factorial de un número con recursividad
 *
 * @author DANILOR
 */
public class FactorialNum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int num;

        System.out.println("OBTENER EL FACTORIAL DE UN  NÚMERO");
        System.out.println("-----------------------------------");
        System.out.println("Introduzca un número: ");
        num = teclado.nextInt();
        System.out.println();
    }

    public static int factorialNumero(int numero) {
        int res;

        if (numero == 1) {
            return 1;
        } else {
            return res = numero * factorialNumero(numero - 1);
        }
    }

}
