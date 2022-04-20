/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoRobots;

import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.tiled.*;

/**
 * Proyecto vacío con Slick
 *
 * @author lrlopez
 */
public class Principal extends BasicGame {

    // Tilemap
    private TiledMap mapa;

    // Estado del jugador
    private float despX, despY;
    private float jugadorX, jugadorY;
    private SpriteSheet cuadros;
//    private SpriteSheet cuadrosNPCS;
    private SpriteSheet cuadrosRobot;
    private Animation jugador;
    private Animation jugadorArriba;
    private Animation jugadorDerecha;
    private Animation jugadorAbajo;
    private Animation jugadorIzquierda;
    private boolean jugadorVivo;

    // Estado de los robots
    private Animation[] robot;
    private Animation robotArriba;
    private Animation robotDerecha;
    private Animation robotAbajo;
    private Animation robotIzquierda;
    private float[] robotX, robotY;
    private boolean[] robotVivo;
    private int numeroRobotsVivos;

    private boolean[][] obstaculo;
    private int totalTilesWidth;
    private int totalTilesHeight;

    private int jugadorWidth, jugadorHeight;
    private int tileWidth, tileHeight;
    private int mapaWidth, mapaHeight;

    // Escritura de cadenas
    private UnicodeFont fuente;

    // Contador de tiempo
    private long tiempo;

    //Musica y efectos sonoros
    private Music musica;
    private Sound sonido;
    private Sound sonido2;

    //Rectángulos de colision robots y jugador
//    private Rectangle [] rectRob;
    private Rectangle rectRobot0;
    private Rectangle rectRobot1;
    private Rectangle rectRobot2;
    private Rectangle rectRobot3;
    private Rectangle rectJugador;

    public Principal(String name) {
        super(name);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
        try {
            AppGameContainer container = new AppGameContainer(new Principal("PMDM - Proyecto Slick"));
            container.setDisplayMode(800, 608, false);
            container.setTargetFrameRate(60);
            container.setVSync(true);
            container.setShowFPS(false);
            container.setUpdateOnlyWhenVisible(false);
            container.start();
        } catch (SlickException e) {
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        mapa = new TiledMap("data/mapatutorial.tmx", "data");
        cuadros = new SpriteSheet("data/heroe.png", 24, 32);
        cuadrosRobot = new SpriteSheet("data/robot.png", 24, 32);
        musica = new Music("data/Cancion_Ejemplo.xm");
        sonido = new Sound("data/FlameMagic.ogg");
        sonido2 = new Sound("data/BigBossDeath.ogg");
        musica.loop();

        //Carga animaciones de jugador
        jugadorArriba = new Animation(cuadros, 0, 0, 2, 0, true, 150, false);
        jugadorDerecha = new Animation(cuadros, 0, 1, 2, 1, true, 150, false);
        jugadorAbajo = new Animation(cuadros, 0, 2, 2, 2, true, 150, false);
        jugadorIzquierda = new Animation(cuadros, 0, 3, 2, 3, true, 150, false);
        jugador = jugadorAbajo;

        // cargar animaciones del robot
        robotArriba = new Animation(cuadrosRobot, 0, 0, 2, 0, true, 150, true);
        robotDerecha = new Animation(cuadrosRobot, 0, 1, 2, 1, true, 150, true);
        robotAbajo = new Animation(cuadrosRobot, 0, 2, 2, 2, true, 150, true);
        robotIzquierda = new Animation(cuadrosRobot, 0, 3, 2, 3, true, 150, true);

        // estado inicial del jugador
        despX = 0;
        despY = 0;
        jugadorX = 325;
        jugadorY = 200;
        jugadorVivo = true;
        rectJugador = new Rectangle(jugadorX, jugadorY, (jugador.getWidth() - 6), jugador.getHeight());

        // estado inicial de los robots       
        robot = new Animation[4];
        robotX = new float[4];
        robotY = new float[4];
        robotVivo = new boolean[4];
        numeroRobotsVivos = 4;

        // los colocamos mirando al jugador y en las 4 esquinas
        robot[0] = robotDerecha;
        robot[1] = robotIzquierda;
        robot[2] = robotDerecha;
        robot[3] = robotIzquierda;

        robotX[0] = 40;
        robotX[1] = 596;
        robotX[2] = 40;
        robotX[3] = 596;

        robotY[0] = 84;
        robotY[1] = 84;
        robotY[2] = 300;
        robotY[3] = 400;

        //Rectángulos de colisiones
        rectRobot0 = new Rectangle(robotX[0], robotY[0], (robot[0].getWidth() - 6), robot[0].getHeight() - 8);
        rectRobot1 = new Rectangle(robotX[1], robotY[1], robot[1].getWidth() - 6, robot[1].getHeight() - 8);
        rectRobot2 = new Rectangle(robotX[2], robotY[2], robot[2].getWidth() - 6, robot[2].getHeight() - 8);
        rectRobot3 = new Rectangle(robotX[3], robotY[3], robot[3].getWidth() - 6, robot[3].getHeight() - 8);
//        rectRob[0] = new Rectangle(robotX[0], robotY[0], robot[0].getWidth(), robot[0].getHeight());
//        rectRob[1] = new Rectangle(robotX[1], robotY[1], robot[1].getWidth(), robot[1].getHeight());
//        rectRob[2] = new Rectangle(robotX[2], robotY[2], robot[2].getWidth(), robot[2].getHeight());
//        rectRob[3] = new Rectangle(robotX[3], robotY[3], robot[3].getWidth(), robot[3].getHeight());

        // indicamos que, de momento, los 4 robots están vivos
        for (int i = 0; i < 4; i++) {
            robotVivo[i] = true;
            robot[i].setLooping(false);
        }

        // cargar tipo de letra de la carpeta data y todos los símboles
        // que podamos necesitar        
        fuente = new UnicodeFont("data/tuffy.ttf", 28, false, false);
        // añade las letras ASCII estándar
        fuente.addAsciiGlyphs();
        // y ahora añadimos los caracteres españoles
        fuente.addGlyphs("áéíóúÁÉÍÓÚñÑ¡¿");
        // en Slick es obligatorio añadir un efecto para poder dibujar
        // texto. Añadimos un efecto vacío.
        fuente.getEffects().add(new ColorEffect(java.awt.Color.RED));
        // cargamos los símbolos del tipo de letra
        fuente.loadGlyphs();

        // a partir de ahora, llamado a fuente.drawString(x, y, texto) ¡podremos
        // escribir en el contenedor!
        // comenzar cuenta de tiempo. Apuntamos el número que contiene el
        // reloj del sistema en milisegundos. De esta forma, restando esta
        // cantidad a la cuenta actual nos dice el número de milisengudos
        // que han transcurrido.
        tiempo = System.currentTimeMillis();

        mapaWidth = mapa.getWidth() * mapa.getTileWidth();
        mapaHeight = mapa.getHeight() * mapa.getTileHeight();

        totalTilesWidth = mapa.getWidth();
        totalTilesHeight = mapa.getHeight();
        obstaculo = new boolean[totalTilesWidth][totalTilesHeight];
        for (int x = 0; x < totalTilesWidth; x++) {
            for (int y = 0; y < totalTilesHeight; y++) {
                obstaculo[x][y] = (mapa.getTileId(x, y, 1) != 0);

            }
        }
        //Medidas jugador
        jugadorWidth = jugador.getWidth();
        jugadorHeight = jugador.getHeight();

        tileWidth = mapa.getTileWidth();
        tileHeight = mapa.getTileHeight();

    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input entrada = gc.getInput();

        // si no quedan robots o ha muerto el jugador, no actualizar nada
        if ((jugadorVivo == false) || (numeroRobotsVivos == 0)) {
            return;
        }
        //Almacena posición anterior de jugador
        float jugadorAnteriorX = jugadorX;
        float jugadorAnteriorY = jugadorY;
        //Almacena posiciones anteriores de robot
        float[] robotAnteriorX = new float[4];
        float[] robotAnteriorY = new float[4];
        for (int i = 0; i < 4; i++) {
            robotAnteriorX[i] = robotX[i];
            robotAnteriorY[i] = robotY[i];
        }

        // movimiento del jugador
        if (entrada.isKeyDown(Input.KEY_DOWN)) {	// Tecla abajo
            jugadorY += delta * 0.1f;
            jugador = jugadorAbajo;
            jugador.update(delta);
            rectJugador.setY(jugadorY);
        }
        if (entrada.isKeyDown(Input.KEY_UP)) {	// Tecla arriba
            jugadorY -= delta * 0.1f;
            jugador = jugadorArriba;
            jugador.update(delta);
            rectJugador.setY(jugadorY);
        }
        if (entrada.isKeyDown(Input.KEY_RIGHT)) {	// Tecla derecha
            jugadorX += delta * 0.1f;
            jugador = jugadorDerecha;
            jugador.update(delta);
            rectJugador.setX(jugadorX);
        }
        if (entrada.isKeyDown(Input.KEY_LEFT)) {	// Tecla izquierda
            jugadorX -= delta * 0.1f;
            jugador = jugadorIzquierda;
            jugador.update(delta);
            rectJugador.setX(jugadorX);
        }

        //---------------------------------------------------------------
        //Movimientos robot[0]
        //Movimiento del robot en eje x positivo
        if(robotVivo[0]){
        if (jugadorX > robotX[0]) {

            robotX[0] += delta * 0.07f;
            robot[0] = robotDerecha;
            rectRobot0.setX(robotX[0]);
            robot[0].update(delta);
        }
        //Movimiento del robot en eje x negativo
        if (jugadorX < robotX[0]) {

            robotX[0] -= delta * 0.07f;
            robot[0] = robotIzquierda;
            rectRobot0.setX(robotX[0]);
            robot[0].update(delta);
        }
        //Movimiento del robot en eje y positivo
        if (jugadorY > robotY[0]) {

            robotY[0] += delta * 0.07f;
            robot[0] = robotAbajo;
            rectRobot0.setY(robotY[0]);
            robot[0].update(delta);
        }

        //Movimiento del robot en eje y negativo
        if (jugadorY < robotY[0]) {

            robotY[0] -= delta * 0.07f;
            robot[0] = robotArriba;
            rectRobot0.setY(robotY[0]);
            robot[0].update(delta);
        }
        }else{
            rectRobot0 = new Rectangle(0, 0, 0, 0);
            rectRobot0.closed();
        }

        //----------------------------------------------------------------
        //---------------------------------------------------------------
        //Movimientos robot[1]
        //Movimiento del robot en eje x positivo
        if (robotVivo[1]) {
        if (jugadorX > robotX[1]) {

            robotX[1] += delta * 0.06f;
            robot[1] = robotDerecha;
            robot[1].update(delta);
            rectRobot1.setX(robotX[1]);
        }
        //Movimiento del robot en eje y positivo
        if (jugadorY > robotY[1]) {

            robotY[1] += delta * 0.06f;
            robot[1] = robotAbajo;
            robot[1].update(delta);
            rectRobot1.setY(robotY[1]);
        }
        //Movimiento del robot en eje x negativo
        if (jugadorX < robotX[1]) {

            robotX[1] -= delta * 0.06f;
            robot[1] = robotIzquierda;
            robot[1].update(delta);
            rectRobot1.setX(robotX[1]);
        }
        //Movimiento del robot en eje y negativo
        if (jugadorY < robotY[1]) {

            robotY[1] -= delta * 0.06f;
            robot[1] = robotArriba;
            robot[1].update(delta);
            rectRobot1.setY(robotY[1]);
        }
        }else{
            rectRobot1 = new Rectangle(0, 0, 0, 0);
            rectRobot1.closed();
        }

        //----------------------------------------------------------------
        //---------------------------------------------------------------
        //Movimientos robot[2]
        //Movimiento del robot en eje x positivo
        if (robotVivo[2]) {
        if (jugadorX > robotX[2]) {

            robotX[2] += delta * 0.05f;
            robot[2] = robotDerecha;
            robot[2].update(delta);
            rectRobot2.setX(robotX[2]);
        }
        //Movimiento del robot en eje y positivo
        if (jugadorY > robotY[2]) {

            robotY[2] += delta * 0.05f;
            robot[2] = robotAbajo;
            robot[2].update(delta);
            rectRobot2.setY(robotY[2]);
        }
        //Movimiento del robot en eje x negativo
        if (jugadorX < robotX[2]) {

            robotX[2] -= delta * 0.05f;
            robot[2] = robotIzquierda;
            robot[2].update(delta);
            rectRobot2.setX(robotX[2]);
        }
        //Movimiento del robot en eje y negativo
        if (jugadorY < robotY[2]) {

            robotY[2] -= delta * 0.05f;
            robot[2] = robotArriba;
            robot[2].update(delta);
            rectRobot2.setY(robotY[2]);
        }
        }else{
            rectRobot2 = new Rectangle(0, 0, 0, 0);
            rectRobot2.closed();
        }

        //----------------------------------------------------------------
        //---------------------------------------------------------------
        //Movimientos robot[3]
        //Movimiento del robot en eje x positivo
        if (robotVivo[3]) {
        if (jugadorX > robotX[3]) {

            robotX[3] += delta * 0.04f;
            robot[3] = robotDerecha;
            robot[3].update(delta);
            rectRobot3.setX(robotX[3]);
        }
        //Movimiento del robot en eje y positivo
        if (jugadorY > robotY[3]) {

            robotY[3] += delta * 0.04f;
            robot[3] = robotAbajo;
            robot[3].update(delta);
            rectRobot3.setY(robotY[3]);
        }
        //Movimiento del robot en eje x negativo
        if (jugadorX < robotX[3]) {

            robotX[3] -= delta * 0.04f;
            robot[3] = robotIzquierda;
            robot[3].update(delta);
            rectRobot3.setX(robotX[3]);
        }
        //Movimiento del robot en eje y negativo
        if (jugadorY < robotY[3]) {

            robotY[3] -= delta * 0.04f;
            robot[3] = robotArriba;
            robot[3].update(delta);
            rectRobot3.setY(robotY[3]);
        }
        }else{
            rectRobot3 = new Rectangle(0, 0, 0, 0);
            rectRobot3.closed();
        }

        //----------------------------------------------------------------
        //Calculos para evitar obstáculos el jugador
        if ((obstaculo[(int) ((jugadorX + 8) / tileWidth)][((int) (jugadorY + jugadorHeight) / tileHeight)])
                || (obstaculo[(int) ((jugadorX - 8 + jugadorWidth) / tileWidth)][((int) (jugadorY + jugadorHeight) / tileHeight)])) {

            jugadorX = jugadorAnteriorX;

            jugadorY = jugadorAnteriorY;

        }
        //Robot desaparece si choca contra obstáculo
        for (int i = 0; i < 4; i++) {
            if ((obstaculo[(int) ((robotX[i] + 8) / tileWidth)][((int) (robotY[i] + jugadorHeight) / tileHeight)])
                    || (obstaculo[(int) ((robotX[i] - 8 + jugadorWidth) / tileWidth)][((int) (robotY[i] + jugadorHeight) / tileHeight)])) {
//                        robotX[i] = robotAnteriorX[i];
//                        robotY[i] = robotAnteriorY[i];

                robot[i].stop();
                if (robotVivo[i]) {
//                    if (sonido.playing() == false) {
                        sonido.play();
//                    }
                    
                    robotVivo[i] = false;
                    numeroRobotsVivos -= 1;
                }

            }

        }

        //NO FUNCIONA
        //Evalúa si robot a tocado a jugador
        for (int i = 0; i < 4; i++) {
            if (rectJugador.intersects(rectRobot0) || rectJugador.intersects(rectRobot1) || rectJugador.intersects(rectRobot2) || rectJugador.intersects(rectRobot3)) {
                if (sonido.playing() == false) {
                    sonido2.play();
                }
                jugadorVivo = false;
                return;
            }
        }

        //Calculos para evitar que jugador se salga del mapa
        if ((jugadorX < 0 || jugadorY < 0 || jugadorX > (mapaWidth - jugadorWidth) || jugadorY > (mapaHeight - jugadorHeight))
                || (obstaculo[(int) (jugadorX / tileWidth)][((int) (jugadorY + jugadorHeight) / tileHeight)])
                || (obstaculo[(int) ((jugadorX + jugadorWidth) / tileWidth)][((int) (jugadorY + jugadorHeight) / tileHeight)])) {
            jugadorX = jugadorAnteriorX;
            jugadorY = jugadorAnteriorY;
        }
        despX = -(jugadorX - gc.getWidth() / 2);
        despY = -(jugadorY - gc.getHeight() / 2);

        //Establece los límites del mapa en el contenedor
        if (despX < (gc.getWidth() - mapaWidth)) {
            despX = gc.getWidth() - mapaWidth;
        }
        if (despX > 0) {
            despX = 0;
        }
        if (despY < (gc.getHeight() - mapaHeight)) {
            despY = gc.getHeight() - mapaHeight;
        }
        if (despY > 0) {
            despY = 0;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.translate(despX, despY);
        mapa.render(0, 0, 0);
        mapa.render(0, 0, 1);
        jugador.draw(jugadorX, jugadorY);
//        g.drawRect(rectJugador.getX(), rectJugador.getY(), rectJugador.getWidth(), rectJugador.getHeight());

        // dibujar robots si están vivos
        for (int i = 0; i < 4; i++) {
            if (robotVivo[i]) {
                robot[i].draw(robotX[i], robotY[i]);
//                g.drawRect(rectRobot0.getX(), rectRobot0.getY(), rectRobot0.getWidth(), rectRobot0.getHeight());
//                g.drawRect(rectRobot1.getX(), rectRobot1.getY(), rectRobot1.getWidth(), rectRobot1.getHeight());
//                g.drawRect(rectRobot2.getX(), rectRobot2.getY(), rectRobot2.getWidth(), rectRobot2.getHeight());
//                g.drawRect(rectRobot3.getX(), rectRobot3.getY(), rectRobot3.getWidth(), rectRobot3.getHeight());
            }
        }

        mapa.render(0, 0, 2);
        mapa.render(0, 0, 3);

        // dibujar robots restantes
        fuente.drawString(500, 10, "Quedan " + numeroRobotsVivos + " robots");

        if (numeroRobotsVivos == 0) {
            String gameOver = "¡Has ganado!";
            // dibujamos el texto centrado en el contenedor
            fuente.drawString((gc.getWidth() - fuente.getWidth(gameOver)) / 2, (gc.getHeight() - fuente.getHeight(gameOver)) / 2, gameOver, Color.yellow);
            return;
        } else {
            if (jugadorVivo == false) {
                String gameOver = "Fin de juego";
                // dibujamos el texto centrado en el contenedor
                fuente.drawString((gc.getWidth() - fuente.getWidth(gameOver)) / 2, (gc.getHeight() - fuente.getHeight(gameOver)) / 2, gameOver, Color.red);
                return;
            }
        }

        // dibujar tiempo transcurrido si no ha acabado el juego
        fuente.drawString(100, 10, "Tiempo: " + (System.currentTimeMillis() - tiempo) / 1000);

    }

}
