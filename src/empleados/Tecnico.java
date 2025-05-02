package empleados;

/**
 * Utilizada para la creación y gestión de los empleados con la clase
 * heredada de "Empleado"
 * @author Santiago Colomer Andreu
 */
public class Tecnico extends Empleado {
    private String puesto, especialidad;

    
    /**
     * Recoge el DNI, nombre, numero de telefono, el puesto y 
     * la especialidad
     * @param dni String que recoge el dato heredado
     * @param nombre String que recoge el dato heredado
     * @param telf int que recoge el dato heredado
     * @param puesto String que recoge el dato de la clase
     * @param especialidad String que recoge el dato de la clase
     **/
    public Tecnico(String puesto, String especialidad, 
            String dni, String nombre, int telf) {
        super(dni, nombre, telf);
        this.puesto = puesto;
        this.especialidad = especialidad;
    }
    
    
    /**
     * Metodo que devuelve el String puesto
     * @return String
     **/
    public String getPuesto() {
        return puesto;
    }

    /**
     * Metodo que asigna un valor al String puesto
     * @param puesto que recoge el valor a asignar
     **/
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Metodo que devuelve el String especialidad
     * @return String
     **/
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Metodo que asigna un valor al String especialidad
     * @param especialidad que recoge el valor a asignar
     **/
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Devuelve el String sobrecargado de todos los datos
     * @return String
     **/    
    @Override
    public String toString(){
        return "Tecnico -> " + super.toString() + "| Puesto: " + puesto +
                " | Especialidad: " + especialidad;
    }
}
