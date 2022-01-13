
package temporizador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.EventListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public  class TemporizadorBean extends JLabel implements ActionListener,Serializable{    
    
    protected int tiempo;
    private final Timer t;
    public static final String PROP_TIEMPO = "tiempo";
    private FinCuentaAtrasListener receptor;

    public interface FinCuentaAtrasListener extends EventListener
{
    public void capturarFinCuentaAtras(FinCuentaAtrasEvent ev);
    
}

    public int getTiempo() {
        return tiempo;
    }
   
    public void setTiempo(int tiempo) {
        int oldTiempo = this.tiempo;
        this.tiempo = tiempo;
        setText(Integer.toString(tiempo));
        repaint();
    }
    //Constructor sin argumentos. Se establece como tiempo por defecto 5 segundos.
    public TemporizadorBean() {
        //propertySupport = new PropertyChangeSupport(this);
        tiempo = 5;
        t = new Timer (1000, this);
        setText(Integer.toString(tiempo));
        
        setActivo(true);
    }
    //Activo es en si mismo una propiedad (sin atributo subyacente)
    //Gestiona si el temporizador está¡ funcionado o no.
    public final void setActivo(boolean valor) {
        if (valor == true)
            t.start();
        else
            t.stop();
    }
    public boolean getActivo() {
        return t.isRunning();
    }
      
    //Accion que se realiza cada vez que se cumplen los 1000 milisegudos establecidos
    //para t. se modifica el valor del texto de la etiqueta, se repinta y se disminuye
    //en un segundo el tiempo restante.
    //Cuando el tiempo llega a cero se para el Timer y se muestra un mensaje de tiempo
    //terminado.
   /* public void actionPerformed(ActionEvent e){
        setText(Integer.toString(tiempo));
        repaint();
        tiempo--;
        if(tiempo == 0){
            setActivo(false);
            JOptionPane.showMessageDialog(null, "Terminado", "Aviso",
            JOptionPane.INFORMATION_MESSAGE);
        }
    }*/
   @Override
    public void actionPerformed(ActionEvent e)
    {
        
        setText(Integer.toString(tiempo));
        repaint();
        tiempo--;
        if(tiempo == 0){
            setActivo(false);
            receptor.capturarFinCuentaAtras( new FinCuentaAtrasEvent(this));
        }
    }
 
    public void addFinCuentaAtrasListener(FinCuentaAtrasListener receptor){
        this.receptor = receptor;
    }

    public void removeFinCuentaAtrasListener(FinCuentaAtrasListener receptor){
        this.receptor=null;
    }
      
}
