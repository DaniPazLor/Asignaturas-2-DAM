/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matricula;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DANILOR
 */
public class MatriculaBean {

    private String DNI;
    private String NombreModulo;
    private String Curso;
    private double Nota;
    private Vector Matriculas=new Vector();

    public MatriculaBean() {
    }

    public MatriculaBean(String DNI, String NombreModulo, String Curso, double Nota) throws Exception {
        this.DNI = DNI;
        this.NombreModulo = NombreModulo;

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
    
 /*******************************************************
     * Actualiza el contenido de la tabla en el vector de alumnos
     * Las propiedades contienen el valor del primer elementos de la tabla
     */
//    private void recargarFilas() throws ClassNotFoundException
//    {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddtarea7", "dani", "5678");
//            Statement s = con.createStatement();
//            ResultSet rs = s.executeQuery ("select * from alumnos");
//            while (rs.next())
//            {
//                Matricula m = new Matricula(rs.getString("DNI"),
//                                      rs.getString("Nombre"),
//                                      rs.getString("Apellidos"),
//                                      rs.getString("Direccion"),
//                                      rs.getDate("FechaNac"));
//
//                Alumnos.add(a);
//            }
//            AlumnoBean.Alumno a = new AlumnoBean.Alumno();
//            a = (AlumnoBean.Alumno) Alumnos.elementAt(1);
//            this.DNI = a.DNI;
//            this.Nombre = a.Nombre;
//            this.Apellidos = a.Apellidos;
//            this.Direccion = a.Direccion;
//            this.FechaNac = a.FechaNac;
//            rs.close();
//            con.close();
//        } catch (SQLException ex) {
//            this.DNI = "";
//            this.Nombre = "";
//            this.Apellidos = "";
//            this.Direccion = "";
//            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
     /********************************************************
     *
     * @param nDNI DNI A buscar, se carga en las propiedades del componente
     */
    public void seleccionarDNI(String nDNI)
    {
        Matricula m = new Matricula();
        int i=0;

        this.DNI = "";
        this.NombreModulo = "";
        this.Curso = "";
        this.Nota = 0;
        while(this.DNI.equals("") && i<=Matriculas.size())
        {
            m = (Matricula)Matriculas.elementAt(i);
            if ( m.DNI.equals(nDNI) )
            {
                this.DNI = m.DNI;
                this.NombreModulo = m.NombreModulo;
                this.Curso = m.Curso;
                this.Nota = m.Nota;
                
            }
        }
    }
}
