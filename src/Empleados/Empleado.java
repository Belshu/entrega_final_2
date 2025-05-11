package Empleados;

import java.util.Date;
import Contables.Nomina;
import java.util.ArrayList;

/**
 * Clase inicial que reúne todos los métodos y atributos, siendo estos últimos:
 * - String: el dni y nombre del empleado. El dni será final 
 * - int: el número de teléfono 
 * - boolean: indicación de si está eliminado o no 
 * - Date: indicación de la fecha de eliminación en caso que lo sea 
 * - ArrayList: todas las nóminas asociadas a él TODOS los atributos serán protegidos
 *
 * @author Santiago Colomer Andreu
 *
 */
public class Empleado {

    // ATRIBUTOS
    protected final String dni;
    protected String nombre;
    protected int telf;
    protected boolean eliminado;
    protected Date fechaEliminacion;
    protected ArrayList<Nomina> nominas;

    /**
     * CONSTRUCTOR: recoge el dni, nombre y número de teléfono, inicializa la
     * lista de nóminas y declara el estado de eliminación del jugador en
     * "false"
     *
     * @param dni String que recoge el dato de los empleados
     * @param nombre String que recoge el dato de los empleados
     * @param telf int que recoge el dato de los empleados
     *
     */
    public Empleado(String dni, String nombre, int telf) {
        this.dni = dni;
        this.nombre = nombre;
        this.telf = telf;
        this.eliminado = false;
        this.nominas = new ArrayList<>();
    }

    // GETTERS Y SETTERS
    /**
     * Método que cambia el booleano "estaEliminado" a "true e inicializa el
     * Date "fechaEliminacion"
     *
     */
    public void eliminar() {
        this.eliminado = true;
        this.fechaEliminacion = new Date();
    }

    /**
     * Método que agrega una nómina a la lista
     *
     * @param n que recoge la nómina a agregar
     *
     */
    public void agregarNomina(Nomina n) {
        this.nominas.add(n);
    }

    /**
     * Método que elimina una nómina de la lista
     *
     * @param n que recoge la nómina a eliminar
     *
     */
    public void eliminarNomina(Nomina n) {
        this.nominas.remove(n);
    }

    public String getDni() {
        return dni;
    }

    /**
     * Método que devuelve el String nombre
     *
     * @return String
     *
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que asigna un valor al String nombre
     *
     * @param nombre que recoge el valor a asignar
     *
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que devuelve el int telf
     *
     * @return int
     *
     */
    public int getTelf() {
        return telf;
    }

    /**
     * Método que asigna un valor al int telf
     *
     * @param telf que recoge el valor para asignar
     *
     */
    public void setTelf(int telf) {
        this.telf = telf;
    }

    /**
     * Método que devuelve el boolean eliminado
     *
     * @return boolean
     *
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * Método que asigna un valor al boolean eliminado
     *
     * @param eliminado que recoge el valor para asignar
     *
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * Método que devuelve el Date fechaEliminacion
     *
     * @return Date
     *
     */
    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    /**
     * Método que asigna un valor al Date fechaEliminacion
     *
     * @param fechaEliminacion que recoge el valor para asignar
     *
     */
    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    /**
     * Método que devuelve el ArrayList nominas
     *
     * @return ArrayList
     *
     */
    public ArrayList<Nomina> getNominas() {
        return nominas;
    }

    /**
     * Método que asigna un valor al ArrayList nominas
     *
     * @param nominas que recoge el valor para asignar
     *
     */
    public void setNominas(ArrayList<Nomina> nominas) {
        this.nominas = nominas;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos de la clase
     *
     * @return String
     *
     */
    @Override
    public String toString() {
        return "DNI: " + dni + " | nombre: " + nombre + " | Telefono: "
                + telf + (eliminado ? " | Eliminado el "
                        + fechaEliminacion + " |" : "");
    }

}
