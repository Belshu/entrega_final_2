package Contables;

/**
* Clase que reúne los métodos y atributos necesarios en la creación de la Nomina: 
* - String: el codigo del concepto y su descripción. El código será final
* - double: el importe del concepto 
* 
 * @author Ágata Gambín Póveda
 */
public class Concepto {
    
    // ATRIBUTOS
    private final String codigoConcepto;
    private String descripcion;
    private double importe;

    /**
     * Recoge el codigo, la descripcion y el importe que se va a destinar
     * 
     * @param codigo String unico para identificar el concepto de cada uno
     * @param descripcion String que recoge una breve descripcion para el concepto en concreto
     * @param importe double que recoge el dinero a pagar
     **/
    public Concepto(String codigo, String descripcion, 
            double importe) {
        this.codigoConcepto = codigo;
        this.descripcion = descripcion;
        this.importe = importe;
    }    
    
    // GETTERS Y SETTERS

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    /**
     * Devuelve el String codigo
     * @return String
     **/
    public String getCodigo() {
        return codigoConcepto;
    }

    /**
     * Devuelve el String descripcion
     * @return String
     **/
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve el Double importe
     * @return double
     **/
    public double getImporte() {
        return importe;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos de la clase
     * @return String
     **/
    @Override
    public String toString(){
        return "Codigo: " + codigoConcepto + " | \n\tDescripcion: " +
                descripcion + " | \n\tImporte: " + 
                String.format("%.2f", importe) + "€";
    }
}
