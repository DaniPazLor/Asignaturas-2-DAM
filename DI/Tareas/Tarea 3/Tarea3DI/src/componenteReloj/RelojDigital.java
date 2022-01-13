package componenteReloj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.*;
import java.util.Date;
import javax.swing.*;

/**
 * Esta clase da forma a un reloj digital con opción de alarma. Implementa
 * Serializable para poderse utilizar como componente (JavaBean) dentro de
 * cualquier aplicación gráfica
 *
 * @author Daniel Paz Lorenzo
 */
public class RelojDigital extends JLabel implements Serializable, ActionListener {

    //Declaración de atributos de clase
    private boolean formatoHora;
    private boolean alarma;
    private int hora_Alarma;
    private int minuto_Alarma;
    private String mensaje_Alarma;
    //Declaración de objetos de clase
    private Timer segundero;
    private Date date;
    private Icon icono = new ImageIcon(getClass().getResource("Despertador.png"));
    DateFormat formatoDeHora;


    /**
     * Constructor de la clase que muestra en modo texto los digitos del reloj
     */
    public RelojDigital() {
        
        date = new Date();
        //Inicializamos las variables con valores por defecto
        setFormatoHora(true);
        setHora_Alarma(00);
        setMinuto_Alarma(00);
        setMensaje_Alarma("LEVANTATEEEEEE !!!!!");

        if (isFormatoHora()) {
            formatoDeHora = new SimpleDateFormat("HH:mm:ss");//Formato de 24 horas
        } else {
            formatoDeHora = new SimpleDateFormat("hh:mm:ss");//Formato de 12 horas
        }
        if (isAlarma()) {
            visualizaAlarma(formatoDeHora);
        } else {
            visualizaDigitosReloj(formatoDeHora);
        }
        segundero = new Timer(1000, this);//Cada segundo lanza un evento a esta misma clase
        segundero.start();//Actúa de segundero
    }
/**************************************************************************/
//Métodos getter & setter de los distintos atributos de la clase
    
    public boolean isFormatoHora() {
        return formatoHora;
    }

    public void setFormatoHora(boolean formatoHora) {
        this.formatoHora = formatoHora;
    }

    public boolean isAlarma() {
        return alarma;
    }

    public void setAlarma(boolean alarma) {
        this.alarma = alarma;
    }

    public int getHora_Alarma() {
        return hora_Alarma;
    }

    public void setHora_Alarma(int hora_Alarma) {
        this.hora_Alarma = hora_Alarma;
    }

    public int getMinuto_Alarma() {
        return minuto_Alarma;
    }

    public void setMinuto_Alarma(int minuto_Alarma) {
        this.minuto_Alarma = minuto_Alarma;
    }

    public String getMensaje_Alarma() {
        return mensaje_Alarma;
    }

    public void setMensaje_Alarma(String mensaje_Alarma) {
        this.mensaje_Alarma = mensaje_Alarma;
    }
/**************************************************************************/
    /**
     * Método sobreescrito de ActionListener que actúa como escucha del evento.
     * Cada vez que se produce el evento refresca el texto con el nuevo mensaje
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        date = new Date();

        if (isFormatoHora()) {
            formatoDeHora = new SimpleDateFormat("HH:mm:ss");//Formato de 24 horas
        } else {
            formatoDeHora = new SimpleDateFormat("hh:mm:ss");//Formato de 12 horas
        }
        if (isAlarma()) {
            if (date.getHours() == getHora_Alarma() && date.getMinutes() == getMinuto_Alarma() && date.getSeconds() == 0) {
                visualizaMensajeAlarma();
            }
            visualizaAlarma(formatoDeHora);
        } else {
            visualizaDigitosReloj(formatoDeHora);
        }

    }

    /**
     * Método que muestra en un textArea los digitos del reloj
     * @param formatoDeHora 12 o 24 horas
     */
    public void visualizaDigitosReloj(DateFormat formatoDeHora) {
        //Muestra el texto en la etiqueta con formato html
        this.setText("<html><body><center><h1 text='green'>" + formatoDeHora.format(date) + "</h1></center></body></html>");
    }

    /**
     * Método que muestra en un textArea los digitos del reloj y los digitos de 
     * la alarma
     * @param formatoDeHora 12 o 24 horas
     */
    public void visualizaAlarma(DateFormat formatoDeHora) {
        //Muestra el texto en la etiqueta con formato html
        this.setText("<html><body><center><h1 text='green'>" + formatoDeHora.format(date)
                + "</h1><br><h2 text='orange'>Alarma activada</h2><p text='orange'>Hora "
                + Integer.toString(getHora_Alarma()) + " : "
                + Integer.toString(getMinuto_Alarma()) + "</p></body></html>");
    }

    /**
     * Método que muestra en un JOptionPane el mensaje de la alarma
     */
    public void visualizaMensajeAlarma() {
        //Muestra el panel con el mensaje definido por el usuario y un icono despertador
        JOptionPane.showMessageDialog(labelFor, getMensaje_Alarma() + "\n" + date, "Ring Ring Ring !!!!!", JOptionPane.INFORMATION_MESSAGE, icono);
    }
}
