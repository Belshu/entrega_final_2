package Menus.Submenus;

import Empleados.Empleado;
import Menus.MenuEmpleados;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Isabel Shuang Piñana Alonso
 */
public class ImprimirEmpleados extends JFrame implements ActionListener{
    private final JPanel mainPanel, secondPanel, radioButtonsPanel, buttonsPanel;
    private final JLabel margenLabel1, margenLabel2, margenLabel3;
    private final JRadioButton txt, pdf;
    private final JButton botonImprimir, botonCancelar;
    
    private final String ruta = System.getProperty("user.dir") + File.separator + "Ficheros";
    private final ArrayList <Empleado> listaEmpleado;
    
    public ImprimirEmpleados() {
        listaEmpleado = MenuEmpleados.getListaEmpleados();
        
        if(listaEmpleado == null) {
            JOptionPane.showMessageDialog(this, "LISTAS VACIAS", "Advertencia", JOptionPane.WARNING_MESSAGE);
            this.dispose();
        }
        
        mainPanel = new JPanel(new BorderLayout());
        secondPanel = new JPanel(new GridLayout(0, 1, 10, 15));
        radioButtonsPanel = new JPanel(new FlowLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        
        margenLabel1 = new JLabel("        ");
        margenLabel2 = new JLabel("        ");
        margenLabel3 = new JLabel("        ");
                
        txt = new JRadioButton("txt");
        pdf = new JRadioButton("pdf");
        
        botonImprimir = new JButton("Imprimir");
        botonCancelar = new JButton("Cancelar");
        
        initialize();
    }
    
    private void initialize() {
        setTitle("Imprimir empleados");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(getMainPanel());
        setVisible(true);
    }
    
    private JPanel getMainPanel() {
        mainPanel.add(margenLabel3, BorderLayout.PAGE_START);
        mainPanel.add(margenLabel1, BorderLayout.LINE_START);
        mainPanel.add(margenLabel2, BorderLayout.LINE_END);
        
        mainPanel.add(getSecondPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
    private JPanel getSecondPanel() {
        secondPanel.add(getRadioButtonsPanel());
        
        return secondPanel;
    }
    
    private JPanel getRadioButtonsPanel() {
        radioButtonsPanel.add(txt);
        radioButtonsPanel.add(pdf);
        
        return radioButtonsPanel;
    }
    
    private JPanel getButtonsPanel() {
        botonImprimir.addActionListener(this);
        botonCancelar.addActionListener(this);
        
        buttonsPanel.add(botonImprimir);
        buttonsPanel.add(botonCancelar);
        
        return buttonsPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonImprimir) {
            if(!txt.isSelected() && !pdf.isSelected()) {
                JOptionPane.showMessageDialog(this, "Selecciona el formato del archivo impreso");
            } else {
                if(txt.isSelected()) { ExportarTXT(); }
                
                if(pdf.isSelected()) { ExportarPDF(); }
            }
        } else if(ae.getSource() == botonCancelar) {
            dispose();
        }
    }
    
    private void ExportarTXT() {
        
        try {
            FileWriter fileWriter = new FileWriter(ruta + File.separator + "Ficheros" + "Empleados.txt");
            
            for(Empleado e : listaEmpleado) {
                if(!e.isEliminado()) { fileWriter.write(e.toString() + "\n"); }
            }
            fileWriter.close();
            JOptionPane.showMessageDialog(this, "FINALIZADO! archivo guardado en: " + ruta);
            
        } catch(IOException ex) {
            System.out.println("ERROR");
        }
    }
    
    private void ExportarPDF() {
        
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(ruta + File.separator + "Ficheros" + "Empleados.pdf"));
            doc.open();
            
            for(Empleado e : listaEmpleado) {
                if(!e.isEliminado()) { doc.add(new Paragraph(e.toString() + "\n")); }
            }
            
            doc.close();
            JOptionPane.showMessageDialog(this, "FINALIZADO! archivo guardado en: " + ruta);            
        } catch(DocumentException | FileNotFoundException ex) {
            System.out.println("ERROR");   
        }
    }
}
