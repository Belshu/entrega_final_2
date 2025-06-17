package Contables;

import java.time.LocalDate;

/**
 * Clase que reúne los métodos y atributos necesarios en la creación de la
 * factura: - String: el código de la misma y será final - double: la cantidad
 * que se maneja - LocalDate: la fecha especificada del pago - Cliente: el
 * cliente de la factura
 *
 * @author Ágata Gambín Póveda
 */
public class Factura {

    // ATRIBUTOS
    private final String codigo;
    private double cantidad;
    private LocalDate fechaPago;
    private Cliente cliente;

    /**
     * Recoge el codigo, la cantidad, la fecha de pago y el cliente de la
     * factura
     *
     * @param codigo String unico para identificar la factura
     * @param cantidad double que recoge el coste de la factura
     * @param fechaPago LocalDate que recoge la fecha de la realizacion de la
     * factura
     * @param cliente Cliente que reoge el cliente que tiene participacion en la
     * factura
     *
     */
    public Factura(String codigo, double cantidad,
            LocalDate fechaPago, Cliente cliente) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.fechaPago = fechaPago;
        this.cliente = cliente;
    }

    // GETTERS Y SETTERS
    /**
     * Devuelve el String codigo
     *
     * @return String
     *
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Devuelve el String cantidad
     *
     * @return String
     *
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Devuelve el LocalDate fechaPago
     *
     * @return LocalDate
     *
     */
    public LocalDate getFechaPago() {
        return fechaPago;
    }

    /**
     * Devuelve el String cliente
     *
     * @return String
     *
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos de la clase
     *
     * @return String
     *
     */
    @Override
    public String toString() {
        return "Factura " + codigo + " -> " + "Cantidad: "
                + String.format("%.2f", cantidad) + "€" + " | Fecha "
                + "de pago: " + fechaPago.getDayOfMonth() + "/"
                + fechaPago.getMonth() + "/" + fechaPago.getYear()
                + " | Cliente: " + cliente;
    }
}
