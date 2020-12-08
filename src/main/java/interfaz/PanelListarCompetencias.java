package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;

import logica.CompetenciaDTO;
import logica.Deporte;
import logica.GestorCompetencia;
import logica.GestorDeporte;
import logica.GestorUsuario;
import logica.Usuario;

public class PanelListarCompetencias extends PanelGenerico {
	
	JLabel labelCompetencias;
	JLabel labelNombre;
	JLabel labelDep;
	JLabel labelMod;
	JLabel labelEstado;
	JLabel labelCriterioBusqueda;
	JButton buttonBuscar;
	JButton buttonLimpiar;
	JButton buttonCancelar;
	JButton buttonCrearComp;
	JButton buttonVerComp;
	JComboBox<String> comboMod;
	JComboBox<String> comboDep;
	JComboBox<String> comboEstado;
	JTextField textfNombre;
	JTable tablaCompetencias;
	JScrollPane scrollPaneCompetencias;
	ArrayList<Deporte> deportes;
	List<CompetenciaDTO> listaComps;
	public PanelListarCompetencias() {
		super();
		inicializarComponentes();
		armarPanel();
	}
	private void inicializarComponentes() {
		//Variables
		deportes = GestorDeporte.buscarTodos();
		Dimension tamCombo = new Dimension(125,30);
		Dimension tamBotGrande = new Dimension(270,30);
		Dimension tamBotChico = new Dimension(150,30);
		listaComps = new ArrayList<CompetenciaDTO>();
		//Labels
		labelCompetencias = new JLabel("Competencias creadas por el usuario: "+GestorUsuario.usuario_autenticado.getNombre()+ " "+ GestorUsuario.usuario_autenticado.getApellido());
		labelCompetencias.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		labelNombre = new JLabel("Nombre: ");
		labelDep = new JLabel("Deporte: ");
		labelMod = new JLabel("Modalidad: ");
		labelEstado = new JLabel("Estado: ");
		labelCriterioBusqueda = new JLabel("<HTML> <B>- Indique al menos un criterio de búsqueda</B></HTML>");
		labelCriterioBusqueda.setFont(labelEstado.getFont().deriveFont(15f));
		//Buttons
		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setPreferredSize(tamBotChico);
		buttonLimpiar = new JButton("Limpiar Filtros");
		buttonLimpiar.setPreferredSize(tamBotChico);
		buttonCancelar = new JButton("Cancelar");
		buttonCrearComp = new JButton("Crear Nueva Competencia");
		buttonVerComp = new JButton("Ver Competencia");
		buttonCancelar.setPreferredSize(tamBotGrande);
		buttonCrearComp.setPreferredSize(tamBotGrande);
		buttonVerComp.setPreferredSize(tamBotGrande);
		
		//Color Buttons
		buttonBuscar.setBackground(colorFondoBoton);
		buttonBuscar.setForeground(colorTextoBoton);
		buttonLimpiar.setBackground(colorFondoBoton);
		buttonLimpiar.setForeground(colorTextoBoton);
		buttonCancelar.setBackground(colorFondoBoton);
		buttonCancelar.setForeground(colorTextoBoton);
		buttonCrearComp.setBackground(colorFondoBoton);
		buttonCrearComp.setForeground(colorTextoBoton);
		buttonVerComp.setBackground(colorFondoBoton);
		buttonVerComp.setForeground(colorTextoBoton);
		
		//Campos y comboboxes
		comboDep = new JComboBox();
		comboMod = new JComboBox();
		comboEstado = new JComboBox();
		comboDep.setPreferredSize(tamCombo);
		comboMod.setPreferredSize(tamCombo);
		comboEstado.setPreferredSize(tamCombo);
		textfNombre = new JTextField(10);
		textfNombre.setText("");
		
		//Cargo Comboboxes
		comboDep.addItem("<Ninguno>");
		for(Deporte d: deportes) {
			comboDep.addItem(d.getNombreDeporte());
		}
		comboMod.addItem("<Ninguna>");
		comboMod.addItem("Liga");
		comboMod.addItem("Elimin. Simple");
		comboMod.addItem("Elimin. Doble");
		comboEstado.addItem("<Ninguno>");
		comboEstado.addItem("Creada");
		comboEstado.addItem("Planificada");
		comboEstado.addItem("En Disputa");
		comboEstado.addItem("Finalizada");
		comboEstado.addItem("Dada de baja");
		//Tablas
		tablaCompetencias = new JTable();
		//tablaCompetencias.setPreferredSize(new Dimension(900,270));
		scrollPaneCompetencias = new JScrollPane(tablaCompetencias);
		scrollPaneCompetencias.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		scrollPaneCompetencias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scrollPaneCompetencias.setPreferredSize(new Dimension(900,270));
		scrollPaneCompetencias.getVerticalScrollBar().setBackground(Color.decode("#5693f5"));
		scrollPaneCompetencias.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			    @Override
			    protected void configureScrollBarColors() {
			        this.thumbColor = Color.decode("#2148bc");
			        this.thumbDarkShadowColor = (Color.decode("#0f2e8a"));
			    }
			});
		//Listeners
		ActionListener buscarListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	construirTablaCompetencias(setearColumnasCompetencias(),obtenerMatrizCompetencias());
            	}
        };
        buttonBuscar.addActionListener(buscarListener);
        
        
		ActionListener cancelarListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelPrincipal());
				ventana.setSize(PanelGenerico.tamPrincipal);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
            	}
        };
        buttonCancelar.addActionListener(cancelarListener);
        /*
         * 
         * TODO: EL COSO DE DESHABILITAR
         * EL BOTON DE VER COMPETENCIA
         * 
         * 
         * 
         */
        
        ActionListener altaCompetenciaListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelAltaCompetencia());
				ventana.setSize(tamAltaComp);
				ventana.revalidate();
				ventana.setLocationRelativeTo(null);
				ventana.repaint();
            	}
        };
        buttonCrearComp.addActionListener(altaCompetenciaListener);
        
        ActionListener verCompetenciaListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
            	JLabel labelAlerta;
            	labelAlerta = new JLabel("<HTML><b> <font color='white'>Seleccione una competencia  </b></HTML>");
            	labelAlerta.setBackground(Color.red);
            	if(tablaCompetencias.getSelectedRow()==-1) {
            		PanelGenerico panelDialogo = new PanelGenerico();
            		panelDialogo.setLayout(new BorderLayout());
            		panelDialogo.add(labelAlerta,BorderLayout.CENTER);
            		JDialog jd = new JDialog(ventana);
            		jd.setContentPane(panelDialogo);
            		JOptionPane jpane = new JOptionPane(labelAlerta,JOptionPane.PLAIN_MESSAGE);
            		getComponents(jpane);
            		jpane.setBackground(Color.decode("#2148bc"));
            		jd = jpane.createDialog(ventana, "ALERTA");            		
            		jd.setVisible(true);
            	}
            	else {
				ventana.setContentPane(new PanelVerCompetencia(GestorCompetencia.buscarCompetencia(listaComps.get(tablaCompetencias.getSelectedRow()).getIdCompetencia())));
				ventana.setSize(tamVerComp);
				ventana.setLocationRelativeTo(null);
				ventana.revalidate();
				ventana.repaint();
            		}
            	}
        };
        buttonVerComp.addActionListener(verCompetenciaListener);
        buttonBuscar.addActionListener(buscarListener);
        
        ActionListener limpiarFiltrosListener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textfNombre.setText("");
        		comboDep.setSelectedIndex(0);
        		comboMod.setSelectedIndex(0);
        		comboEstado.setSelectedIndex(0);
        	}
        };
        buttonLimpiar.addActionListener(limpiarFiltrosListener);
        construirTablaCompetencias(setearColumnasCompetencias(),obtenerMatrizCompetencias());
        
	}
	
	private void armarPanel() {
	SpringLayout sLayout = new SpringLayout();
	this.setLayout(sLayout);
	add(labelCompetencias);
	sLayout.putConstraint(SpringLayout.WEST,labelCompetencias,30,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,labelCompetencias,10,SpringLayout.NORTH,this);
	add(labelNombre);
	sLayout.putConstraint(SpringLayout.WEST,labelNombre,170,SpringLayout.WEST,labelCompetencias);
	sLayout.putConstraint(SpringLayout.NORTH,labelNombre,40,SpringLayout.NORTH,labelCompetencias);
	add(labelDep);
	sLayout.putConstraint(SpringLayout.WEST,labelDep,170,SpringLayout.WEST,labelCompetencias);
	sLayout.putConstraint(SpringLayout.NORTH,labelDep,35,SpringLayout.SOUTH,labelNombre);
	add(labelMod);
	sLayout.putConstraint(SpringLayout.WEST,labelMod,30,SpringLayout.EAST,textfNombre);
	sLayout.putConstraint(SpringLayout.NORTH,labelMod,0,SpringLayout.NORTH,labelNombre);
	add(labelEstado);
	sLayout.putConstraint(SpringLayout.WEST,labelEstado,0,SpringLayout.WEST,labelMod);
	sLayout.putConstraint(SpringLayout.NORTH,labelEstado,0,SpringLayout.NORTH,labelDep);
	add(buttonBuscar);
	sLayout.putConstraint(SpringLayout.WEST,buttonBuscar,30,SpringLayout.EAST,comboMod);
	sLayout.putConstraint(SpringLayout.NORTH,buttonBuscar,0,SpringLayout.NORTH,comboMod);
	add(buttonLimpiar);
	sLayout.putConstraint(SpringLayout.WEST,buttonLimpiar,0,SpringLayout.WEST,buttonBuscar);
	sLayout.putConstraint(SpringLayout.NORTH,buttonLimpiar,0,SpringLayout.NORTH,comboDep);
	add(comboDep);
	sLayout.putConstraint(SpringLayout.WEST,comboDep,20,SpringLayout.EAST,labelDep);
	sLayout.putConstraint(SpringLayout.SOUTH,comboDep,23,SpringLayout.NORTH,labelDep);
	add(comboMod);
	sLayout.putConstraint(SpringLayout.WEST,comboMod,0,SpringLayout.WEST,comboEstado);
	sLayout.putConstraint(SpringLayout.SOUTH,comboMod,23,SpringLayout.NORTH,labelMod);
	add(comboEstado);
	sLayout.putConstraint(SpringLayout.WEST,comboEstado,30,SpringLayout.EAST,labelEstado);
	sLayout.putConstraint(SpringLayout.SOUTH,comboEstado,23,SpringLayout.NORTH,labelEstado);
	add(textfNombre);
	sLayout.putConstraint(SpringLayout.WEST,textfNombre,20,SpringLayout.EAST,labelNombre);
	sLayout.putConstraint(SpringLayout.NORTH,textfNombre,35,SpringLayout.NORTH,labelCompetencias);
	add(scrollPaneCompetencias);
	sLayout.putConstraint(SpringLayout.WEST,scrollPaneCompetencias,55,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,scrollPaneCompetencias,80,SpringLayout.SOUTH,labelDep);
	add(buttonCancelar);
	sLayout.putConstraint(SpringLayout.WEST,buttonCancelar,15,SpringLayout.WEST,scrollPaneCompetencias);
	sLayout.putConstraint(SpringLayout.NORTH,buttonCancelar,20,SpringLayout.SOUTH,scrollPaneCompetencias);
	add(buttonCrearComp);
	sLayout.putConstraint(SpringLayout.WEST,buttonCrearComp,30,SpringLayout.EAST,buttonCancelar);
	sLayout.putConstraint(SpringLayout.NORTH,buttonCrearComp,20,SpringLayout.SOUTH,scrollPaneCompetencias);
	add(buttonVerComp);
	sLayout.putConstraint(SpringLayout.EAST,buttonVerComp,-15,SpringLayout.EAST,scrollPaneCompetencias);
	sLayout.putConstraint(SpringLayout.NORTH,buttonVerComp,20,SpringLayout.SOUTH,scrollPaneCompetencias);
	add(labelCriterioBusqueda);
	sLayout.putConstraint(SpringLayout.WEST,labelCriterioBusqueda,5,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,labelCriterioBusqueda,15,SpringLayout.SOUTH,buttonVerComp);
	}
	
	private void construirTablaCompetencias(String[] columnas, Object[][] datos) {
		// TODO Auto-generated method stub
		ModeloTablaCompetencias model = new ModeloTablaCompetencias(datos,columnas);
		tablaCompetencias.setModel(model);
		tablaCompetencias.getTableHeader().setDefaultRenderer(new GenericoTableHeaderRenderer());
		
		tablaCompetencias.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldasGen("texto"));
		tablaCompetencias.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldasGen("texto"));
		tablaCompetencias.getColumnModel().getColumn(2).setCellRenderer(new GestionCeldasGen("texto"));
		tablaCompetencias.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldasGen("texto"));

		tablaCompetencias.getTableHeader().setReorderingAllowed(false);
		tablaCompetencias.getTableHeader().setForeground(Color.white);
		tablaCompetencias.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		tablaCompetencias.setRowHeight(35);
		tablaCompetencias.setGridColor(Color.BLACK);
		tablaCompetencias.setShowGrid(true);
		
		tablaCompetencias.getColumnModel().getColumn(0).setPreferredWidth(250);
		tablaCompetencias.getColumnModel().getColumn(1).setPreferredWidth(150);
		tablaCompetencias.getColumnModel().getColumn(2).setPreferredWidth(250);
		tablaCompetencias.getColumnModel().getColumn(3).setPreferredWidth(250);
		
	}
	private Object[][] obtenerMatrizCompetencias() {
		listaComps = GestorCompetencia.buscarCompetenciaPorFiltros(textfNombre.getText().trim(),comboDep.getSelectedItem().toString(),comboMod.getSelectedItem().toString(),comboEstado.getSelectedItem().toString());
		String[][] matrizCompetencias = new String[listaComps.size()][4];
		int i=0;
		for (CompetenciaDTO cDTO : listaComps) {
			matrizCompetencias[i][0] = cDTO.getNombre();
			matrizCompetencias[i][1] = cDTO.getDeporte();
			matrizCompetencias[i][2] = cDTO.getModalidad();
			matrizCompetencias[i][3] = cDTO.getEstado();
			i++;
		}
		return matrizCompetencias;
	}
	private String[] setearColumnasCompetencias() {
		String[] columnas = new String[4];
		columnas[0] = "Nombre";
		columnas[1] = "Deporte";
		columnas[2] = "Modalidad";
		columnas[3] = "Estado";
		return columnas;
	}
	
}
