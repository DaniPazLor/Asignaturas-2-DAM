package Empresa;

import HibernateUtil.NewHibernateUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.*;

/**
 * Clase que contiene los métodos principales de la lógica de la aplicación
 *
 * @author Daniel Paz Lorenzo
 */
public class Main {

    /**
     * Método principal del proyecto donde se ejecuta el menú de la aplicación
     *
     * @param args
     */
    public static void main(String[] args) {
        //Declaración de atributos de clase
        Byte opcion;
        boolean val;

        do {//Muestra el menú de la aplicación y evalua si es correcto o no
            //Instancia de una sesión a partir de la clase NewHibernateUtil
            SessionFactory instanciaSingelton = NewHibernateUtil.getSessionFactory();
            Session session = instanciaSingelton.openSession();

            Scanner teclado = new Scanner(System.in);
            val = true;
            try {
                System.out.println("-----------------------------------------------");
                System.out.println("MENÚ DE OPCIONES PARA TAREA 4 DE ACCESO A DATOS");
                System.out.println("-----------------------------------------------");
                System.out.println("1.- Realizar una inserción sobre la tabla EMP");
                System.out.println("2.- Realizar un borrado sobre la tabla EMP");
                System.out.println("3.- Visualizar listado de las tablas EMP Y DEPT");
                System.out.println("4.- Salir");
                System.out.println("Elija una opción: ");
                //Recogemos la opción de teclado del usuario
                opcion = teclado.nextByte();

                switch (opcion) {
                    case 1:
                        inserccionEMP(session);
                        break;
                    case 2:
                        borrarEMP(session);
                        break;
                    case 3:
                        listadoEmpDept(session);
                        break;
                    case 4:
                        val = false;
                        break;
                    default:
                        System.out.println("Introduzca una opción del 1-4");
                        System.err.println("Opción incorrecta");
                }
                //Recoge la excepción si el dato introducido no es correcto
            } catch (Exception e) {
                System.err.println("El tipo de dato introducido no es correcto");
            }
        } while (val);

    }

    /**
     * Método para insertar en la tabla Empleados un nuevo empleado
     *
     * @param session la sesión creada de conexión con la bbdd
     */
    public static void inserccionEMP(Session session) {
        try {

            Transaction tx = session.beginTransaction();//Crea una transacción de la sesion
            Emp empleado = new Emp();//Crea objeto de la clase Emp
            //Introducimos los datos dentro del objeto
            empleado.setEmpno((short) 8000);
            empleado.setEname("DANIEL");
            empleado.setJob("VENDEDOR");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            Date date = dateFormat.parse("02/05/84");
            empleado.setHiredate(date);

            empleado.setSal(new BigDecimal(2580));
            empleado.setComm(new BigDecimal(200));
            empleado.setDept(new Dept((byte) 20));
            //Guardamos el objeto dentro de la sesión
            session.save(empleado);

            tx.commit();//Ejecuta la transacción
            session.close();//Cierra sesión
            System.out.println("El nuevo empleado se ha introducido correctamente\n");
        } catch (Exception ex) {//Muestra error si no se ha podido insertar
            System.err.println("error inserccion");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para borrar de la tabla un empleado dado un número concreto
     *
     * @param session la sesión creada de conexión con la bbdd
     */
    public static void borrarEMP(Session session) {
        try {
            Transaction tx = session.beginTransaction();//Crea una transacción de la sesion
            Emp empleado = new Emp((short) 8000);//Crea objeto de la clase Emp

            session.delete(empleado);//Borramos de la sesión el objeto seleccionado

            tx.commit();//Ejecuta la transacción
            session.close();//Cierra la sesión
            System.out.println("El empleado con número " + empleado.getEmpno() + " fué borrado con éxito\n");
        } catch (Exception ex) {//Muestra error si no se ha podido borrar
            System.err.println("error borrado");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para listar los empleados que hay dentro de la tabla empleados
     *
     * @param session la sesión creada de conexión con la bbdd
     */
    public static void listadoEmpDept(Session session) {
        //Crea una consulta a la base de datos con sintaxis HQL
        Query consulta = session.createQuery("SELECT e.empno,e.ename,e.sal,d.dname,d.loc FROM Dept as d, Emp as e WHERE d.deptno = e.dept");
        //Mete el resultado de la consulta en un vector para luego mostrarlos por pantalla
        List<Object[]> listaDepartamento = consulta.list();
        System.out.println("EMPNO | ENAME   | SAL  | DNAME         | LOC");
        System.out.println("-----------------------------------------------------");
        for (Object[] dept : listaDepartamento) {
            System.out.println(dept[0] + "  | " + dept[1] + "  | " + dept[2] + "   | " + dept[3] + "      |" + dept[4]);
        }

        session.close();//Cierra la sesión
    }
}
