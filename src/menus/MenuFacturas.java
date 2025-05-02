package menus;

import contables.Cliente;
import contables.Factura;
import empleados.Empleado;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Isabel Shuang Piñana Alonso
 */
public class MenuFacturas extends JFrame implements ActionListener{
    private static ArrayList <Factura> listaFacturas;
    
    private final JPanel mainPanel, subPanel1, subPanel2, buttonsPanel;
    private final JTabbedPane ventanas;
    
    private JList listaFacturas2, listaEmpleados;
    private DefaultListModel facturasListModel, empleadosListModel;
    
    public static void inicializarListas() {
        listaFacturas = new ArrayList<>();
        
        listaFacturas.add(new Factura("111111", 100.00, LocalDate.now(), new Cliente("222221", "cliente 1")));
        listaFacturas.add(new Factura("111112", 200.00, LocalDate.now(), new Cliente("222222", "cliente 2")));
        listaFacturas.add(new Factura("111113", 300.00, LocalDate.now(), new Cliente("222223", "cliente 3")));
        listaFacturas.add(new Factura("111114", 400.00, LocalDate.now(), new Cliente("222224", "cliente 4")));
        listaFacturas.add(new Factura("111115", 500.00, LocalDate.now(), new Cliente("222225", "cliente 5")));
    }

    public MenuFacturas(){
        mainPanel = new JPanel(new BorderLayout());
        
        subPanel1 = new JPanel();
        subPanel2 = new JPanel();
        
        buttonsPanel = new JPanel(new FlowLayout());
        
        ventanas = new JTabbedPane(JTabbedPane.NORTH);
    }
    
    public JPanel getMainPanel() {
        mainPanel.add(getVentanas());
        
        return mainPanel;
    }
    
    private JTabbedPane getVentanas() {
        ventanas.add("Facturas", getSubPanel1());
        ventanas.add("Nóminas", getSubPanel2());
        
        return ventanas;
    }
    
    private JPanel getSubPanel1() {
        subPanel1.add(new JScrollPane(getListaFacturas2()), BorderLayout.CENTER);
        
        return subPanel1;
    }
    
    private JList getListaFacturas2() {
        listaFacturas2 = new JList(getFacturasListModel());
        
        return listaFacturas2;
    }
    
    private DefaultListModel getFacturasListModel() {
        facturasListModel = new DefaultListModel<>();
        
        for(Factura f : listaFacturas) facturasListModel.addElement(f);
        
        return facturasListModel;
    }
    
    private JPanel getSubPanel2() {
        // subPanel.add(new JScrollPane(getListaEmpleados(), BorderLayout.CENTER);
        
        return subPanel2;
    }
    
    private JList getListaEmpleados() {
        listaEmpleados = new JList<>(getEmpleadosListModel());
        
        return listaEmpleados;
    }
    
    private DefaultListModel getEmpleadosListModel() {
        empleadosListModel = new DefaultListModel<>();
        
        for(Empleado e : MenuEmpleados.getListaEmpleados()) {
            
        }
        
        return empleadosListModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
