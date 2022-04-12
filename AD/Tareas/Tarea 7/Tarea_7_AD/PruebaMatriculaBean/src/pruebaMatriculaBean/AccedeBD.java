package pruebaMatriculaBean;

import java.util.logging.Level;
import java.util.logging.Logger;
import matricula.MatriculaBean;
import matricula.MatriculaBean.CambioModoEvent;
import matricula.MatriculaBean.CambioModoListener;

/**
 * Clase que gestiona los accesos a la base de datos por medio del componente
 * MatriculaBean y que implementa la interfaz de escucha del evento
 *
 * @author Daniel Paz Lorenzo
 */
public class AccedeBD implements CambioModoListener {

    MatriculaBean matricula;

    /**
     * Constructor de la clase
     */
    public AccedeBD() {
        try {
            matricula = new MatriculaBean();
            matricula.addBDModificadaListener(this);
        } catch (Exception ex) {
            System.err.println("Error en la generación de la clase matrícula");
            Logger.getLogger(AccedeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que muestra todos los registros de la clase matrículas
     */
    public void listado() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("        LISTADO COMPLETO DE ALUMNOS MATRICULADOS");
        matricula.recargarFilas();
        for (int i = 0; i < matricula.Matriculas.size(); i++) {
            matricula.seleccionarFila(i);
            System.out.println("Matrícula " + (i + 1) + "\n\tDNI:" + matricula.getDNI());
            System.out.println("\tNombre Módulo: " + matricula.getNombreModulo());
            System.out.println("\tCurso: " + matricula.getCurso());
            System.out.println("\tNota: " + matricula.getNota());
        }
    }

    /**
     * Método que muestra los registros de un número dni dado por parámetro
     *
     * @param numDNI número de dni solicitado
     */
    public void listarPorDNI(String numDNI) {

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
            System.out.println("Número del DNI no válido");
            Logger.getLogger(AccedeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para añadir una nueva matricula en la tabla matrículas
     */
    void anade() {
        matricula.setDNI("71139088L");
        matricula.setNombreModulo("Acceso a datos");
        matricula.setCurso("21-22");
        matricula.setNota(6.75);

        matricula.addMatricula();
        System.out.println("\n--Nueva matrícula añadida correctamente--\n");
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
