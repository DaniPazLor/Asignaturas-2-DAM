/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Matricula;

import java.beans.*;
import java.io.Serializable;
import java.sql.*;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class MatriculaBean implements Serializable {

        //Datos para la conexión con mi base de datos de MySql
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/bbddtarea7";
    static final String USER = "Dani";
    static final String PASS = "5678";
    /*****************************************************
     * Propiedades del Bean.
     * Crearemos una propiedad por cada campo de la tabla de
     * la base de datos del siguiente modo:
     *
     * DNI: String
     * NombreModulo: String
     * Curso: String
     * Nota: Double
     */
    protected String DNI;

    /**
     * Get the value of DNI
     *
     * @return the value of DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Set the value of DNI
     *
     * @param DNI new value of DNI
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    protected String NombreModulo;

    /**
     * Get the value of NombreModulo
     *
     * @return the value of NombreModulo
     */
    public String getNombreModulo() {
        return NombreModulo;
    }

    /**
     * Set the value of NombreModulo
     *
     * @param NombreModulo new value of NombreModulo
     */
    public void setNombreModulo(String NombreModulo) {
        this.NombreModulo = NombreModulo;
    }
    protected String Curso;

    /**
     * Get the value of Curso
     *
     * @return the value of Curso
     */
    public String getCurso() {
        return Curso;
    }

    /**
     * Set the value of Curso
     *
     * @param Curso new value of Curso
     */
    public void setCurso(String Curso) {
        this.Curso = Curso;
    }
    protected double Nota;

    /**
     * Get the value of Nota
     *
     * @return the value of Nota
     */
    public double getNota() {
        return Nota;
    }

    /**
     * Set the value of Nota
     *
     * @param Nota new value of Nota
     */
    public void setNota(double Nota) {
        this.Nota = Nota;
    }



    private PropertyChangeSupport propertySupport;

    public MatriculaBean() {
        propertySupport = new PropertyChangeSupport(this);
        try {
            recargarFilas();
        } catch (ClassNotFoundException ex) {
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0.0;
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    /*******************************************************
     * Definimos los métodos y atributos privados del
     * componente que usaremos para darle funcionalidad.
     *
     */

    /*******************************************************
     * Representa el modo de funcionamiento.
     * Cuando toma valor verdadero trabajamos con todas las matrículas.
     * Cuando toma valor falso trabajamos con las matrículas de un DNI concreto.
     */
     boolean modo;
    /*****************************************************
     * Clase auxiliar que usaremos para crear un vector privado
     * de matriculas.
     */
    private class Matricula
    {
        String DNI;
        String NombreModulo;
        String Curso;
        double Nota;

        public Matricula()
        {}

        public Matricula(String nDNI, String nNombreModulo, String nCurso, double nNota)
        {
          this.DNI = nDNI;
          this.NombreModulo = nNombreModulo;
          this.Curso = nCurso;
          this.Nota = nNota;
        }
    }


    /******************************************************
     * Usaremos un vector auxiliar para cargar la información de la
     * tabla de forma que tengamos acceso a los datos sin necesidad
     * de estar conectados constantemente
     */
    private Vector Matriculas=new Vector();

    /*******************************************************
     * Actualiza todo el contenido de la tabla en el vector
     * de matriculas.
     * Las propiedades contienen el valor del primer elementos de la tabla
     */
    public void recargarFilas() throws ClassNotFoundException
    {
        /*****************************
         * Comprobamos que el vector no esté ya rellenado
         */
        if(!Matriculas.isEmpty())
        {
            Matriculas.removeAllElements();
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "root", "br4dplor");


            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select * from matriculas");
            while (rs.next())
            {
                Matricula a = new Matricula(rs.getString("DNI"),
                                      rs.getString("NombreModulo"),
                                      rs.getString("Curso"),
                                      rs.getDouble("Nota"));

                Matriculas.add(a);
            }
            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(1);
            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;
            modo = true;
            if (receptor!=null)
                receptor.capturarModoModificado( new ModoModificadoEvent(this, modo));
            rs.close();
            con.close();
        } catch (SQLException ex) {
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0.0;
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*******************************************************
     * Actualiza el contenido de la tabla en el vector de
     * matriculas en función del DNI que se pasa como parametro
     * Las propiedades contienen el valor del primer elementos de la tabla
     *
     * @param nDNI DNI del Matricula para el que se van a cargar las matriculas.
     */
    public void recargarDNI(String nDNI) throws ClassNotFoundException
    {

        if(!Matriculas.isEmpty())
        {
            Matriculas.removeAllElements();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "root", "br4dplor");
            PreparedStatement s = con.prepareStatement("select * from matriculas where DNI = ?");
            s.setString(1, nDNI);
            ResultSet rs = s.executeQuery();
            while (rs.next())
            {
                Matricula a = new Matricula(rs.getString("DNI"),
                                      rs.getString("NombreModulo"),
                                      rs.getString("Curso"),
                                      rs.getDouble("Nota"));

                Matriculas.add(a);
            }
            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(1);
            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;
            modo = false;
            receptor.capturarModoModificado( new ModoModificadoEvent(this, modo));
            rs.close();
            con.close();
        } catch (SQLException ex) {
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0.0;
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /********************************************************
     *
     * @param i numero de la fila a cargar en las propiedades del componente
     */
    public void seleccionarFila(int i)
    {
        if(i<=Matriculas.size())
        {
            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(i);
            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;
        }else{
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0.0;
        }
    }

   
    /*********************************************************************
     * Código para añadir un nuevo Matricula a la base de datos.
     * cada vez que se modifca el estado de la BD se genera un evento para
     * que se recargue el componente.
     */

    private ModoModificadoListener receptor;

    public class ModoModificadoEvent extends java.util.EventObject
    {
        // constructor
        public boolean modo;
        public ModoModificadoEvent(Object source, boolean nModo)
        {
            super(source);
            modo = nModo;
        }
    }


    //Define la interfaz para el nuevo tipo de evento
    public interface ModoModificadoListener extends EventListener
    {
        public void capturarModoModificado(ModoModificadoEvent ev);
    }

    public void addModoModificadoListener(ModoModificadoListener receptor)
    {
        this.receptor = receptor;
    }
    public void removeModoModificadoListener(ModoModificadoListener receptor)
    {
        this.receptor=null;
    }


    /*******************************************************
     * Método que añade un Matricula a la base de datos
     * añade un registro a la base de datos formado a partir
     * de los valores de las propiedades del componente.
     *
     * Se presupone que se han usado los métodos set para configurar
     * adecuadamente las propiedades con los datos del nuevo registro.
     */
    public void addMatricula() throws ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "root", "br4dplor");
            PreparedStatement s = con.prepareStatement("insert into matriculas values (?,?,?,?)");

            s.setString(1, DNI);
            s.setString(2, NombreModulo);
            s.setString(3, Curso);
            s.setDouble(4, Nota);


            s.executeUpdate ();

            /*En función del modo recargamos los datos de todas las matriculas
             * o de las de un DNI concreto.
             */
            if (modo){
                recargarFilas();

            }else
                recargarDNI(DNI);
        }
        catch(SQLException ex)
        {
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
