package Menus;

import Contables.Nomina;
import Contables.Cliente;
import Contables.Factura;
import Empleados.Empleado;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
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
    
    private final JPanel mainPanel, facturasPanel, nominasPanel, buttonsPanel;
    private final JTabbedPane ventanas;
    
    private JList <Factura> listaFacturas2;
    private JList <Nomina> listaNominas2;
    
    private DefaultListModel <Factura> facturasListModel;
    private DefaultListModel <Nomina> nominasListModel;
    
    private final JButton botonAnadir, botonImprimir;
    
    public static void inicializarListas() {
        listaFacturas = new ArrayList<>();
        listaNominas = new ArrayList<>();
        
        listaFacturas.add(new Factura("111111", 100.00, LocalDate.now(), new Cliente("222221", "cliente 1")));
        listaFacturas.add(new Factura("111112", 200.00, LocalDate.now(), new Cliente("222222", "cliente 2")));
        
        for(int i = 0; i < MenuEmpleados.getListaEmpleados().size(); i++){
            Empleado empleado = MenuEmpleados.getListaEmpleados().get(i);
            if(!empleado.isEliminado()) {
                Nomina n = new Nomina(empleado.getDni(), 1, (2000 + i));
                empleado.agregarNomina(n);
            
                listaNominas.add(n);
            }
        }
    }

    public MenuFacturas(){
        mainPanel = new JPanel(new BorderLayout());
        
        facturasPanel = new JPanel(new BorderLayout());
        nominasPanel = new JPanel(new BorderLayout());
        
        buttonsPanel = new JPanel(new FlowLayout());
        
        botonAnadir = new JButton("Anadir");
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
        facturasPanel.add(new JScrollPane(getListaFacturas2()), BorderLayout.CENTER);
        
        return facturasPanel;
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
        nominasPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        nominasPanel.add(new JScrollPane(getListaNominas2()), BorderLayout.CENTER);
        
        return nominasPanel;
    }
    
    private JPanel getButtonsPanel() {
        botonAnadir.addActionListener(this);
        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonImprimir);
        
        return buttonsPanel;
    }
    
    private JList <Nomina> getListaNominas2() {
        listaNominas2 = new JList<>(getNominasListModel());
        
        return listaNominas2;
    }
    
    private DefaultListModel <Nomina> getNominasListModel() {
        nominasListModel = new DefaultListModel<>();
        
        for(Nomina n : listaNominas) {
            nominasListModel.addElement(n);
        }
        
        return nominasListModel;
    }
    
    public void updateNominas(Empleado elegido) {
        if(nominasListModel == null) { nominasListModel = new DefaultListModel<>(); }
        
        for(Nomina n : listaNominas) {
            if(elegido.getDni().equals(n.getDniEmpleado())){
                nominasListModel.removeElement(n);
                break;
            }
        }
        
        if(listaNominas2 == null) { listaNominas2 = new JList<>(nominasListModel); } 
        else { listaNominas2.setModel((nominasListModel)); }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
    public void anadirNomina() {
        
    }
}
