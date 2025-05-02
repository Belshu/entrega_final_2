package Contables;

import java.time.LocalDate;

/**
 * Utilizada para la creación y gestión de facturas realizadas
 * @author Ágata Gambín Póveda
 */
public class Factura {
    private final String codigo;
    private double cantidad;
    private LocalDate fechaPago;
    private Cliente cliente;
    
    /**
     * Recoge el codigo, la cantidad, la fecha de pago y el cliente de la factura
     * @param codigo String unico para identificar la factura
     * @param cantidad double que recoge el coste de la factura
     * @param fechaPago LocalDate que recoge la fecha de la realizacion de la factura
     * @param cliente Cliente que reoge el cliente que tiene participacion en la factura
     **/
    public Factura(String codigo, double cantidad, 
            LocalDate fechaPago, Cliente cliente) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.fechaPago = fechaPago;
        this.cliente = cliente;
    }

    /**
     * Devuelve el String codigo
     * @return String
     **/
    public String getCodigo() {
        return codigo;
    }

    /**
     * Devuelve el String cantidad
     * @return String
     **/
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Devuelve el LocalDate fechaPago
     * @return LocalDate
     **/
    public LocalDate getFechaPago() {
        return fechaPago;
    }

    /**
     * Devuelve el String cliente
     * @return String
     **/
    public Cliente getCliente() {
        return cliente;
    }    

    /**
     * Devuelve el String sobrecargado de todos los datos
     * @return String
     **/    
    @Override
    public String toString(){
        return "Factura " + codigo + " -> " + "Cantidad: " +
                String.format("%.2f", cantidad) + "€" + " | Fecha"
                + "de pago: " + fechaPago.getDayOfMonth() + "/" + 
                fechaPago.getMonth() + "/" + fechaPago.getYear() +
                " | Cliente: " + cliente;
    }
}
