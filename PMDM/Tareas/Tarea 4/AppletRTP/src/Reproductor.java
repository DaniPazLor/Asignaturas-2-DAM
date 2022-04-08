
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Reproductor extends JFrame implements ActionListener,ChangeListener,ListSelectionListener{

 private JPanel panelSup,panelInf,panelMedio;
 private JButton botonPlay,botonStop;
 private JList areaCanciones;
 private File[] canciones;
 private JLabel labelCancion,labelVolumen, labelTiempo,labelDuracion;
 private ReproductorMP3 cancion;
 private JSlider sliderVolumen, sliderTiempo;
 private JScrollPane scroll;
 private int indice;
 private JMenuBar menuBar;
 private JMenu menu;
 private JMenuItem Abrir,Salir;
 
 public Reproductor(){
  Gui();
 }
 
 public void Gui(){
  super.setTitle("Reproductor");
  setSize(800, 400);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
//  ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("icono.png"));
//  setIconImage(icono.getImage());
  //creo el menu de la aplicacion
  menuBar = new JMenuBar();
  menuBar.setBackground(Color.BLACK);
  menuBar.setForeground(Color.white);
  menu = new JMenu("Archivo");
  menu.setBackground(Color.BLACK);
  menu.setForeground(Color.white);
  Abrir = new JMenuItem("Abrir");
  Abrir.setBackground(Color.BLACK);
  Abrir.setForeground(Color.white);
  Salir = new JMenuItem("Salir");
  Salir.setBackground(Color.BLACK);
  Salir.setForeground(Color.white);
  menu.add(Abrir);
  menu.add(Salir);
  menuBar.add(menu);
  setJMenuBar(menuBar);
//  panelSup = new PanelPersonal();
  panelSup = new JPanel();
  panelSup.setLayout(new FlowLayout());
  labelCancion = new JLabel("Ninguna cancion cargada");
  labelCancion.setForeground(Color.WHITE);
  panelSup.add(labelCancion);
  this.add(panelSup,BorderLayout.NORTH);
  panelMedio = new JPanel(new GridLayout(1,1,10,10));
  areaCanciones = new JList();
  scroll = new JScrollPane(areaCanciones);
  panelMedio.add(scroll);
  this.add(panelMedio,BorderLayout.CENTER);
//  panelInf = new PanelPersonal();
  panelInf = new JPanel();
  panelInf.setLayout(new FlowLayout());
  labelVolumen = new JLabel("Volumen");
  labelVolumen.setForeground(Color.white);
  sliderVolumen = new JSlider();
  sliderVolumen.setMinimum(0);
  sliderVolumen.setMaximum(60);
  botonPlay = new JButton();
//  ImageIcon iconoPlay = new ImageIcon(getClass().getClassLoader().getResource("imagenes/ButtonPlay.png"));
//  ImageIcon iconoPlayAux = new ImageIcon(iconoPlay.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)); 
//  botonPlay.setIcon(iconoPlayAux);
  botonPlay.setEnabled(false);
  botonStop = new JButton();
//  ImageIcon iconoParar = new ImageIcon(getClass().getClassLoader().getResource("imagenes/ButtonStop.png"));
//  ImageIcon iconoPararAux = new ImageIcon(iconoParar.getImage().getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING));
//  botonStop.setIcon(iconoPararAux);
  botonStop.setEnabled(false);
  labelTiempo = new JLabel("Tiempo");
  labelTiempo.setForeground(Color.white);
  sliderTiempo = new JSlider();
  labelDuracion = new JLabel(" ");
  labelDuracion.setForeground(Color.white);
  panelInf.add(labelVolumen);
  panelInf.add(sliderVolumen);
  panelInf.add(botonPlay);
  panelInf.add(botonStop);
  panelInf.add(labelTiempo);
  panelInf.add(sliderTiempo);
  panelInf.add(labelDuracion);
  this.add(panelInf,BorderLayout.SOUTH);
  botonPlay.addActionListener(this);
  botonStop.addActionListener(this);
  Abrir.addActionListener(this);
  Salir.addActionListener(this);
  sliderVolumen.addChangeListener(this);
  areaCanciones.addListSelectionListener(this);
  setVisible(true);
 }
 
 //metodos de los botones

 @Override
 public void actionPerformed(ActionEvent e) {
  if(e.getSource()==Abrir){
   URL url= null;
   JFileChooser jfc = new JFileChooser();
   jfc.setFileFilter(new FileNameExtensionFilter("Archivo de sonido .mp3","mp3"));
   jfc.setMultiSelectionEnabled(true);
   int apr = jfc.showOpenDialog(this);
   if(apr == jfc.APPROVE_OPTION){
    try {
     canciones = jfc.getSelectedFiles(); 
     url = canciones[0].getAbsoluteFile().toURL();
     labelCancion.setText(jfc.getSelectedFile().getName());
     areaCanciones.setListData(canciones);
    } catch (MalformedURLException e1) {
     e1.printStackTrace();
    }
   }
   cancion = new ReproductorMP3(url);
   labelDuracion.setText(cancion.getDuracion());
   botonPlay.setEnabled(true);
  }else if(e.getSource()==botonPlay){
   cancion.play();
   cancion.moverBarra(sliderTiempo);
   botonStop.setEnabled(true);
   botonPlay.setEnabled(false);
  }else if(e.getSource()==botonStop){
   cancion.stop();
   cancion.pararBarra(sliderTiempo);
   botonPlay.setEnabled(true);
   botonStop.setEnabled(false);
  }else if(e.getSource()==Salir){
   System.exit(0);
  }
 }

 public static void main(String[] args){
  Reproductor reproductor = new Reproductor();
 }

 //metodo que se ejecuta al hacer clic en un item del JList
 
 @Override
 public void stateChanged(ChangeEvent event) {
  if(event.getSource()==sliderVolumen){
   float volumen = sliderVolumen.getValue();
   cancion.setVolumen(volumen);
  }
 }

 @Override
 public void valueChanged(ListSelectionEvent event) {
  if(event.getSource()==areaCanciones){
   indice = areaCanciones.getSelectedIndex();
   cancion.cerrar();
   cancion.pararBarra(sliderTiempo);
   URL url=null;
   try {
    url = canciones[indice].toURL();
   } catch (MalformedURLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   cargarCancion(url);
  }
 }
 
 //metodo que carga la cancion elegida del JList
 
 public void cargarCancion(URL url){
  cancion = null;
  cancion = new ReproductorMP3(url);
  labelCancion.setText(canciones[indice].getName());
  cancion.play();
  cancion.moverBarra(sliderTiempo);
  botonPlay.setEnabled(false);
  botonStop.setEnabled(true);
  labelDuracion.setText(cancion.getDuracion());
 }
 
}