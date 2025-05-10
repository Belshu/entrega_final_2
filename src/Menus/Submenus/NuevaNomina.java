package Menus.Submenus;

import Contables.Concepto;
import Contables.Nomina;
import Empleados.Empleado;
import Menus.MenuEmpleados;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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
    private ArrayList <Nomina> nominas;
    private ArrayList <Empleado> elegidos;
    
    private int dia, mes, anio;
    
    private ArrayList <Concepto> listaConceptos;
    
    private JPanel mainPanel, formularioPanel, empleadoPanel, empleadoPanel2, fechaPanel, conceptosPanel, buttonsPanel;
    
    private JLabel seleccionarEmpleadoLabel, fechaLabel, fechaLabel2, fechaLabel3, conceptosLabel;
    private JTextField diaTextField, mesTextField, anioTextField;
    private JTextArea conceptosTextArea, seleccionarEmpleadosTextArea;
    
    private JButton botonSeleccionarEmpleado, botonConceptos, botonAceptar, botonCancelar;
    
    public NuevaNomina() {
        listaConceptos = new ArrayList<>();
        nominas = new ArrayList <>();
        elegidos = new ArrayList<>();
        
        mainPanel = new JPanel(new BorderLayout());
        formularioPanel = new JPanel();
        empleadoPanel = new JPanel(new BorderLayout());
        empleadoPanel2 = new JPanel(new FlowLayout());
        fechaPanel = new JPanel(new FlowLayout());
        conceptosPanel = new JPanel(new FlowLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        seleccionarEmpleadoLabel = new JLabel("Seleccionar Empleado:", JLabel.CENTER);
        
        fechaLabel = new JLabel("(dia/mes/año): ", JLabel.CENTER);
        fechaLabel2 = new JLabel(" / ");
        fechaLabel3 = new JLabel(" / ");
        conceptosLabel = new JLabel("Añadir conceptos: ");
        
        diaTextField = new JTextField(2);
        mesTextField = new JTextField(2);
        anioTextField = new JTextField(5);
        
        seleccionarEmpleadosTextArea = new JTextArea(6, 30);        
        conceptosTextArea = new JTextArea(6, 30);
        
        botonSeleccionarEmpleado = new JButton("Añadir a...");
        botonConceptos = new JButton("Nuevo");
        botonAceptar = new JButton("Aceptar");
        botonCancelar = new JButton("Cancelar");
        
        initialize();
    }
    
    private void initialize() {
        setTitle("Nueva nomina");
        setSize(600, 400);
        setContentPane(getMainPanel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
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
        seleccionarEmpleadosTextArea.setEnabled(false);
        
        empleadoPanel2.add(botonSeleccionarEmpleado);
        empleadoPanel2.add(seleccionarEmpleadosTextArea);
        
        return empleadoPanel2;
    }
    
    private JPanel getFechaPanel() {
        fechaPanel.add(fechaLabel);
        fechaPanel.add(diaTextField);
        fechaPanel.add(fechaLabel2);
        fechaPanel.add(mesTextField);
        fechaPanel.add(fechaLabel3);        
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
            anadirNomina();
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
        
        listaEmpleados2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaEmpleados2);
        
        JButton botonAnadir = new JButton("Añadir");
        botonAnadir.addActionListener(e -> {
            for(Empleado empleado : listaEmpleados2.getSelectedValuesList()) {
                elegidos.add(empleado);
                seleccionarEmpleadosTextArea.append(empleado.getDni() + "\n");
            }
            
            seleccionFrame.dispose();
        });
        
        seleccionFrame.setLayout(new BorderLayout());
        seleccionFrame.add(scrollPane, BorderLayout.CENTER);
        seleccionFrame.add(botonAnadir, BorderLayout.SOUTH);
        seleccionFrame.setVisible(true);
    }
    
    private void mostrarFormularioConceptos() {
        JFrame formularioConceptos = new JFrame();
        
        JPanel formularioPanelConceptos = new JPanel(new BorderLayout());
        JPanel formularioPanelConceptos2 = new JPanel();
        JPanel descripcionPanel = new JPanel(new FlowLayout());
        JPanel importePanel = new JPanel(new FlowLayout());
        formularioPanelConceptos2.setLayout(new BoxLayout(formularioPanelConceptos2,BoxLayout.Y_AXIS));
        JButton botonAgregar = new JButton("Agregar");
        
        formularioConceptos.setSize(450, 300);
        formularioConceptos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioConceptos.setLocationRelativeTo(null);
        formularioConceptos.setResizable(false);
        
        JTextArea descripcionTextArea = new JTextArea(10, 30);
        JTextField importeTextField = new JTextField(20);
        
        descripcionPanel.add(new JLabel("Descripcion: "));
        descripcionPanel.add(new JScrollPane(descripcionTextArea));
        
        importePanel.add(new JLabel("Importe: "));
        importePanel.add(importeTextField);
        
        formularioPanelConceptos2.add(descripcionPanel);
        formularioPanelConceptos2.add(Box.createVerticalStrut(0));
        formularioPanelConceptos2.add(importePanel);
        
        formularioPanelConceptos.add(formularioPanelConceptos2, BorderLayout.CENTER);
        formularioPanelConceptos.add(botonAgregar, BorderLayout.PAGE_END);
        
        formularioConceptos.setContentPane(formularioPanelConceptos);
        formularioConceptos.setVisible(true);
        
        botonAgregar.addActionListener(e -> {
            Random rand = new Random();
            int codigo = rand.nextInt(10000000);

            if (!"".equals(descripcionTextArea.getText()) || descripcionTextArea.getText() != null
                    || "".equals(importeTextField.getText()) || importeTextField.getText() != null) {
                try {
                    Concepto concepto = new Concepto(String.valueOf(codigo), descripcionTextArea.getText(),
                            Double.parseDouble(importeTextField.getText()));

                    listaConceptos.add(concepto);
                    JOptionPane.showMessageDialog(this, "Concepto creado con exito!", "Concepto creado", JOptionPane.INFORMATION_MESSAGE);
                    conceptosTextArea.append(concepto.getCodigo() + " | " + concepto.getImporte() + "\n");

                    formularioConceptos.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                    "Faltan campos", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
private void anadirNomina() {     
    if (seleccionarEmpleadosTextArea.getText().isEmpty() || 
        mesTextField.getText().isEmpty() || 
        anioTextField.getText().isEmpty()) {
        
        JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
            "Faltan campos", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        dia = Integer.parseInt(diaTextField.getText());
        mes = Integer.parseInt(mesTextField.getText());
        anio = Integer.parseInt(anioTextField.getText());
        
        if(mes < 1 || mes > 12 || dia < 1 || dia > 31) {
            JOptionPane.showMessageDialog(this, "Error en la fecha de creación de la nomina. Por favor, revisa ese campo.",
            "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for(Empleado empleado : elegidos) {
                Nomina nomina = new Nomina(empleado.getDni(), dia, mes, anio);
                empleado.agregarNomina(nomina);
                
                if(!listaConceptos.isEmpty()) {
                    for (Concepto c : listaConceptos) {
                        nomina.agregarConcepto(c);
                    }
                }
                
                nominas.add(nomina);
            }
        
            JOptionPane.showMessageDialog(this, "Nomina creada con éxito!", "Nomina creada", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.",
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public ArrayList <Empleado> getElegidos() {
        return elegidos;
    }
    
    public ArrayList <Nomina> getNominas() {
        return nominas;
    }
    
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }
}
