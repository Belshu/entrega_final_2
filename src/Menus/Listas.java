
package Menus;

import Empleados.Directivo;
import Empleados.Tecnico;
import Empleados.Empleado;
import Empleados.Jugador;
import Contables.Factura;
import java.util.ArrayList;

public interface Listas {
    ArrayList <Jugador> listaJ = new ArrayList<>();
    ArrayList <Tecnico> listaT = new ArrayList<>();
    ArrayList <Directivo> listaD = new ArrayList<>();
    // ArrayList <Partido> listaP = new ArrayList<>();
    ArrayList <Empleado> listaE =  new ArrayList<>();
    ArrayList <Factura> facturas = new ArrayList<>();
}
