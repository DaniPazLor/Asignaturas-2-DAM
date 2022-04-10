/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matricula;

import Alumno.AlumnoBean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DANILOR
 */
public class MatriculaBean {

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private String sampleProperty;

    private PropertyChangeSupport propertySupport;

    public MatriculaBean() throws Exception {
        propertySupport = new PropertyChangeSupport(this);
        try {
            recargarFilas();
        } catch (ClassNotFoundException ex) {
            this.ID = 0;
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0;
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    protected int ID;
    protected String DNI;
    protected String NombreModulo;
    protected String Curso;
    protected double Nota;

    public int getID() {
        return ID;
    }

    public void setId(int id) {
        this.ID = ID;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombreMódulo() {
        return NombreModulo;
    }

    public void setNombreMódulo(String NombreMódulo) {
        this.NombreModulo = NombreMódulo;
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
    private class Matricula {

        int ID;
        String DNI;
        String NombreModulo;
        String Curso;
        double Nota;

        public Matricula() {
        }

        public Matricula(int ID, String DNI, String NombreMódulo, String Curso, double Nota) throws Exception {
            this.ID = ID;
            this.DNI = DNI;
            this.NombreModulo = NombreMódulo;
//            if ((Curso.length() == 5)
//                    && (Curso.charAt(0) <= 57 && Curso.charAt(0) >= 48)
//                    && (Curso.charAt(1) <= 57 && Curso.charAt(1) >= 48)
//                    && (Curso.charAt(2) == 45)
//                    && (Curso.charAt(3) <= 57 && Curso.charAt(3) >= 48)
//                    && (Curso.charAt(4) <= 57 && Curso.charAt(4) >= 48)) {
//                this.Curso = Curso;
//            } else {
//                throw new Exception("[ERROR] Curso debe contener los dos años que lo componen separados por un \n"
//                        + "guión, por ejemplo 11-12.");
//            }
            // compila una cadena en un objeto Pattern
            Pattern p = Pattern.compile("[1-9]{2}-[1-9]{2}");

// Usa el objeto Pattern para crear un objeto Matcher
            Matcher m = p.matcher(Curso);
////boolean b = m.matches(); // devuelve verdadero

            if (m.matches()) {
                this.Curso = Curso;
            } else {
                throw new Exception("Curso debe tener el siguiente patrón \"11-12\"");
            }
            this.Nota = Nota;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getDNI() {
            return DNI;
        }

        public void setDNI(String DNI) {
            this.DNI = DNI;
        }

        public String getNombreMódulo() {
            return NombreModulo;
        }

        public void setNombreMódulo(String NombreMódulo) {
            this.NombreModulo = NombreMódulo;
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
    private Vector Matriculas = new Vector();

       /*******************************************************
     * Actualiza el contenido de la tabla en el vector de alumnos
     * Las propiedades contienen el valor del primer elementos de la tabla
     */
    private void recargarFilas() throws ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "dani", "5678");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select * from alumnos");
            while (rs.next())
            {
                MatriculaBean.Matricula m = new MatriculaBean.Matricula(rs.getInt("ID"),
                                      rs.getString("DNI"),
                                      rs.getString("NombreModulo"),
                                      rs.getString("Curso"),
                                      rs.getDouble("Nota"));

                Matriculas.add(m);
            }
            MatriculaBean.Matricula m = new MatriculaBean.Matricula();
            m = (MatriculaBean.Matricula) Matriculas.elementAt(1);
             this.ID = m.ID;
            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;
            rs.close();
            con.close();
        } catch (SQLException ex) {
         this.ID = 0;
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0;
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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
            this.ID = m.ID;
            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;
        }else{            
            this.ID = 0;
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = 0;
        }
    }
    /**
     * *****************************************************
     * Actualiza el contenido de la tabla en el vector de matriculas Las
     * propiedades contienen el valor del primer elementos de la tabla
     */
    private void recargarDNI(String numDNI) throws ClassNotFoundException, SQLException, Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "dani", "5678");
            PreparedStatement s = con.prepareStatement("select * from matriculas where DNI = ?");
            s.setString(1, numDNI);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                Matricula m = new Matricula(rs.getInt("ID"),
                        rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota"));

                Matriculas.add(m);
            }
            Matricula m = new Matricula();
            m = (Matricula) Matriculas.elementAt(1);
            this.ID = m.ID;
            this.DNI = m.DNI;
            this.NombreModulo = m.NombreModulo;
            this.Curso = m.Curso;
            this.Nota = m.Nota;
            rs.close();
            con.close();
        } catch (SQLException ex) {
            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            //this.Nota = "";
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /*********************************************************************
     * Código para añadir un nuevo alumno a la base de datos.
     * cada vez que se modifca el estado de la BD se genera un evento para
     * que se recargue el componente.
     */

    private BDModificadaListener receptor;

    public class BDModificadaEvent extends java.util.EventObject
    {
        // constructor
        public BDModificadaEvent(Object source)
        {
            super(source);
        }
    }
    

    //Define la interfaz para el nuevo tipo de evento
    public interface BDModificadaListener extends EventListener
    {
        public void capturarBDModificada(BDModificadaEvent ev);
    }

    public void addBDModificadaListener(BDModificadaListener receptor)
    {
        this.receptor = receptor;
    }
    public void removeBDModificadaListener(BDModificadaListener receptor)
    {
        this.receptor=null;
    }
    
    private void addMatricula() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "dani", "5678");
            String consulta = "INSERT INTO matriculas VALUES = (NULL,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setInt(1, ID);
            ps.setString(2, DNI);
            ps.setString(3, NombreModulo);
            ps.setString(4, Curso);
            ps.setDouble(5, Nota);
            
            
            ps.executeUpdate ();
            recargarFilas();
            receptor.capturarBDModificada( new BDModificadaEvent(this));
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
