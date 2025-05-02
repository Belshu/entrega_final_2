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
    private static ArrayList <Jugador> listaJugadores;
    private static ArrayList <Tecnico> listaTecnicos;
    private static ArrayList <Directivo> listaDirectivos;
    
    private final JPanel mainPanel, buttonsPanel;
    private final JButton botonAnadir, botonModificar, botonEliminar;
    private JList <Empleado> listaEmpleados;
    
    private DefaultListModel <Empleado> empleadoListModel;
    
    
   public static void inicializarListas(){
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
   }
   
    public MenuEmpleados(){
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        botonAnadir = new JButton("Anadir");
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
    }
    
    public JPanel getMainPanel(){
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(new JScrollPane(getListaEmpleados()), BorderLayout.CENTER);
        mainPanel.setVisible(true);
        
        return mainPanel;
    }
    
    private JPanel getButtonsPanel(){
        botonAnadir.addActionListener(this);
        botonModificar.addActionListener(this);
        botonEliminar.addActionListener(this);
        
        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonModificar);
        buttonsPanel.add(botonEliminar);
        
        return buttonsPanel;
    }
    
    private JList getListaEmpleados() {
        listaEmpleados = new JList<>(getEmpleadoListModel());
        
        return listaEmpleados;
    }
    
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
