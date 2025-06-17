package Contables;

/**
 * Utilizada para el registro y gestión de los clientes
 *
 * @author Ágata Gambín Póveda
 *
 */
public class Cliente {

    private String cif, nombre;

    /**
     * Recoge el CIF y el nombre de la empresa
     *
     * @param cif String que recoge el DNI de las empresas
     * @param nombre String que recoge el nombre de la empresa
     *
     */
    public Cliente(String cif, String nombre) {
        this.cif = cif;
        this.nombre = nombre;
    }

    /**
     * Devuelve el String CIF
     *
     * @return String;
     *
     */
    public String getCif() {
        return cif;
    }

    /**
     * Da un valor al CIF de la empresa
     *
     * @param cif String del que CIF tomara su valor
     *
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Devuelve el String nombre
     *
     * @return String
     *
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el CIF de la empresa
     *
     * @param nombre String del que el nombre tomara su valor
     *
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos
     *
     * @return String
     *
     */
    @Override
    public String toString() {
        return "| Nombre: " + nombre + " (CIF: " + cif + ")";
    }
}
