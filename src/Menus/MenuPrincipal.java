package Menus;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Menu inicial del programa: formado únicamente por el panel principal y un
 * JTabbedPane donde sus ventanas conformarán el nombre de "Empleados",
 * "Partidos" y "Facturas". Hacer click en él te llevará a los paneles de cada
 * uno, mostrando las listas y en algunos casos, unos botones para gestionarlas.
 *
 * @author Isabel Shuang Piñana Alonso
 */
public class MenuPrincipal extends JFrame {

    // ATRIBUTOS
    private final JPanel mainPanel;
    private final JTabbedPane ventanas;

    /**
     * CONSTRUCTOR: Inicialización de los atributos finales -> paneles y
     * ventanas - mainPanel será el panel principal - ventanas desplazará las
     * ventanas de cada una al ser seleccioanda
     *
     */
    public MenuPrincipal() {
        mainPanel = new JPanel(new BorderLayout());
        ventanas = new JTabbedPane(JTabbedPane.LEFT);

        initialize();
    }

    /**
     * Método que inicia el tamaño de la interfaz, poner el panel contenido y
     * diversas propiedades
     *
     */
    private void initialize() {
        setTitle("Gestor de equipo de futbol");
        setContentPane(getMainPanel());
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setResizable(false);
        setVisible(true);
    }

    /**
     * Método que devuelve el panel principal donde se colocarán las ventanas
     *
     * @return JPanel
     *
     */
    private JPanel getMainPanel() {
        mainPanel.add(getVentanas());

        return mainPanel;
    }

    /**
     * Método que devuelve las ventanas, primero creando un menu de cada y
     * añadiendo los paneles de cada una
     *
     * @return JTabbedPane
     *
     */
    private JTabbedPane getVentanas() {
        MenuFacturas menuFacturas = new MenuFacturas();
        MenuPartidos menuPartidos = new MenuPartidos();
        MenuEmpleados menuEmpleados = new MenuEmpleados(menuFacturas, menuPartidos);

        ventanas.add("Empleados", menuEmpleados.getMainPanel());
        ventanas.add("Partidos", menuPartidos.getMainPanel());
        ventanas.add("Facturas", menuFacturas.getMainPanel());

        return ventanas;
    }

    /**
     * Método que asigna el número de ventana seleccionada en el primer menu.
     * Contiene un try/catch en caso de que haya un error donde el index sea
     * fuera del rango de las ventanas
     *
     * @param index int
     *
     */
    public void setSelectedIndex(int index) {
        try {
            ventanas.setSelectedIndex(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR -> index fuera de alcance");
        }
    }
}
