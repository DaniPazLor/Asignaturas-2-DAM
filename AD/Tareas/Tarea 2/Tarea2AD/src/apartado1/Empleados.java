package apartado1;

/**
 * Clase que contiene como atributos los datos de los empleados
 *
 * @author Daniel Paz Lorenzo
 */
public class Empleados {

    //Creación de atributos
    private int codigo;
    private String nombre;
    private String direccion;
    private float salario;
    private float comision;

    /**
     * Constructor de la clase genérico
     */
    public Empleados() {

    }

    /**
     * Constructor de la clase con parámetros
     *
     * @param codigo de empleado
     * @param nombre de empleado
     * @param direccion de empleado
     * @param salario de empleado
     * @param comision de empleado
     */
    public Empleados(int codigo, String nombre, String direccion, float salario, float comision) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.salario = salario;
        this.comision = comision;
    }

    //Métodos getter & setter de la clase
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public float getSalario() {
        return salario;
    }

    public float getComision() {
        return comision;
    }

}
