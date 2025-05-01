package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import partido.Partido;

/**
 *
 * @author mint
 */
public class MenuPartidos extends JFrame implements ActionListener{
    private static ArrayList <Partido> listaPartidos;
    
    public static void inicializarLista() {
        listaPartidos = new ArrayList<>();
    
        
    }
    
    public MenuPartidos(){
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
