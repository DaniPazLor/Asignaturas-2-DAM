/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorialslick;

import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.Input.*;
import org.newdawn.slick.tiled.*;

/**
 * Proyecto vacío con Slick
 *
 * @author lrlopez
 */
public class TutorialSlick extends BasicGame {

    private TiledMap mapa;
    private float despX, despY;
    private float jugadorX, jugadorY;
    private SpriteSheet cuadros;
    private SpriteSheet cuadrosNPCS;
    private Animation jugador;
    private Animation jugadorArriba;
    private Animation jugadorDerecha;
    private Animation jugadorAbajo;
    private Animation jugadorIzquierda;

    private boolean[][] obstaculo;
    private int totalTilesWidth;
    private int totalTilesHeight;

    private int jugadorWidth, jugadorHeight;
    private int tileWidth, tileHeight;
    private int mapaWidth, mapaHeight;

    //Declaración variables NPCS
    private Animation nojugadorArriba;

    private Animation nojugadorDerecha;

    private Animation nojugadorAbajo;

    private Animation nojugadorIzquierda;

    private Animation[] nojugador;

    private float[] nojugadorX;

    private float[] nojugadorY;

    private float[] destinoX;

    private float[] destinoY;

    private static final int numeroNPCs = 4;

    public TutorialSlick(String name) {
        super(name);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
        try {
            AppGameContainer container = new AppGameContainer(new TutorialSlick("PMDM - Proyecto Slick"));
            container.setDisplayMode(800, 608, false);
            container.setTargetFrameRate(60);
            container.setVSync(true);
            container.start();
        } catch (SlickException e) {
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        mapa = new TiledMap("data/mapatutorial.tmx", "data");
        cuadros = new SpriteSheet("data/heroe.png", 24, 32);

        jugadorArriba = new Animation(cuadros, 0, 0, 2, 0, true, 150, false);
        jugadorDerecha = new Animation(cuadros, 0, 1, 2, 1, true, 150, false);
        jugadorAbajo = new Animation(cuadros, 0, 2, 2, 2, true, 150, false);
        jugadorIzquierda
                = new Animation(cuadros, 0, 3, 2, 3, true, 150, false);
        jugador = jugadorAbajo;

        despX = 0;
        despY = 0;
        jugadorX = 325;
        jugadorY = 200;

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
        jugadorWidth = jugador.getWidth();
        jugadorHeight = jugador.getHeight();
        tileWidth = mapa.getTileWidth();
        tileHeight = mapa.getTileHeight();

        //Animar NPCS
        cuadrosNPCS = new SpriteSheet("data/robot.png", 24, 32);
        nojugadorArriba = new Animation(cuadrosNPCS, 0, 0, 2, 0, true, 150, true);

        nojugadorDerecha = new Animation(cuadrosNPCS, 0, 1, 2, 1, true, 150, true);

        nojugadorAbajo = new Animation(cuadrosNPCS, 0, 2, 2, 2, true, 150, true);

        nojugadorIzquierda
                = new Animation(cuadrosNPCS, 0, 3, 2, 3, true, 150, true);

        nojugador = new Animation[numeroNPCs];

        nojugadorX = new float[numeroNPCs];

        nojugadorY = new float[numeroNPCs];

        destinoX = new float[numeroNPCs];

        destinoY = new float[numeroNPCs];

        for (int i = 0; i < numeroNPCs; i++) {

            nojugador[i] = nojugadorAbajo;

            nojugadorX[i] = (float) (Math.random() * mapaWidth);

            nojugadorY[i] = (float) (Math.random() * mapaHeight);

            destinoX[i] = (float) (Math.random() * mapaWidth);

            destinoY[i] = (float) (Math.random() * mapaHeight);

        }

    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input entrada = gc.getInput();

        float jugadorAnteriorX = jugadorX;
        float jugadorAnteriorY = jugadorY;
        if (entrada.isKeyDown(Input.KEY_DOWN)) {	// Tecla abajo
            jugadorY += delta * 0.1f;
            jugador = jugadorAbajo;
            jugador.update(delta);
        }
        if (entrada.isKeyDown(Input.KEY_UP)) {	// Tecla arriba
            jugadorY -= delta * 0.1f;
            jugador = jugadorArriba;
            jugador.update(delta);
        }
        if (entrada.isKeyDown(Input.KEY_RIGHT)) {	// Tecla derecha
            jugadorX += delta * 0.1f;
            jugador = jugadorDerecha;
            jugador.update(delta);
        }
        if (entrada.isKeyDown(Input.KEY_LEFT)) {	// Tecla izquierda
            jugadorX -= delta * 0.1f;
            jugador = jugadorIzquierda;
            jugador.update(delta);
        }

        //Calculos para evitar obstáculos
        if ((obstaculo[(int) ((jugadorX + 8) / tileWidth)][((int) (jugadorY + jugadorHeight) / tileHeight)])
                || (obstaculo[(int) ((jugadorX - 8 + jugadorWidth) / tileWidth)][((int) (jugadorY + jugadorHeight) / tileHeight)])) {

            jugadorX = jugadorAnteriorX;

            jugadorY = jugadorAnteriorY;

        }
        //Calculos para evitar que se salga del mapa
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
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.translate(despX, despY);
        mapa.render(0, 0, 0);
        mapa.render(0, 0, 1);
        jugador.draw(jugadorX, jugadorY);
        mapa.render(0, 0, 2);
        mapa.render(0, 0, 3);

//Renderizado NPCS
        for (int i = 0; i < numeroNPCs; i++) {

            nojugador[i].draw(nojugadorX[i], nojugadorY[i]);

        }

    }

}
