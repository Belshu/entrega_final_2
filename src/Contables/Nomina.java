package Contables;

import java.util.ArrayList;

/**
 * Utilizada para la creación y gestión de nóminas realizadas
 * @author Ágata Gambín Póveda
 */

public class Nomina {
   private final String dniEmpleado;
   private final int mes, anio;
   private final ArrayList <Concepto> conceptos;
   
       /**
     * Recoge el mes y el anio de la realizacion de la nomina
     * @param dniEmpleado String que recoge el dni del empleado al que pertenece
     * @param mes int que recoge el mes de la creacion de la nomina
     * @param anio int que recoge el anio de la creacion de la nomina
     **/
    public Nomina(String dniEmpleado, int mes, int anio) {
        this.dniEmpleado = dniEmpleado;
        this.mes = mes;
        this.anio = anio;
        this.conceptos = new ArrayList<>();
    }

    /**
     * Devuelve el String dniEmpleado
     * @return String
     **/
    public String getDniEmpleado() {
        return dniEmpleado;
    }

    /**
     * Devuelve el int mes
     * @return int
     **/
    public int getMes() {
        return mes;
    }

    /**
     * Devuelve el int anio
     * @return int
     **/
    public int getAnio() {
        return anio;
    }

    /**
     * Devuelve el ArrayList conceptos
     * @return ArrayList
     **/
    public ArrayList<Concepto> getConceptos() {
        return conceptos;
    }

    /**
     * Añade un concepto a la lista
     * @param concepto
     **/ 
    public void agregarConcepto(Concepto concepto) {
        conceptos.add(concepto);
    }
    
    /**
     * Elimina un concepto de la lista
     * @param concepto
     **/
    public void eliminarConcepto(Concepto concepto){
        conceptos.remove(concepto);
    }
    
    /**
     * Devuelve un double que recoge el total de los importes de cada 
     * concepto
     * @return double
     **/
    public double calcularTotal() {
        double total = 0;
        for (Concepto concepto : conceptos) {
            total += concepto.getImporte();
        }
        return total;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos
     * @return String
     **/
    @Override
    public String toString() {
        
    String conceptosStr = conceptos.stream()
                                   .map(concepto -> "  - " + concepto)
                                   .reduce("", (a, b) -> a + b + "\n");
    return String.format("DNI Empleado: " + dniEmpleado + " | Mes/Año: %s/%d\n "
            + "Conceptos:\n "
            + "%s Total: %.2f €", mes, anio, conceptosStr, calcularTotal());

    }
}
