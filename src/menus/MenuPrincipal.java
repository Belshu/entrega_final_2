package menus;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Isabel Shuang Piñana Alonso
 */

public class MenuPrincipal extends JFrame implements ChangeListener {
    private final JPanel mainPanel, contentPanel;
    private final JTabbedPane ventanas;
    
    public MenuPrincipal(){
        
        mainPanel = new JPanel(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());
        ventanas = new JTabbedPane(JTabbedPane.LEFT);
        
        initialize();
    }
    
    private void initialize(){
        setContentPane(getMainPanel());
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private JPanel getMainPanel(){        
        mainPanel.add(getVentanas());
        
        return mainPanel;
    }
    
    private JTabbedPane getVentanas(){
        MenuEmpleados menuEmpleados = new MenuEmpleados();
        MenuPartidos menuPartidos = new MenuPartidos();
        
        ventanas.add("Empleados", menuEmpleados.getMainPanel());
        ventanas.add("Partidos", menuPartidos.getMainPanel());
        
        ventanas.addChangeListener(this);
        
        return ventanas;
    }
    
    @Override
    public void stateChanged(ChangeEvent ce) {
        int index = ventanas.getSelectedIndex();
        contentPanel.removeAll();
        switch(index){
            case 0:
                MenuEmpleados menuEmpleados = new MenuEmpleados();
                contentPanel.add(menuEmpleados.getMainPanel(), BorderLayout.CENTER);
            break;
            
            case 1:
                MenuPartidos menuPartidos = new MenuPartidos();
                contentPanel.add(menuPartidos.getMainPanel(), BorderLayout.CENTER);
            break;
        }
        contentPanel.revalidate();
        contentPanel.repaint();
        
    }
    
    public void setSelectedIndex(int index){
        try {
            ventanas.setSelectedIndex(index);
            // System.out.println(ventanas.getSelectedIndex());
        } catch(IndexOutOfBoundsException e) {
            System.out.println("ERROR -> index fuera de alcance");
        }
    }
}
