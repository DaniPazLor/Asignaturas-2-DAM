/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matricula;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author DANILOR
 */
public class Matricula {
    protected String DNI;
    protected String NombreModulo;
    protected String Curso;
    protected double Nota;


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
