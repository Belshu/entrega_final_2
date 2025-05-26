package Menus.Submenus;

import Contables.Concepto;
import Contables.Nomina;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * Menu de conceptos al darle al botón de gestionar en la pestaña de las nóminas.
 * Seleccionando una nómina, podrás gestionar sus conceptos y se mostrará en esta
 * interfaz
 * 
 * @author Isabel S. Piñana Alonso
 */
public class MenuConceptos extends JFrame implements ActionListener {
    
    // PANELES
    private JPanel mainPanel, buttonsPanel;
    
    // ELEMENTOS
    private JList <Concepto> listaConceptos2;
    private DefaultListModel <Concepto> conceptoListModel;
    private JButton botonCrear, botonModificar, botonEliminar;
    
    // NOMINA SELECCIOANDA
    private Nomina nomina;
    
    /**
     * CONSTRUCTOR: recoge la nómina elegida por el usuario y le asignamos
     * el valor para guardarla
     * 
     * @param nomina 
     **/
    public MenuConceptos(Nomina nomina) {
        this.nomina = nomina;
        
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        botonCrear = new JButton("Crear");
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
        
        initialize();
    }
    
    /**
     * Método que asigna algunas características de la interfaz
     **/
    private void initialize() {
        setTitle("Conceptos");
        setSize(800, 500);
        setContentPane(getMainPanel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    /**
     * Método que devuelve el panel principal, conteniendo en él el panel de
     * botones y la JList de conceptos
     * 
     * @return JPanel
     **/
    private JPanel getMainPanel() {
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(getListaConceptos2(), BorderLayout.CENTER);
        
        return mainPanel;
    }
    
    /**
     * Método que devuelve el panel de los botones
     * 
     * @return JPanel
     **/
    private JPanel getButtonsPanel() {
        botonCrear.addActionListener(this);
        botonModificar.addActionListener(this);
        botonEliminar.addActionListener(this);
        
        buttonsPanel.add(botonCrear);
        buttonsPanel.add(botonModificar);
        buttonsPanel.add(botonEliminar);
        
        return buttonsPanel;
    }
    
    /**
     * Método que devuelve la lista visual de los conceptos
     * 
     * @return JList
     **/
    private JList getListaConceptos2() {
        listaConceptos2 = new JList <>(getConceptoListModel());
        listaConceptos2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        return listaConceptos2;
    }
    
    /**
     * Método que devuelve el modelo de la lista de los empleados
     * 
     * @return DefaultListModel
     **/
    private DefaultListModel getConceptoListModel() {
        conceptoListModel = new DefaultListModel<>();
        
        if(!nomina.getConceptos().isEmpty()) {
            for(Concepto concepto : nomina.getConceptos()) {
            conceptoListModel.addElement(concepto);
        }
        }
        
        return conceptoListModel;
    }
    
    /**
     * Metodo sobrecargado que recoge las acciones dentro de la interfaz y se le asignara una utilidad a los
     * botones correspondientes
     * @param ae ActionEvent 
     **/
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonCrear) {
            nuevoConcepto();
        } else if(ae.getSource() == botonModificar) {
            Concepto elegido = listaConceptos2.getSelectedValue();
            
            if(elegido != null) {
                modificarConcepto(elegido);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un concepto para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if(ae.getSource() == botonEliminar) {
            Concepto elegido = listaConceptos2.getSelectedValue();
            
            if(elegido != null) {
                int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este concepto?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
                
                if (confirmar == JOptionPane.YES_OPTION) {
                    nomina.eliminarConcepto(elegido);
                    conceptoListModel.removeElement(elegido);
                    listaConceptos2.setModel(conceptoListModel);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un concepto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     * Método que abre un formulario para añadir un nuevo concepto
     **/
    private void nuevoConcepto() { 
        // JFRAME
        JFrame formularioConceptos = new JFrame();
        
        // PANELES
        JPanel formularioPanelConceptos = new JPanel(new BorderLayout());
        JPanel formularioPanelConceptos2 = new JPanel();
        JPanel textAreaPanel = new JPanel(new FlowLayout());
        JPanel radioButtonsPanel = new JPanel(new FlowLayout());
        JPanel importePanel = new JPanel(new FlowLayout());
        formularioPanelConceptos2.setLayout(new BoxLayout(formularioPanelConceptos2,BoxLayout.Y_AXIS));
        
        // ELEMENTOS
        JButton botonAgregar = new JButton("Agregar");
        JRadioButton botonSalarioBase = new JRadioButton("Salario base");
        JRadioButton botonPrimaPorVictoria = new JRadioButton("Prima por victoria");
        JRadioButton botonPagaExtra = new JRadioButton("Paga Extra");
        JTextArea descripcionTextArea = new JTextArea(5, 30);
        JTextField importeTextField = new JTextField(20);
        
        // PROPIEDADES DEL JFRAME
        formularioConceptos.setSize(600, 300);
        formularioConceptos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioConceptos.setLocationRelativeTo(null);
        formularioConceptos.setResizable(false);
        
        // AÑADIR ELEMENTOS AL RADIOBUTTONPANEL
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(botonSalarioBase);
        grupo.add(botonPrimaPorVictoria);
        grupo.add(botonPagaExtra);
        
        radioButtonsPanel.add(botonSalarioBase);
        radioButtonsPanel.add(botonPrimaPorVictoria);
        radioButtonsPanel.add(botonPagaExtra);
        
        // AÑADIR ELEMENTOS AL TEXTAREAPANEL
        textAreaPanel.add(new JLabel("Descripcion: "));
        textAreaPanel.add(new JScrollPane(descripcionTextArea));
        
        // AÑADIR ELEMENTOS AL IMPORTEPANEL
        importePanel.add(new JLabel("Importe: "));
        importePanel.add(importeTextField);
        
        // AÑADIR PANELES AL PRINCIPAL
        formularioPanelConceptos2.add(radioButtonsPanel);
        formularioPanelConceptos2.add(Box.createVerticalStrut(0));
        formularioPanelConceptos2.add(textAreaPanel);
        formularioPanelConceptos2.add(Box.createVerticalStrut(0));
        formularioPanelConceptos2.add(importePanel);
        
        formularioPanelConceptos.add(formularioPanelConceptos2, BorderLayout.CENTER);
        formularioPanelConceptos.add(botonAgregar, BorderLayout.PAGE_END);
        
        formularioConceptos.setContentPane(formularioPanelConceptos);
        formularioConceptos.setVisible(true);
        
        // ACTIONLISTENERS
        
        botonSalarioBase.addActionListener((ActionEvent e) -> {
            descripcionTextArea.setText("Salario base.");
        });
        
        botonPrimaPorVictoria.addActionListener((ActionEvent e) -> {
            descripcionTextArea.setText("Prima por victoria.");
        });
        
        botonPagaExtra.addActionListener((ActionEvent e) -> {
            descripcionTextArea.setText("Paga extra.");
        });
        
        botonAgregar.addActionListener((ActionEvent e) -> {
            Random rand = new Random();
            int codigo = rand.nextInt(1000000);
            if (descripcionTextArea.getText().isEmpty() || importeTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.", 
                        "Faltan campos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if(!nomina.getConceptos().isEmpty()) {
                for(Concepto c : nomina.getConceptos()) {
                    if(c.getCodigo().equals(codigo)) {
                        while(c.getCodigo().equals(codigo)) {
                            codigo = rand.nextInt(1000000);
                        }
                    }
                }
            }
            
            try {
                Concepto concepto = new Concepto(String.valueOf(codigo), descripcionTextArea.getText(),
                        Double.parseDouble(importeTextField.getText()));
                nomina.agregarConcepto(concepto);
                conceptoListModel.addElement(concepto);
                formularioConceptos.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    /**
     * Método que modifica el concepto seleccionado por el usuario
     **/
    private void modificarConcepto(Concepto elegido) {
        // JFRAME
        JFrame formularioConceptos = new JFrame();
        
        // PANELES
        JPanel formularioPanelConceptos = new JPanel(new BorderLayout());
        JPanel formularioPanelConceptos2 = new JPanel();
        JPanel textAreaPanel = new JPanel(new FlowLayout());
        JPanel radioButtonsPanel = new JPanel(new FlowLayout());
        JPanel importePanel = new JPanel(new FlowLayout());
        formularioPanelConceptos2.setLayout(new BoxLayout(formularioPanelConceptos2,BoxLayout.Y_AXIS));
        
        // ELEMENTOS
        JButton botonAceptar = new JButton("Aceptar cambios");
        JRadioButton botonSalarioBase = new JRadioButton("Salario base");
        JRadioButton botonPrimaPorVictoria = new JRadioButton("Prima por victoria");
        JRadioButton botonPagaExtra = new JRadioButton("Paga Extra");
        JTextArea descripcionTextArea = new JTextArea(5, 30);
        JTextField importeTextField = new JTextField(20);
        
        // PROPIEDADES DEL JFRAME
        formularioConceptos.setSize(600, 300);
        formularioConceptos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioConceptos.setLocationRelativeTo(null);
        formularioConceptos.setResizable(false);
        
        // AÑADIR ELEMENTOS AL RADIOBUTTONPANEL
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(botonSalarioBase);
        grupo.add(botonPrimaPorVictoria);
        grupo.add(botonPagaExtra);
        
        switch(elegido.getDescripcion()) {
            case "Salario base." -> {
                botonSalarioBase.setSelected(true);
                botonPrimaPorVictoria.setSelected(false);
                botonPagaExtra.setSelected(false);
            }
            case "Prima por victoria." -> {
                botonSalarioBase.setSelected(false);
                botonPrimaPorVictoria.setSelected(true);
                botonPagaExtra.setSelected(false);
            }
            case "Paga extra." -> {
                botonSalarioBase.setSelected(false);
                botonPrimaPorVictoria.setSelected(false);
                botonPagaExtra.setSelected(true);
            }
        }
        
        radioButtonsPanel.add(botonSalarioBase);
        radioButtonsPanel.add(botonPrimaPorVictoria);
        radioButtonsPanel.add(botonPagaExtra);
        
        // AÑADIR ELEMENTOS AL TEXTAREAPANEL
        descripcionTextArea.setText(elegido.getDescripcion());
        
        textAreaPanel.add(new JLabel("Descripcion: "));
        textAreaPanel.add(new JScrollPane(descripcionTextArea));
        
        // AÑADIR ELEMENTOS AL IMPORTEPANEL
        importeTextField.setText(String.valueOf(elegido.getImporte()));
        
        importePanel.add(new JLabel("Importe: "));
        importePanel.add(importeTextField);
        
        // AÑADIR PANELES AL PRINCIPAL
        formularioPanelConceptos2.add(radioButtonsPanel);
        formularioPanelConceptos2.add(Box.createVerticalStrut(0));
        formularioPanelConceptos2.add(textAreaPanel);
        formularioPanelConceptos2.add(Box.createVerticalStrut(0));
        formularioPanelConceptos2.add(importePanel);
        
        formularioPanelConceptos.add(formularioPanelConceptos2, BorderLayout.CENTER);
        formularioPanelConceptos.add(botonAceptar, BorderLayout.PAGE_END);
        
        formularioConceptos.setContentPane(formularioPanelConceptos);
        formularioConceptos.setVisible(true);
        
        // ACTIONLISTENERS
        
        botonSalarioBase.addActionListener((ActionEvent e) -> {
            descripcionTextArea.setText("Salario base.");
        });
        
        botonPrimaPorVictoria.addActionListener((ActionEvent e) -> {
            descripcionTextArea.setText("Prima por victoria.");
        });
        
        botonPagaExtra.addActionListener((ActionEvent e) -> {
            descripcionTextArea.setText("Paga extra.");
        });
        
        botonAceptar.addActionListener((ActionEvent e) -> {
            if (descripcionTextArea.getText().isEmpty() || importeTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.", 
                        "Faltan campos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                elegido.setDescripcion(descripcionTextArea.getText());
                elegido.setImporte(Double.parseDouble(importeTextField.getText()));
                
                listaConceptos2.setModel(conceptoListModel);
                
                formularioConceptos.dispose();
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
