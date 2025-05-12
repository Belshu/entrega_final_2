package Menus.Submenus;

import Contables.Nomina;
import Empleados.Empleado;
import Menus.MenuEmpleados;
import Menus.MenuPartidos;
import Partidos.Partido;
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
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Clase que imprime las listas estáticas y las almacena en la carpeta de "Ficheros"
 * del proyecto. La lista que se imprimirá dependerá del constructor que se declare
 * en cada interfaz, sabiéndose así cual es la lista que debe imprimirse (la de 
 * los empleados, los partidos...) y si desea que se imprima en un documento .txt o 
 * .pdf
 * 
 * @author Isabel Shuang Piñana Alonso
 */
public class Imprimir extends JFrame implements ActionListener{
    
    // PANELES
    private final JPanel mainPanel, secondPanel, radioButtonsPanel, buttonsPanel;
    
    // ELEMENTOS
    private final JLabel margenLabel1, margenLabel2, margenLabel3;
    private final JRadioButton txt, pdf;
    private final JButton botonImprimir, botonCancelar;
    
    // RUTA Y LISTAS
    private final String ruta = System.getProperty("user.dir") + File.separator + "Ficheros";
    private ArrayList <Empleado> listaEmpleados;
    private ArrayList <Partido> listaPartidos;
    
    // BOOLEANS 
    private boolean eliminados;
    private boolean empleados, partidos, nominas;
    
    /**
     * CONSTRUCTOR: declaración de los atributos finales.Declarado especialmente para
     * imprimir los partidos
     * @param who
     **/
    public Imprimir(int who) {
        
        if(who == 0) {
            listaPartidos = MenuPartidos.getListaPartidos();
        
            if(listaPartidos == null || listaPartidos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "LISTA VACIA", "Advertencia", JOptionPane.WARNING_MESSAGE);
                this.dispose();
            }
            partidos = true;
            nominas = false;
            empleados = false;
        } else if(who == 1) {
            this.listaEmpleados = MenuEmpleados.getListaEmpleados();

            nominas = true;            
            partidos = false;
            empleados = false;
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
    
    /**
     * CONSTRUCTOR: declaración de los atributos finales.Declarado especialmente para
     * imprimir los partidos. El boolean que tiene como parámetro decidirá si se trata
     * de los empleados eliminados o no
     * 
     * @param eliminados
     **/
    public Imprimir(boolean eliminados) {
        this.eliminados = eliminados;
        
        empleados = true;        
        partidos = false;
        nominas = false;
        
        listaEmpleados = MenuEmpleados.getListaEmpleados();
        
        if(listaEmpleados == null || listaEmpleados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "LISTA VACIA", "Advertencia", JOptionPane.WARNING_MESSAGE);
            this.dispose();
        }
        
        if(eliminados) {
            boolean hay = false;
            for(Empleado e : listaEmpleados) {
                if(e.isEliminado()) {
                    hay = true;
                    break;
                }
            }
            
            if(!hay) { 
                JOptionPane.showMessageDialog(this, "LISTA VACIA", "Advertencia", JOptionPane.WARNING_MESSAGE);
                this.dispose();
            }
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
    
    /**
     * Método que inicia el tamaño de la interfaz, poner el panel contenido y 
     * diversas propiedades
     **/
    private void initialize() {
        setTitle("Imprimir");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(getMainPanel());
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Método que devuelve el panel principal donde se colocarán los margenes, el
     * panel secundario y el de los botones
     * @return JPanel
     **/
    private JPanel getMainPanel() {
        mainPanel.add(margenLabel3, BorderLayout.PAGE_START);
        mainPanel.add(margenLabel1, BorderLayout.LINE_START);
        mainPanel.add(margenLabel2, BorderLayout.LINE_END);
        
        mainPanel.add(getSecondPanel(), BorderLayout.CENTER);
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_END);
        
        return mainPanel;
    }
    
    /**
     * Método que devuelve el panel secundario donde se colocarán el panel de los radiobuttons
     * @return JPanel
     **/
    private JPanel getSecondPanel() {
        secondPanel.add(getRadioButtonsPanel());
        
        return secondPanel;
    }
    
    /**
     * Método que devuelve el panel de los radiobutton donde se colocarán los elementos
     * correspondientes
     * @return JPanel
     **/
    private JPanel getRadioButtonsPanel() {
        radioButtonsPanel.add(txt);
        radioButtonsPanel.add(pdf);
        
        return radioButtonsPanel;
    }
    
    /**
     * Método que devuelve el panel de botones donde se colocarán los elementos
     * correspondientes
     * @return JPanel
     **/
    private JPanel getButtonsPanel() {
        botonImprimir.addActionListener(this);
        botonCancelar.addActionListener(this);
        
        buttonsPanel.add(botonImprimir);
        buttonsPanel.add(botonCancelar);
        
        return buttonsPanel;
    }
    
    /**
     * Metodo sobrecargado que recoge las acciones dentro de la interfaz y se le asignara una utilidad a los
     * botones correspondientes
     * @param ae ActionEvent 
     **/
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botonImprimir) {
            
            if(empleados) {
                if(!eliminados) {
                    Collections.sort(listaEmpleados, (Empleado t1, Empleado t2) -> t1.getDni().compareTo(t2.getDni()));
                } else if (eliminados) {
                    Collections.sort(listaEmpleados, (Empleado t1, Empleado t2) -> t1.getNombre().compareTo(t2.getNombre()));
                }
            }
            
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
    
    /**
     * Método que exporta la lista correspondiente a .txt
     **/
    private void ExportarTXT() {
        
        if (empleados) {
            String nombre = "Empleados.txt";
            if (eliminados) {
                nombre = "Empleados_eliminados.txt";
            }

            try {
                FileWriter fileWriter = new FileWriter(ruta + File.separator + "Ficheros" + nombre);

                for (Empleado e : listaEmpleados) {
                    if (!eliminados) {
                        if (!e.isEliminado()) {
                            fileWriter.write(e.toString() + "\n");
                        }
                    } else if (eliminados) {
                        if (e.isEliminado()) {
                            fileWriter.write(e.toString() + "\n");
                        }
                    }
                }
                fileWriter.close();
                JOptionPane.showMessageDialog(this, "FINALIZADO! archivo guardado en: " + ruta);

            } catch (IOException ex) {
                System.out.println("ERROR");
            }
        } else if (partidos) {            
            try {
                FileWriter fileWriter = new FileWriter(ruta + File.separator + "Ficheros" + "Partidos.txt");

                for (Partido p : listaPartidos) {
                    fileWriter.write(p.toString());
                }
                fileWriter.close();
                JOptionPane.showMessageDialog(this, "FINALIZADO! archivo guardado en: " + ruta);

            } catch (IOException ex) {
                System.out.println("ERROR");
            }
        } else if (nominas) {            
            try {
                FileWriter fileWriter = new FileWriter(ruta + File.separator + "Ficheros" + "Nominas.txt");

                for(Empleado e : listaEmpleados) {
                    if(!e.getNominas().isEmpty() && !e.isEliminado()) {
                        for(Nomina n : e.getNominas()) {
                                fileWriter.write("DNI: " + e.getDni() + " | Nombre: " + e.getNombre() + "\n-> NOMINAS: \n"
                            + n.toString());
                        }
                    }
                }
                
                fileWriter.close();
                JOptionPane.showMessageDialog(this, "FINALIZADO! archivo guardado en: " + ruta);

            } catch (IOException ex) {
                System.out.println("ERROR");
            }
        }
    }
    
    /**
     * Método que exporta la lista correspondiente a .pdf
     **/
    private void ExportarPDF() {
        if(empleados) {
            String nombre = "Empleados.pdf";
            if (eliminados) {
                nombre = "Empleados_eliminados.pdf";
            }

            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(ruta + File.separator + "Ficheros" + nombre));
                doc.open();

                for (Empleado e : listaEmpleados) {
                    if (!eliminados) {
                        if (!e.isEliminado()) {
                            doc.add(new Paragraph(e.toString() + "\n"));
                        }
                    } else if (eliminados) {
                        if (e.isEliminado()) {
                            doc.add(new Paragraph(e.toString() + "\n"));
                        }
                    }
                }

                doc.close();
                JOptionPane.showMessageDialog(this, "FINALIZADO! archivo guardado en: " + ruta);
            } catch (DocumentException | FileNotFoundException ex) {
                System.out.println("ERROR");
            }
        } else if (partidos) {
            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(ruta + File.separator + "Ficheros" + "Partidos.pdf"));
                doc.open();

                for(Partido p : listaPartidos){
                    doc.add(new Paragraph(p.toString() + "\n"));
                }

                doc.close();
                JOptionPane.showMessageDialog(this, "FINALIZADO! archivo guardado en: " + ruta);
            } catch (DocumentException | FileNotFoundException ex) {
                System.out.println("ERROR");
            }
        } else if (nominas) {
            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(ruta + File.separator + "Ficheros" + "Nominas.pdf"));
                doc.open();

                for(Empleado e : listaEmpleados) {
                    if(!e.getNominas().isEmpty() && !e.isEliminado()) {
                        for(Nomina n : e.getNominas()) {
                            doc.add(new Paragraph("DNI: " + e.getDni() + " | Nombre: " + e.getNombre() + "\n-> NOMINAS: \n"
                            + n.toString()));
                        }
                    }
                }

                doc.close();
                JOptionPane.showMessageDialog(this, "FINALIZADO! archivo guardado en: " + ruta);
            } catch (DocumentException | FileNotFoundException ex) {
                System.out.println("ERROR");
            }
        }
    }
}
