/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaMatriculaBean;

//import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import matricula.MatriculaBean;
import matricula.MatriculaBean.CambioModoEvent;
import matricula.MatriculaBean.CambioModoListener;

/**
 *
 * @author DANILOR
 */
public class AccedeBD implements CambioModoListener {

    MatriculaBean matricula;

    public AccedeBD() {
        try {
            matricula = new MatriculaBean();
            matricula.addBDModificadaListener((CambioModoListener) this);
        } catch (Exception ex) {
            Logger.getLogger(AccedeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listado() throws ClassNotFoundException {
        System.out.println("-------------------------------------------------------------");
        System.out.println("        LISTADO COMPLETO DE ALUMNOS MATRICULADOS");
        matricula.recargarFilas();
        for (int i = 0; i < matricula.Matriculas.size(); i++) {
            matricula.seleccionarFila(i);
            System.out.println("Matrícula " + (i+1) + "\n\tDNI:" + matricula.getDNI());
            System.out.println("\tNombre Módulo: " + matricula.getNombreModulo());
            System.out.println("\tCurso: " + matricula.getCurso());
            System.out.println("\tNota: " + matricula.getNota());
        }
    }

    public void listarPorDNI(String numDNI){

        try {
            System.out.println("-------------------------------------------------------------");
            System.out.println("            LISTADO DE MÓDULOS MATRICULADOS POR DNI");
            matricula.recargarDNI(numDNI);
           
            for (int i = 0; i < matricula.Matriculas.size(); i++) {
                matricula.seleccionarFila(i);
                System.out.println("\tDNI:" + matricula.getDNI());
                System.out.println("\tNombre Módulo: " + matricula.getNombreModulo());
                System.out.println("\tCurso: " + matricula.getCurso());
                System.out.println("\tNota: " + matricula.getNota());
            }
        } catch (Exception ex) {
            System.out.println("Número del DNI no válido");;
        }
    }

    void anade() {
        matricula.setDNI("71139088L");
        matricula.setNombreModulo("Acceso a datos");
        matricula.setCurso("21-22");
        matricula.setNota(6.75);

        matricula.addMatricula();
    }

    @Override
    public void capturarCambioModo(CambioModoEvent ev) {
         if (ev.modo) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Modo true: se han cargado las matrículas de todos los alumnos");
        } else {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Modo false: se han cargado las matriculas de un solo alumno");
        }
    }


}
