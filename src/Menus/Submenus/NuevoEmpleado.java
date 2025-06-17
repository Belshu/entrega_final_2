package Menus.Submenus;

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

/**
 * Menu del programa que hereda de JFrame e implementa la interfaz
 * ActionListener
 *
 * @author Isabel Shuang Piñana Alonso
 *
 */
public class NuevoEmpleado extends JFrame implements ActionListener, ItemListener {

    // PANELES
    private JPanel formularioPanel;
    private final JPanel mainPanel, radioButtonsPanel, buttonsPanel;

    // RADIOBUTTONS
    private final JRadioButton jugador, tecnico, directivo;

    // LABELES Y TEXTFIELDS
    private final JLabel dniLabel, nombreLabel, apellidoLabel, telfLabel,
            demarcacionLabel, edadLabel, valorLabel,
            puestoLabel, especialidadLabel,
            cargoLabel, margenLabel1, margenLabel2;
    private final JTextField dniTextField, nombreTextField, apellidoTextField, telfTextField,
            demarcacionTextField, edadTextField, valorTextField,
            puestoTextField, especialidadTextField,
            cargoTextField;

    // BOTONES
    private final JButton botonAnadir, botonCancelar;

    // VARIABLES
    private String dni, nombre, apellido, puesto, especialidad, cargo;
    private int telf, demarcacion, edad;
    private double valor;
    private boolean estado = true;

    /**
     * CONSTRUCTOR: inicializa los atributos finalesla ArrayList estática de
     * partidos
     *
     */
    public NuevoEmpleado() {
        mainPanel = new JPanel(new BorderLayout());
        radioButtonsPanel = new JPanel(new FlowLayout());
        formularioPanel = new JPanel(new GridLayout(0, 2, 15, 20));
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

    /**
     * Método que inicia el tamaño de la interfaz, poner el panel contenido y
     * diversas propiedades
     *
     */
    private void initialize() {
        int altura = 600;

        if (jugador.isSelected()) {
            altura = 500;
        } else if (tecnico.isSelected()) {
            altura = 300;
        } else if (directivo.isSelected()) {
            altura = 250;
        }

        setSize(500, altura);
        setTitle("Nuevo empleado");
        setContentPane(getMainPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Método que devuelve el panel principal
     *
     * @return JPanel
     */
    private JPanel getMainPanel() {
        mainPanel.add(margenLabel1, BorderLayout.LINE_START);
        mainPanel.add(margenLabel2, BorderLayout.LINE_END);
        mainPanel.add(getRadioButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);

        return mainPanel;
    }

    /**
     * Método que devuelve el panel que contiene los radiobuttons
     *
     * @return JPanel
     */
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

    /**
     * Método que devuelve el panel que contiene un formulario, compuesto por
     * los labeles y los textfields y añaden ajustando el tamaño dependiendo del
     * tipo de empleado elegido
     *
     * @return JPanel
     */
    private JPanel getFormularioPanel() {
        formularioPanel.add(dniLabel);
        formularioPanel.add(dniTextField);
        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreTextField);
        formularioPanel.add(telfLabel);
        formularioPanel.add(telfTextField);

        if (jugador.isSelected()) {
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 30));
            formularioPanel.add(apellidoLabel);
            formularioPanel.add(apellidoTextField);
            formularioPanel.add(demarcacionLabel);
            formularioPanel.add(demarcacionTextField);
            formularioPanel.add(edadLabel);
            formularioPanel.add(edadTextField);
            formularioPanel.add(valorLabel);
            formularioPanel.add(valorTextField);
        } else if (tecnico.isSelected()) {
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 15));
            formularioPanel.add(puestoLabel);
            formularioPanel.add(puestoTextField);
            formularioPanel.add(especialidadLabel);
            formularioPanel.add(especialidadTextField);
        } else if (directivo.isSelected()) {
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 5));
            formularioPanel.add(cargoLabel);
            formularioPanel.add(cargoTextField);
        }

        return formularioPanel;
    }

    /**
     * Método que devuelve el panel que contiene los botones de aceptar o
     * cancelar
     *
     * @return JPanel
     */
    private JPanel getButtonsPanel() {
        botonAnadir.addActionListener(this);
        botonCancelar.addActionListener(this);

        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonCancelar);

        return buttonsPanel;
    }

    /**
     * Método que devuelve el panel actualizado del formulario, ajustando tamaño
     * y elementos necesarios dependiendo del tipo
     *
     * @return JPanel
     */
    private void updateFormularioPanel() {

        formularioPanel.removeAll();

        formularioPanel.add(dniLabel);
        formularioPanel.add(dniTextField);
        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreTextField);
        formularioPanel.add(telfLabel);
        formularioPanel.add(telfTextField);

        int altura = 300;

        if (jugador.isSelected()) {
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 30));
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
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 15));
            formularioPanel.add(puestoLabel);
            formularioPanel.add(puestoTextField);
            formularioPanel.add(especialidadLabel);
            formularioPanel.add(especialidadTextField);
            altura = 300;
        } else if (directivo.isSelected()) {
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 5));
            formularioPanel.add(cargoLabel);
            formularioPanel.add(cargoTextField);
            altura = 250;
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

    /**
     * Metodo sobrecargado que recoge las acciones dentro de la interfaz y se le
     * asignara una utilidad a los botones correspondientes y el tipo de
     * radiobutton seleccionado
     *
     * @param ae ActionEvent 
     *
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == botonAnadir) {
            if (dniTextField.getText().isEmpty() || nombreTextField.getText().isEmpty()
                    || telfTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                        "Faltan campos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                dni = dniTextField.getText();
                nombre = nombreTextField.getText();
                telf = Integer.parseInt(telfTextField.getText());

                if (jugador.isSelected()) {

                    if (apellidoTextField.getText().isEmpty() || demarcacionTextField.getText().isEmpty()
                            || edadTextField.getText().isEmpty() || valorTextField.getText().isEmpty()) {
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
                    }

                } else if (tecnico.isSelected()) {

                    if (puestoTextField.getText().isEmpty() || especialidadTextField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                                "Faltan campos", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    puesto = puestoTextField.getText();
                    especialidad = especialidadTextField.getText();
                } else if (directivo.isSelected()) {

                    if (cargoTextField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                                "Faltan campos", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    cargo = cargoTextField.getText();
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
     * Método sobrecargado que recoge el estado y el cambio a cada elemento
     * similar, en este caso los radiobutton
     *
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            updateFormularioPanel();
        }
    }

    /**
     * Método que devuelve el String dni
     *
     * @return String
     *
     */
    public String getDni() {
        return dni;
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
