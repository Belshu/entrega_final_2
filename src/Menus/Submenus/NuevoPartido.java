package Menus.Submenus;

import Empleados.Jugador;
import Menus.MenuPartidos;
import Partidos.Partido;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

/**
 * Menu formulario para crear un nuevo partido, heredando de JFrame e
 * implementando ActionListener
 *
 * @author Isabel Shuang Piñana Alonso
     *
 */
public class NuevoPartido extends JFrame implements ActionListener {

    // Listas y atributos
    private final ArrayList<Jugador> listaJugadores;
    private ArrayList<Jugador> jugadoresSeleccionados;
    private Partido partidoNuevo;

    // Paneles
    private final JPanel mainPanel, formularioPanel, nombreRivalPanel, golesPanel, radioButtonsPanel, jugadoresPanel, buttonsPanel;

    // Elementos
    private final JLabel nombreRivalLabel, golesLabel, jugadoresLabel;
    private final JTextField nombreRivalTextField, golesTextField1, golesTextField2;
    private final JTextArea jugadoresTextArea;

    // Botones
    private final JRadioButton local, noLocal;
    private final JButton botonListaJugadores, botonAceptar, botonCancelar;

    /**
     * CONSTRUCTOR: pide la lista de jugadores actuales y le asignamos esos
     * valores a la listaJugadores que tenemos ahora mismo. Inicializamos todos
     * los atributos
     *
     * @param listaJugadores ArrayList Jugador
         *
     */
    public NuevoPartido(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
        jugadoresSeleccionados = new ArrayList<>();

        mainPanel = new JPanel(new BorderLayout());
        formularioPanel = new JPanel();
        nombreRivalPanel = new JPanel();
        golesPanel = new JPanel();
        radioButtonsPanel = new JPanel();
        jugadoresPanel = new JPanel();
        buttonsPanel = new JPanel();

        nombreRivalLabel = new JLabel("Nombre del rival: ");
        golesLabel = new JLabel(" - ");
        jugadoresLabel = new JLabel("Jugadores: ");

        nombreRivalTextField = new JTextField(20);
        golesTextField1 = new JTextField(3);
        golesTextField2 = new JTextField(3);

        jugadoresTextArea = new JTextArea(10, 30);

        local = new JRadioButton("Local");
        noLocal = new JRadioButton("No local");

        botonListaJugadores = new JButton("Lista jugadores");
        botonAceptar = new JButton("Aceptar");
        botonCancelar = new JButton("Cancelar");

        initialize();
    }

    /**
     * Método que inicia el tamaño de la interfaz, poner el panel contenido y
     * diversas propiedades
        *
     */
    private void initialize() {
        setTitle("PARTIDO");
        setSize(500, 400);
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
        JLabel titulo = new JLabel("DATOS DEL PARTIDO", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.PLAIN, 25));

        mainPanel.add(titulo, BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);

        return mainPanel;
    }

    /**
     * Método que devuelve el panel donde contendrán los elementos que formarán
     * el formulario. Siendo BoxLayout, este será vertical (similar a
     * GridLayout) con una separación de 2 bloques en cada elemento
     *
     * @return JPanel
         *
     */
    private JPanel getFormularioPanel() {
        formularioPanel.setLayout(new BoxLayout(formularioPanel, BoxLayout.Y_AXIS));

        formularioPanel.add(getNombreRivalPanel());
        formularioPanel.add(Box.createVerticalStrut(2));
        formularioPanel.add(getGolesPanel());
        formularioPanel.add(Box.createVerticalStrut(2));
        formularioPanel.add(getRadioButtonsPanel());
        formularioPanel.add(Box.createVerticalStrut(2));
        formularioPanel.add(getJugadoresPanel());

        return formularioPanel;
    }

    /**
     * Método que devuelve el panel que contiene el label nombre y el Textfield
     * correspondiente
     *
     * @return JPanel
         *
     */
    private JPanel getNombreRivalPanel() {
        nombreRivalPanel.add(nombreRivalLabel);
        nombreRivalPanel.add(nombreRivalTextField);
        return nombreRivalPanel;
    }

    /**
     * Método que devuelve el panel que contiene los textField y labeles
     * correspondientes
     *
     * @return JPanel
         *
     */
    private JPanel getGolesPanel() {
        golesPanel.add(golesTextField1);
        golesPanel.add(golesLabel);
        golesPanel.add(golesTextField2);
        return golesPanel;
    }

    /**
     * Método que devuelve el panel que contiene los radiobuttons que determinan
     * el tipo de partido
     *
     * @return JPanel
         *
     */
    private JPanel getRadioButtonsPanel() {
        ButtonGroup grupo = new ButtonGroup();

        grupo.add(local);
        grupo.add(noLocal);

        local.setSelected(true);

        radioButtonsPanel.add(local);
        radioButtonsPanel.add(noLocal);
        return radioButtonsPanel;
    }

    /**
     * Método que devuelve el panel que contiene el botón, el textArea y un
     * label para añadir los jugadores participantes
     *
     * @return JPanel
         *
     */
    private JPanel getJugadoresPanel() {
        botonListaJugadores.addActionListener(this);
        jugadoresTextArea.setEnabled(false);

        jugadoresPanel.add(jugadoresLabel);
        jugadoresPanel.add(botonListaJugadores);
        jugadoresPanel.add(new JScrollPane(jugadoresTextArea));
        return jugadoresPanel;
    }

    /**
     * Método que devuelve el panel que contiene los botones correspondientes
     * para aceptar o no
     *
     * @return JPanel
         *
     */
    private JPanel getButtonsPanel() {
        buttonsPanel.add(botonAceptar);
        buttonsPanel.add(botonCancelar);

        botonAceptar.addActionListener(this);
        botonCancelar.addActionListener(this);
        return buttonsPanel;
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
        if (ae.getSource() == botonListaJugadores) {
            mostrarListaJugadores();
        }

        if (ae.getSource() == botonAceptar) {
            crearPartido();
        } else if (ae.getSource() == botonCancelar) {
            dispose();
        }
    }

    /**
     * Método que muestra una nueva interfaz con todos los jugadores disponibles
     * a seleccionar
         *
     */
    private void mostrarListaJugadores() {
        JFrame seleccionFrame = new JFrame("Seleccionar Jugadores");

        seleccionFrame.setSize(1000, 500);
        seleccionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seleccionFrame.setLocationRelativeTo(null);
        seleccionFrame.setResizable(false);

        DefaultListModel<Jugador> jugadoresListModel = new DefaultListModel<>();
        for (Jugador jugador : listaJugadores) {
            if (!jugador.isEliminado() && !jugadoresSeleccionados.contains(jugador)) {
                jugadoresListModel.addElement(jugador);
            }
        }

        JList<Jugador> listaJugadores2 = new JList<>(jugadoresListModel);

        listaJugadores2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaJugadores2);

        JButton botonAnadir = new JButton("Añadir");

        botonAnadir.addActionListener(e -> {
            StringBuilder seleccionados = new StringBuilder();

            for (Jugador jugador : listaJugadores2.getSelectedValuesList()) {
                if (!jugadoresSeleccionados.contains(jugador)) {
                    jugadoresSeleccionados.add(jugador);
                    seleccionados.append(jugador.getDni()).append(" | ").append(jugador.getNombre()).append("\n");
                }
            }

            jugadoresTextArea.append(seleccionados.toString());
            seleccionFrame.dispose();
        });

        seleccionFrame.setLayout(new BorderLayout());
        seleccionFrame.add(scrollPane, BorderLayout.CENTER);
        seleccionFrame.add(botonAnadir, BorderLayout.SOUTH);
        seleccionFrame.setVisible(true);
    }

    /**
     * Método que crea un partido basado en la información recogida, vigilando
     * que no haya ninguna vacía ni con margen de 
     *
     */
    private void crearPartido() {
        boolean esLocal = true;

        if (noLocal.isSelected()) {
            esLocal = false;
        } else if (local.isSelected()) {
            esLocal = true;
        }

        if (jugadoresSeleccionados.isEmpty() || nombreRivalTextField.getText().isEmpty()
                || golesTextField1.getText().isEmpty() || golesTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                    "Faltan campos", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                partidoNuevo = new Partido(nombreRivalTextField.getText(), new Date(),
                        Integer.parseInt(golesTextField1.getText()), Integer.parseInt(golesTextField2.getText()),
                        esLocal, jugadoresSeleccionados);

                MenuPartidos.getListaPartidos().add(partidoNuevo);

                JOptionPane.showMessageDialog(this, "Partido registrado con exito!", "Partido registrado", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                        "Faltan campos", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Método que devuelve el partido generado con los campos recogidos
     *
     * @return Partido 
     *
     */
    public Partido getPartidoNuevo() {
        return partidoNuevo;
    }
}
