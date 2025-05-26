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
 * Clase que crea una nueva nomina o un conjunto de estas dependiendo de la 
 * cantidad de jugadores que elija el usuario para ponerselas. Tomará los datos
 * del usuario en base a los JTextField y los agregará a cada una para luego 
 * asignárselo a los empleados elegidos
 * 
 * @author Isabel Shuang Piñana Alonso
 */

public class NuevaNomina extends JFrame implements ActionListener{
    
    // LISTAS
    private ArrayList <Empleado> elegidos;
    private Nomina nomina;
    private ArrayList <Concepto> listaConceptos;
    
    // INFORMACIÓN A ALMACENAR
    private int dia, mes, anio;
    
    // PANELES
    private final JPanel mainPanel, formularioPanel, empleadoPanel, empleadoPanel2, fechaPanel, conceptosPanel, buttonsPanel;
    
    // ELEMENTOS
    private final JLabel seleccionarEmpleadoLabel, fechaLabel, fechaLabel2, fechaLabel3, conceptosLabel;
    private final JTextField diaTextField, mesTextField, anioTextField;
    private final JTextArea conceptosTextArea, seleccionarEmpleadosTextArea;
    
    private final JButton botonSeleccionarEmpleado, botonConceptos, botonAceptar, botonCancelar;
    
    /**
     * CONSTRUCTOR: inicialización de los atributos finales
     **/
    public NuevaNomina() {
        listaConceptos = new ArrayList<>();
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
    
    /**
     * Método que inicia el tamaño de la interfaz, poner el panel contenido y 
     * diversas propiedades
     **/
    private void initialize() {
        setTitle("Nueva nomina");
        setSize(600, 400);
        setContentPane(getMainPanel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Método que devuelve el panel principal donde se colocarán las ventanas
     * @return JPanel
     **/
    private JPanel getMainPanel() {
        JLabel marginLabel = new JLabel("     ");
        marginLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        
        mainPanel.add(marginLabel, BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
    /**
     * Método que devuelve el panel de los formularios donde se colocarán el panel de
     * empleados, de fecha y de conceptos
     * @return JPanel
     **/
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
    
    /**
     * Método que devuelve el panel de empleados donde se colocarán los elementos
     * correspondientes
     * @return JPanel
     **/
    private JPanel getEmpleadoPanel() {
        empleadoPanel.add(seleccionarEmpleadoLabel, BorderLayout.PAGE_START);
        empleadoPanel.add(getEmpleadoPanel2(), BorderLayout.CENTER);
        
        return empleadoPanel;
    }

    /**
     * Método que devuelve el segundo panel de empleados donde se colocarán los elementos
     * correspondientes
     * @return JPanel
     **/
    private JPanel getEmpleadoPanel2() {
        botonSeleccionarEmpleado.addActionListener(this);
        seleccionarEmpleadosTextArea.setEnabled(false);
        
        empleadoPanel2.add(botonSeleccionarEmpleado);
        empleadoPanel2.add(seleccionarEmpleadosTextArea);
        
        return empleadoPanel2;
    }
    
    /**
     * Método que devuelve el panel de fecha donde se colocarán los elementos
     * correspondientes
     * @return JPanel
     **/
    private JPanel getFechaPanel() {
        fechaPanel.add(fechaLabel);
        fechaPanel.add(diaTextField);
        fechaPanel.add(fechaLabel2);
        fechaPanel.add(mesTextField);
        fechaPanel.add(fechaLabel3);        
        fechaPanel.add(anioTextField);
        
        return fechaPanel;
    }
    
    /**
     * Método que devuelve el panel de conceptos donde se colocarán los elementos
     * correspondientes
     * @return JPanel
     **/
    private JPanel getConceptosPanel() {
        botonConceptos.addActionListener(this);
        conceptosTextArea.setEnabled(false);
        
        conceptosPanel.add(conceptosLabel);
        conceptosPanel.add(botonConceptos);
        conceptosPanel.add(new JScrollPane(conceptosTextArea));
        
        return conceptosPanel;
    }
    
    /**
     * Método que devuelve el panel de botones donde se colocarán los elementos
     * correspondientes
     * @return JPanel
     **/
    private JPanel getButtonsPanel() {
        botonAceptar.addActionListener(this);
        botonCancelar.addActionListener(this);
        
        buttonsPanel.add(botonAceptar);
        buttonsPanel.add(botonCancelar);
        
        return buttonsPanel;
    }
    
    /**
     * Metodo sobrecargado que recoge las acciones dentro de la interfaz y se le asignara una utilidad a los
     * botones correspondientes
     * @param ae ActionEvent 
     **/
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
    
    /**
     * Método que muestra una lista de empleados que e usuario podrá seleccionar
     * para agregarles una nómina
     **/
    private void mostrarListaEmpleados() {
        JFrame seleccionFrame = new JFrame("Seleccionar Empleados");
        
        seleccionFrame.setSize(1000, 500);
        seleccionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seleccionFrame.setLocationRelativeTo(null);
        seleccionFrame.setResizable(false);
        
        DefaultListModel <Empleado> empleadoListModel = new DefaultListModel<>();
        
        empleadoListModel.clear();

        
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
    
    /**
     * Método que muestra una interfaz de formulario para rellenar los datos del concepto
     * que se desea agregar
     **/
    private void mostrarFormularioConceptos() {
        
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
                JOptionPane.showMessageDialog(NuevaNomina.this, "Complete todos los campos, por favor.", 
                        "Faltan campos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if(!listaConceptos.isEmpty()) {
                for(Concepto c : listaConceptos) {
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
                listaConceptos.add(concepto);
                conceptosTextArea.append(concepto.getCodigo() + " | " + concepto.getImporte() + "\n");
                formularioConceptos.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(NuevaNomina.this, "Error en la entrada de datos. Por favor, revise los campos.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Método que añade los datos a la nueva o nuevas nóminas, con la información necesaria
     **/
    private void anadirNomina() {
        if (seleccionarEmpleadosTextArea.getText().isEmpty()
                || mesTextField.getText().isEmpty()
                || anioTextField.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                    "Faltan campos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            dia = Integer.parseInt(diaTextField.getText());
            mes = Integer.parseInt(mesTextField.getText());
            anio = Integer.parseInt(anioTextField.getText());

            if (mes < 1 || mes > 12 || dia < 1 || dia > 31) {
                JOptionPane.showMessageDialog(this, "Error en la fecha de creación de la nomina. Por favor, revisa ese campo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                for (Empleado elegido : elegidos) {
                    for(Empleado e : MenuEmpleados.getListaEmpleados()) {
                        if(elegido.getDni().equals(e.getDni()) && !e.isEliminado()) {
                            nomina = new Nomina(dia, mes, anio);

                            if (!listaConceptos.isEmpty()) {
                                for (Concepto c : listaConceptos) {
                                    nomina.agregarConcepto(c);
                                }
                            }
                            e.agregarNomina(nomina);
                        }
                    }
                }
                dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // GETTERS Y SETTERS
    public ArrayList <Empleado> getElegidos() {
        return elegidos;
    }

    public Nomina getNomina() {
        return nomina;
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
