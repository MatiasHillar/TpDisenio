package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import acceso.ParticipanteDAO;
import logica.CompetenciaDTO;
import logica.GestorCompetencia;

public class PanelListarParticipantes extends PanelGenerico{
	JLabel labelPartic;
	ButtonGenerico buttonAgregarP;
	ButtonGenerico buttonModificarP;
	ButtonGenerico buttonEliminarP;
	ButtonGenerico buttonCancelar;
	JTable tablaParticipantes;
	JScrollPane scrollPaneParticipantes;
	CompetenciaDTO dtoComp;
	String nombreC;
	public PanelListarParticipantes(CompetenciaDTO dtoC) {
		super();
		dtoComp = dtoC;
		inicializarComponentes();
		armarPanel();
	}
	
	private void inicializarComponentes() {
	//Labels
	nombreC=dtoComp.getNombre();
	labelPartic = new JLabel("<HTML>Participantes de Competencia: <B>"+nombreC+"</B> </HTML>",SwingConstants.CENTER);
	labelPartic.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
	//Buttons
	buttonAgregarP = new ButtonGenerico("Agregar Nuevo Participante");
	buttonModificarP = new ButtonGenerico("Modificar Participante");
	buttonEliminarP = new ButtonGenerico("Eliminar Participante");
	buttonCancelar = new ButtonGenerico("Cancelar");
	buttonAgregarP.setPreferredSize(new Dimension(200,30));
	buttonModificarP.setPreferredSize(new Dimension(200,30));
	buttonEliminarP.setPreferredSize(new Dimension(200,30));
	buttonCancelar.setPreferredSize(new Dimension(200,30));
	//Color Buttons
	buttonModificarP.setEnabled(false);
	buttonEliminarP.setEnabled(false);
	
	
	//Tablas
	tablaParticipantes = new JTable();
	tablaParticipantes.setPreferredSize(new Dimension(400,200));
	scrollPaneParticipantes = new JScrollPane(tablaParticipantes);
	scrollPaneParticipantes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
	scrollPaneParticipantes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	scrollPaneParticipantes.setPreferredSize(new Dimension(400,200));
	scrollPaneParticipantes.getVerticalScrollBar().setBackground(Color.decode("#5693f5"));
	scrollPaneParticipantes.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.decode("#2148bc");
		        this.thumbDarkShadowColor = (Color.decode("#0f2e8a"));
		    }
		});
	construirTablaParticipantes(setearColumnasParticipantes(),obtenerArrayDatosParticipantes());
	
	//Listeners
	tablaParticipantes.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	 	public void valueChanged(ListSelectionEvent event) {
	 		buttonModificarP.setEnabled(true);
	 		buttonEliminarP.setEnabled(true);
	 }
	});
	
	ActionListener EliminarParticipanteListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//ParticipanteDAO.delete(SELECCIONADO);
		}
	};
	ActionListener AgregarParticipanteListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
			ventana.setContentPane(new PanelAltaParticipante(dtoComp));
			ventana.setSize(tamAltaPartic);
			ventana.setLocationRelativeTo(null);
			ventana.revalidate();
			ventana.repaint();
		}
	};
	buttonAgregarP.addActionListener(AgregarParticipanteListener);
	ActionListener cancelarListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
			ventana.setContentPane(new PanelVerCompetencia(GestorCompetencia.buscarCompetencia(dtoComp.getIdCompetencia())));
			ventana.setSize(tamVerComp);
			ventana.setLocationRelativeTo(null);
			ventana.revalidate();
			ventana.repaint();
			}
		};
	buttonCancelar.addActionListener(cancelarListener);
	}
	
	private void armarPanel() {
	SpringLayout sLayout = new SpringLayout();
	this.setLayout(sLayout);
	add(labelPartic);
	sLayout.putConstraint(SpringLayout.WEST,labelPartic,45,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,labelPartic,50,SpringLayout.NORTH,this);
	add(scrollPaneParticipantes);
	sLayout.putConstraint(SpringLayout.WEST,scrollPaneParticipantes,50,SpringLayout.WEST,this);
	sLayout.putConstraint(SpringLayout.NORTH,scrollPaneParticipantes,100,SpringLayout.NORTH,this);
	add(buttonAgregarP);
	sLayout.putConstraint(SpringLayout.WEST,buttonAgregarP,100,SpringLayout.WEST,scrollPaneParticipantes);
	sLayout.putConstraint(SpringLayout.NORTH,buttonAgregarP,20,SpringLayout.SOUTH,scrollPaneParticipantes);
	add(buttonModificarP);
	sLayout.putConstraint(SpringLayout.WEST,buttonModificarP,0,SpringLayout.WEST,buttonAgregarP);
	sLayout.putConstraint(SpringLayout.NORTH,buttonModificarP,20,SpringLayout.SOUTH,buttonAgregarP);
	add(buttonEliminarP);
	sLayout.putConstraint(SpringLayout.WEST,buttonEliminarP,0,SpringLayout.WEST,buttonAgregarP);
	sLayout.putConstraint(SpringLayout.NORTH,buttonEliminarP,20,SpringLayout.SOUTH,buttonModificarP);
	add(buttonCancelar);
	sLayout.putConstraint(SpringLayout.WEST,buttonCancelar,0,SpringLayout.WEST,buttonAgregarP);
	sLayout.putConstraint(SpringLayout.NORTH,buttonCancelar,20,SpringLayout.SOUTH,buttonEliminarP);
	}
	
	private void construirTablaParticipantes(String[] columna,Object[][] datosParticipantes) {
		 ModeloTablaParticipantes model = new ModeloTablaParticipantes(datosParticipantes,columna);
		 tablaParticipantes.setModel(model);
		 tablaParticipantes.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldasGen("texto"));
		 tablaParticipantes.getTableHeader().setDefaultRenderer(new GenericoTableHeaderRenderer());

		 tablaParticipantes.getTableHeader().setReorderingAllowed(false);
		 tablaParticipantes.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		 tablaParticipantes.setRowHeight(35);
		 tablaParticipantes.setGridColor(Color.BLACK);
		 
		 tablaParticipantes.getColumnModel().getColumn(0).setPreferredWidth(100);
	}
	private Object[][] obtenerArrayDatosParticipantes() {
		String[][] nombresParticipantes = new String[dtoComp.getParticipantes().length][2];
		nombresParticipantes = dtoComp.getParticipantes();
		return nombresParticipantes;
	}
	private String[] setearColumnasParticipantes() {
		String[] Columna = new String[1]; 
		Columna[0] = "Nombre Participante";
		return Columna;
	}
}
