package PaquetePrincipal;

/******************************************************************************
 * clase que proporciona un hilo que escribe datos
 *
 * @author IMCG
 */
public class HiloEscritor extends Thread {

    private Semaforo semaforo;
    //semaforo de control de acceso recibido por el constructor. Le proporciona
    //al hilo el método de acceso para escribir datos, así como el método para
    //actualizar su estado cuando finaliza esa escritura

    /**************************************************************************/
    //constructor: se le pasa el nombre y el semáforo
    public HiloEscritor(String nombre, Semaforo s) {
        this.setName(nombre);
        this.semaforo = s;
    }
    @Override
    public void run() {
    //método con el comportamiento del hilo
        System.out.println(getName() + ": Intentando escribir");
        //mensaje para la Salida y comprobar funcionamiento
        semaforo.accesoEscribir();
        //el hilo ha escrito
        try {
            sleep((int) (Math.random()) * 50);
            //duerme el hilo un tiempo aleatorio antes de comunicar el fin de
            //la lectura, para dar ocasión de que los demás hilos hagan
            //intentos fallidos de lectura/escritura y comprobar funcionamiento
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        semaforo.escrituraFinalizada();
        //comunica al semáforo la finalización de la escritura
    }
}
