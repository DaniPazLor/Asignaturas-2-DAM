package apartado2;

/**
 * Clase que contiene todos los atributos del libro junto con sus métodos
 * constructor gettter & setter y string
 *
 * @author Daniel Paz Lorenzo
 */
public class Libro {

    private int año;
    private String titulo;
    private String nombreAutor;
    private String apellidoAutor;
    private String editorial;
    private double precio;

    public Libro(int año, String titulo, String nombreAutor, String apellidoAutor, String editorial, double precio) {
        this.año = año;
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.editorial = editorial;
        this.precio = precio;
    }

    public Libro() {

    }

    public int getAño() {
        return año;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public String getEditorial() {
        return editorial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro{" + "año=" + año + ", titulo=" + titulo + ", nombreAutor=" + nombreAutor + ", apellidoAutor=" + apellidoAutor + ", editorial=" + editorial + ", precio=" + precio + '}';
    }

}
