package Menus;

import Contables.Nomina;
import Contables.Cliente;
import Contables.Factura;
import Empleados.Empleado;
import Menus.Submenus.Imprimir;
import Menus.Submenus.MenuConceptos;
import Menus.Submenus.NuevaFactura;
import Menus.Submenus.NuevaNomina;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

/**
 * Menu de gestión de facturas y nóminas: se crean dos listas estáticas en las
 * que guardan las facturas y las nominas creadas respectivamente. Contiene dos
 * ventanas de JTabbedPane que divide ambas listas en la parte visual. En base a
 * los botones se gestionarán según lo que quiera el usuario
 *
 * @author Isabel Shuang Piñana Alonso
 */
public class MenuFacturas implements ActionListener {

    // LISTAS ESTÁTICAS
    private static ArrayList<Factura> listaFacturas;

    // PANELES
    private final JPanel mainPanel, facturasPanel, nominasPanel, buttonsPanel, buttonsPanel2;

    // ELEMENTOS
    private final JTabbedPane ventanas;
    private JList<Factura> listaFacturas2;
    private JList<Nomina> listaNominas2;
    private final JButton botonAnadir, botonImprimir, botonAnadir2, botonConceptos;

    // MODELOS DE LISTAS
    private DefaultListModel<Factura> facturasListModel;
    private DefaultListModel<Nomina> nominasListModel;

    /**
     * Método que inicializa las listas de prueba
     */
    public static void inicializarListas() {
        listaFacturas = new ArrayList<>();

        for (Empleado empleado : MenuEmpleados.getListaEmpleados()) {
            if (!empleado.isEliminado()) {
                empleado.agregarNomina(new Nomina(1, 1, 2000));

                Random rand = new Random();

                int codigo = rand.nextInt(1000000);

                if (!listaFacturas.isEmpty()) {
                    for (Factura f : listaFacturas) {
                        if (f.getCodigo().equals(codigo)) {
                            while (f.getCodigo().equals(codigo)) {
                                codigo = rand.nextInt(1000000);
                            }
                        }
                    }
                }

                listaFacturas.add(new Factura(String.valueOf(codigo), 0, LocalDate.of(2000, 1, 1),
                        new Cliente(empleado.getDni(), empleado.getNombre())));
            }
        }
    }

    /**
     * CONSTRUCTOR: inicialización de los atributos finales
     *
     */
    public MenuFacturas() {

        if (listaFacturas == null) {
            listaFacturas = new ArrayList<>();
        }

        mainPanel = new JPanel(new BorderLayout());

        facturasPanel = new JPanel(new BorderLayout());
        nominasPanel = new JPanel(new BorderLayout());

        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel2 = new JPanel(new FlowLayout());

        botonAnadir = new JButton("Anadir");
        botonAnadir2 = new JButton("Anadir");
        botonConceptos = new JButton("Gestionar conceptos");
        botonImprimir = new JButton("Imprimir");

        ventanas = new JTabbedPane(JTabbedPane.NORTH);
    }

    /**
     * Método que devuelve el panel principal Será público para pasarlo a las
     * ventanas del menú principal
     *
     * @return JPanel
     */
    public JPanel getMainPanel() {
        mainPanel.add(getVentanas());

        return mainPanel;
    }

    /**
     * Método que devuelve las ventanas, añadiendo los paneles de cada una
     *
     * @return JTabbedPane
     *
     */
    private JTabbedPane getVentanas() {
        ventanas.add("Facturas", getFacturasPanel());
        ventanas.add("Nóminas", getNominasPanel());

        return ventanas;
    }

    /**
     * Método que devuelve el panel de las facturas
     *
     */
    private JPanel getFacturasPanel() {
        facturasPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        facturasPanel.add(new JScrollPane(getListaFacturas2()), BorderLayout.CENTER);

        return facturasPanel;
    }

    private JPanel getButtonsPanel() {
        botonAnadir.addActionListener(this);
        buttonsPanel.add(botonAnadir);

        return buttonsPanel;
    }

    /**
     * Método que devuelve la lista visual de las facturas
     *
     */
    private JList<Factura> getListaFacturas2() {
        listaFacturas2 = new JList(getFacturasListModel());
        listaFacturas2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listaFacturas2;
    }

    /**
     * Método que devuelve el modelo de la lista de las facturas
     *
     */
    private DefaultListModel<Factura> getFacturasListModel() {
        facturasListModel = new DefaultListModel<>();

        if (!listaFacturas.isEmpty()) {
            for (Factura f : listaFacturas) {
                facturasListModel.addElement(f);
            }
        }

        return facturasListModel;
    }

    private JPanel getNominasPanel() {
        nominasPanel.add(getButtonsPanel2(), BorderLayout.PAGE_START);
        nominasPanel.add(new JScrollPane(getListaNominas2()), BorderLayout.CENTER);

        return nominasPanel;
    }

    /**
     * Método que devuelve el panel de botones
     *
     * @return buttonsPanel
     */
    private JPanel getButtonsPanel2() {
        botonAnadir2.addActionListener(this);
        botonConceptos.addActionListener(this);
        botonImprimir.addActionListener(this);
        buttonsPanel2.add(botonAnadir2);
        buttonsPanel2.add(botonConceptos);
        buttonsPanel2.add(botonImprimir);

        return buttonsPanel2;
    }

    /**
     * Método que devuelve la lista visual de las nominas
     *
     */
    private JList<Nomina> getListaNominas2() {
        listaNominas2 = new JList<>(getNominasListModel());
        listaNominas2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listaNominas2;
    }

    /**
     * Método que devuelve el modelo de la lista de las nominas
     *
     */
    private DefaultListModel<Nomina> getNominasListModel() {
        nominasListModel = new DefaultListModel<>();

        for (Empleado e : MenuEmpleados.getListaEmpleados()) {
            if (!e.isEliminado()) {
                for (Nomina n : e.getNominas()) {
                    nominasListModel.addElement(n);
                }
            }
        }

        return nominasListModel;
    }

    /**
     * Método que actualiza el modelo de la lista de las nóminas
     *
     * @param elegido
     *
     */
    public void updateNominas(Empleado elegido) {
        if (nominasListModel == null) {
            nominasListModel = new DefaultListModel<>();
        }

        for (Nomina n : elegido.getNominas()) {
            nominasListModel.removeElement(n);
        }

        if (listaNominas2 == null) {
            listaNominas2 = new JList<>(nominasListModel);
        } else {
            listaNominas2.setModel((nominasListModel));
        }
    }

    /**
     * Metodo sobrecargado que recoge las acciones dentro de la interfaz y se le
     * asignara una utilidad a los botones correspondientes
     *
     * @param ae ActionEvent 
     *
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == botonAnadir) {
            anadirFactura();
        } else if (ae.getSource() == botonAnadir2) {
            anadirNomina();
        } else if (ae.getSource() == botonConceptos) {
            gestionarConceptos();
        } else if (ae.getSource() == botonImprimir) {
            ArrayList<Nomina> listaNominas = new ArrayList<>();

            for (Empleado e : MenuEmpleados.getListaEmpleados()) {
                if (!e.isEliminado()) {
                    for (Nomina n : e.getNominas()) {
                        listaNominas.add(n);
                    }
                }
            }
            new Imprimir(1);
        }
    }

    /**
     * Método que añade una nueva factura al cerrar la ventana creada en la
     * interfaz NuevaFactura y actualiza las listas y los modelos de listas
     *
     */
    private void anadirFactura() {
        NuevaFactura nuevaFactura = new NuevaFactura();

        nuevaFactura.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (nuevaFactura.getFactura() != null) {
                    facturasListModel.addElement(nuevaFactura.getFactura());
                    listaFacturas.add(nuevaFactura.getFactura());
                }
            }
        });
    }

    /**
     * Método que añade una nueva o varias nominas al cerrar la ventana creada
     * en la interfaz NuevaNomina y actualiza las listas y los modelos de
     * listas, añadiendo también una factura nueva correspondiente a la nómina
     *
     */
    private void anadirNomina() {
        NuevaNomina nuevaNomina = new NuevaNomina();

        nuevaNomina.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {

                Nomina nueva = nuevaNomina.getNomina();

                if (!nuevaNomina.getElegidos().isEmpty()) {
                    nominasListModel.addElement(nueva);

                    for (Empleado elegido : nuevaNomina.getElegidos()) {
                        for (Nomina n : elegido.getNominas()) {
                            if (n.equals(nueva)) {
                                String codigo = generarCodigoUnico();

                                LocalDate fecha = LocalDate.of(nueva.getAnio(), n.getMes(), n.getDia());

                                crearFactura(String.valueOf(codigo), n.calcularTotal(), fecha,
                                        new Cliente(elegido.getDni(), elegido.getNombre()));

                            }
                        }
                    }
                }
            }

            private void crearFactura(String codigo, double cantidad, LocalDate fecha, Cliente cliente) {
                Factura factura = new Factura(codigo, cantidad, fecha, cliente);

                facturasListModel.addElement(factura);
                listaFacturas.add(factura);
            }
        });
    }

    /**
     * Método que toma la nomina seleccionada y cuando se cierra el menu de los
     * conceptos, crea la nómina asignada al empleado elegido y creamos una
     * factura en base a esta.
     *
     */
    private void gestionarConceptos() {
        Nomina nomina = listaNominas2.getSelectedValue();

        if (nomina != null) {
            MenuConceptos menuConceptos = new MenuConceptos(nomina);

            menuConceptos.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    listaNominas2.setModel(nominasListModel);

                    for (Empleado e : MenuEmpleados.getListaEmpleados()) {
                        for (Nomina n : e.getNominas()) {
                            if (n.equals(nomina)) {
                                String codigo = generarCodigoUnico();

                                LocalDate fecha = LocalDate.of(nomina.getAnio(), n.getMes(), n.getDia());

                                crearFactura(String.valueOf(codigo), n.calcularTotal(), fecha,
                                        new Cliente(e.getDni(), e.getNombre()));

                            }
                        }
                    }
                }
            });
        }
    }

    /**
     * Método que genera un código único para cada factura
     *
     */
    private String generarCodigoUnico() {
        Random rand = new Random();
        String codigo;
        do {
            codigo = String.valueOf(rand.nextInt(1000000));
        } while (existeCodigo(codigo));
        return codigo;
    }

    /**
     * Método que comprueba si el código generado para crear una factura ya
     * existe
     *
     * @param Codigo
     */
    private boolean existeCodigo(String codigo) {
        for (Factura f : listaFacturas) {
            if (f.getCodigo().equals(codigo)) {
                return true; // El código ya existe
            }
        }
        return false; // El código es único
    }

    /**
     * Método que crea una factura y la añade a la lista de facturas y al
     * ListModel correspondiente
     *
     * @param codigo
     * @param cantidad
     * @param localDate
     * @param fecha
     * @param cliente
     */
    private void crearFactura(String codigo, double cantidad, LocalDate fecha, Cliente cliente) {
        Factura factura = new Factura(codigo, cantidad, fecha, cliente);
        facturasListModel.addElement(factura);
        listaFacturas.add(factura);
    }

    /**
     * Método que devuelve la ArrayList estática de las facturas
     *
     * @return ArrayList Factura
     *
     */
    public static ArrayList<Factura> getListaFacturas() {
        return listaFacturas;
    }
}
