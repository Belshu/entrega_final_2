package Menus.Submenus;

import Empleados.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Isabel Shuang Piñana Alonso
 */

public class NuevoEmpleado extends JFrame implements ActionListener {    
    private JPanel formularioPanel;
    private final JPanel mainPanel, buttonsPanel;
    
    private final JLabel nombreLabel, apellidoLabel, telfLabel,
            demarcacionLabel, edadLabel, valorLabel,
            puestoLabel, especialidadLabel, 
            cargoLabel, margenLabel1, margenLabel2, margenLabel3;
    private final JTextField nombreTextField, apellidoTextField, telfTextField,
            demarcacionTextField, edadTextField, valorTextField,
            puestoTextField, especialidadTextField,
            cargoTextField;
    
    private final JButton botonAnadir, botonCancelar;
    
    private int index;
    
    public NuevoEmpleado (Empleado empleado) {
        
        if(empleado instanceof Jugador j) {
            index = 0;
        }
        else if(empleado instanceof Tecnico t) {
            index = 1;
        }
        else if(empleado instanceof Directivo d){
            index = 2;
        }
        
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
        
        botonAnadir = new JButton("Anadir");
        botonCancelar = new JButton("Cancelar");
        
        initialize();
    }
    
    private void initialize() {
        String title = "Nuevo empleado";
        
        int altura = 600;
        
        if(index == 0) {
            title += ": Jugador";
            altura = 500;
        }
        else if(index == 1) {
            title += ": Tecnico";
            altura = 300;
        }
        else if(index == 2) {
            title += ": Directivo";
            altura = 200;
        }
        
        setTitle(title);
        setSize(500, altura);
        setContentPane(getMainPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setResizable(false);
        setVisible(true);
    }
    
    private JPanel getMainPanel() {
        
        int tamano = 10;
        
        if(index == 0) {
            tamano = 30;
        }
        else if(index == 1){
            tamano = 20;
        }
        else if(index == 2) {
            tamano = 10;
        }
        
        margenLabel3.setFont(new Font("Arial", Font.PLAIN, 10));
                
        mainPanel.add(margenLabel1, BorderLayout.LINE_START);
        mainPanel.add(margenLabel2, BorderLayout.LINE_END);
        mainPanel.add(margenLabel3, BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
    private JPanel getFormularioPanel() {
        formularioPanel = new JPanel();
        
        if(index == 0) {
            formularioPanel = new JPanel(new GridLayout(0, 2, 15, 40));
        }
        else if(index == 1) {
            formularioPanel.setLayout(new GridLayout(0, 2, 15, 30));
        }
        else if(index == 2) {
            formularioPanel = new JPanel(new GridLayout(0, 2, 1, 5));
        }
                
        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreTextField);
        formularioPanel.add(telfLabel);
        formularioPanel.add(telfTextField);

        if (index == 0) {
            formularioPanel.add(apellidoLabel);
            formularioPanel.add(apellidoTextField);
            formularioPanel.add(demarcacionLabel);
            formularioPanel.add(demarcacionTextField);
            formularioPanel.add(edadLabel);
            formularioPanel.add(edadTextField);
            formularioPanel.add(valorLabel);
            formularioPanel.add(valorTextField);
        } else if (index == 1) {
            formularioPanel.add(puestoLabel);
            formularioPanel.add(puestoTextField);
            formularioPanel.add(especialidadLabel);
            formularioPanel.add(especialidadTextField);
        } else if (index == 2) {
            formularioPanel.add(cargoLabel);
            formularioPanel.add(cargoTextField);
        }
        formularioPanel.setVisible(true);
        
        return formularioPanel;
    }
    
    private JPanel getButtonsPanel() {
        botonAnadir.addActionListener(this);
        botonCancelar.addActionListener(this);
        
        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonCancelar);
        
        return buttonsPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonAnadir) {
            
        }
        else if(ae.getSource() == botonCancelar) {
            dispose();
        }
    }    
}
