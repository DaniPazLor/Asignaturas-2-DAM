package mi_Componente;

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

    //Declaración de objetos de clase
    private Timer segundero;
    private Date date = new Date();
    private Icon icono = new ImageIcon(getClass().getResource("Despertador.png"));
//    DateFormat formatoDeHora;
    //Declaración de variables de clase
    private boolean formatoHora;
    private boolean alarma;
    private int hora_Alarma;
    private int minuto_Alarma;
    private String mensaje_Alarma;

    /**
     * Constructor de la clase que muestra en modo texto los digitos del reloj
     */
    public RelojDigital() {
        //Inicializamos las variables
        setFormatoHora(true);
        setHora_Alarma(0);
        setMinuto_Alarma(0);
        setMensaje_Alarma("LEVANTATEEEEEE !!!!!");
        DateFormat formatoDeHora;

        if (isFormatoHora()) {
            formatoDeHora = new SimpleDateFormat("HH:mm:ss");
            } else {
            formatoDeHora = new SimpleDateFormat("hh:mm:ss");
            }
            if (isAlarma()) {
                if (date.getHours() == getHora_Alarma() && date.getMinutes() == getMinuto_Alarma() && date.getSeconds() == 0) {

                    visualizaMensajeAlarma();
                }
                visualizaAlarma(formatoDeHora);
            } else {
                visualizaDigitosReloj(formatoDeHora);
            }
        segundero = new Timer(1000, this);
        segundero.start();
        System.out.println("comienzo hilo");

    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
DateFormat formatoDeHora;
        
        System.out.println("evento iniciado");
        if (isFormatoHora()) {
            formatoDeHora = new SimpleDateFormat("HH:mm:ss");
            } else {
            formatoDeHora = new SimpleDateFormat("hh:mm:ss");
            }
            if (isAlarma()) {
                if (date.getHours() == getHora_Alarma() && date.getMinutes() == getMinuto_Alarma() && date.getSeconds() == 0) {

                    visualizaMensajeAlarma();
                }
                visualizaAlarma(formatoDeHora);
            } else {
                visualizaDigitosReloj(formatoDeHora);
            }

//        
//            if (isAlarma()) {
//                if (date.getHours() == getHora_Alarma() && date.getMinutes() == getMinuto_Alarma() && date.getSeconds() == 0) {
//                    visualizaMensajeAlarma();
//                }
//                visualizaAlarma(formatoDeHora);
//            } else {
//                visualizaDigitosReloj(formatoDeHora);
//            }
        

    }

    public void visualizaDigitosReloj(DateFormat formatoDeHora) {
        this.setText("<html><body><center><h1 text='green'>" + formatoDeHora.format(date) + "</h1></center></body></html>");
    }

    public void visualizaAlarma(DateFormat formatoDeHora) {
        this.setText("<html><body><center><h1 text='green'>" + formatoDeHora.format(date)
                + "</h1><br><h2 text='orange'>Alarma activada</h2><p text='orange'>Hora "
                + Integer.toString(getHora_Alarma()) + " : "
                + Integer.toString(getMinuto_Alarma()) + "</p></body></html>");
    }

    public void visualizaMensajeAlarma() {
        JOptionPane.showMessageDialog(labelFor, getMensaje_Alarma() + "\n" + date, "Ring Ring Ring !!!!!", JOptionPane.INFORMATION_MESSAGE, icono);

    }
}
