package menus;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Menu inicial del programa
 * @author Isabel Shuang Piñana Alonso
 **/

public class primerMenu extends JFrame implements ActionListener {
    public static int index;
    
    private final JPanel mainPanel, buttonsPanel, marginPanel1, marginPanel2;
    private final JLabel title, marginLabel1, marginLabel2, marginLabel3;
    private final JButton botonEmpleados, botonPartidos, botonFacturas, botonImprimir, botonSalir;
    
    /**
     * CONSTRUCTOR: Inicialización de los atributos finales y puesta del título de la pestaña
     **/
    public primerMenu() {
        
        // Título en el encabezado de la pestaña
        super("Menu inicial");
        
        // Paneles
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new GridLayout(0, 1, 20, 15));
        marginPanel1 = new JPanel(new BorderLayout());
        marginPanel2 = new JPanel(new BorderLayout());
        
        // Título de la interfaz
        title = new JLabel("Gestión del club", JLabel.CENTER);
        
        // Márgenes
        marginLabel1 = new JLabel("                                   ");
        marginLabel2 = new JLabel("                                   ");
        marginLabel3 = new JLabel(" ", JLabel.CENTER);
        
        // Botones
        botonEmpleados = new JButton("Gestionar empleados");
        botonPartidos = new JButton("Gestionar partidos");
        botonFacturas = new JButton("Gestionar facturas");
        botonImprimir = new JButton("Imprimir datos");
        botonSalir = new JButton("Cerrar programa");
        
        initialize();
    }
    
    /**
     * Método que inicia el tamaño de la interfaz, poner el panel contenido y 
     * diversas propiedades
     **/
    private void initialize(){        
        setContentPane(getMainPanel());
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
    /**
     * Método que devuelve el panel principal donde se colocarán el título
     * de JLabel, el panel de botones. los paneles de márgenes u el JLabel de
     * margen 3.
     * Se modifica la fuente del título para que se vea más grande
     * @return JPanel
     **/
    private JPanel getMainPanel() {
        title
        .setFont(new Font("Arial", Font.PLAIN, 50));
        mainPanel.add(title, BorderLayout.PAGE_START);
        
        mainPanel.add(getButtonsPanel(), BorderLayout.CENTER);
        mainPanel.add(getMarginPanel1(), BorderLayout.WEST);
        mainPanel.add(getMarginPanel2(), BorderLayout.EAST);
        mainPanel.add(marginLabel3, BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
    /**
     * Método que devuelve el panel de botones donde se colocarán los botones de
     * selección y poner la fuente de letra de estos
     * @return JPanel
     **/
    private JPanel getButtonsPanel(){
        botonEmpleados.setFont(new Font("Arial", Font.PLAIN, 23));
        botonPartidos.setFont(new Font("Arial", Font.PLAIN, 23));
        botonFacturas.setFont(new Font("Arial", Font.PLAIN, 23));
        botonImprimir.setFont(new Font("Arial", Font.PLAIN, 23));
        botonSalir.setFont(new Font("Arial", Font.PLAIN, 23));
        
        botonEmpleados.addActionListener(this);
        botonPartidos.addActionListener(this);
        botonFacturas.addActionListener(this);
        botonImprimir.addActionListener(this);
        botonSalir.addActionListener(this);
        
        buttonsPanel.add(botonEmpleados);
        buttonsPanel.add(botonPartidos);
        buttonsPanel.add(botonFacturas);
        buttonsPanel.add(botonImprimir);
        buttonsPanel.add(botonSalir);
        
        return buttonsPanel;
    }
    
    /**
     * Método que devuelve el panel de primer margen que contiene el JLabel de
     * margen en el centro
     * @return marginPanel1
     **/
    private JPanel getMarginPanel1(){
        marginPanel1.add(marginLabel1, BorderLayout.CENTER);
        return marginPanel1;
    }
    
    /**
     * Método que devuelve el panel de segundo margen que contiene el JLabel de
     * margen en el centro
     * @return marginPanel2
     **/
    private JPanel getMarginPanel2(){
        marginPanel2.add(marginLabel2, BorderLayout.CENTER);
        return marginPanel2;
    }
    
    /**
     * Metodo sobrecargado que recoge las acciones dentro de la interfaz y se le 
     * asignara una utilidad a los botones correspondientes
     * @param ae ActionEvent
     **/
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonEmpleados){
            index = 0;
            
        }
        else if(ae.getSource() == botonSalir){
            System.exit(0);
        }
        
        setVisible(false);
        dispose();
    }
    
    /**
     * Método principal "main", inicializa las listas de cada menu y el 
    * y el constructor de este primer menu
     **/
    public static void main(String[] args) {
        new primerMenu();
    }
}
