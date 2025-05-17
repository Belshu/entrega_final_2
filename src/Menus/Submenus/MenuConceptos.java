package Menus.Submenus;

import Contables.Concepto;
import Contables.Nomina;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Isabel S. Piñana Alonso
 */
public class MenuConceptos extends JFrame implements ActionListener {
    private JPanel mainPanel, buttonsPanel;
    
    private JList <Concepto> listaConceptos2;
    private DefaultListModel <Concepto> conceptoListModel;
    private JButton botonCrear, botonModificar, botonEliminar;
    
    private ArrayList <Concepto> listaConceptos;
    
    public MenuConceptos(Nomina nomina) {
        listaConceptos = (nomina.getConceptos() == null) ? new ArrayList<>() : nomina.getConceptos();
        
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        botonCrear = new JButton("Crear");
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        
        initialize();
    }
    
    private void initialize() {
        setTitle("Conceptos");
        setSize(800, 500);
        setContentPane(getMainPanel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    private JPanel getMainPanel() {
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(getListaConceptos2(), BorderLayout.CENTER);
        
        return mainPanel;
    }
    
    private JPanel getButtonsPanel() {
        buttonsPanel.add(botonCrear);
        buttonsPanel.add(botonModificar);
        buttonsPanel.add(botonEliminar);
        
        return buttonsPanel;
    }
    
    
    private JList getListaConceptos2() {
        listaConceptos2 = new JList <>(getConceptoListModel());
        listaConceptos2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        return listaConceptos2;
    }
    
    private DefaultListModel getConceptoListModel() {
        conceptoListModel = new DefaultListModel<>();
        
        for(Concepto concepto : listaConceptos) {
            conceptoListModel.addElement(concepto);
        }
        
        return conceptoListModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonCrear) {
            
        } else if(ae.getSource() == botonModificar) {
            
        } else if(ae.getSource() == botonEliminar) {
            
        }
    }
    
    
}
