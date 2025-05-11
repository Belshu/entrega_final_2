package Empleados;

/**
 * Clase Jugador que hereda de Empleado y teniendo otros atributos aparte:
 * - String: los apellidos del jugador
 * - int: la demarcación y la edad del jugador
 * - double: el valor monetario del jugador
 * - boolea: en estado del jugador si está o no disponible
 * 
 * @author Santiago Colomer Andreu
 **/

public class Jugador extends Empleado {
    
    // ATRIBUTOS
    private String apellidos;
    private int demarcacion, edad;
    private double valor;
    private boolean estado;

    /**
     * Recoge el DNI, nombre, numero de telefono, apellidos, demarcacion, la edad, 
     * el valor del jugador y su estado
     * 
     * @param dni String que recoge el dato heredado
     * @param nombre String que recoge el dato heredado
     * @param telf int que recoge el dato heredado
     * @param apellidos String que recoge el dato de la clase
     * @param demarcacion String que recoge el dato de la clase
     * @param edad int que recoge el dato de la clase
     * @param valor double que recoge el dato de la clase
     * @param estado boolean que recoge el dato de la clase
     **/
    public Jugador(String dni, String nombre, int telf, 
            String apellidos, int demarcacion, int edad, double valor, 
            boolean estado) {
        super(dni, nombre, telf);
        this.apellidos = apellidos;
        this.demarcacion = demarcacion;
        this.edad = edad;
        this.valor = valor;
        this.estado = estado;
    }
    
    // GETTERS Y SETTERS
    /**
     * Metodo que devuelve el String apellidos
     * @return String
     **/
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Metodo que asigna un valor al String apellidos
     * @param apellidos que recoge el valor a asignar
     **/
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Metodo que devuelve el int demarcacion
     * @return int
     **/
    public int getDemarcacion() {
        return demarcacion;
    }

    /**
     * Metodo que asigna un valor al int demarcacion
     * @param demarcacion que recoge el valor a asignar
     **/
    public void setDemarcacion(int demarcacion) {
        this.demarcacion = demarcacion;
    }

    /**
     * Metodo que devuelve el int edad
     * @return int
     **/
    public int getEdad() {
        return edad;
    }

    /**
     * Metodo que asigna un valor al int edad
     * @param edad que recoge el valor a asignar
     **/
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Metodo que devuelve el double valor
     * @return double
     **/
    public double getValor() {
        return valor;
    }

    /**
     * Metodo que asigna un valor al double valor
     * @param valor que recoge el valor a asignar
     **/
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Metodo que devuelve el boolean estado
     * @return boolean
     **/
    public boolean isEstado() {
        return estado;
    }

    /**
     * Metodo que asigna un valor al boolean estado
     * @param estado que recoge el valor a asignar
     **/
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos de la clase
     * @return String
     **/    
    @Override
    public String toString() {
        return "Jugador -> " + super.toString() +
                " | Apellido: " + apellidos + 
                " | Demarcacion: " + demarcacion +
                " | Edad: " + edad + 
                " | Valor de mercado: " + valor + "M€" + 
                " | Estado: " + (estado ? "Disponible" : "No disponible");
    }
}
