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

import acceso.ParticipanteDAO;
import logica.CompetenciaDTO;
import logica.GestorCompetencia;

public class PanelListarParticipantes extends JPanel{
	JLabel labelPartic;
	JButton buttonAgregarP;
	JButton buttonModificarP;
	JButton buttonEliminarP;
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
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.decode("#2148bc");
        Color color2 = Color.decode("#10104a");
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
	private void inicializarComponentes() {
	//Labels
	nombreC=dtoComp.getNombre();
	labelPartic = new JLabel("<HTML>Participantes de Competencia: <B>"+nombreC+"</B> </HTML>",SwingConstants.CENTER);
	labelPartic.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
	//Buttons
	buttonAgregarP = new JButton("Agregar Nuevo Participante");
	buttonModificarP = new JButton("Modificar Participante");
	buttonEliminarP = new JButton("Eliminar Participante");
	buttonAgregarP.setPreferredSize(new Dimension(200,30));
	buttonModificarP.setPreferredSize(new Dimension(200,30));
	buttonEliminarP.setPreferredSize(new Dimension(200,30));
	//Color Buttons
	buttonModificarP.setEnabled(false);
	buttonEliminarP.setEnabled(false);
	buttonAgregarP.setBackground(Color.decode("#112349"));
	buttonAgregarP.setForeground(Color.white);
	buttonModificarP.setBackground(Color.decode("#112349"));
	buttonModificarP.setForeground(Color.white);
	buttonEliminarP.setBackground(Color.decode("#112349"));
	buttonEliminarP.setForeground(Color.white);
	
	//Tablas
	tablaParticipantes = new JTable();
	tablaParticipantes.setPreferredSize(new Dimension(400,200));
	scrollPaneParticipantes = new JScrollPane(tablaParticipantes);
	scrollPaneParticipantes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
	scrollPaneParticipantes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	scrollPaneParticipantes.setPreferredSize(new Dimension(400,200));
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
			//ParticipanteDAO.delete(SELECCIONADO);
			JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
			ventana.setContentPane(new PanelAltaParticipante(dtoComp));
			ventana.setSize(new Dimension(400,350));
			ventana.setLocationRelativeTo(null);
			ventana.revalidate();
			ventana.repaint();
		}
	};
	buttonAgregarP.addActionListener(AgregarParticipanteListener);
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
	//Colores
	this.setBackground(Color.decode("#21489c"));
	}
	
	private void construirTablaParticipantes(String[] columna,Object[][] datosParticipantes) {
		 ModeloTablaParticipantes model = new ModeloTablaParticipantes(datosParticipantes,columna);
		 tablaParticipantes.setModel(model);
		 tablaParticipantes.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldasGen("texto"));
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
