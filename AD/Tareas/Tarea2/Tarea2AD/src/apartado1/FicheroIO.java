package apartado1;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que contiene los métodos para la gestión de la entradas y salidas del
 * fichero
 *
 * @author Daniel Paz Lorenzo
 */
public class FicheroIO {

    /**
     * Método que solicita y recoge los datos introducidos por el usuario
     */
    public void introducirDatos() {
        //Declaración de variables y objetos
        Scanner teclado = new Scanner(System.in);
        int codigo = 0;
        String nombre = null, direccion = null;
        float salario = 0, comision = 0;
        boolean op;

        do {
            op = true;
            try {
                System.out.println("\n\nIntroduzca los datos del empleado");
                System.out.println("---------------------------------");
                System.out.println("Introduzca codigo");
                codigo = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Introduzca nombre");
                nombre = teclado.nextLine();
                System.out.println("Introduzca direccion");
                direccion = teclado.nextLine();
                System.out.println("Introduzca salario");
                salario = teclado.nextFloat();
                System.out.println("Introduzca comision");
                comision = teclado.nextFloat();
                //Si se genera excepción vuelve a solicitar los datos
            } catch (Exception e) {
                System.err.println("Error al introducir los datos");
                teclado.next();
                op = false;
            }
        } while (!op);

        try {
            //Guardamos los datos en un objeto tipo Empleados
            Empleados empleado = new Empleados(codigo, nombre, direccion, salario, comision);
            //Llamamos a método para escribir datos en el fichero
            escribirFichero(empleado);
        } catch (IOException ex) {
            Logger.getLogger(FicheroIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para escribir los datos del objeto empleado en el fichero
     * EMPLEADOS.DAT
     *
     * @param empleado contiene los datos que se van a escribir en el fichero
     * @throws IOException si no se puede escribir en el fichero
     */
    public void escribirFichero(Empleados empleado) throws IOException {
        try {
            //Creamos el fichero y los flujos
            RandomAccessFile ficheroEmpleados = new RandomAccessFile("ficheros\\EMPLEADOS.DAT", "rwd");
            ficheroEmpleados.seek(ficheroEmpleados.length());//Nos colocamos al final del fichero
            ficheroEmpleados.writeUTF("," + String.valueOf(empleado.getCodigo()) + "," + empleado.getNombre() + "," + empleado.getDireccion() + ","
                    + empleado.getSalario() + "," + empleado.getComision());//escribimos los datos
            ficheroEmpleados.writeChar(13);//Añadimos un salto de línea
            System.out.println("Se han añadido los datos correctamente al fichero EMPLEADOS.DAT");
        } catch (FileNotFoundException ex) {
            System.err.println("No se ha podido crear el fichero");
            Logger.getLogger(FicheroIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para mostrar los datos del fichero en pantalla
     *
     * @throws FileNotFoundException si no se encuentra el fichero
     * @throws IOException si no se puede acceder al fichero
     */
    public void mostrarFichero() throws FileNotFoundException, IOException {
        //Creamos un objeto de acceso aleatorio al fichero
        RandomAccessFile ficheroEmpleados = new RandomAccessFile("ficheros\\EMPLEADOS.DAT", "rwd");
        System.out.println("Código |Nombre |Dirección |Sueldo |Comisión");
        //Leemos el fichero hasta que no tenga nada
        while (ficheroEmpleados.read() != -1) {
            //Mostramos línea a línea
            System.out.println(ficheroEmpleados.readLine());
        }

    }

    /**
     * Método que lee el fichero para extraer los datos y asignarlos al atributo
     * de empleado correspondiente. Estos los guarda en un array que luego será
     * devuelto
     *
     * @return arrayList de objeto Empleados
     * @throws FileNotFoundException si no se encuentra el fichero
     * @throws IOException si no se puede acceder al fichero
     */
    public ArrayList<Empleados> leerFichero() throws FileNotFoundException, IOException {
        //Creamos un objeto de acceso aleatorio al fichero
        RandomAccessFile ficheroEmpleados = new RandomAccessFile("ficheros\\EMPLEADOS.DAT", "rwd");
        //Creamos los objetos y arraylist de empleados
        Empleados empleado;
        ArrayList<Empleados> listaEmpleados = new ArrayList();
        int contLinea = 0;
        //Leemos el fichero completo
        while (ficheroEmpleados.read() != -1) {
            empleado = new Empleados();
            String linea = ficheroEmpleados.readLine();
            //Separamos cada línea por la coma y asignamos el valor que contiene
            //al atributo correspondiente
            String[] divLinea = linea.split(",");
            empleado.setCodigo(Integer.parseInt(divLinea[1]));
            empleado.setNombre(divLinea[2]);
            empleado.setDireccion(divLinea[3]);
            empleado.setSalario(Float.parseFloat(divLinea[4]));
            empleado.setComision(Float.parseFloat(divLinea[5]));
            //Añadimos el objeto empleado al array
            listaEmpleados.add(contLinea, empleado);
            contLinea++;
        }

        return listaEmpleados;
    }

}
