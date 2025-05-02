package menus;

import empleados.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Menu inicial del programa que hereda de JFrame e implementa la interfaz 
 * ActionListener
 * @author Isabel Shuang Piñana Alonso
 */
public class MenuEmpleados extends JFrame implements ActionListener {
    private static ArrayList <Empleado> listaEmpleados;
    private static ArrayList <Jugador> listaJugadores;
    private static ArrayList <Tecnico> listaTecnicos;
    private static ArrayList <Directivo> listaDirectivos;
    
    private final JPanel mainPanel, buttonsPanel;
    private final JButton botonAnadir, botonAnadirNomina, botonModificar, botonEliminar;
    private JList <Empleado> listaEmpleados2;
    
    private DefaultListModel <Empleado> empleadoListModel;
    
    /**
     * Método que nicializa las listas de prueba
     **/
   public static void inicializarListas(){
       listaEmpleados = new ArrayList <>();
       listaJugadores = new ArrayList <>();
       listaTecnicos = new ArrayList <>();
       listaDirectivos = new ArrayList <>();
       
       listaJugadores.add(new Jugador("111111111A", "Jugador1", 123456, "1", 0, 21, 111, true));
       listaJugadores.add(new Jugador("111111111B", "Jugador2", 123456, "2", 1, 22, 112, true));
       listaJugadores.add(new Jugador("111111111C", "Jugador3", 123456, "3", 2, 23, 113, true));
              
       listaTecnicos.add(new Tecnico("Informatico", "Redes de sistemas", "222222222A", "Tecnico 1", 654321));
       listaTecnicos.add(new Tecnico("Informatico", "ciberseguridad", "222222222B", "Tecnico 2", 654321));
       listaTecnicos.add(new Tecnico("Informatico", "Programador", "222222222C", "Tecnico 3", 654321));
              
       listaDirectivos.add(new Directivo("Subdirector", "333333333A", "Directivo 1", 135246));
       listaDirectivos.add(new Directivo("Director", "333333333B", "Directivo 2", 135246));
       listaDirectivos.add(new Directivo("CEO", "333333333C", "Directivo 3", 135246));
       
       for(Jugador j : listaJugadores) listaEmpleados.add(j);
       for(Tecnico t : listaTecnicos) listaEmpleados.add(t);       
       for(Directivo d : listaDirectivos) listaEmpleados.add(d);
   }
   
   /**
    * CONSTRUCTOR: inicialización de los atributos finales 
    **/
    public MenuEmpleados(){
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        botonAnadir = new JButton("Anadir");
        botonAnadirNomina = new JButton("Anadir nomina");
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
    }
   
    /**
     * Método que devuelve el panel principal
     * Será público para pasarlo a las ventanas del menú principal
     * @return JPanel
     **/
    public JPanel getMainPanel(){
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(new JScrollPane(getListaEmpleados2()), BorderLayout.CENTER);
        mainPanel.setVisible(true);
        
        return mainPanel;
    }
    
    /**
     * Método que devuelve el panel de botones, donde se añaden en él y asignan
     * un ActionListener
     * @return buttonsPanel
     **/
    private JPanel getButtonsPanel(){
        botonAnadir.addActionListener(this);
        botonAnadirNomina.addActionListener(this);
        botonModificar.addActionListener(this);
        botonEliminar.addActionListener(this);
        
        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonAnadirNomina);
        buttonsPanel.add(botonModificar);
        buttonsPanel.add(botonEliminar);
        
        return buttonsPanel;
    }
    
    /**
     * Método que devuelve el JList inicializado contenido en él el ListModel
     * @return JList
     **/
    private JList getListaEmpleados2() {
        listaEmpleados2 = new JList<>(getEmpleadoListModel());
        
        return listaEmpleados2;
    }
    
    /**
     * Método que devuelve el ListModel inicializado y añadido todos los elementos de
     * las listas
     * @return DefaultListModel
     **/
    private DefaultListModel getEmpleadoListModel() {
        empleadoListModel = new DefaultListModel<>();
        
        for(Jugador e : listaJugadores) if(!e.isEliminado()) empleadoListModel.addElement(e);
        for(Tecnico e : listaTecnicos) if(!e.isEliminado()) empleadoListModel.addElement(e);
        for(Directivo e : listaDirectivos) if(!e.isEliminado()) empleadoListModel.addElement(e);
        
        return empleadoListModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonAnadir) {
            
        }
        else if(ae.getSource() == botonModificar) {
            
        }
        else if(ae.getSource() == botonEliminar) {
            
        }
    }
    
    private void anadirEmpleado(){
        
    }
    
    private void modificarEmpleado() {
        
    }
    
    private void eliminarEmpleado() {
        
    }
    
    public static ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public static ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public static ArrayList<Tecnico> getListaTecnicos() {
        return listaTecnicos;
    }

    public static ArrayList<Directivo> getListaDirectivos() {
        return listaDirectivos;
    }
}
