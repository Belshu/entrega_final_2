
package Empleados;

/**
 * Clase Jugador que hereda de Empleado y teniendo otro atributo aparte:
 * - String: el cargo del directivo
 * 
 * @author Santiago Colomer Andreu
 */

public class Directivo extends Empleado{
    
    // ATRIBUTOS
    private String cargo;

   /**
     * Recoge el DNI, nombre, numero de telefono y el cargo
     * 
     * @param dni String que recoge el dato heredado
     * @param nombre String que recoge el dato heredado
     * @param telf int que recoge el dato heredado
     * @param cargo String que recoge el dato de la clase
     **/
    public Directivo(String cargo, String dni, String nombre, 
            int telf) {
        super(dni, nombre, telf);
        this.cargo = cargo;
    }
    
    // GETTERS Y SETTERS
    /**
     * Metodo que devuelve el String cargo
     * @return String
     **/
    public String getCargo() {
        return cargo;
    }

    /**
     * Metodo que asigna un valor al String cargo
     * @param cargo que recoge el valor a asignar
     **/
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos
     * @return String
     **/
    @Override
    public String toString(){
        return "Directivo -> " + super.toString() + " | Cargo: " 
                + cargo;
    }
}
