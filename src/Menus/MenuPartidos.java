package Menus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Partido.Partido;

/**
 * Menu inicial del programa que hereda de JFrame e implementa la interfaz 
 * ActionListener
 * @author Isabel Shuang Piñana Alonso
 */
public class MenuPartidos extends JFrame implements ActionListener {
    private static ArrayList <Partido> listaPartidos;
    
    private final JPanel mainPanel, buttonsPanel;
    private final JButton botonAnadir, botonModificar, botonImprimir;
    private JList <Partido> listaPartidos2;
    
    private DefaultListModel <Partido> partidosListModel;
    
    /**
     * Método que nicializa las listas de prueba
     **/
    public static void inicializarLista() {
        listaPartidos = new ArrayList<>();
        
        listaPartidos.add(new Partido("Equipo rival 1",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
        listaPartidos.add(new Partido("Equipo rival 2",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
        listaPartidos.add(new Partido("Equipo rival 3",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
        listaPartidos.add(new Partido("Equipo rival 4",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
        listaPartidos.add(new Partido("Equipo rival 5",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
    }
    
    /**
    * CONSTRUCTOR: inicialización de los atributos finales 
    **/
    public MenuPartidos(){
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        botonAnadir = new JButton("Anadir");
        botonModificar = new JButton("Modificar");
        botonImprimir = new JButton("Imprimir");
    }
    
    /**
     * Método que devuelve el panel principal
     * Será público para pasarlo a las ventanas del menú principal
     * @return JPanel
     **/
    public JPanel getMainPanel() {
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(new JScrollPane(getListaPartidos2()), BorderLayout.CENTER);
        mainPanel.setVisible(true);

        return mainPanel;
    }
    
    /**
     * Método que devuelve el panel de botones, donde se añaden en él y asignan
     * un ActionListener
     * @return buttonsPanel
     **/
    private JPanel getButtonsPanel() {
        botonAnadir.addActionListener(this);
        botonModificar.addActionListener(this);
        botonImprimir.addActionListener(this);

        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonModificar);
        buttonsPanel.add(botonImprimir);

        return buttonsPanel;
    }

    /**
     * Método que devuelve el JList inicializado contenido en él el ListModel
     * @return JList
     **/    
    private JList getListaPartidos2() {
        listaPartidos2 = new JList<>(getPartidosListModel());
        
        return listaPartidos2;
    }
    
    /**
     * Método que devuelve el ListModel inicializado y añadido todos los elementos de
     * las listas
     * @return DefaultListModel
     **/    
    private DefaultListModel getPartidosListModel() {
        partidosListModel = new DefaultListModel<>();
        
        for(Partido p : listaPartidos) partidosListModel.addElement(p);
        
        return partidosListModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonAnadir) {
            
        }
        else if(ae.getSource() == botonModificar) {
            
        }
        else if(ae.getSource() == botonImprimir) {
            
        }
    }
}
