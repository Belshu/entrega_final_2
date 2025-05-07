package Menus.Submenus;

import Contables.Concepto;
import Contables.Nomina;
import Empleados.Empleado;
import Menus.MenuEmpleados;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * 
 * @author Isabel Shuang Piñana Alonso
 */

public class NuevaNomina extends JFrame implements ActionListener{
    private Nomina nuevaNomina;
    
    private String dniEmpleado;
    private int mes, anio;
    private ArrayList <Concepto> listaConceptos;
    
    private JPanel mainPanel, formularioPanel, empleadoPanel, empleadoPanel2, fechaPanel, conceptosPanel, buttonsPanel;
    
    private JLabel seleccionarEmpleadoLabel, fechaLabel, fechaLabel2, conceptosLabel;
    private JTextField seleccionarEmpleadoTextField, mesTextField, anioTextField;
    private JTextArea conceptosTextArea;
    
    private JButton botonSeleccionarEmpleado, botonConceptos, botonAceptar, botonCancelar;
    
    public NuevaNomina() {
        listaConceptos = new ArrayList<>();
        
        mainPanel = new JPanel(new BorderLayout());
        formularioPanel = new JPanel();
        empleadoPanel = new JPanel(new BorderLayout());
        empleadoPanel2 = new JPanel(new FlowLayout());
        fechaPanel = new JPanel(new FlowLayout());
        conceptosPanel = new JPanel(new FlowLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        seleccionarEmpleadoLabel = new JLabel("Seleccionar Empleado:", JLabel.CENTER);
        
        fechaLabel = new JLabel("(mes/año): ", JLabel.CENTER);
        fechaLabel2 = new JLabel(" / ");
        conceptosLabel = new JLabel("Añadir conceptos: ");
        
        seleccionarEmpleadoTextField = new JTextField(20);
        mesTextField = new JTextField(2);
        anioTextField = new JTextField(2);
        
        conceptosTextArea = new JTextArea(10, 30);
        
        botonSeleccionarEmpleado = new JButton("Añadir a...");
        botonConceptos = new JButton("Nuevo");
        botonAceptar = new JButton("Aceptar");
        botonCancelar = new JButton("Cancelar");
        
        initialize();
    }
    
    private void initialize() {
        setTitle("Nueva nomina");
        setSize(500, 300);
        setContentPane(getMainPanel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel getMainPanel() {
        JLabel marginLabel = new JLabel("     ");
        marginLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        
        mainPanel.add(marginLabel, BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
    private JPanel getFormularioPanel() {
        formularioPanel.setLayout(new BoxLayout(formularioPanel, BoxLayout.Y_AXIS));
        
        formularioPanel.add(getEmpleadoPanel());
        formularioPanel.add(Box.createVerticalStrut(0));
        formularioPanel.add(getFechaPanel());
        formularioPanel.add(Box.createVerticalStrut(0));
        formularioPanel.add(getConceptosPanel());
        formularioPanel.add(Box.createVerticalStrut(0));
        
        return formularioPanel;
    }
    
    private JPanel getEmpleadoPanel() {
        empleadoPanel.add(seleccionarEmpleadoLabel, BorderLayout.PAGE_START);
        empleadoPanel.add(getEmpleadoPanel2(), BorderLayout.CENTER);
        
        return empleadoPanel;
    }
    
    private JPanel getEmpleadoPanel2() {
        botonSeleccionarEmpleado.addActionListener(this);
        seleccionarEmpleadoTextField.setEnabled(false);
        
        empleadoPanel2.add(botonSeleccionarEmpleado);
        empleadoPanel2.add(seleccionarEmpleadoTextField);
        
        return empleadoPanel2;
    }
    
    private JPanel getFechaPanel() {
        fechaPanel.add(fechaLabel);
        fechaPanel.add(mesTextField);
        fechaPanel.add(fechaLabel2);        
        fechaPanel.add(anioTextField);
        
        return fechaPanel;
    }
    
    private JPanel getConceptosPanel() {
        botonConceptos.addActionListener(this);
        conceptosTextArea.setEnabled(false);
        
        conceptosPanel.add(conceptosLabel);
        conceptosPanel.add(botonConceptos);
        conceptosPanel.add(new JScrollPane(conceptosTextArea));
        
        return conceptosPanel;
    }
    
    private JPanel getButtonsPanel() {
        botonAceptar.addActionListener(this);
        botonCancelar.addActionListener(this);
        
        buttonsPanel.add(botonAceptar);
        buttonsPanel.add(botonCancelar);
        
        return buttonsPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonSeleccionarEmpleado) {
            mostrarListaEmpleados();
        }
        
        if(ae.getSource() == botonConceptos) {
            mostrarFormularioConceptos();
        }
        
        if(ae.getSource() == botonAceptar) {
            
        } else if(ae.getSource() == botonCancelar) {
           dispose();
        }
    }
    
    private void mostrarListaEmpleados() {
        JFrame seleccionFrame = new JFrame("Seleccionar Empleados");
        
        seleccionFrame.setSize(1000, 500);
        seleccionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seleccionFrame.setLocationRelativeTo(null);
        seleccionFrame.setResizable(false);
        
        DefaultListModel <Empleado> empleadoListModel = new DefaultListModel<>();
        for(Empleado empleado : MenuEmpleados.getListaEmpleados()) {
            if(!empleado.isEliminado()) {
                empleadoListModel.addElement(empleado);
            }
        }
        
        JList <Empleado> listaEmpleados2 = new JList<>(empleadoListModel);
        listaEmpleados2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaEmpleados2);
        
        JButton botonAnadir = new JButton("Añadir");
        
        botonAnadir.addActionListener(e -> {
            Empleado elegido = listaEmpleados2.getSelectedValue();
            
            seleccionarEmpleadoTextField.setText(elegido.getDni());
            dniEmpleado = elegido.getDni();
            seleccionFrame.dispose();
        });
        
        seleccionFrame.setLayout(new BorderLayout());
        seleccionFrame.add(scrollPane, BorderLayout.CENTER);
        seleccionFrame.add(botonAnadir, BorderLayout.SOUTH);
        seleccionFrame.setVisible(true);
    }
    
    private void mostrarFormularioConceptos() {
        JFrame formularioConceptos = new JFrame();
        JPanel formularioPanel = new JPanel(new BorderLayout());
        JPanel formularioPanel2 = new JPanel(new GridLayout(0, 2, 10, 10));
        JButton botonAgregar = new JButton("Agregar");
        
        formularioConceptos.setSize(500, 500);
        formularioConceptos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioConceptos.setLocationRelativeTo(null);
        formularioConceptos.setResizable(false);
        
        JTextArea descripcionTextArea = new JTextArea(10, 30);
        JTextField importeTextField = new JTextField(20);
        
        formularioPanel2.add(new JLabel("Descripcion: "));
        formularioPanel2.add(new JScrollPane(descripcionTextArea));
        formularioPanel2.add(new JLabel("Importe: "));
        formularioPanel2.add(importeTextField);
        
        botonAgregar.addActionListener(e -> {
            try {
                Random rand = new Random();
                int codigo = rand.nextInt(10000000);
                
                listaConceptos.add(new Concepto(String.valueOf(codigo), descripcionTextArea.getText(), 
                        Double.parseDouble(importeTextField.getText())));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch(NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                        "Faltan campos", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        formularioPanel.add(formularioPanel2, BorderLayout.CENTER);
        formularioPanel.add(botonAgregar, BorderLayout.PAGE_END);
    }
    
    private void anadirNomina() {
        
    }
    
}
