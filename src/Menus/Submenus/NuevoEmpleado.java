package Menus.Submenus;

import Empleados.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NuevoEmpleado extends JFrame implements ActionListener, ItemListener {

    private JPanel formularioPanel;
    private final JPanel mainPanel, radioButtonsPanel, buttonsPanel;

    private final JRadioButton jugador, tecnico, directivo;

    private final JLabel dniLabel, nombreLabel, apellidoLabel, telfLabel,
            demarcacionLabel, edadLabel, valorLabel,
            puestoLabel, especialidadLabel,
            cargoLabel, margenLabel1, margenLabel2;
    private final JTextField dniTextField, nombreTextField, apellidoTextField, telfTextField,
            demarcacionTextField, edadTextField, valorTextField,
            puestoTextField, especialidadTextField,
            cargoTextField;

    private final JButton botonAnadir, botonCancelar;

    private String dni, nombre, apellido, puesto, especialidad, cargo;
    private int telf, demarcacion, edad;
    private double valor;
    private boolean estado = true;

    public NuevoEmpleado() {
        mainPanel = new JPanel(new BorderLayout());
        radioButtonsPanel = new JPanel(new FlowLayout());
        buttonsPanel = new JPanel(new FlowLayout());

        jugador = new JRadioButton("Jugador");
        tecnico = new JRadioButton("Tecnico");
        directivo = new JRadioButton("Directivo");

        margenLabel1 = new JLabel("           ");
        margenLabel2 = new JLabel("           ");

        dniLabel = new JLabel("DNI: ");
        nombreLabel = new JLabel("Nombre: ");
        apellidoLabel = new JLabel("Apellido: ");
        telfLabel = new JLabel("Telefono: ");
        demarcacionLabel = new JLabel("Demarcacion: ");
        edadLabel = new JLabel("Edad: ");
        valorLabel = new JLabel("Valor: ");
        puestoLabel = new JLabel("Puesto: ");
        especialidadLabel = new JLabel("Especialidad: ");
        cargoLabel = new JLabel("Cargo: ");

        dniTextField = new JTextField();
        nombreTextField = new JTextField();
        apellidoTextField = new JTextField();
        telfTextField = new JTextField();
        demarcacionTextField = new JTextField();
        edadTextField = new JTextField();
        valorTextField = new JTextField();
        puestoTextField = new JTextField();
        especialidadTextField = new JTextField();
        cargoTextField = new JTextField();

        botonAnadir = new JButton("Anadir");
        botonCancelar = new JButton("Cancelar");

        initialize();
    }

    private void initialize() {
        setTitle("Nuevo empleado");
        setSize(500, 500);
        setContentPane(getMainPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private JPanel getMainPanel() {
        mainPanel.add(margenLabel1, BorderLayout.LINE_START);
        mainPanel.add(margenLabel2, BorderLayout.LINE_END);
        mainPanel.add(getRadioButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);

        return mainPanel;
    }

    private JPanel getRadioButtonsPanel() {
        jugador.addItemListener(this);
        tecnico.addItemListener(this);
        directivo.addItemListener(this);

        ButtonGroup grupo = new ButtonGroup();

        grupo.add(jugador);
        grupo.add(tecnico);
        grupo.add(directivo);

        radioButtonsPanel.add(jugador);
        radioButtonsPanel.add(tecnico);
        radioButtonsPanel.add(directivo);

        jugador.setSelected(true);

        return radioButtonsPanel;
    }

    private JPanel getFormularioPanel() {
        formularioPanel = new JPanel(new GridLayout(0, 2, 15, 20));

        formularioPanel.add(dniLabel);
        formularioPanel.add(dniTextField);
        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreTextField);
        formularioPanel.add(telfLabel);
        formularioPanel.add(telfTextField);
        updateFormularioPanel();

        return formularioPanel;
    }

    private JPanel getButtonsPanel() {
        botonAnadir.addActionListener(this);
        botonCancelar.addActionListener(this);

        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonCancelar);

        return buttonsPanel;
    }

    private void updateFormularioPanel() {
        if (formularioPanel == null) {
            formularioPanel = new JPanel(new GridLayout(0, 2, 15, 2));
        }

        formularioPanel.removeAll();

        formularioPanel.add(dniLabel);
        formularioPanel.add(dniTextField);
        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreTextField);
        formularioPanel.add(telfLabel);
        formularioPanel.add(telfTextField);

        int altura = 300;

        if (jugador.isSelected()) {
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 35));
            formularioPanel.add(apellidoLabel);
            formularioPanel.add(apellidoTextField);
            formularioPanel.add(demarcacionLabel);
            formularioPanel.add(demarcacionTextField);
            formularioPanel.add(edadLabel);
            formularioPanel.add(edadTextField);
            formularioPanel.add(valorLabel);
            formularioPanel.add(valorTextField);
            altura = 500;
        } else if (tecnico.isSelected()) {
            formularioPanel.add(puestoLabel);
            formularioPanel.add(puestoTextField);
            formularioPanel.add(especialidadLabel);
            formularioPanel.add(especialidadTextField);
        } else if (directivo.isSelected()) {
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 5));
            formularioPanel.add(cargoLabel);
            formularioPanel.add(cargoTextField);
            altura = 200;
        }

        setSize(500, altura);

        formularioPanel.revalidate();
        formularioPanel.repaint();
    }

    public int tipoEmpleado() {
        int index = 0;

        if (tecnico.isSelected()) {
            index = 1;
        }
        if (directivo.isSelected()) {
            index = 2;
        }

        return index;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == botonAnadir) {
            try {
                if (!dniTextField.getText().isEmpty() && !nombreTextField.getText().isEmpty() && !telfTextField.getText().isEmpty()) {
                    dni = dniTextField.getText();
                    nombre = nombreTextField.getText();
                    telf = Integer.parseInt(telfTextField.getText());

                    if (jugador.isSelected()) {
                        if (!apellidoTextField.getText().isEmpty() && !demarcacionTextField.getText().isEmpty()
                                && !edadTextField.getText().isEmpty() && !valorTextField.getText().isEmpty()) {
                            apellido = apellidoTextField.getText();
                            demarcacion = Integer.parseInt(demarcacionTextField.getText());
                            edad = Integer.parseInt(edadTextField.getText());
                            valor = Double.parseDouble(valorTextField.getText());
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (tecnico.isSelected()) {
                        if (!puestoTextField.getText().isEmpty() && !especialidadTextField.getText().isEmpty()) {
                            puesto = puestoTextField.getText();
                            especialidad = especialidadTextField.getText();
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (directivo.isSelected()) {
                        if (!cargoTextField.getText().isEmpty()) {
                            cargo = cargoTextField.getText();
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == botonCancelar) {
            dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            updateFormularioPanel();
        }
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getCargo() {
        return cargo;
    }

    public int getTelf() {
        return telf;
    }

    public int getDemarcacion() {
        return demarcacion;
    }

    public int getEdad() {
        return edad;
    }

    public double getValor() {
        return valor;
    }

    public boolean isEstado() {
        return estado;
    }
}
