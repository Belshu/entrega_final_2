package menus;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Isabel Shuang Piñana Alonso
 */

public class menuPrincipal extends JFrame implements ChangeListener {
    private final JPanel mainPanel;
    
    public menuPrincipal(){
        mainPanel = new JPanel(new BorderLayout());
    }
    
    @Override
    public void stateChanged(ChangeEvent ce) {
        
    }
    
}
