package Menus;

import Contables.Nomina;
import Contables.Cliente;
import Contables.Factura;
import Empleados.Directivo;
import Empleados.Empleado;
import Empleados.Jugador;
import Empleados.Tecnico;
import Menus.Submenus.Imprimir;
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
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * Menu del programa que hereda de JFrame e implementa la interfaz
 * ActionListener
 * @author Isabel Shuang Piñana Alonso
 */
public class MenuFacturas extends JFrame implements ActionListener{
    private static ArrayList <Factura> listaFacturas;
    private static ArrayList <Nomina> listaNominas;
    
    private final JPanel mainPanel, facturasPanel, nominasPanel, buttonsPanel, buttonsPanel2;
    private final JTabbedPane ventanas;
    
    private JList <Factura> listaFacturas2;
    private JList <Nomina> listaNominas2;
    
    private DefaultListModel <Factura> facturasListModel;
    private DefaultListModel <Nomina> nominasListModel;
    
    private final JButton botonAnadir, botonImprimir, botonAnadir2;
    
    public static void inicializarListas() {
        listaFacturas = new ArrayList<>();
        listaNominas = new ArrayList<>();
        
        for(int i = 0; i < MenuEmpleados.getListaEmpleados().size(); i++){
            Empleado empleado = MenuEmpleados.getListaEmpleados().get(i);
            
            if(!empleado.isEliminado()) {
                Nomina n = new Nomina(empleado.getDni(), 1, 1, (2000 + i));
                empleado.agregarNomina(n);
            
                listaNominas.add(n);
                
                Random rand = new Random();
                
                int codigo = rand.nextInt(1000000);
                
                listaFacturas.add(new Factura(String.valueOf(codigo), 0, LocalDate.of(2000 + i, 1, 1),
                new Cliente(empleado.getDni(), empleado.getNombre())));
            }
        }
    }

    public MenuFacturas(){
        mainPanel = new JPanel(new BorderLayout());
        
        facturasPanel = new JPanel(new BorderLayout());
        nominasPanel = new JPanel(new BorderLayout());
        
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel2 = new JPanel(new FlowLayout());
        
        botonAnadir = new JButton("Anadir");
        botonAnadir2 = new JButton("Anadir");
        botonImprimir = new JButton("Imprimir");
        
        ventanas = new JTabbedPane(JTabbedPane.NORTH);
    }
    
    public JPanel getMainPanel() {
        mainPanel.add(getVentanas());
        
        return mainPanel;
    }
    
    private JTabbedPane getVentanas() {
        ventanas.add("Facturas", getFacturasPanel());
        ventanas.add("Nóminas", getNominasPanel());
        
        return ventanas;
    }
    
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
    
    private JList <Factura> getListaFacturas2() {
        listaFacturas2 = new JList(getFacturasListModel());
        
        return listaFacturas2;
    }
    
    private DefaultListModel <Factura> getFacturasListModel() {
        facturasListModel = new DefaultListModel<>();
        
        for(Factura f : listaFacturas) facturasListModel.addElement(f);
        
        return facturasListModel;
    }
    
    private JPanel getNominasPanel() {
        nominasPanel.add(getButtonsPanel2(), BorderLayout.PAGE_START);
        nominasPanel.add(new JScrollPane(getListaNominas2()), BorderLayout.CENTER);
        
        return nominasPanel;
    }

    private JPanel getButtonsPanel2() {
        botonAnadir2.addActionListener(this);
        botonImprimir.addActionListener(this);
        buttonsPanel2.add(botonAnadir2);
        buttonsPanel2.add(botonImprimir);

        return buttonsPanel2;
    }

    private JList<Nomina> getListaNominas2() {
        listaNominas2 = new JList<>(getNominasListModel());

        return listaNominas2;
    }

    private DefaultListModel<Nomina> getNominasListModel() {
        nominasListModel = new DefaultListModel<>();

        for (Nomina n : listaNominas) {
            nominasListModel.addElement(n);
        }

        return nominasListModel;
    }

    public void updateNominas(Empleado elegido) {
        if (nominasListModel == null) {
            nominasListModel = new DefaultListModel<>();
        }

        for (Nomina n : listaNominas) {
            if (elegido.getDni().equals(n.getDniEmpleado())) {
                nominasListModel.removeElement(n);
                break;
            }
        }

        if (listaNominas2 == null) {
            listaNominas2 = new JList<>(nominasListModel);
        } else {
            listaNominas2.setModel((nominasListModel));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonAnadir) {
            anadirFactura();
        } else if (ae.getSource() == botonAnadir2) {
            anadirNomina();
        } else if(ae.getSource() == botonImprimir) {
            new Imprimir(listaNominas);
        }
    }
    
    private void anadirFactura() {
        // NUEVAFACTURA
    }

    private void anadirNomina() {
        NuevaNomina menu = new NuevaNomina();

        menu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                
                if (menu.getNominas() != null || menu.getNominas().isEmpty()) {
                    for (Nomina nomina : menu.getNominas()) {
                        nominasListModel.addElement(nomina);
                        listaNominas.add(nomina);
                        
                        for (Empleado e : MenuEmpleados.getListaEmpleados()) {
                            if (e.getDni().equals(nomina.getDniEmpleado())) {
                                e.agregarNomina(nomina);

                                Random rand = new Random();
                                LocalDate fecha = LocalDate.of(menu.getAnio(),
                                        menu.getMes(), menu.getDia());

                                int codigo = rand.nextInt(1000000);

                                int i = 0;
                                while (listaFacturas.get(i).getCodigo().equals(codigo)) {
                                    codigo = rand.nextInt(1000000);
                                }

                                crearFactura(String.valueOf(codigo), nomina.calcularTotal(), fecha,
                                        new Cliente(e.getDni(), e.getNombre()));
                            }

                            /*
                            if (e instanceof Jugador) {
                                for (Jugador j : MenuEmpleados.getListaJugadores()) {
                                    if (j.getDni().equals(nomina.getDniEmpleado())) {
                                        j.agregarNomina(nomina);
                                        break;
                                    }
                                }
                                break;
                            } else if (e instanceof Tecnico) {
                                for (Tecnico t : MenuEmpleados.getListaTecnicos()) {
                                    if (t.getDni().equals(nomina.getDniEmpleado())) {
                                        t.agregarNomina(nomina);
                                        break;
                                    }
                                }
                                break;
                            } else if (e instanceof Directivo) {
                                for (Directivo d : MenuEmpleados.getListaDirectivos()) {
                                    if (d.getDni().equals(nomina.getDniEmpleado())) {
                                        d.agregarNomina(nomina);
                                        break;
                                    }
                                }
                                break;
                            }
                            */
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
}
