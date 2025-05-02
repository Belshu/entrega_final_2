package menus;

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
import partido.Partido;

/**
 *
 * @author mint
 */
public class MenuPartidos extends JFrame implements ActionListener{
    private static ArrayList <Partido> listaPartidos;
    
    private final JPanel mainPanel, buttonsPanel;
    private final JButton botonAnadir, botonModificar, botonEliminar;
    private JList <Partido> listaPartidos2;
    
    private DefaultListModel <Partido> partidosListModel;
    
    public static void inicializarLista() {
        listaPartidos = new ArrayList<>();
        
        listaPartidos.add(new Partido("Equipo rival 1",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
        listaPartidos.add(new Partido("Equipo rival 2",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
        listaPartidos.add(new Partido("Equipo rival 3",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
        listaPartidos.add(new Partido("Equipo rival 4",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
        listaPartidos.add(new Partido("Equipo rival 5",new Date(), 1, 0, true, MenuEmpleados.getListaJugadores()));
    }
    
    public MenuPartidos(){
        mainPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        botonAnadir = new JButton("Anadir");
        botonModificar = new JButton("Modificar");
        botonEliminar = new JButton("Eliminar");
    }
    
    public JPanel getMainPanel() {
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(new JScrollPane(getListaPartidos2()), BorderLayout.CENTER);
        mainPanel.setVisible(true);

        return mainPanel;
    }

    private JPanel getButtonsPanel() {
        botonAnadir.addActionListener(this);
        botonModificar.addActionListener(this);
        botonEliminar.addActionListener(this);

        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonModificar);
        buttonsPanel.add(botonEliminar);

        return buttonsPanel;
    }
    
    private JList getListaPartidos2() {
        listaPartidos2 = new JList<>(getPartidosListModel());
        
        return listaPartidos2;
    }
    
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
        else if(ae.getSource() == botonEliminar) {
            
        }
    }
}
