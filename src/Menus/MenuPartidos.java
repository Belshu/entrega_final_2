package Menus;

import Empleados.Empleado;
import Empleados.Jugador;
import Menus.Submenus.Imprimir;
import Menus.Submenus.NuevoPartido;
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
import Partidos.Partido;
import javax.swing.ListSelectionModel;

/**
 * Menu de gestión de partidos: se crea una lista estática que almacena los partidos
 * y para su gestión a base de botones
 * 
 * @author Isabel Shuang Piñana Alonso
 */
public class MenuPartidos extends JFrame implements ActionListener {
    
    // LISTA
    private static ArrayList <Partido> listaPartidos;
    private final ArrayList <Jugador> listaJugadores;
    
    // PANELES
    private final JPanel mainPanel, buttonsPanel;
    
    // ELEMENTOS
    private final JButton botonAnadir, botonImprimir;
    private JList <Partido> listaPartidos2;
    
    // MODELO DE LISTA
    private DefaultListModel <Partido> partidosListModel;
    
    /**
     * Método que nicializa las listas de prueba
     **/
    public static void inicializarLista() {
        listaPartidos = new ArrayList<>();
        
        ArrayList <Jugador> listaJugadoresAuxialiar = new ArrayList<>();
        
        for(Empleado e : MenuEmpleados.getListaEmpleados()) {
            if(e instanceof Jugador j && !e.isEliminado()) {
                listaJugadoresAuxialiar.add(j);
            }
        }
        
        listaPartidos.add(new Partido("Equipo rival 1",new Date(), 1, 0, true, listaJugadoresAuxialiar));
        listaPartidos.add(new Partido("Equipo rival 2",new Date(), 1, 0, true, listaJugadoresAuxialiar));
        listaPartidos.add(new Partido("Equipo rival 3",new Date(), 1, 0, true, listaJugadoresAuxialiar));
    }
    
    /**
    * CONSTRUCTOR: inicialización de los atributos finales 
    **/
    public MenuPartidos(){
        if(listaPartidos == null) {
            listaPartidos = new ArrayList<>();
        }
        
        listaJugadores = new ArrayList<>();
        
        if(MenuEmpleados.getListaEmpleados() != null) {
            for(Empleado e : MenuEmpleados.getListaEmpleados()) {
                if(e instanceof Jugador j && !e.isEliminado()) {
                    listaJugadores.add(j);
                }
            }
        }
        
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        botonAnadir = new JButton("Anadir");
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
        botonImprimir.addActionListener(this);

        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonImprimir);

        return buttonsPanel;
    }

    /**
     * Método que devuelve el JList inicializado contenido en él el ListModel
     * @return JList
     **/    
    private JList getListaPartidos2() {
        listaPartidos2 = new JList<>(getPartidosListModel());
        listaPartidos2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);                
        return listaPartidos2;
    }
    
    /**
     * Método que devuelve el ListModel inicializado y añadido todos los elementos de
     * las listas
     * @return DefaultListModel
     **/    
    private DefaultListModel getPartidosListModel() {
        partidosListModel = new DefaultListModel<>();
        
        if(!listaPartidos.isEmpty()) {
            for(Partido p : listaPartidos) partidosListModel.addElement(p);
        }
        
        return partidosListModel;
    }
    
    /**
     * Metodo sobrecargado que recoge las acciones dentro de la interfaz y se le asignara una utilidad a los
     * botones correspondientes
     * @param ae ActionEvent 
     **/
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonAnadir) {
            anadirPartido();
        }
        else if(ae.getSource() == botonImprimir) {
            new Imprimir(0);
        }
    }
    
    /**
     * Método que añade el partido nuevo al cerrar la ventana creada en la interfaz NuevoPartido
     **/
    private void anadirPartido() {
        NuevoPartido nuevoPartido = new NuevoPartido(listaJugadores);
        
        nuevoPartido.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                partidosListModel.addElement(nuevoPartido.getPartidoNuevo());
                listaPartidos.add(nuevoPartido.getPartidoNuevo());
            }
        });
    }
    
    public void updateListaJugadores(Jugador jugador, int who) {
        switch(who) {
            case 0 -> listaJugadores.add(jugador);
            case 1 -> {
                for(Jugador j : listaJugadores) {
                    if(j.getDni().equals(jugador.getDni())) {
                        listaJugadores.set(listaJugadores.indexOf(j), jugador);
                    }
                }
            }
            case 2 -> {
                for(Jugador j :listaJugadores) {
                    if(j.getDni().equals(jugador.getDni())) {
                        j.setEliminado(true);
                        j.setFechaEliminacion(new Date());
                    }
                }
            }
        }
    }

    /**
     * Método que devuelve la ArrayList estática de partidos
     * @return ArrayList Partido
     **/
    public static ArrayList<Partido> getListaPartidos() {
        return listaPartidos;
    }
}
