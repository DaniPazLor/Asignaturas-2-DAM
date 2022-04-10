
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.media.CannotRealizeException;
import javax.media.Codec;
import javax.media.Format;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;
import javax.swing.JSlider;


public class ReproductorMP3 {
 
 Player player;
 int velocidad = 1000;
 int cuadro;
 Timer tiempo;
 TimerTask tarea;
 boolean reproduciendo;
 
 public ReproductorMP3(URL url){
  /* Esta es la direccion del codec que decodifica los mp3 */
  String jffmpegAudioDecoder = "net.sourceforge.jffmpeg.AudioDecoder";
  /* Cargamos el codec y lo guardamos en un objeto de tipo Codec */
  Codec codecAudio = null;
  try {
   codecAudio = (Codec) Class.forName(jffmpegAudioDecoder).newInstance();
  } catch (InstantiationException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  } catch (IllegalAccessException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  } catch (ClassNotFoundException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }
  /* Agregamos los codec al PlugInManager */
  PlugInManager.addPlugIn(jffmpegAudioDecoder,codecAudio.getSupportedInputFormats(),new Format[]{new AudioFormat("LINEAR")},PlugInManager.CODEC);
  try {
   player = Manager.createRealizedPlayer(url);
   
  } catch (NoPlayerException | CannotRealizeException | IOException e) {
   e.printStackTrace();
  }
 }
 
 //metodos play, stop y cerrar 
 
 public void play(){
  player.start();
  reproduciendo=true;
 }
 
 public void stop(){
  player.stop();
  reproduciendo=false;
 }
 
 public void cerrar(){
  if(reproduciendo){
   stop();
   player.close();
  }
 }
 
 //metodos de la barras de volumen y tiempo
 
 public void moverBarra(final JSlider slider){
  tiempo = new Timer();
  tarea = new TimerTask(){
   public void run(){
    cuadro = (int) Math.round(player.getMediaTime().getSeconds()*100 / player.getDuration().getSeconds());
    if(player.getMediaTime().getSeconds()==player.getDuration().getSeconds()||!reproduciendo){
     cuadro = 100;          
     stop();
    }else{
     slider.setValue(cuadro);
    }
   String tiempo = getTiempo();
   slider.setToolTipText(tiempo);
   }
  };
  tiempo.schedule(tarea, 0, velocidad);
 }
 
 public void pararBarra(final JSlider slider){
  if(reproduciendo){
  tarea.cancel();
  tiempo.cancel();
  }
 }
 
 public float getVolumen(){
  return player.getGainControl().getLevel();
  
 }
 
 public void setVolumen(float volumen){
  player.getGainControl().setLevel(volumen/100f);
 }
 
 public String getDuracion(){
  String cad="";
  double segundos = player.getDuration().getSeconds();
  int minutos = (int) (segundos / 60);
  for(int i=0;i<minutos;i++){
   segundos = segundos - 60;
  }
  int segundosAux =(int) (segundos);
  if(minutos<10)cad += "0";
  cad+=minutos+":";
  if(segundosAux<10)cad += "0";
  cad+=segundosAux;
  return cad;
 }
 
 public String getTiempo(){
  String cad="";
  double segundos = player.getMediaTime().getSeconds();
  int minutos = (int) (segundos / 60);
  for(int i=0;i<minutos;i++){
   segundos = segundos - 60;
  }
  int segundosAux =(int) (segundos);
  if(minutos<10)cad += "0";
  cad+=minutos+":";
  if(segundosAux<10)cad += "0";
  cad+=segundosAux;
  return cad;
 }
 
}