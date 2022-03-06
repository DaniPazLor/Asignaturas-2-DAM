package ejercicio_1;

/**
 * Clase que contiene los métodos y atributos de Jefe
 *
 * @author Daniel Paz Lorenzo
 */
public class Jefe {

    private String nombre;
    private int anioEmpresa;
    private int edad;
    private Hijo hijo;

    public Jefe(String nombre, int anioEmpresa, int edad, Hijo hijo) {
        this.nombre = nombre;
        this.anioEmpresa = anioEmpresa;
        this.edad = edad;
        this.hijo = hijo;
    }

    public Jefe() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioEmpresa() {
        return anioEmpresa;
    }

    public void setAnioEmpresa(int anioEmpresa) {
        this.anioEmpresa = anioEmpresa;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Hijo getHijo() {
        return hijo;
    }

    public void setHijo(Hijo hijo) {
        this.hijo = hijo;
    }

    @Override
    public String toString() {
        return "Nombre del jefe: " + nombre + ", años en la empresa=" + anioEmpresa + ", edad=" + edad + " " + hijo;
    }

}
