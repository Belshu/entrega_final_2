/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mint
 */

import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Pestañas Verticales");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        // Crear un panel principal con un BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Crear un JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT); // Pestañas a la izquierda
        
        // Crear paneles para cada pestaña
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Contenido de la Pestaña 1"));
        
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Contenido de la Pestaña 2"));
        
        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Contenido de la Pestaña 3"));
        
        // Agregar los paneles al JTabbedPane
        tabbedPane.addTab("Pestaña 1", panel1);
        tabbedPane.addTab("Pestaña 2", panel2);
        tabbedPane.addTab("Pestaña 3", panel3);
        
        // Agregar el JTabbedPane al panel principal
        mainPanel.add(tabbedPane, BorderLayout.WEST);
        
        // Agregar un panel para mostrar el contenido de la pestaña seleccionada
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(new JLabel("Selecciona una pestaña"), BorderLayout.CENTER);
        
        // Escuchar cambios en la selección de pestañas
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            contentPanel.removeAll();
            switch (selectedIndex) {
                case 0:
                    contentPanel.add(new JLabel("Contenido de la Pestaña 1"), BorderLayout.CENTER);
                    break;
                case 1:
                    contentPanel.add(new JLabel("Contenido de la Pestaña 2"), BorderLayout.CENTER);
                    break;
                case 2:
                    contentPanel.add(new JLabel("Contenido de la Pestaña 3"), BorderLayout.CENTER);
                    break;
            }
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        
        // Agregar el panel de contenido al panel principal
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Agregar el panel principal al marco
        frame.add(mainPanel);
        
        // Hacer visible el marco
        frame.setVisible(true);
    }
}

