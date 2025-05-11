package Menus.Submenus;

import Contables.Cliente;
import Contables.Factura;
import Menus.MenuFacturas;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Isabel Shuang Piñana Alonso
 */
public class NuevaFactura extends JFrame implements ActionListener {
    private Factura factura;
    
    private JPanel mainPanel, formularioPanel, cantidadPanel, fechaPanel, clientePanel, buttonsPanel;
    private JLabel cantidadLabel, cantidadLabel2, fechaLabel1, fechaLabel2, fechaLabel3, clienteLabel1, clienteLabel2;
    private JTextField cantidadTextField, diaTextField, mesTextField, anioTextField, cifTextField, nombreTextField;
    private JButton botonAceptar, botonCancelar;
    
    public NuevaFactura() {
        
        // PANELES
        mainPanel = new JPanel(new BorderLayout());
        formularioPanel = new JPanel();
        cantidadPanel = new JPanel(new FlowLayout());
        fechaPanel = new JPanel(new FlowLayout());
        clientePanel = new JPanel(new GridLayout(0, 1, 2, 1));
        buttonsPanel = new JPanel(new FlowLayout());
        
        // LABELES
        cantidadLabel = new JLabel("Cantidad: ");
        cantidadLabel2 = new JLabel("€");
        fechaLabel1 = new JLabel("(dia/mes/año): ");
        fechaLabel2 = new JLabel("/");
        fechaLabel3 = new JLabel("/");
        clienteLabel1 = new JLabel("CIF: ");
        clienteLabel2 = new JLabel("Nombre: ");
        
        // TEXTFIELDS
        cantidadTextField = new JTextField(4);
        diaTextField = new JTextField(2);
        mesTextField = new JTextField(2);
        anioTextField = new JTextField(4);
        cifTextField = new JTextField();
        nombreTextField = new JTextField();
        
        // BOTONES
        botonAceptar = new JButton("Aceptar");
        botonCancelar = new JButton("Cancelar");
        
        initialize();
    }
    
    private void initialize() {
        setTitle("Nueva factura");
        setContentPane(getMainPanel());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    private JPanel getMainPanel() {
        mainPanel.add(getFormularioPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
    private JPanel getFormularioPanel() {
        formularioPanel.setLayout(new BoxLayout(formularioPanel, BoxLayout.Y_AXIS));
        formularioPanel.add(getCantidadPanel());
        formularioPanel.add(Box.createVerticalStrut(0));
        formularioPanel.add(getFechaPanel());
        formularioPanel.add(Box.createVerticalStrut(0));
        formularioPanel.add(getClientePanel());
        
        return formularioPanel;
    }
    
    private JPanel getCantidadPanel() {
        cantidadPanel.add(cantidadLabel);        
        cantidadPanel.add(cantidadTextField);
        cantidadPanel.add(cantidadLabel2);
        
        return cantidadPanel;
    }
    
    private JPanel getFechaPanel() {
        fechaPanel.add(fechaLabel1);
        fechaPanel.add(diaTextField);
        fechaPanel.add(fechaLabel2);
        fechaPanel.add(mesTextField);
        fechaPanel.add(fechaLabel3);
        fechaPanel.add(anioTextField);
        
        return fechaPanel;
    }
    
    private JPanel getClientePanel() {
        clientePanel.setBorder(new TitledBorder("Datos del cliente"));
        
        clientePanel.add(clienteLabel1);
        clientePanel.add(cifTextField);
        clientePanel.add(clienteLabel2);
        clientePanel.add(nombreTextField);
        
        return clientePanel;
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
        if(ae.getSource() == botonAceptar) {
            
            if(cantidadTextField.getText().isEmpty() || diaTextField.getText().isEmpty()
                    || mesTextField.getText().isEmpty() || anioTextField.getText().isEmpty()
                    || cifTextField.getText().isEmpty() || nombreTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Complete todos los campos, por favor.",
                    "Faltan campos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int dia = Integer.parseInt(diaTextField.getText());
                int mes = Integer.parseInt(mesTextField.getText());
                int anio = Integer.parseInt(anioTextField.getText());

                if (mes < 1 || mes > 12 || dia < 1 || dia > 31) {
                    JOptionPane.showMessageDialog(this, "Error en la fecha de creacion de la factura. Por favor, revisa ese campo.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    LocalDate fecha = LocalDate.of(anio, mes, dia);
                    double cantidad = Double.parseDouble(cantidadTextField.getText());

                    Random rand = new Random();
                    int cod = rand.nextInt(1000000);

                    if (!MenuFacturas.getListaFacturas().isEmpty()) {
                        int i = 0;
                        while (MenuFacturas.getListaFacturas().get(i).getCodigo().equals(cod)) {
                            cod = rand.nextInt(1000000);
                        }
                    }
                    String codigo = String.valueOf(cod);

                    factura = new Factura(codigo, cantidad, fecha, new Cliente(cifTextField.getText(), nombreTextField.getText()));
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Por favor, revise los campos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(ae.getSource() == botonCancelar) {
            dispose();
        }
    }
    
    public Factura getFactura() {
        return factura;
    }
}
