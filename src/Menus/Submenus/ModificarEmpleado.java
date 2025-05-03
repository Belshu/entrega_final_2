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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Isabel Shuang Piñana Alonso
 */

public class ModificarEmpleado extends JFrame implements ActionListener {
    private Empleado empleado;
    private Jugador jugador;
    private Tecnico tecnico;
    private Directivo directivo;
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
    
    private final JButton botonAplicar, botonCancelar;
    
    private int index;
    
    private String nombre, apellido, puesto, especialidad, cargo;
    private int telf, demarcacion, edad;
    private double valor;
    private boolean estado;
    
    public ModificarEmpleado (Empleado empleado) {
        this.empleado = empleado;
        
        nombre = empleado.getNombre();
        telf = empleado.getTelf();
        
        if(empleado instanceof Jugador j) {
            index = 0;
            
            apellido = j.getApellidos();
            demarcacion = j.getDemarcacion();
            edad = j.getEdad();
            valor = j.getValor();
            estado = j.isEstado();
        }
        else if(empleado instanceof Tecnico t) {
            index = 1;
            
            puesto = t.getPuesto();
            especialidad = t.getEspecialidad();
        }
        else if(empleado instanceof Directivo d){
            index = 2;
            
            cargo = d.getCargo();
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
        
        botonAplicar = new JButton("Aplicar cambios");
        botonCancelar = new JButton("Cancelar");
        
        initialize();
    }
    
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
    
    private JPanel getMainPanel() {
                
        int tamano = switch (index) {
            case 0 -> 30;
            case 1 -> 20;
            case 2 -> 10;
            default -> 10;
        };
        
        margenLabel3.setFont(new Font("Arial", Font.PLAIN, 10));
                
        mainPanel.add(margenLabel1, BorderLayout.LINE_START);
        mainPanel.add(margenLabel2, BorderLayout.LINE_END);
        mainPanel.add(margenLabel3, BorderLayout.PAGE_START);
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
    private JPanel getFormularioPanel() {
        
        formularioPanel = switch (index) {
            case 0 -> new JPanel(new GridLayout(0, 2, 15, 35));
            case 1 -> new JPanel(new GridLayout(0, 2, 15, 20));
            case 2 -> new JPanel(new GridLayout(0, 2, 1, 5));
            default -> new JPanel();
        };
        
        formularioPanel.add(nombreLabel);
        nombreTextField.setText(nombre);
        formularioPanel.add(nombreTextField);
        
        formularioPanel.add(telfLabel);
        telfTextField.setText(String.valueOf(telf));
        formularioPanel.add(telfTextField);
        
        switch(index){
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
    
    private JPanel getButtonsPanel() {
        botonAplicar.addActionListener(this);
        botonCancelar.addActionListener(this);
        
        buttonsPanel.add(botonAplicar);
        buttonsPanel.add(botonCancelar);
        
        return buttonsPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonAplicar) {
            
            try {
                nombre = nombreTextField.getText();
                telf = Integer.parseInt(telfTextField.getText());
                
                switch(index) {
                    case 0 -> {
                        apellido = apellidoTextField.getText();
                        demarcacion = Integer.parseInt(demarcacionTextField.getText());
                        edad = Integer.parseInt(edadTextField.getText());
                        valor = Double.parseDouble(valorTextField.getText());
                    }
                    
                    case 1 -> {
                        puesto = puestoTextField.getText();
                        especialidad = especialidadTextField.getText();
                    }
                    
                    case 2 -> {
                        cargo = cargoTextField.getText();
                    }
                }
                
                dispose();
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.", "Error", JOptionPane.ERROR_MESSAGE);                
            }
        }
        else if(ae.getSource() == botonCancelar) {
            dispose();
        }
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
}
