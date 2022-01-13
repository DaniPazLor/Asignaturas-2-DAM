package DIF.Clase23;

import java.awt.Graphics;
import java.beans.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Reloj extends JLabel implements Serializable ,Runnable{

    Thread hilo;
    private Boolean hora;

    public Reloj() {
        hilo = new Thread(this);
        hilo.start();
        setHora(true);//inicializo a verdadero para que en principio marque fecha y hora
        Date date = new Date();
        if (getHora() == true) {//si está en verdadero
            DateFormat formatoFecha = new SimpleDateFormat("HH:mm:ss");//generaré un formato con la fecha con mes mm
            this.setText(formatoFecha.format(date));//Le aplico este formato a la variable date
        } else {
            DateFormat formatoFechaMM = new SimpleDateFormat("hh:mm:ss");
            this.setText(formatoFechaMM.format(date));//Le aplico este formato a la variable date
        }
    }

    public Boolean getHora() {
        return hora;
    }

    public void setHora(Boolean hora) {
        this.hora = hora;
    }

    //Se empaqueta con click derecho sobre el proyecto y Clean and Build
    //Voy a sobreescribir un método para repintar la etiqueta cuando lo tickee
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            
                Date date = new Date();
                if (getHora() == true) {//si está en verdadero
                    DateFormat formatoFecha = new SimpleDateFormat("HH:mm:ss");//generaré un formato con la fecha con mes mm
                    this.setText(formatoFecha.format(date));//Le aplico este formato a la variable date
                } else {
                    DateFormat formatoFechaMM = new SimpleDateFormat("hh:mm:ss");
                    this.setText(formatoFechaMM.format(date));//Le aplico este formato a la variable date
                }

    }
    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (hilo == ct) {
            Date date = new Date();
                if (getHora() == true) {//si está en verdadero
                    DateFormat formatoFecha = new SimpleDateFormat("HH:mm:ss");//generaré un formato con la fecha con mes mm
                    this.setText(formatoFecha.format(date));//Le aplico este formato a la variable date
                } else {
                    DateFormat formatoFechaMM = new SimpleDateFormat("hh:mm:ss");
                    this.setText(formatoFechaMM.format(date));//Le aplico este formato a la variable date
                }
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                
            }
            
        }
    }

    

}
