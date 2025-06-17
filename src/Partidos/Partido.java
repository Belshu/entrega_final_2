package Partidos;

import Empleados.Jugador;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que crea un partido con sus atributos de: - String: nombre del rival -
 * Date: fecha del partido - int: goles marcados y goles del rival - boolean: si
 * es local o no - ArrayList: la lista de jugadores participantes de nuestro
 * equipo
 *
 * @author Santiago Colomer Andreu
 *
 */
public class Partido {

    // ATRIBUTOS
    private String nombreRival;
    private Date fecha;
    private int goles, golRival;
    private boolean esLocal;
    private ArrayList<Jugador> listaJugadores;

    /**
     * Recoge el nombre del rival, la fecha, los goles marcados por nosotros,
     * los del rival, si es local el partido y una lista de jugadores que
     * participaron de nuestro equipo
     *
     * @param rival String que recoge el nombre del equipo nombreRival
     * @param fecha Date que recoge la fecha de celebracion del partido
     * @param goles int que recoge el numero de goles nuestros
     * @param golRival int que recoge el numero de goles rivales
     * @param esLocal boolean que determina si somos o no locales
     * @param listaJugadores ArrayList que recoge todos los jugadores enemigos
     * participantes
     *
     */
    public Partido(String rival, Date fecha, int goles, int golRival,
            boolean esLocal, ArrayList<Jugador> listaJugadores) {
        this.nombreRival = rival;
        this.fecha = fecha;
        this.goles = goles;
        this.golRival = golRival;
        this.esLocal = esLocal;
        this.listaJugadores = listaJugadores;
    }

    /**
     * Metodo que devuelve el String nombreRival
     *
     * @return String
     *
     */
    public String getNombreRival() {
        return nombreRival;
    }

    // GETTERS Y SETTERS
    /**
     * Metodo que asigna un valor al String nombreRival
     *
     * @param nombreRival que recoge el valor a asignar
     *
     */
    public void setNombreRival(String nombreRival) {
        this.nombreRival = nombreRival;
    }

    /**
     * Metodo que devuelve el Date fecha
     *
     * @return Date
     *
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Metodo que asigna un valor al Date fecha
     *
     * @param fecha que recoge el valor a asignar
     *
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo que devuelve el int goles
     *
     * @return int
     *
     */
    public int getGoles() {
        return goles;
    }

    /**
     * Metodo que asigna un valor al int goles
     *
     * @param goles que recoge el valor a asignar
     *
     */
    public void setGoles(int goles) {
        this.goles = goles;
    }

    /**
     * Metodo que devuelve el int golRival
     *
     * @return int
     *
     */
    public int getGolRival() {
        return golRival;
    }

    /**
     * Metodo que asigna un valor al int golRival
     *
     * @param golRival que recoge el valor a asignar
     *
     */
    public void setGolRival(int golRival) {
        this.golRival = golRival;
    }

    /**
     * Metodo que devuelve el boolean esLocal
     *
     * @return boolean
     *
     */
    public boolean isEsLocal() {
        return esLocal;
    }

    /**
     * Metodo que asigna un valor al boolean esLocal
     *
     * @param esLocal que recoge el valor a asignar
     *
     */
    public void setEsLocal(boolean esLocal) {
        this.esLocal = esLocal;
    }

    /**
     * Metodo que devuelve el ArrayList listaJugadores
     *
     * @return ArrayList
     *
     */
    public ArrayList<Jugador> getListaJugador() {
        return listaJugadores;
    }

    /**
     * Metodo que asigna un valor al ArrayList listaJugadores
     *
     * @param listaJugadores que recoge el valor a asignar
     *
     */
    public void setListaJugador(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos de la clase
     *
     * @return String
     *
     */
    @Override
    public String toString() {
        StringBuilder participantes = new StringBuilder();

        for (Jugador jugador : listaJugadores) {
            participantes.append(jugador.getNombre()).append(" ").append(jugador.getApellidos()).append(", ");
        }

        if (participantes.length() > 0) {
            participantes.setLength(participantes.length() - 2);
        }

        return "Partido -> Rival: " + nombreRival
                + " | Fecha: " + fecha
                + " | Resultado: " + (esLocal ? "Local" : "Visitante")
                + " " + goles + "-" + golRival
                + " | Jugadores Participantes: [" + participantes + "]";
    }
}
