package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Esta clase que hereda de JFrame muestra por pantalla una ventana que contiene
 * distintos componentes destinados a hacer peticiones al usuario para hacer la
 * reserva
 *
 * @author Daniel Paz Lorenzo
 */
public class VentanaReservas extends JFrame implements ActionListener {

    //Recogemos la resolución del dispositivo
    Toolkit miPantalla = Toolkit.getDefaultToolkit();
    Dimension tamanoMiPantalla = miPantalla.getScreenSize();

    public VentanaReservas() {
        //Aplicamos una reducción proporcional al tamaño de resolución de la
        //pantalla para que quede más pequeña y centrada y la hacemos visible
        setBounds(this.tamanoMiPantalla.width / 3, this.tamanoMiPantalla.height / 2, this.tamanoMiPantalla.width / 2, this.tamanoMiPantalla.height / 2);
        initComponents();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipoCocina = new javax.swing.ButtonGroup();
        textoNombre = new javax.swing.JTextField();
        textoTelefono = new javax.swing.JTextField();
        etNombre = new javax.swing.JLabel();
        etTelefono = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        spinnerDia = new javax.swing.JSpinner();
        etiqDia = new javax.swing.JLabel();
        etiqFechaEvento = new javax.swing.JLabel();
        etiqTitulo = new javax.swing.JLabel();
        comboBoxTipoCocina = new javax.swing.JComboBox<>();
        spinnerNumPer = new javax.swing.JSpinner();
        etiqNumPer = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        enviarBoton = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        panelDatosCongreso = new javax.swing.JPanel();
        etiqCongreso = new javax.swing.JLabel();
        spinnerJornadas = new javax.swing.JSpinner();
        spinnerHab = new javax.swing.JSpinner();
        etiqJornadas = new javax.swing.JLabel();
        etiqHabitaciones = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        etiqTipoEvento = new javax.swing.JLabel();
        botonRadioBanquete = new javax.swing.JRadioButton();
        botonRadioJornada = new javax.swing.JRadioButton();
        radioBotonCongreso = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ventana de Reservas");
        setBackground(new java.awt.Color(51, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setLocation(this.tamanoMiPantalla.width/5,this.tamanoMiPantalla.height/5);

        textoNombre.setColumns(10);
        textoNombre.setToolTipText("nombre");
        textoNombre.setName("Nombre"); // NOI18N

        textoTelefono.setColumns(8);
        textoTelefono.setToolTipText("Teléfono");
        textoTelefono.setName("telefono"); // NOI18N

        etNombre.setFont(new java.awt.Font("Felix Titling", 0, 11)); // NOI18N
        etNombre.setText("Nombre: ");
        etNombre.setToolTipText("");

        etTelefono.setFont(new java.awt.Font("Felix Titling", 0, 11)); // NOI18N
        etTelefono.setText("Teléfono: ");
        etTelefono.setToolTipText("");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        spinnerDia.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(1629123494069L), new java.util.Date(1976278740000L), java.util.Calendar.DAY_OF_MONTH));
        spinnerDia.setToolTipText("fecha de reserva");

        etiqDia.setText("Día");

        etiqFechaEvento.setFont(new java.awt.Font("Felix Titling", 0, 11)); // NOI18N
        etiqFechaEvento.setText("Fecha del evento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqDia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqFechaEvento)
                    .addComponent(spinnerDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(etiqFechaEvento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqDia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        etiqTitulo.setFont(new java.awt.Font("Felix Titling", 1, 14)); // NOI18N
        etiqTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqTitulo.setText("RESERVA DEL SALÓN HABANA");
        etiqTitulo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        etiqTitulo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        etiqTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        comboBoxTipoCocina.setFont(new java.awt.Font("Footlight MT Light", 0, 11)); // NOI18N
        comboBoxTipoCocina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de cocina", "Bufé", "Carta", "Pedir cita con chef", "No precisa" }));
        comboBoxTipoCocina.setToolTipText("Elija el tipo de cocina que desea");

        spinnerNumPer.setModel(new javax.swing.SpinnerNumberModel(0, 0, 200, 1));
        spinnerNumPer.setToolTipText("Elija el número de personas que acudirán");

        etiqNumPer.setFont(new java.awt.Font("Felix Titling", 0, 11)); // NOI18N
        etiqNumPer.setText("Nº de personas (máx. 200)");

        enviarBoton.setText("Enviar reserva");
        enviarBoton.setToolTipText("");
        enviarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarBotonActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        panelDatosCongreso.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelDatosCongreso.setToolTipText("");

        etiqCongreso.setFont(new java.awt.Font("Felix Titling", 0, 11)); // NOI18N
        etiqCongreso.setText("Datos del congreso");
        etiqCongreso.setEnabled(false);

        spinnerJornadas.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        spinnerJornadas.setToolTipText("Seleccione número de jornadas");
        spinnerJornadas.setEnabled(false);

        spinnerHab.setModel(new javax.swing.SpinnerNumberModel(0, 0, 150, 1));
        spinnerHab.setToolTipText("Seleccione número de habitaciones");
        spinnerHab.setEnabled(false);

        etiqJornadas.setFont(new java.awt.Font("Felix Titling", 0, 11)); // NOI18N
        etiqJornadas.setText("Nº de jornadas (máx. 100)");
        etiqJornadas.setEnabled(false);

        etiqHabitaciones.setFont(new java.awt.Font("Felix Titling", 0, 11)); // NOI18N
        etiqHabitaciones.setText("Nº de habitaciones");
        etiqHabitaciones.setEnabled(false);

        javax.swing.GroupLayout panelDatosCongresoLayout = new javax.swing.GroupLayout(panelDatosCongreso);
        panelDatosCongreso.setLayout(panelDatosCongresoLayout);
        panelDatosCongresoLayout.setHorizontalGroup(
            panelDatosCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosCongresoLayout.createSequentialGroup()
                .addComponent(etiqJornadas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosCongresoLayout.createSequentialGroup()
                        .addComponent(etiqCongreso)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDatosCongresoLayout.createSequentialGroup()
                        .addComponent(spinnerJornadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(etiqHabitaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelDatosCongresoLayout.setVerticalGroup(
            panelDatosCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosCongresoLayout.createSequentialGroup()
                .addComponent(etiqCongreso)
                .addGap(18, 18, 18)
                .addGroup(panelDatosCongresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerJornadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqJornadas)
                    .addComponent(etiqHabitaciones))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setToolTipText("Elija el tipo de evento que desea");

        etiqTipoEvento.setBackground(new java.awt.Color(204, 204, 204));
        etiqTipoEvento.setFont(new java.awt.Font("Felix Titling", 0, 11)); // NOI18N
        etiqTipoEvento.setText("Tipo de evento");

        grupoTipoCocina.add(botonRadioBanquete);
        botonRadioBanquete.setFont(new java.awt.Font("Footlight MT Light", 0, 11)); // NOI18N
        botonRadioBanquete.setText("Banquete");
        botonRadioBanquete.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                botonRadioBanqueteItemStateChanged(evt);
            }
        });

        grupoTipoCocina.add(botonRadioJornada);
        botonRadioJornada.setFont(new java.awt.Font("Footlight MT Light", 0, 11)); // NOI18N
        botonRadioJornada.setText("Jornada");
        botonRadioJornada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                botonRadioJornadaItemStateChanged(evt);
            }
        });

        grupoTipoCocina.add(radioBotonCongreso);
        radioBotonCongreso.setFont(new java.awt.Font("Footlight MT Light", 0, 11)); // NOI18N
        radioBotonCongreso.setText("Congreso");
        radioBotonCongreso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBotonCongresoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonRadioJornada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiqTipoEvento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonRadioBanquete, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(radioBotonCongreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(etiqTipoEvento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonRadioBanquete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(botonRadioJornada)
                .addGap(10, 10, 10)
                .addComponent(radioBotonCongreso))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(etNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(etTelefono))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiqTitulo)
                        .addGap(36, 36, 36)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enviarBoton)
                .addGap(46, 46, 46))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiqNumPer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerNumPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboBoxTipoCocina, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(panelDatosCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqTitulo)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etNombre)
                    .addComponent(etTelefono))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiqNumPer, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerNumPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTipoCocina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatosCongreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(enviarBoton))
                .addGap(23, 23, 23))
        );

        textoNombre.getAccessibleContext().setAccessibleName("");
        textoTelefono.getAccessibleContext().setAccessibleName("");
        textoTelefono.getAccessibleContext().setAccessibleDescription("teléfono");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        //Cierra la ventana
        this.dispose();

    }//GEN-LAST:event_botonCancelarActionPerformed

    private void enviarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarBotonActionPerformed
        //Muestra un cuadro de diálogo como respuesta al evento de pulsar el botón
        JOptionPane.showConfirmDialog(null, "La reserva se ha enviado correctamente",
                "MENSAJE DE INFORMACIÓN", JOptionPane.CLOSED_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_enviarBotonActionPerformed


    private void radioBotonCongresoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBotonCongresoItemStateChanged
        this.etiqCongreso.setEnabled(true);
        this.etiqJornadas.setEnabled(true);
        this.etiqHabitaciones.setEnabled(true);
        this.spinnerJornadas.setEnabled(true);
        this.spinnerHab.setEnabled(true);
    }//GEN-LAST:event_radioBotonCongresoItemStateChanged

    private void botonRadioJornadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_botonRadioJornadaItemStateChanged
        this.etiqCongreso.setEnabled(false);
        this.etiqJornadas.setEnabled(false);
        this.etiqHabitaciones.setEnabled(false);
        this.spinnerJornadas.setEnabled(false);
        this.spinnerHab.setEnabled(false);
    }//GEN-LAST:event_botonRadioJornadaItemStateChanged

    private void botonRadioBanqueteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_botonRadioBanqueteItemStateChanged
        this.etiqCongreso.setEnabled(false);
        this.etiqJornadas.setEnabled(false);
        this.etiqHabitaciones.setEnabled(false);
        this.spinnerJornadas.setEnabled(false);
        this.spinnerHab.setEnabled(false);
    }//GEN-LAST:event_botonRadioBanqueteItemStateChanged

    @Override
    public void actionPerformed(ActionEvent e) {
        initComponents();
        setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JRadioButton botonRadioBanquete;
    private javax.swing.JRadioButton botonRadioJornada;
    private javax.swing.JComboBox<String> comboBoxTipoCocina;
    private javax.swing.JButton enviarBoton;
    private javax.swing.JLabel etNombre;
    private javax.swing.JLabel etTelefono;
    private javax.swing.JLabel etiqCongreso;
    private javax.swing.JLabel etiqDia;
    private javax.swing.JLabel etiqFechaEvento;
    private javax.swing.JLabel etiqHabitaciones;
    private javax.swing.JLabel etiqJornadas;
    private javax.swing.JLabel etiqNumPer;
    private javax.swing.JLabel etiqTipoEvento;
    private javax.swing.JLabel etiqTitulo;
    private javax.swing.ButtonGroup grupoTipoCocina;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelDatosCongreso;
    private javax.swing.JRadioButton radioBotonCongreso;
    private javax.swing.JSpinner spinnerDia;
    private javax.swing.JSpinner spinnerHab;
    private javax.swing.JSpinner spinnerJornadas;
    private javax.swing.JSpinner spinnerNumPer;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoTelefono;
    // End of variables declaration//GEN-END:variables

}
