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

public class NuevoPartido extends JFrame implements ActionListener {
    private final ArrayList<Jugador> listaJugadores;
    private ArrayList<Jugador> jugadoresSeleccionados;
    private Partido partidoNuevo;

    private final JPanel mainPanel, formularioPanel, nombreRivalPanel, golesPanel, radioButtonsPanel, jugadoresPanel, buttonsPanel;

    private final JLabel nombreRivalLabel, golesLabel, jugadoresLabel;
    private final JTextField nombreRivalTextField, golesTextField1, golesTextField2;
    private final JTextArea jugadoresTextArea;

    private final JRadioButton local, noLocal;
    private final JButton botonListaJugadores, botonAceptar, botonCancelar;

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

    private void initialize() {
        setTitle("PARTIDO");
        setSize(500, 400);
        setContentPane(getMainPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private JPanel getMainPanel() {
        JLabel titulo = new JLabel("DATOS DEL PARTIDO", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.PLAIN, 25));

        mainPanel.add(titulo, BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);

        return mainPanel;
    }

    private JPanel getFormularioPanel() {
        formularioPanel.setLayout(new BoxLayout(formularioPanel, BoxLayout.Y_AXIS)); // Usar BoxLayout vertical

        formularioPanel.add(getNombreRivalPanel());
        formularioPanel.add(Box.createVerticalStrut(2)); // Espacio vertical entre componentes
        formularioPanel.add(getGolesPanel());
        formularioPanel.add(Box.createVerticalStrut(2)); // Espacio vertical entre componentes
        formularioPanel.add(getRadioButtonsPanel());
        formularioPanel.add(Box.createVerticalStrut(2)); // Espacio vertical entre componentes
        formularioPanel.add(getJugadoresPanel());

        return formularioPanel;
    }

    private JPanel getNombreRivalPanel() {
        nombreRivalPanel.add(nombreRivalLabel);
        nombreRivalPanel.add(nombreRivalTextField);
        return nombreRivalPanel;
    }

    private JPanel getGolesPanel() {
        golesPanel.add(golesTextField1);
        golesPanel.add(golesLabel);
        golesPanel.add(golesTextField2);
        return golesPanel;
    }

    private JPanel getRadioButtonsPanel() {
        ButtonGroup grupo = new ButtonGroup();
        
        grupo.add(local);
        grupo.add(noLocal);
        
        local.setSelected(true);
        
        radioButtonsPanel.add(local);
        radioButtonsPanel.add(noLocal);
        return radioButtonsPanel;
    }

    private JPanel getJugadoresPanel() {
        botonListaJugadores.addActionListener(this);
        jugadoresTextArea.setEnabled(false);
        
        jugadoresPanel.add(jugadoresLabel);
        jugadoresPanel.add(botonListaJugadores);
        jugadoresPanel.add(new JScrollPane(jugadoresTextArea));
        return jugadoresPanel;
    }

    private JPanel getButtonsPanel() {
        buttonsPanel.add(botonAceptar);
        buttonsPanel.add(botonCancelar);
        
        botonAceptar.addActionListener(this);
        botonCancelar.addActionListener(this);
        return buttonsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonListaJugadores) {
            mostrarListaJugadores();
        }
        
        if(ae.getSource() == botonAceptar) {
            crearPartido();
        } else if(ae.getSource() == botonCancelar) {
            dispose();
        }
    }
    
    private void mostrarListaJugadores() {
        JFrame seleccionFrame = new JFrame("Seleccionar Jugadores");
        
        seleccionFrame.setSize(1000, 500);
        seleccionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seleccionFrame.setLocationRelativeTo(null);
        seleccionFrame.setResizable(false);

        DefaultListModel<Jugador> listModel = new DefaultListModel<>();
        for (Jugador jugador : listaJugadores) {
            listModel.addElement(jugador);
        }

        JList<Jugador> listaJugadores2 = new JList<>(listModel);
        listaJugadores2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaJugadores2);

        JButton botonAnadir = new JButton("Añadir");
        
        botonAnadir.addActionListener(e -> {
            StringBuilder seleccionados = new StringBuilder();
            
            for (Jugador jugador : listaJugadores2.getSelectedValuesList()) {
                jugadoresSeleccionados.add(jugador);
                
                seleccionados.append(jugador.getDni()).append(" | ").append(jugador.getNombre()).append("\n");
            }
            
            jugadoresTextArea.setText(seleccionados.toString());
            seleccionFrame.dispose(); 
        });

        seleccionFrame.setLayout(new BorderLayout());
        seleccionFrame.add(scrollPane, BorderLayout.CENTER);
        seleccionFrame.add(botonAnadir, BorderLayout.SOUTH);
        seleccionFrame.setVisible(true);
    }
    
    private void crearPartido() {
        boolean esLocal = true;
        
        if(noLocal.isSelected()) esLocal = false;
        else if(local.isSelected()) esLocal = true;
        
        if(jugadoresSeleccionados.isEmpty() || "".equals(nombreRivalTextField.getText()) 
                || "".equals(golesTextField1.getText()) || "".equals(golesTextField2.getText())) {
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

    public Partido getPartidoNuevo() {
        return partidoNuevo;
    }
}
