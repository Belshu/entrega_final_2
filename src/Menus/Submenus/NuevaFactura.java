package Menus.Submenus;

import Contables.Factura;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
public class NuevaFactura extends JFrame implements ActionListener {
    private Factura factura;
    
    private JPanel mainPanel, formularioPanel, cantidadPanel, fechaPanel, clientePanel, buttonsPanel;
    private JLabel cantidadLabel, fechaLabel1, fechaLabel2, fechaLabel3, clienteLabel1, clienteLabel2;
    private JTextField cantidadTextField, diaTextField, mesTextField, anioTextField, cifTextField, nombreTextField;
    private JButton botonAceptar, botonCancelar;
    
    public NuevaFactura() {
        mainPanel = new JPanel(new BorderLayout());
        formularioPanel = new JPanel();
        cantidadPanel = new JPanel(new FlowLayout());
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }    
}
