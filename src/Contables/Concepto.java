package Contables;

/**
 * Utilizado para el registro y gestión de los conecptos de las 
 * nóminas
 * @author Ágata Gambín Póveda
 */
public class Concepto {
    private final String codigoConcepto;
    private String descripcion;
    private double importe;

    /**
     * Recoge el codigo, la descripcion y el importe que se va a destinar
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
     * Devuelve el String sobrecargado de todos los datos
     * @return String
     **/
    @Override
    public String toString(){
        return "|Codigo: " + codigoConcepto + "|Descripcion: " +
                descripcion + "|Importe: " + 
                String.format("%.2f", importe) + "€";
    }
}
