package modelo;

/**
 * Clase Vuelo que contiene los campos de la tabla vuelos de la bbdd En ella se
 * definen los métodos getter y setter que nos van a ayudar a introducir y
 * recuperar la info
 *
 * @author Daniel Paz Lorenzo
 */
public class Vuelo {

    //Deeclaración de atributos de la clase
    String cod_vuelo = null;
    String hora_salida;
    String destino;
    String procedencia;
    int plazas_fumador;
    int plazas_nofumador;
    int plazas_turista;
    int plazas_primera;

    /**
     * Constructor de la clase Vuelo
     */
    public Vuelo() {
    }

    public String getCod_vuelo() {
        return cod_vuelo;
    }

    public void setCod_vuelo(String cod_vuelo) {
        this.cod_vuelo = cod_vuelo;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public int getPlazas_fumador() {
        return plazas_fumador;
    }

    public void setPlazas_fumador(int plazas_fumador) {
        this.plazas_fumador = plazas_fumador;
    }

    public int getPlazas_nofumador() {
        return plazas_nofumador;
    }

    public void setPlazas_nofumador(int plazas_nofumador) {
        this.plazas_nofumador = plazas_nofumador;
    }

    public int getPlazas_turista() {
        return plazas_turista;
    }

    public void setPlazas_turista(int plazas_turista) {
        this.plazas_turista = plazas_turista;
    }

    public int getPlazas_primera() {
        return plazas_primera;
    }

    public void setPlazas_primera(int plazas_primera) {
        this.plazas_primera = plazas_primera;
    }

}
