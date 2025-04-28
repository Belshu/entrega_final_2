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

public class primerMenu extends JFrame implements ActionListener {
    private final JPanel mainPanel, buttonsPanel, marginPanel1, marginPanel2;
    private final JLabel title, marginLabel1, marginLabel1o2, marginLabel1o3, marginLabel1o4, marginLabel1o5, marginLabel2, marginLabel3;
    private final JButton botonEmpleados, botonPartidos, botonFacturas, botonImprimir, botonSalir;
    
    public primerMenu() {
        super("Menu inicial");
        
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new GridLayout(0, 1, 20, 15));
        marginPanel1 = new JPanel(new GridLayout(0, 1, 1, 1));
        marginPanel2 = new JPanel(new BorderLayout());
        
        title = new JLabel("Gestión del club", JLabel.CENTER);
        marginLabel1 = new JLabel("                                   ");
        marginLabel1o2 = new JLabel(" ");
        marginLabel1o3 = new JLabel(" ");
        marginLabel1o4 = new JLabel(" ");
        marginLabel1o5 = new JLabel(" ");
        marginLabel2 = new JLabel("                                   ");
        marginLabel3 = new JLabel(" ", JLabel.CENTER);
        
        
        botonEmpleados = new JButton("Gestionar empleados");
        botonPartidos = new JButton("Gestionar partidos");
        botonFacturas = new JButton("Gestionar facturas");
        botonImprimir = new JButton("Imprimir datos");
        botonSalir = new JButton("Cerrar programa");
        
        initialize();
    }
    
    private void initialize(){        
        setContentPane(getMainPanel());
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
    private JPanel getMainPanel() {
        mainPanel.add(getTitles(), BorderLayout.PAGE_START);
        mainPanel.add(getButtonsPanel(), BorderLayout.CENTER);
        mainPanel.add(getMarginPanel1(), BorderLayout.WEST);
        mainPanel.add(getMarginPanel2(), BorderLayout.EAST);
        mainPanel.add(getMarginLabel3(), BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
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
    
    private JPanel getMarginPanel1(){
        marginPanel1.add(getMarginLabel1());
        marginPanel1.add(getMarginLabel1o2());
        marginPanel1.add(getMarginLabel1o3());
        marginPanel1.add(getMarginLabel1o4());
        marginPanel1.add(getMarginLabel1o5());
        
        return marginPanel1;
    }
    
    private JPanel getMarginPanel2(){
        marginPanel2.add(getMarginLabel2(), BorderLayout.CENTER);
        
        return marginPanel2;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonEmpleados){
            new menuEmpleados();
        }
        else if(ae.getSource() == botonPartidos) {
            new menuPartidos();
        }
        else if(ae.getSource() == botonFacturas){
            new menuFacturas();
        }
        else if(ae.getSource() == botonImprimir){
            new menuImprimir();
        }
    }
    
    public static void main(String[] args) {
        new primerMenu();
    }
    
    private JLabel getTitles(){
        title.setFont(new Font("Arial", Font.PLAIN, 50));
        
        return title;
    }
    
    private JLabel getMarginLabel1() {
        return marginLabel1;
    }
    
    private JLabel getMarginLabel1o2(){
        return marginLabel1o2;
    }
    
    private JLabel getMarginLabel1o3(){
        return marginLabel1o3;
    }
    
    private JLabel getMarginLabel1o4(){
        return marginLabel1o4;
    }
    
    private JLabel getMarginLabel1o5(){
        return marginLabel1o5;
    }
    
    private JLabel getMarginLabel2(){
        return marginLabel2;
    }
    
    private JLabel getMarginLabel3(){
        return marginLabel3;
    }
}
