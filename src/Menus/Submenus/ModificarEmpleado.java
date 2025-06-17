package Menus.Submenus;

import Empleados.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Clase que Modifica al empleado seleccionado por el usuario y pone la
 * información en los TextField para que sea modificado como el usuario quiera
 *
 * @author Isabel Shuang Piñana Alonso
 */
public class ModificarEmpleado extends JFrame implements ActionListener {

    // PANELES
    private JPanel formularioPanel;
    private final JPanel mainPanel, buttonsPanel;

    // ELEMENTOS
    private final JLabel nombreLabel, apellidoLabel, telfLabel,
            demarcacionLabel, edadLabel, valorLabel,
            puestoLabel, especialidadLabel,
            cargoLabel, margenLabel1, margenLabel2, margenLabel3;
    private final JTextField nombreTextField, apellidoTextField, telfTextField,
            demarcacionTextField, edadTextField, valorTextField,
            puestoTextField, especialidadTextField,
            cargoTextField;

    private final JButton botonAplicar, botonCancelar;
    private final JRadioButton disponible, noDisponible;
    private final ButtonGroup grupoBotones;

    // INFORMACION A ALMACENAR
    private int index;

    private String nombre, apellido, puesto, especialidad, cargo;
    private int telf, demarcacion, edad;
    private double valor;
    private boolean estado;

    /**
     * CONSTRUCTOR: recoge un empleado y se comprueba el tipo de empleado en
     * base a un entero index. Los String tomarán el valor del empleado
     *
     * @param empleado Empleado
     *
     */
    public ModificarEmpleado(Empleado empleado) {

        // INTERFAZ
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());

        margenLabel1 = new JLabel("           ");
        margenLabel2 = new JLabel("           ");
        margenLabel3 = new JLabel("           ", JLabel.CENTER);

        nombreLabel = new JLabel("Nombre: ");
        apellidoLabel = new JLabel("Apellido: ");
        telfLabel = new JLabel("Telefono: ");
        demarcacionLabel = new JLabel("Demarcacion: ");
        edadLabel = new JLabel("Edad: ");
        valorLabel = new JLabel("Valor: ");
        puestoLabel = new JLabel("Puesto: ");
        especialidadLabel = new JLabel("Especialidad: ");
        cargoLabel = new JLabel("Cargo: ");

        nombreTextField = new JTextField();
        apellidoTextField = new JTextField();
        telfTextField = new JTextField();
        demarcacionTextField = new JTextField();
        edadTextField = new JTextField();
        valorTextField = new JTextField();
        puestoTextField = new JTextField();
        especialidadTextField = new JTextField();
        cargoTextField = new JTextField();

        disponible = new JRadioButton("Disponible");
        noDisponible = new JRadioButton("No disponible");
        grupoBotones = new ButtonGroup();

        botonAplicar = new JButton("Aplicar cambios");
        botonCancelar = new JButton("Cancelar");

        // DATOS DEL EMPLEADO
        nombre = empleado.getNombre();
        telf = empleado.getTelf();

        if (empleado instanceof Jugador j) {
            index = 0;

            apellido = j.getApellidos();
            demarcacion = j.getDemarcacion();
            edad = j.getEdad();
            valor = j.getValor();
            estado = j.isEstado();

            disponible.setSelected(estado);
            noDisponible.setSelected(!estado);
        } else if (empleado instanceof Tecnico t) {
            index = 1;

            puesto = t.getPuesto();
            especialidad = t.getEspecialidad();
        } else if (empleado instanceof Directivo d) {
            index = 2;

            cargo = d.getCargo();
        }

        initialize();
    }

    /**
     * Método que asigna algunas características de la interfaz y dependienddo
     * del tipo de Empleado se pondrá un título distinto
     *
     */
    private void initialize() {
        String title = "Modificar empleado";

        int altura = 600;

        switch (index) {
            case 0 -> {
                title += ": Jugador";
                altura = 500;
            }
            case 1 -> {
                title += ": Tecnico";
                altura = 300;
            }
            case 2 -> {
                title += ": Directivo";
                altura = 200;
            }
            default -> {
            }
        }

        setTitle(title);
        setSize(500, altura);
        setContentPane(getMainPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Método que asigna la fuente y lo importante: el tamaño de la letra del
     * label que sirve para margen en la parte superior y lo devolverá
     *
     * @return JPanel
     *
     */
    private JPanel getMainPanel() {
        margenLabel3.setFont(new Font("Arial", Font.PLAIN, 10));

        mainPanel.add(margenLabel1, BorderLayout.LINE_START);
        mainPanel.add(margenLabel2, BorderLayout.LINE_END);
        mainPanel.add(margenLabel3, BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);

        return mainPanel;
    }

    /**
     * Método que, dependiendo del tipo de empleado, cambiará el número de
     * columnas que tendrá el panel a la vez que se le asignará en orden los
     * labeles y los textfield y devolverá el panel
     *
     * @return JPanel
     *
     */
    private JPanel getFormularioPanel() {

        formularioPanel = switch (index) {
            case 0 ->
                new JPanel(new GridLayout(0, 2, 15, 35));
            case 1 ->
                new JPanel(new GridLayout(0, 2, 15, 20));
            case 2 ->
                new JPanel(new GridLayout(0, 2, 1, 5));
            default ->
                new JPanel();
        };

        formularioPanel.add(nombreLabel);
        nombreTextField.setText(nombre);
        formularioPanel.add(nombreTextField);

        formularioPanel.add(telfLabel);
        telfTextField.setText(String.valueOf(telf));
        formularioPanel.add(telfTextField);

        switch (index) {
            case 0 -> {
                formularioPanel.add(apellidoLabel);
                apellidoTextField.setText(apellido);
                formularioPanel.add(apellidoTextField);

                formularioPanel.add(demarcacionLabel);
                demarcacionTextField.setText(String.valueOf(demarcacion));
                formularioPanel.add(demarcacionTextField);

                formularioPanel.add(edadLabel);
                edadTextField.setText(String.valueOf(edad));
                formularioPanel.add(edadTextField);

                formularioPanel.add(valorLabel);
                valorTextField.setText(String.valueOf(valor));
                formularioPanel.add(valorTextField);

                grupoBotones.add(disponible);
                grupoBotones.add(noDisponible);

                formularioPanel.add(disponible);
                formularioPanel.add(noDisponible);
            }

            case 1 -> {
                formularioPanel.add(puestoLabel);
                puestoTextField.setText(puesto);
                formularioPanel.add(puestoTextField);

                formularioPanel.add(especialidadLabel);
                especialidadTextField.setText(especialidad);
                formularioPanel.add(especialidadTextField);
            }

            case 2 -> {
                formularioPanel.add(cargoLabel);
                cargoTextField.setText(cargo);
                formularioPanel.add(cargoTextField);
            }
        }

        return formularioPanel;
    }

    /**
     * Método que contiene los botones y lo devolverá
     *
     * @return JPanel
     *
     */
    private JPanel getButtonsPanel() {
        botonAplicar.addActionListener(this);
        botonCancelar.addActionListener(this);

        buttonsPanel.add(botonAplicar);
        buttonsPanel.add(botonCancelar);

        return buttonsPanel;
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
        if (ae.getSource() == botonAplicar) {

            if (nombreTextField.getText().isEmpty() || telfTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                        "Faltan campos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                nombre = nombreTextField.getText();
                telf = Integer.parseInt(telfTextField.getText());

                switch (index) {
                    case 0 -> {

                        if (demarcacionTextField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                                    "Faltan campos", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        demarcacion = Integer.parseInt(demarcacionTextField.getText());

                        if (demarcacion < 0 || demarcacion > 11) {
                            JOptionPane.showMessageDialog(this, "La demarcación debe ser entre 0 y 11", "WARNING", JOptionPane.WARNING_MESSAGE);
                            demarcacion = 0;
                            demarcacionTextField.setText("");
                            return;
                        } else {
                            apellido = apellidoTextField.getText();
                            edad = Integer.parseInt(edadTextField.getText());
                            valor = Double.parseDouble(valorTextField.getText());
                            if (disponible.isSelected()) {
                                estado = true;
                            } else {
                                estado = false;
                            }
                        }
                    }

                    case 1 -> {

                        if (puestoTextField.getText().isEmpty() || especialidadTextField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                                    "Faltan campos", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        puesto = puestoTextField.getText();
                        especialidad = especialidadTextField.getText();
                    }

                    case 2 -> {
                        if (puestoTextField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                                    "Faltan campos", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        cargo = cargoTextField.getText();
                    }
                }
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == botonCancelar) {
            dispose();
        }
    }

    /**
     * Método que devuelve el String nombre
     *
     * @return String
     *
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que devuelve el String apellido
     *
     * @return String
     *
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método que devuelve el String puesto
     *
     * @return String
     *
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Método que devuelve el String especialidad
     *
     * @return String
     *
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Método que devuelve el String cargo
     *
     * @return String
     *
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Método que devuelve el int telf
     *
     * @return int
     *
     */
    public int getTelf() {
        return telf;
    }

    /**
     * Método que devuelve el int demarcacion
     *
     * @return int
     *
     */
    public int getDemarcacion() {
        return demarcacion;
    }

    /**
     * Método que devuelve el int edad
     *
     * @return int
     *
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método que devuelve el double valor
     *
     * @return double
     *
     */
    public double getValor() {
        return valor;
    }

    /**
     * Método que devuelve el boolean estado
     *
     * @return boolean
     *
     */
    public boolean isEstado() {
        return estado;
    }
}
