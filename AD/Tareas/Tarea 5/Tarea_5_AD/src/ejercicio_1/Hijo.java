package ejercicio_1;

/**
 * Clase que contiene los métodos y atributos de Hijo
 *
 * @author Daniel Paz Lorenzo
 */
public class Hijo {

    String nombre;
    int edad;

    public Hijo(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Hijo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return " Hijo:" + "nombre " + nombre + ", edad " + edad + '}';

    }

}
