package menus;

import empleados.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Menu inicial del programa que hereda de JFrame e implementa la interfaz 
 * ActionListener
 * @author Isabel Shuang Piñana Alonso
 */
public class menuEmpleados extends JFrame implements ActionListener {
    private static ArrayList <Jugador> listaJugadores;
    private static ArrayList <Tecnico> listaTecnicos;
    private static ArrayList <Directivo> listaDirectivos;
    
    private final JPanel mainPanel, buttonsPanel;
    private final JButton anadir, modificar, eliminar;
    
   public static void inicializarListas(){
       listaJugadores = new ArrayList <>();
       listaTecnicos = new ArrayList <>();
       listaDirectivos = new ArrayList <>();
       
       listaJugadores.add(new Jugador("111111111A", "Jugador", 123456, "1", 0, 21, 111, true));
       listaJugadores.add(new Jugador("111111111B", "Jugador", 123456, "2", 1, 22, 112, true));
       listaJugadores.add(new Jugador("111111111C", "Jugador", 123456, "3", 2, 23, 113, true));
       
       listaTecnicos.add(new Tecnico("Informatico", "Redes de sistemas", "222222222A", "Tecnico 1", 654321));
       listaTecnicos.add(new Tecnico("Informatico", "ciberseguridad", "222222222B", "Tecnico 2", 654321));
       listaTecnicos.add(new Tecnico("Informatico", "Programador", "222222222C", "Tecnico 3", 654321));
       
       listaDirectivos.add(new Directivo("Subdirector", "333333333A", "Directivo 1", 135246));
       listaDirectivos.add(new Directivo("Director", "333333333B", "Directivo 2", 135246));
       listaDirectivos.add(new Directivo("CEO", "333333333C", "Directivo 3", 135246));
   }
   
    public menuEmpleados(){
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        anadir = new JButton("Anadir");
        modificar = new JButton("Modificar");
        eliminar = new JButton("Eliminar");
    }
    
    public JPanel getMainPanel(){
        mainPanel.add(getButtonsPanel(), BorderLayout.LINE_START);
        mainPanel.setVisible(true);
        
        return mainPanel;
    }
    
    private JPanel getButtonsPanel(){
        anadir.addActionListener(this);
        modificar.addActionListener(this);
        eliminar.addActionListener(this);
        
        buttonsPanel.add(anadir);
        buttonsPanel.add(modificar);
        buttonsPanel.add(eliminar);
        
        return buttonsPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
