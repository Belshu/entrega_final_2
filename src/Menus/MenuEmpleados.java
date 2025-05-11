package Menus;

import Empleados.Directivo;
import Empleados.Tecnico;
import Empleados.Empleado;
import Empleados.Jugador;
import Menus.Submenus.Imprimir;
import Menus.Submenus.ModificarEmpleado;
import Menus.Submenus.NuevoEmpleado;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

/**
 * Menu de gestión de empleados: se crean unas listas estáticas que contendrán
 * todos los empleados y por separado; los jugadores, técnicos y directivos.
 * Contiene tres ventanas de JTabbedPane que divide la forma de mostrar las listas
 * en esas cuatro pestañas. En base a los botones, se sabrá lo que desea el usuario
 * hacer con ello
 *
 * @author Isabel Shuang Piñana Alonso
 */
public class MenuEmpleados extends JFrame implements ActionListener {

    // LISTAS ESTÁTICAS
    private static ArrayList<Empleado> listaEmpleados;
    private static ArrayList<Jugador> listaJugadores;
    private static ArrayList<Tecnico> listaTecnicos;
    private static ArrayList<Directivo> listaDirectivos;

    // PANELES
    private final JPanel mainPanel, todosPanel, jugadoresPanel, tecnicosPanel, directivosPanel, buttonsPanel;
    private final JTabbedPane ventanas;
    
    // ELEMENTOS
    private final JButton botonAnadir, botonModificar, botonImprimir, botonImprimirEliminados, botonEliminar;
    private JList<Empleado> listaEmpleados2;
    private JList<Jugador> listaJugadores2;
    private JList<Tecnico> listaTecnicos2;
    private JList<Directivo> listaDirectivos2;

    // MODELOS DE LISTAS
    private DefaultListModel<Empleado> empleadoListModel;
    private DefaultListModel<Jugador> jugadorListModel;
    private DefaultListModel<Tecnico> tecnicoListModel;
    private DefaultListModel<Directivo> directivoListModel;
    
    // EMPLEADO ELEGIDO POR EL USUARIO
    Empleado elegido;
    
    // OTRA INTERFAZ
    MenuFacturas menuFacturas;

    /**
     * Método que inicializa las listas de prueba
     */
    public static void inicializarListas() {
        listaEmpleados = new ArrayList<>();
        listaJugadores = new ArrayList<>();
        listaTecnicos = new ArrayList<>();
        listaDirectivos = new ArrayList<>();

        listaJugadores.add(new Jugador("111111111A", "Jugador1", 123456, "1", 0, 21, 111, true));
        listaJugadores.add(new Jugador("111111111B", "Jugador2", 123456, "2", 1, 22, 112, true));

        listaTecnicos.add(new Tecnico("Informatico", "Redes de sistemas", "222222222A", "Tecnico 1", 654321));
        listaTecnicos.add(new Tecnico("Informatico", "ciberseguridad", "222222222B", "Tecnico 2", 654321));

        listaDirectivos.add(new Directivo("Subdirector", "333333333A", "Directivo 1", 135246));
        listaDirectivos.add(new Directivo("Director", "333333333B", "Directivo 2", 135246));

        for (Jugador j : listaJugadores) {
            listaEmpleados.add(j);
        }
        for (Tecnico t : listaTecnicos) {
            listaEmpleados.add(t);
        }
        for (Directivo d : listaDirectivos) {
            listaEmpleados.add(d);
        }
    }

    /**
     * CONSTRUCTOR: inicialización de los atributos finales. Pide el menuFacturas
     * creado para que, cuando se elimine un empleado, se actualice la JList del otro
     * menu
     * @param menuFacturas
     */
    public MenuEmpleados(MenuFacturas menuFacturas) {
        this.menuFacturas = menuFacturas;
        
        if(listaEmpleados == null) {
            listaEmpleados = new ArrayList<>();
        } 
        
        if(listaJugadores == null) {
            listaJugadores = new ArrayList<>();
        }
        
        if(listaTecnicos == null) {
            listaTecnicos = new ArrayList<>();
        }
        
        if(listaDirectivos == null) {
            listaDirectivos = new ArrayList<>();
        }

        // PANELES
        mainPanel = new JPanel(new BorderLayout());
        todosPanel = new JPanel(new BorderLayout());
        jugadoresPanel = new JPanel(new BorderLayout());
        tecnicosPanel = new JPanel(new BorderLayout());
        directivosPanel = new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());

        // BOTONES
        botonAnadir = new JButton("Añadir");
        botonModificar = new JButton("Modificar");
        botonImprimir = new JButton("Imprimir");
        botonImprimirEliminados = new JButton("Imprimir eliminados");
        botonEliminar = new JButton("Eliminar");

        ventanas = new JTabbedPane(JTabbedPane.NORTH);
    }

    /**
     * Método que devuelve el panel principal
     * @return JPanel
     */
    public JPanel getMainPanel() {
        mainPanel.add(getButtonsPanel(), BorderLayout.PAGE_START);
        mainPanel.add(new JScrollPane(getVentanas()), BorderLayout.CENTER);
        
        return mainPanel;
    }

    /**
     * Método que devuelve el panel de botones
     * @return buttonsPanel
     */
    private JPanel getButtonsPanel() {
        botonAnadir.addActionListener(this);
        botonModificar.addActionListener(this);
        botonImprimir.addActionListener(this);
        botonImprimirEliminados.addActionListener(this);
        botonEliminar.addActionListener(this);

        buttonsPanel.add(botonAnadir);
        buttonsPanel.add(botonModificar);
        buttonsPanel.add(botonImprimir);
        buttonsPanel.add(botonImprimirEliminados);
        buttonsPanel.add(botonEliminar);

        return buttonsPanel;
    }

    /**
     * Método que devuelve las ventanas, añadiendo los paneles de cada una
     * @return JTabbedPane
     **/    
    private JTabbedPane getVentanas() {
        ventanas.add("Todos", getTodosPanel());
        ventanas.add("Jugadores", getJugadoresPanel());
        ventanas.add("Técnicos", getTecnicosPanel());
        ventanas.add("Directivos", getDirectivosPanel());
        return ventanas;
    }

    /**
     * Método que devuelve el panel de todos y muestra los empleados
     **/
    private JPanel getTodosPanel() {
        todosPanel.add(new JScrollPane(getListaEmpleados2()), BorderLayout.CENTER);
        return todosPanel;
    }

    /**
     * Método que devuelve la lista visual de los empleados
     **/
    private JList<Empleado> getListaEmpleados2() {
        listaEmpleados2 = new JList<>(getEmpleadoListModel());
        listaEmpleados2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listaEmpleados2;
    }

    /**
     * Método que devuelve el modelo de la lista de los empleados
     **/
    private DefaultListModel<Empleado> getEmpleadoListModel() {
        empleadoListModel = new DefaultListModel<>();
        
        if(!listaEmpleados.isEmpty()) {
            for (Empleado e : listaEmpleados) {
                if (!e.isEliminado()) {
                    empleadoListModel.addElement(e);
                }
            }
        }
        
        return empleadoListModel;
    }

    /**
     * Método que devuelve el panel de todos y muestra los jugadores
     **/
    private JPanel getJugadoresPanel() {
        jugadoresPanel.add(new JScrollPane(getListaJugadores2()), BorderLayout.CENTER);
        return jugadoresPanel;
    }

    /**
     * Método que devuelve la lista visual de los jugadores
     **/
    private JList<Jugador> getListaJugadores2() {
        listaJugadores2 = new JList<>(getJugadorListModel());
        listaJugadores2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listaJugadores2;
    }

    /**
     * Método que devuelve el modelo de la lista de los jugadores
     **/
    private DefaultListModel<Jugador> getJugadorListModel() {
        jugadorListModel = new DefaultListModel<>();

        if(!listaJugadores.isEmpty()) {
            for (Jugador j : listaJugadores) {
                if (!j.isEliminado()) {
                    jugadorListModel.addElement(j);
                }
            }
        }
        return jugadorListModel;
    }

    /**
     * Método que devuelve el panel de todos y muestra los tecnicos
     **/
    private JPanel getTecnicosPanel() {
        tecnicosPanel.add(new JScrollPane(getListaTecnicos2()), BorderLayout.CENTER);
        return tecnicosPanel;
    }

    /**
     * Método que devuelve la lista visual de los tecnicos
     **/
    private JList<Tecnico> getListaTecnicos2() {
        listaTecnicos2 = new JList<>(getTecnicoListModel());
        listaTecnicos2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        return listaTecnicos2;
    }

    /**
     * Método que devuelve el modelo de la lista de los tecnicos
     **/
    private DefaultListModel<Tecnico> getTecnicoListModel() {
        tecnicoListModel = new DefaultListModel<>();
        if(!listaTecnicos.isEmpty()) {
            for (Tecnico t : listaTecnicos) {
                if (!t.isEliminado()) {
                    tecnicoListModel.addElement(t);
                }
            }
        }
        return tecnicoListModel;
    }

    /**
     * Método que devuelve el panel de todos y muestra los directivos
     **/
    private JPanel getDirectivosPanel() {
        directivosPanel.add(new JScrollPane(getListaDirectivos2()), BorderLayout.CENTER);
        return directivosPanel;
    }

    /**
     * Método que devuelve la lista visual de los directivos
     **/
    private JList<Directivo> getListaDirectivos2() {
        listaDirectivos2 = new JList<>(getDirectivoListModel());
        listaDirectivos2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        return listaDirectivos2;
    }

    /**
     * Método que devuelve el modelo de la lista de los directivos
     **/
    private DefaultListModel<Directivo> getDirectivoListModel() {
        directivoListModel = new DefaultListModel<>();
        if(!listaDirectivos.isEmpty()) {
            for (Directivo d : listaDirectivos) {
                if (!d.isEliminado()) {
                    directivoListModel.addElement(d);
                }
            }
        }
        return directivoListModel;
    }

    /**
     * Metodo sobrecargado que recoge las acciones dentro de la interfaz y se le asignara una utilidad a los
     * botones correspondientes
     * @param ae ActionEvent 
     **/
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == botonAnadir) {
            anadirEmpleado();
        } else if (ae.getSource() == botonModificar) {
            modificarEmpleado();
        } else if (ae.getSource() == botonImprimir) {
            new Imprimir(false);
        } else if (ae.getSource() == botonImprimirEliminados) {
            new Imprimir(true);
        } else if (ae.getSource() == botonEliminar) {
            eliminarEmpleado();
        }
    }

    /**
     * Método que añade el empleado nuevo al cerrar la ventana creada en la interfaz NuevoEmpleado y
     * actualiza las listas y los modelos de listas
     **/
    private void anadirEmpleado() {
        try {
            NuevoEmpleado nuevoEmpleado = new NuevoEmpleado();

            nuevoEmpleado.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (nuevoEmpleado.getDni() != null && nuevoEmpleado.getNombre() != null && nuevoEmpleado.getTelf() != 0) {
                        Empleado nuevo = null;

                        switch (nuevoEmpleado.tipoEmpleado()) {
                            case 0 -> {
                                Jugador jugador = new Jugador(nuevoEmpleado.getDni(), nuevoEmpleado.getNombre(), nuevoEmpleado.getTelf(),
                                        nuevoEmpleado.getApellido(), nuevoEmpleado.getDemarcacion(), nuevoEmpleado.getEdad(),
                                        nuevoEmpleado.getValor(),
                                        nuevoEmpleado.isEstado());
                                listaJugadores.add(jugador);
                                nuevo = jugador;
                            }
                            case 1 -> {
                                Tecnico tecnico = new Tecnico(nuevoEmpleado.getPuesto(), nuevoEmpleado.getEspecialidad(),
                                        nuevoEmpleado.getDni(), nuevoEmpleado.getNombre(), nuevoEmpleado.getTelf());
                                listaTecnicos.add(tecnico);
                                nuevo = tecnico;
                            }
                            case 2 -> {
                                Directivo directivo = new Directivo(nuevoEmpleado.getCargo(),
                                        nuevoEmpleado.getDni(), nuevoEmpleado.getNombre(), nuevoEmpleado.getTelf());
                                listaDirectivos.add(directivo);
                                nuevo = directivo;
                            }
                        }

                        if (nuevo != null) {
                            listaEmpleados.add(nuevo);
                            empleadoListModel.addElement(nuevo);
                            if (nuevo instanceof Jugador jugador) {
                                jugadorListModel.addElement(jugador);
                            } else if (nuevo instanceof Tecnico tecnico) {
                                tecnicoListModel.addElement(tecnico);
                            } else if (nuevo instanceof Directivo directivo) {
                                directivoListModel.addElement(directivo);
                            }
                        }

                        listaEmpleados2.setModel(empleadoListModel);
                        listaJugadores2.setModel(jugadorListModel);
                        listaTecnicos2.setModel(tecnicoListModel);
                        listaDirectivos2.setModel(directivoListModel);
                    }
                }
            });
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    /**
     * Método que modifica el empleado seleccionado al cerrar la ventana creada en la interfaz NuevoEmpleado y
     * actualiza las listas y los modelos de listas
     */
    private void modificarEmpleado() {
        switch (ventanas.getSelectedIndex()) {
            case 0 ->
                elegido = listaEmpleados2.getSelectedValue();
            case 1 ->
                elegido = listaJugadores2.getSelectedValue();
            case 2 ->
                elegido = listaTecnicos2.getSelectedValue();
            case 3 ->
                elegido = listaDirectivos2.getSelectedValue();
            default -> {
                return;
            }
        }

        if (elegido != null) {
            try {
                ModificarEmpleado modificarEmpleado = new ModificarEmpleado(elegido);

                modificarEmpleado.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        if (modificarEmpleado.getNombre() != null) {
                            elegido.setNombre(modificarEmpleado.getNombre());
                            elegido.setTelf(modificarEmpleado.getTelf());

                            if (elegido instanceof Jugador jugador) {
                                jugador.setApellidos(modificarEmpleado.getApellido());
                                jugador.setDemarcacion(modificarEmpleado.getDemarcacion());
                                jugador.setEdad(modificarEmpleado.getEdad());
                                jugador.setValor(modificarEmpleado.getValor());
                                jugador.setEstado(modificarEmpleado.isEstado());
                            } else if (elegido instanceof Tecnico tecnico) {
                                tecnico.setPuesto(modificarEmpleado.getPuesto());
                                tecnico.setEspecialidad(modificarEmpleado.getEspecialidad());
                            } else if (elegido instanceof Directivo directivo) {
                                directivo.setCargo(modificarEmpleado.getCargo());
                            }

                            listaEmpleados2.setModel(empleadoListModel);
                            listaJugadores2.setModel(jugadorListModel);
                            listaTecnicos2.setModel(tecnicoListModel);
                            listaDirectivos2.setModel(directivoListModel);
                        }
                    }
                });
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Método que elimina el empleado seleccionado y actualiza las listas y los modelos de listas
     **/    
    private void eliminarEmpleado() {
        
        switch (ventanas.getSelectedIndex()) {
            case 0 ->
                elegido = listaEmpleados2.getSelectedValue();
            case 1 ->
                elegido = listaJugadores2.getSelectedValue();
            case 2 ->
                elegido = listaTecnicos2.getSelectedValue();
            case 3 ->
                elegido = listaDirectivos2.getSelectedValue();
        }

        if (elegido != null) {
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar a este empleado?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                elegido.setEliminado(true);
                elegido.setFechaEliminacion(new Date());
                
                    empleadoListModel.removeElement(elegido);
                
                if (elegido instanceof Jugador) {
                    jugadorListModel.removeElement(elegido);
                } else if (elegido instanceof Tecnico) {
                    tecnicoListModel.removeElement(elegido);
                } else if (elegido instanceof Directivo) {
                    directivoListModel.removeElement(elegido);
                }

                listaEmpleados2.setModel(empleadoListModel);
                listaJugadores2.setModel(jugadorListModel);
                listaTecnicos2.setModel(tecnicoListModel);
                listaDirectivos2.setModel(directivoListModel);
                
                menuFacturas.updateNominas(elegido);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    // GETTERS DE LISTAS ESTATICAS
    /**
     * Método que devuelve la ArrayList estática de empleados
     * @return ArrayList Empleado
     **/
    public static ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    
    /**
     * Método que devuelve la ArrayList estática de jugadores
     * @return ArrayList Jugador
     **/
    public static ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    
    /**
     * Método que devuelve la ArrayList estática de tecnicos
     * @return ArrayList Tecnico
     **/
    public static ArrayList<Tecnico> getListaTecnicos() {
        return listaTecnicos;
    }

    /**
     * Método que devuelve la ArrayList estática de directivos
     * @return ArrayList Directivo
     **/
    public static ArrayList<Directivo> getListaDirectivos() {
        return listaDirectivos;
    }
}
