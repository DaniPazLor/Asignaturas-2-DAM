/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebamatriculabean;

import Matricula.MatriculaBean;
import Matricula.MatriculaBean.ModoModificadoEvent;
import Matricula.MatriculaBean.ModoModificadoListener;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author usuario
 */
public class AccedeBD implements ModoModificadoListener {
    MatriculaBean matricula;

    public AccedeBD()
    {
         matricula = new MatriculaBean();
         matricula.addModoModificadoListener((ModoModificadoListener) this);
    }

    public void listado()
    {
        for(int i=0; i<4; i++)
        {
            matricula.seleccionarFila(i);
            System.out.println("Matrícula " + i + "\n\tDNI:" + matricula.getDNI());
            System.out.println("\tNombre: " + matricula.getNombreModulo());
            System.out.println("\tApellidos: " + matricula.getCurso());
            System.out.println("\tDireccion: " + matricula.getNota());
        }
    }

    public void listadoDNI(String nDNI) throws ClassNotFoundException
    {
        matricula.recargarDNI(nDNI);

        for(int i=0; i<4; i++)
        {
            matricula.seleccionarFila(i);
            System.out.println("Matrícula " + i + "\n\tDNI:" + matricula.getDNI());
            System.out.println("\tNombre: " + matricula.getNombreModulo());
            System.out.println("\tApellidos: " + matricula.getCurso());
            System.out.println("\tDireccion: " + matricula.getNota());
        }
    }

    void anade()
    {
        matricula.setDNI("98765432A");
        matricula.setNombreModulo("Acceso a datos");
        matricula.setCurso("11-12");
        matricula.setNota(7.25);
        try {
            matricula.addMatricula();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccedeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void capturarModoModificado(ModoModificadoEvent ev)
    {
        if(ev.modo)
            System.out.println("Se ha cargado toda la tabla");
        else
            System.out.println("Se ha cargado la matricula de un alumno");

    }
}
