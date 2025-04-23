package contables;

import java.util.ArrayList;

/**
 * Utilizada para la creación y gestión de nóminas realizadas
 * @author Ágata Gambín Póveda
 */

public class Nomina {
   private int mes, anio;
   private ArrayList <Concepto> conceptos;
   
       /**
     * Recoge el mes y el anio de la realizacion de la nomina
     * @param mes int que recoge el mes de la creacion de la nomina
     * @param anio int que recoge el anio de la creacion de la nomina
     **/
    public Nomina(int mes, int anio) {
        this.mes = mes;
        this.anio = anio;
        this.conceptos = new ArrayList<>();
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
    return String.format("Nómina del mes: %s del año %d\n Conceptos:\n %s Total: %.2f €",
                         mes, anio, conceptosStr, calcularTotal());

    }
}
