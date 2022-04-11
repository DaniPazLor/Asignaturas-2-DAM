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

    public void listado() {
        System.out.println("Listado completo de alumnos matriculados");
        for (int i = 0; i < 15; i++) {
            matricula.seleccionarFila(i);
            System.out.println("Matrícula " + i + "\n\tDNI:" + matricula.getDNI());
            System.out.println("\tNombre Módulo: " + matricula.getNombreModulo());
            System.out.println("\tCurso: " + matricula.getCurso());
            System.out.println("\tNota: " + matricula.getNota());
        }
    }

    public void listarPorDNI(String numDNI){
        try {
            matricula.recargarDNI(numDNI);
            System.out.println("Listado de módulos matriculados por Dni");
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
    public void capturarCambioModo(MatriculaBean.CambioModoEvent evento) {
        if (evento.modo) {
            System.out.println("Se ha cargado toda la tabla");
        } else {
            System.out.println("Se ha cargado la matricula de un alumno");
        }

    }
}
