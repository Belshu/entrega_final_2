package Menus;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Menu inicial del programa: se inicailizan las listas de ejemplo y muestra una
 * interfaz con cuatro botones (uno para ir directamente a la ventana de gestión
 * de empleados, otro para los partidos, otro para las facturas y el último para 
 * salir del programa).
 * Dependiendo del botón que haya pulsado el usuario, un int index tomará un valor diferente 
 * y al crear el objeto MenuPrincipal, le pasaremos con su método "setSelectedIndex" para que 
 * atuomáticamente aparezca la ventana de gestión correspondiente
 * 
 * @author Isabel Shuang Piñana Alonso
 **/

public class PrimerMenu extends JFrame implements ActionListener {
    
    // ATRIBUTOS
    private final JPanel mainPanel, buttonsPanel, marginPanel1, marginPanel2;
    private final JLabel title, marginLabel1, marginLabel2, marginLabel3;
    private final JButton botonEmpleados, botonPartidos, botonFacturas, botonSalir;
    
    /**
     * CONSTRUCTOR: Inicialización de los atributos finales
     **/
    public PrimerMenu() {        
        
        // PANELES
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new GridLayout(0, 1, 300, 15));
        marginPanel1 = new JPanel(new BorderLayout());
        marginPanel2 = new JPanel(new BorderLayout());
        
        // LABELES
        title = new JLabel("Gestión del club", JLabel.CENTER);
        marginLabel1 = new JLabel("                                   ");
        marginLabel2 = new JLabel("                                   ");
        marginLabel3 = new JLabel(" ", JLabel.CENTER);
        
        // BOTONES
        botonEmpleados = new JButton("Gestionar empleados");
        botonPartidos = new JButton("Gestionar partidos");
        botonFacturas = new JButton("Gestionar facturas");
        botonSalir = new JButton("Cerrar programa");
        
        initialize();
    }
    
    /**
     * Método que inicia el tamaño de la interfaz, poner el panel contenido y 
     * diversas propiedades
     **/
    private void initialize(){ 
        setTitle("Menu inicial");
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
        title.setFont(new Font("Arial", Font.PLAIN, 50));
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
        botonSalir.setFont(new Font("Arial", Font.PLAIN, 23));
        
        botonEmpleados.addActionListener(this);
        botonPartidos.addActionListener(this);
        botonFacturas.addActionListener(this);
        botonSalir.addActionListener(this);
        
        buttonsPanel.add(botonEmpleados);
        buttonsPanel.add(botonPartidos);
        buttonsPanel.add(botonFacturas);
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
        int index = 0;
        
        if(ae.getSource() == botonEmpleados){
            index = 0;
        }
        else if(ae.getSource() == botonPartidos){
            index = 1;
        }
        else if(ae.getSource() == botonFacturas) {
            index = 2;
        }
        else if(ae.getSource() == botonSalir){
            System.exit(0);
        }
        
        MenuPrincipal menuPrincipal = new MenuPrincipal();

        menuPrincipal.setSelectedIndex(index);

        setVisible(false);
        dispose();
    }
    
    /**
     * Método principal "main", inicializa las listas de cada menu y el 
    * y el constructor de este primer menu. Antes de declarar el constructor
    * se pregunta por terminal si quiere empezar con las listas de ejemplo ya
    * con cierta información en su interior
     * @param args
     **/
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        
        try {
            System.out.println("Activar listas de prueba[ 1->SI / 2-> NO ]: ");
            int eleccion = S.nextInt();
            
            if(eleccion == 1) {
                MenuEmpleados.inicializarListas();
                MenuPartidos.inicializarLista();
                MenuFacturas.inicializarListas();
            }
        } catch (Exception ex) {
            System.out.println("comando no valido");
        }
        
        new PrimerMenu();
    }
}
