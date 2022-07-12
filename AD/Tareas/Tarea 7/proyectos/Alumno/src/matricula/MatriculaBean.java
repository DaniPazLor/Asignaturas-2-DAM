package matricula;

import Alumno.AlumnoBean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que conforma el componente JavaBean de MatriculaBEan que representa la
 * tabla matriculas de la base de datos junto con los métodos de acceso y
 * modificación
 *
 * @author Daniel Paz Lorenzo
 */
public class MatriculaBean implements Serializable{

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

//    private String sampleProperty;
    private PropertyChangeSupport propertySupport;

    protected String DNI;
    protected String NombreModulo;
    protected String Curso;
    protected double Nota;
    public boolean modo;

    /**
     * Constructor de la clase
     *
     * @throws Exception
     */
    public MatriculaBean()  {
        propertySupport = new PropertyChangeSupport(this);
        recargarFilas();

    }

    /**
     * *************************************************
     *
     * Métodos getter & setter de la clase MatriculaBean
     */
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombreModulo() {
        return NombreModulo;
    }

    public void setNombreModulo(String NombreModulo) {
        this.NombreModulo = NombreModulo;
    }

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String Curso) {
        this.Curso = Curso;
    }

    public double getNota() {
        return Nota;
    }

    public void setNota(double Nota) {
        this.Nota = Nota;
    }

    /**
     ****************************************************
     * Definimos los métodos y atributos privados del componente que usaremos
     * para darle funcionalidad.
     *
     */
    /**
     **************************************************
     * Clase auxiliar que usaremos para crear un vector privado de matriculas.
     */
    public class Matricula {

        public int ID;
        public String DNI;
        public String NombreModulo;
        public String Curso;
        public double Nota;

        public Matricula() {
        }

        public Matricula(String DNI, String NombreModulo, String Curso, double Nota) throws Exception {

            this.DNI = DNI;
            this.NombreModulo = NombreModulo;
            this.Curso = Curso;
            this.Nota = Nota;
        }

        public String getDNI() {
            return DNI;
        }

        public void setDNI(String DNI) {
            this.DNI = DNI;
        }

        public String getNombreModulo() {
            return NombreModulo;
        }

        public void setNombreModulo(String NombreModulo) {
            this.NombreModulo = NombreModulo;
        }

        public String getCurso() {
            return Curso;
        }

        public void setCurso(String Curso) {
            this.Curso = Curso;
        }

        public double getNota() {
            return Nota;
        }

        public void setNota(double Nota) {
            this.Nota = Nota;
        }
    }

    /**
     * ****************************************************
     * Usaremos un vector auxiliar para cargar la información de la tabla de
     * forma que tengamos acceso a los datos sin necesidad de estar conectados
     * constantemente
     */
    public Vector Matriculas = new Vector();

    /**
     * *******************************************************************
     * Creamos la clase que hereda de EvenObject para generar el evento de
     * cambio de modo
     */
    private CambioModoListener receptor;

    public class CambioModoEvent extends java.util.EventObject {

        public boolean modo;

        // constructor
        public CambioModoEvent(Object source, boolean cambioModo) {
            super(source);
            modo = cambioModo;
        }
    }

    //Define la interfaz para el nuevo tipo de evento
    public interface CambioModoListener extends EventListener {

        public void capturarCambioModo(CambioModoEvent ev);
    }

    public void addBDModificadaListener(CambioModoListener receptor) {
        this.receptor = receptor;
    }

    public void removeBDModificadaListener(CambioModoListener receptor) {
        this.receptor = null;
    }

    /**
     * Método que actualiza el contenido de la tabla en el vector de Matriculas
     * Las propiedades contienen el valor del primer elementos de la tabla
     */
    public void recargarFilas() {
        Matriculas.removeAllElements();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "Dani", "5678");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from matriculas");

            while (rs.next()) {
                Matricula m = new Matricula(
                        rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota"));

                Matriculas.add(m);
            }

            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(1);

            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;

            modo = true;
            if (receptor != null) {
                receptor.capturarCambioModo(new CambioModoEvent(this, modo));
            }

            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("No existen datos en la base de datos");
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0;

        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido conectar a la base de datos");
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.err.println("Error en la clase matricula");
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ******************************************************
     * Método para cargar la fila del vector Matriculas correspondiente a i
     *
     * @param i numero de la fila a cargar en las propiedades del componente
     */
    public void seleccionarFila(int i) {
        if (i <= Matriculas.size()) {
            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(i);

            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;
        } else {

            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0.0;
        }
    }

    /**
     * *****************************************************
     * Actualiza el contenido de la tabla en el vector de matriculas Las
     * propiedades contienen el valor del primer elementos de la tabla
     *
     * @param numDNI número del dni solicitado
     */
    public void recargarDNI(String numDNI) {

        Matriculas.removeAllElements();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "Dani", "5678");
            PreparedStatement s = con.prepareStatement("select * from matriculas where DNI = ?");
            s.setString(1, numDNI);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                Matricula m = new Matricula(
                        rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota"));

                Matriculas.add(m);
            }
            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(1);

            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;

            modo = false;
            receptor.capturarCambioModo(new CambioModoEvent(this, modo));

            rs.close();
            con.close();
        } catch (SQLException ex) {
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0.0;
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido conectar a la base de datos");
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.err.println("Error en la clase matricula");
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para añadir una nueva matrícula en la base de datos
     */
    public void addMatricula() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "Dani", "5678");
            String consulta = "INSERT INTO matriculas VALUES (?,?,?,?,null)";
            PreparedStatement ps = con.prepareStatement(consulta);

            ps.setString(1, DNI);
            ps.setString(2, NombreModulo);
            ps.setString(3, Curso);
            ps.setDouble(4, Nota);

            ps.executeUpdate();

        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido conectar a la base de datos");
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.err.println("Error en la sentencia SQL");
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
