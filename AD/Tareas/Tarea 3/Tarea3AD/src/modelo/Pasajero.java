package modelo;

/**
 * Clase pasajeros que contiene los campos de la tabla pasajeros de la bbdd En
 * ella se definen los métodos getter y setter que nos van a ayudar a introducir
 * y recuperar la info
 *
 * @author Daniel Paz Lorenzo
 */
public class Pasajero {

    //Declaración de atributos de clase
    int num;
    String cod_vuelo;
    String tipo_plaza;
    String fumador;

    /**
     * Constructor de clase pasajero
     */
    public Pasajero() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCod_vuelo() {
        return cod_vuelo;
    }

    public void setCod_vuelo(String cod_vuelo) {
        this.cod_vuelo = cod_vuelo;
    }

    public String getTipo_plaza() {
        return tipo_plaza;
    }

    public void setTipo_plaza(String tipo_plaza) {
        this.tipo_plaza = tipo_plaza;
    }

    public String getFumador() {
        return fumador;
    }

    public void setFumador(String fumador) {
        this.fumador = fumador;
    }

}
