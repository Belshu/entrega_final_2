/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mint
 */

import Contables.Concepto;
import Contables.Nomina;
import Menus.MenuEmpleados;
import Menus.MenuFacturas;
import Menus.MenuPartidos;
import Menus.Submenus.*;

public class Test {

    public static void main(String[] args) {
        // new NuevoEmpleado(new Directivo("A","12345", "AAa", 12345));
        // new NuevoEmpleado(new Tecnico("AA","AA","123","AAA", 1234));
        // new ModificarEmpleado(new Jugador("AA","AA",123,"AAA",0,0,0, true));
        // new ImprimirEmpleados(true);
        // new ImprimirEmpleados(false);
        
        // MenuPartidos.inicializarLista();
        // new NuevoPartido(MenuEmpleados.getListaJugadores());

        // new NuevaNomina();
        // new NuevaFactura();
        Nomina nomina = new Nomina(1, 1, 1);
        nomina.agregarConcepto(new Concepto("1111", "KK", 100.00));
        new MenuConceptos(nomina);
    }
}

