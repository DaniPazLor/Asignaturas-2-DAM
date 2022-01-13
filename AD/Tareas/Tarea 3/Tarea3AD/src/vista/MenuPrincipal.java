package vista;

import controlador.*;
import java.sql.SQLException;
import java.util.*;
import modelo.*;

/**
 * Clase principal de la aplicación que muestra los menús y recoge la
 * información solicitada al usuario
 *
 * @author Daniel Paz Lorenzo
 */
public class MenuPrincipal {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        //Declaración de atributos y objetos
        boolean salir;//Controlar la salida del bucle del menú de opciones
        short op;//Recoge opción menú
        ConsultasBBDD consultasBBDD;
        Scanner teclado = new Scanner(System.in);

        do {
            salir = true;

            System.out.println("----------------------------------------------------");
            System.out.println("        MENÚ PRINCIPAL APLICACIÓN TAREA 3 AD");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Mostrar información general de la base de datos");
            System.out.println("2. Mostrar información de la tabla pasajeros");
            System.out.println("3. Ver información de los pasajeros de un vuelo");
            System.out.println("4. Insertar un vuelo");
            System.out.println("5. Borrar información de un vuelo");
            System.out.println("6. Modificar los vuelos de fumadores a no fumadores");
            System.out.println("7. Salir de la aplicación");

            System.out.print("Elija una opción: ");
            try {
                teclado.hasNext();
                op = teclado.nextShort();

                switch (op) {

                    case 1:
                        //Llama a métodos de la clase Consultas para mostrar la info general de la bbdd
                        consultasBBDD = new ConsultasBBDD();
                        consultasBBDD.mostrarInformacionBBDD();

                        break;
                    case 2:
                        //Llama a métodos de la clase Consultas para mostrar los datos de la tabla pasajeros
                        consultasBBDD = new ConsultasBBDD();
                        consultasBBDD.consultaPasajeros();

                        break;
                    case 3:
                        //Solicita al usuario el código de vuelo y lo recoge dentro del objeto
                        Pasajero pasajero = new Pasajero();
                        do {
                            salir = true;

                            System.out.print("\nIntroduzca el código de vuelo del que desea ver la información --> ");
                            teclado.hasNext();
                            pasajero.setCod_vuelo(teclado.next());
                            if (pasajero.getCod_vuelo().length() > 10) {
                                System.err.println("Cadena del código de vuelo demasiado larga");
                                salir = false;
                            }

                        } while (!salir);
                        //Pasamos como parámetro el objeto vuelo
                        consultasBBDD = new ConsultasBBDD();
                        consultasBBDD.consultaPasajerosVuelo(pasajero.getCod_vuelo());

                        break;
                    case 4:
                        //Recoge los datos del vuelo proporcionados por el usuario
                        Vuelo vuelo = new Vuelo();
                        do {
                            salir = true;
                            try {
                                System.out.print("\nIntroduzca el código de vuelo --> ");
                                teclado.hasNext();
                                vuelo.setCod_vuelo(teclado.next());

                                System.out.print("\nFecha y hora de salida en formato (dd/mm/aa-hh:mm) --> ");
                                vuelo.setHora_salida(teclado.next());

                                System.out.print("\nDestino --> ");
                                vuelo.setDestino(teclado.next());

                                System.out.print("\nProcedencia --> ");
                                vuelo.setProcedencia(teclado.next());

                                System.out.print("\nNúmero de plazas de fumador --> ");
                                vuelo.setPlazas_fumador(teclado.nextInt());

                                System.out.print("\nNúmero de plazas de no fumador --> ");
                                vuelo.setPlazas_nofumador(teclado.nextInt());

                                System.out.print("\nNúmero de plazas de turista --> ");
                                vuelo.setPlazas_turista(teclado.nextInt());

                                System.out.print("\nNúmero de plazas de primera --> ");
                                vuelo.setPlazas_primera(teclado.nextInt());
                                //Controlamos que los datos introducidos no excedan 
                                //el límite de la cadena para cada dato sino lanzamos excepción
                                if (vuelo.getCod_vuelo().length() > 10 || vuelo.getHora_salida().length() > 15 || vuelo.getDestino().length() > 15 || vuelo.getProcedencia().length() > 15) {
                                    salir = false;
                                    throw new Exception("La cadena introducida es demasiado larga");
                                }
                                //Pasamos como parámetro el objeto vuelo generado
                                consultasBBDD = new ConsultasBBDD();
                                consultasBBDD.insertarVuelo(vuelo);

                            } catch (Exception e) {
                                if (e.getMessage() != null) {
                                    System.err.println(e.getMessage());
                                }
                                System.err.println("El tipo de dato introducido no es correcto");
                                salir = false;
                            }
                        } while (!salir);

                        break;
                    case 5:
                        //Recogemos en un objeto tipo Vuelo el código solicitado al usuario
                        Vuelo vuelo1 = new Vuelo();
                        do {
                            salir = true;

                            System.out.print("\nIntroduzca el código de vuelo que desea borrar --> ");
                            teclado.hasNext();
                            vuelo1.setCod_vuelo(teclado.next());
                            if (vuelo1.getCod_vuelo().length() > 10) {
                                System.err.println("Cadena del código de vuelo demasiado larga");
                                salir = false;
                            }
                        } while (!salir);
                        //Pasamos al método el código de vuelo recogido anteriormente
                        consultasBBDD = new ConsultasBBDD();
                        consultasBBDD.borrarVuelo(vuelo1.getCod_vuelo());

                        break;
                    case 6:
                        //Llama al método que modifica vuelos de fumadores por no fumadores
                        consultasBBDD = new ConsultasBBDD();
                        consultasBBDD.modificarVuelosFumadoresNoFumadores();

                        break;
                    case 7:
                        salir = false;

                        break;
                    default:
                        salir = true;
                        System.err.println("Por favor introduzca un número del 1 al 6");
                }
            } catch (Exception e) {
                salir = true;
                teclado.next();
                System.err.println("Por favor introduzca un número del 1 al 6");
            }

        } while (salir);

    }

}
