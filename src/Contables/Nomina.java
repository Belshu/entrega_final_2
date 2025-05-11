package Contables;

import java.util.ArrayList;

/**
 * Clase que reúne los métodos y atributos necesarios en la creación de la Nomina: 
 * - String: el dni del empleado 
 * - int: el día, el mes y el anio de la creación de la nómina 
 * - ArrayList: una lista de conceptos asignados a la nómina
 *
 * @author Ágata Gambín Póveda
 */
public class Nomina {

    // ATRIBUTOS
    private final String dniEmpleado;
    private final int dia, mes, anio;
    private final ArrayList<Concepto> conceptos;

    /**
     * Recoge el mes y el anio de la realizacion de la nomina
     *
     * @param dniEmpleado String que recoge el dni del empleado al que pertenece
     * @param mes int que recoge el mes de la creacion de la nomina
     * @param anio int que recoge el anio de la creacion de la nomina
     *
     */
    public Nomina(String dniEmpleado, int dia, int mes, int anio) {
        this.dniEmpleado = dniEmpleado;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.conceptos = new ArrayList<>();
    }

    // GETTERS Y SETTERS
    /**
     * Devuelve el String dniEmpleado
     * @return String
     */
    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public int getDia() {
        return dia;
    }

    /**
     * Devuelve el int mes
     * @return int
     */
    public int getMes() {
        return mes;
    }

    /**
     * Devuelve el int anio
     * @return int
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Devuelve el ArrayList conceptos
     * @return ArrayList
     */
    public ArrayList<Concepto> getConceptos() {
        return conceptos;
    }

    /**
     * Añade un concepto a la lista
     * @param concepto
     */
    public void agregarConcepto(Concepto concepto) {
        conceptos.add(concepto);
    }

    /**
     * Elimina un concepto de la lista
     * @param concepto
     */
    public void eliminarConcepto(Concepto concepto) {
        conceptos.remove(concepto);
    }

    /**
     * Devuelve un double que recoge el total de los importes de cada concepto
     * @return double
     */
    public double calcularTotal() {
        double total = 0;
        for (Concepto concepto : conceptos) {
            total += concepto.getImporte();
        }
        return total;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos de la clase
     * @return String
     */
    @Override
    public String toString() {

        String conceptosStr = "\n - ";

        for (Concepto c : conceptos) {
            conceptosStr += c.toString() + "\n - ";
        }

        return String.format("DNI Empleado: " + dniEmpleado + " | Dia/Mes/Año: %d/%d/%d\n "
                + "Conceptos:"
                + "%s Total: %.2f€", dia, mes, anio, conceptosStr, calcularTotal());

    }
}
